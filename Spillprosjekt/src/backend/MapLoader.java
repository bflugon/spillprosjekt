package backend;

import graphics.Block;
import graphics.Board;
import graphics.Tower;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MapLoader {
	public void loadSave(File loadPath, Board board) {
		try {
			Scanner loadScanner = new Scanner(loadPath);
			Block[][] grid = board.getGrid();
			while (loadScanner.hasNext()) {
				for (int y = 0; y < grid.length; y++) {
					for (int x = 0; x < grid[0].length; x++) {
						int id = loadScanner.nextInt();
						if (id > 99) {
							String towerID = String.valueOf(id);
							Tower newTower = new Tower(grid[y][x], board);
							newTower.setBarrel(GameData.barrels.get(Character.getNumericValue(towerID.charAt(0))));
							newTower.setAmmo(GameData.ammo.get(Character.getNumericValue(towerID.charAt(1))));
							newTower.setBase(GameData.bases.get(Character.getNumericValue(towerID.charAt(2))));
							grid[y][x].setBlockID(GameData.foundation);
						}
						else {
							grid[y][x].setBlockID(id);
						}
						if (id == GameData.start) {
							board.setStart(grid[y][x]);
						}
						else if (id == GameData.goal) {
							board.setGoal(grid[y][x]);
						}
					}
				}
				int id = loadScanner.nextInt();
				board.setMoney(id);
			
				loadScanner.close();
			}
		} catch(Exception e) {}
	}
	
	public void save(int mapNum, Board board) {
		File file = new File("resources/maps/"+mapNum+".map");
		Block[][] grid = board.getGrid();
		file.delete();
	    try {
	        BufferedWriter out = new BufferedWriter(new FileWriter(file));
	        for (int i = 0; i < grid.length; i++) {
	        	if (i != 0) {
	        		out.write("\n");
	        	}
	        	for (int j = 0; j < grid[i].length; j++) {	
	        		if (grid[i][j].getBlockID() < 10) {
	        		out.write(" " + grid[i][j].getBlockID() + "  ");
	        		}
	        		else {
	        			out.write(grid[i][j].getBlockID() + " ");
	        		}
	        	}
	        }
	        out.write("\n");
	        out.write(board.getMoney());
	        out.close();
	    } catch (IOException e) {
	    }
	}
}