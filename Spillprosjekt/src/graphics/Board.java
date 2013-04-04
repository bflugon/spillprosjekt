package graphics;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.lang.annotation.Target;
import java.util.ArrayList;

import backend.Colors;
import backend.GameData;
import backend.Pathfinder;
import backend.Tilesets;

public class Board {
	
	private int activeTower,
				enemyLives;
	private final int 	worldHeight = 9,
						worldWidth = 13,
						blockSize = 60;
	
	
	private Pathfinder pathfinder;

	
	private Block[][] grid;
	private Block 	start,
					goal;
	
	private Rectangle nextWave;
	private Rectangle goToShop;
	
	private ArrayList<Rectangle> towerButtons;
	private ArrayList<Tower> towers;
	private Enemy[] enemies;
	
	public Board(){
		createBoard();
		
//		Finner korsteste vei on request
		pathfinder = new Pathfinder(this);
		pathfinder.findPath();
		
//		Lager listen som inneholder taarnene
		towers = new ArrayList<Tower>();
		
		nextWave = new Rectangle(720,570,80,80);
		goToShop = new Rectangle(630,570,80,80);
		
		activeTower = 0;
		enemyLives = 3;
//		Fyller arrayet med fiender
		enemies = new Enemy[80];
		for(int i = 0; i<enemies.length; i++){
			enemies[i] = new Enemy(this);
		}
		
		towerButtons = new ArrayList<Rectangle>();
//		Lager like mange knapper som taarn man kan plassere
		for(int i = 0; i < GameData.modelTowers.size();i++){
			towerButtons.add(new Rectangle(20+i*90,570, 80, 80));
		}
	}
	
	private void createBoard(){
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
	}
	
	public void placeTower(){
//		Sjekker om musen er over en blokk
		for(Block[] row: grid){
			for(Block block: row){
				if(block.contains(Screen.CURSOR)){
					if(block.getBlockID() == GameData.foundation && block.isOpen()) {
						Tower newTower = new Tower(block, this);
						GameData.modelTowers.get(activeTower).copyTower(newTower);
						newTower.updateProperties();
						towers.add(newTower);	
						block.setOpen(false);
					}
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
	
	private int enemiesSpawned = 20, spawnFrame = 0, spawnRate = 400;
	private void spawnTimer() {
		if(enemiesSpawned == 20) return;
		if(spawnFrame >= spawnRate) {
			for(int i = 0; i < enemies.length;i++) {
				if(!enemies[i].inGame()) {
					enemies[i].spawnEnemy(enemyLives, 4, start);
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
		
//		Tegner knappene
		for(int i = 0; i < towerButtons.size(); i++){
			g.setColor(Colors.range);
			g.fillRect(towerButtons.get(i).x, towerButtons.get(i).y, towerButtons.get(i).width, towerButtons.get(i).height);
			GameData.modelTowers.get(i).drawButton(g, towerButtons.get(i));
		}
		
//		Draw enemies
		for(Enemy enemy: enemies){
			if(enemy.inGame()) enemy.draw(g);
		}

//		Tegner taarnene og rekkevidden hvis musen er over et av dem
		Tower mouseOver = null;
		for(Tower tower: towers){
			tower.draw(g);
			if(tower.contains(Screen.CURSOR)) mouseOver = tower;
		}
		if(mouseOver!=null)mouseOver.drawRange(g);
		
//		Tegn knapper for meny og neste bolge
		g.setColor(Colors.range);
		g.fillRect(nextWave.x, nextWave.y, nextWave.width, nextWave.height);
		g.drawImage(Tilesets.button_tileset[GameData.nextWave], nextWave.x, nextWave.y, nextWave.width, nextWave.height, null);
		g.fillRect(goToShop.x, goToShop.y, goToShop.width, goToShop.height);
		g.drawImage(Tilesets.button_tileset[GameData.goToShop], goToShop.x, goToShop.y, goToShop.width, goToShop.height, null);
		
	}

	public void nextWave(){
		if(nextWave.contains(Screen.CURSOR) && enemiesSpawned == 20){
			enemyLives++;
			enemiesSpawned = 0;
		}
	}
	
	public void changeActiveTower() {
		for(int i = 0; i < towerButtons.size(); i++){
			if(towerButtons.get(i).contains(Screen.CURSOR)){
				activeTower = i;
			}
		}
	}

	public void updateButtons(){
		towerButtons = new ArrayList<Rectangle>();
//		Lager like mange knapper som taarn man kan plassere
		for(int i = 0; i < GameData.modelTowers.size();i++){
			towerButtons.add(new Rectangle(20+i*90,570, 80, 80));
		}
	}
	
	public boolean menuClicked() {
		return goToShop.contains(Screen.CURSOR);
	}
}
