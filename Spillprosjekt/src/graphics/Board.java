package graphics;

import java.awt.Graphics;
import java.lang.annotation.Target;
import java.util.ArrayList;

import backend.GameData;
import backend.Pathfinder;
import backend.Tilesets;

public class Board {
	
	private final int 	worldHeight = 9,
						worldWidth = 13,
						blockSize = 60;
	
//	Finner korsteste vei for fiendene og hindrer deg fra aa blokke dem
	private Pathfinder pathfinder;
	
//	Brettet vart
	private Block[][] grid;
//	Start og slutt for fiendene
	private Block 	start,
					goal;
	
//	Liste med alle taarnene
	private ArrayList<Tower> towers;
	
//	Array med alle fiender
	private Enemy[] enemies;
	
	public Board(){	
//		Lager brettet
		grid = new Block[worldHeight][worldWidth];
		
//		Legger til blokker i arrayet
		for(int y = 0; y < grid.length; y++){
			for(int x = 0; x < grid[0].length; x++){
				grid[y][x] = new Block(20+blockSize*x, 20+blockSize*y, x, y, blockSize);
			}
		}
		
//		Bor hentes fra kartet senere
		start = grid[0][0];
		goal = grid[8][12];
		
//		Finner korsteste vei on request
		pathfinder = new Pathfinder(this);
		pathfinder.findPath();
		
//		Lager listen som inneholder taarnene
		towers = new ArrayList<Tower>();
		
//		Fyller arrayet med fiender
		enemies = new Enemy[60];
		for(int i = 0; i<enemies.length; i++){
			enemies[i] = new Enemy(this);
		}
	}
	
	public void placeTower(){
//		Sjekker om musen er over en blokk
		for(Block[] row: grid){
			for(Block block: row){
				if(block.contains(Screen.CURSOR)){
					if(block.getBlockID() == GameData.foundation && block.isOpen()) towers.add(new Tower(block, this));	
				}
			}
		}
	}
	public void addFoundation() {
//		Sjekker om musen er over en blokk
		for(Block[] row: grid){
			for(Block block: row){
				if(block.contains(Screen.CURSOR)){
					if(block.getBlockID() == GameData.grass){
						block.setBlockID(GameData.foundation);
						if(!pathfinder.findPath()) {
							block.setBlockID(GameData.grass);
							pathfinder.findPath();
						}
					}
				}
			}
		}
	}
	
	public Block[][] getGrid(){
		return grid;
	}
	public Block getStart(){
		return start;
	}
	public Block getGoal(){
		return goal;
	}
	
	public int getBlockSize() {
		return blockSize;
	}
	
	private int enemiesSpawned = 0, spawnFrame = 0, spawnRate = 400;
	private void spawnTimer() {
		
		if(spawnFrame >= spawnRate) {
			for(int i = 0; i < enemies.length;i++) {
				if(!enemies[i].inGame()) {
					enemies[i].spawnEnemy(10, 4, start);
					enemiesSpawned++;
					break;
				}
			}
			spawnFrame = 0;
		} else {
			spawnFrame ++;
		}
	}
	
	public Enemy[] getEnemies(){
		return enemies;
	}
	
	public void physics() {
		spawnTimer();
		
		for(Enemy enemy: enemies){
			if(enemy.inGame()) enemy.physics();
		}
		
		for(Tower tower: towers){
			tower.physics();
		}
	}
	public void draw(Graphics g) {
//		Tegner blokkene
		for(Block[] row: grid){
			for(Block block: row){
				block.draw(g);
			}
		}
		
//		Tegner taarnene
		for(Tower tower: towers){
			tower.draw(g);
		}
		
//		Draw enemies
		for(Enemy enemy: enemies){
			if(enemy.inGame()) enemy.draw(g);
		}
	}
}
