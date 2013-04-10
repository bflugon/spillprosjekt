package backend;

import graphics.Block;
import graphics.Board;

import java.util.HashSet;
import java.util.Set;

public class Pathfinder {

	/*******************
	 *** Dijkstraish ***
	 *******************/
	
	Block[][] grid;
	Board board;
	
	public Pathfinder(Board board){
		this.grid = board.getGrid();
		this.board = board;
	}
	
//	Finner ruten fra slutt til start for aa enklere kunne bevege fiendene vha "prev"-feltene
	public boolean findPath() {
//		Alle noder som kan naas, men ikke er undersokt
		Set<Block> openSet = new HashSet<Block>();
//		Alle noder man har funnet korteste vei til
		Set<Block> closedSet = new HashSet<Block>();
		
//		Finnes det en vei gjennom kartet?
		boolean ok = false;

//		Nullstill alle blokkene
		for(int y = 0; y<grid.length; y++) {
			for(int x = 0; x<grid[0].length;x++) {
				Block block = grid[y][x];
				block.setF(9999);
				block.setPrev(null);
			}
		}
		
//		Stifinningen begynner fra siste blokk
		Block current = board.getGoal();
//		Koster 0 aa naa blokken man begynner paa
		current.setF(0);
//		Setter maalet til stien
		Block goal = board.getStart();
		
//		Legg til alle noder den kan naa fra current
		addToOpen(openSet, current);
		
//		Saa lenge det finnes noder den kan naa, men ikke har undersokt...
		while (openSet.size() != 0){
			
//			Finn blokken med lavest kostnad
			current = openSet.iterator().next();
			for (Block block: openSet) {
				if(block == goal) {
					ok =true;
				}
				if(current.getF() > block.getF()) {
					current = block;
				}
			}
			
//			Fjaern den fra openSet
			openSet.remove(current);
//			Den er undersokt og kan naa stenges
			closedSet.add(current);
//			Legg til alle noder den kan naa fra current
			addToOpen(openSet, current);
		}
		
//		Hvis det ikke finnes en vei...
		if(!ok) {
			return false;
		}		
//		Hvis det finnes en vei
		return true;
	}
	
	
//	Legger til noder som kan naas(?) fra nodene som er undersokt 
	private void addToOpen(Set<Block> openSet, Block current) {
		for(int y = 0; y < grid.length; y++) {
			for (int x = 0; x < grid[0].length; x++) {
				Block block = grid[y][x];
				
//				Distanse i x- og y-retning
				int distX = Math.abs(block.getIndX() - current.getIndX());
				int distY = Math.abs(block.getIndY() - current.getIndY());
				
//				Hvis blokken grenser til current
				if(distX <= 1 && distY <= 1 && block != current) {
//					Hvis blokken ikke er diagonal eller en blokk som sperrer
					if( (distX != 1 || distY != 1)  && (block.getBlockID() == 0 || block.getBlockID() == 3)) {
//						Hvis kostnaden for aa naa blokken er lavere enn den var fra for...
						if (block.getG() + current.getF() < block.getF()) {
//							Oppdater kostnaden, legg til i openSet og sett current som prev(previous)
							block.setF(current.getF() + block.getG());
							block.setPrev(current);
							openSet.add(block);
						}
					}
				}
			}
		}
	}
}
