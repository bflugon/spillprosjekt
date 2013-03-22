package graphics;

import java.awt.Graphics;
import java.util.ArrayList;

public class Board {
	
	private final int 	worldHeight = 9,
						worldWidth = 13,
						blockSize = 60;
	
	private Block[][] board;
	private ArrayList<Tower> towers;
	
	public Board(){
		
		//Lager brettet
		board = new Block[worldHeight][worldWidth];
		
		for(int y = 0; y < board.length; y++){
			for(int x = 0; x < board[0].length; x++){
				board[y][x] = new Block(20+blockSize*x, 20+blockSize*y, x, y, blockSize);
			}
		}
		
		//Lager listen som inneholder taarnene
		towers = new ArrayList<Tower>();
	}
	
	public void placeTower(){
//		Sjekker om musen er over en blokk
		for(Block[] row: board){
			for(Block block: row){
				if(block.contains(Screen.CURSOR)){
					towers.add(new Tower(block));	
				}
			}
		}
	}
	
	public void physics() {
		
	}

	public void draw(Graphics g) {
//		Tegner blokkene
		for(Block[] row: board){
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
