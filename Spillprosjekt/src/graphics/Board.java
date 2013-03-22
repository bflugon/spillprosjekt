package graphics;

import java.awt.Graphics;
import java.lang.annotation.Target;
import java.util.ArrayList;

import backend.GameData;
import backend.Pathfinder;

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
	}
	
	public void placeTower(){
//		Sjekker om musen er over en blokk
		for(Block[] row: grid){
			for(Block block: row){
				if(block.contains(Screen.CURSOR)){
					if(block.getBlockID() == GameData.foundation && block.isOpen()) towers.add(new Tower(block));	
				}
			}
		}
	}
	public void addFoundation() {
//		Sjekker om musen er over en blokk
		for(Block[] row: grid){
			for(Block block: row){
				if(block.contains(Screen.CURSOR)){
					if(block.getBlockID() == GameData.grass) block.setID(GameData.foundation);
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
	
	public void physics() {
		
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
	}


}
