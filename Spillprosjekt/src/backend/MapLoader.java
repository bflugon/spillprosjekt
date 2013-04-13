package backend;

import graphics.Block;
import graphics.Board;

import java.io.File;
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
						grid[y][x].setBlockID(id);
						if (id == GameData.start) {
							board.setStart(grid[y][x]);
						}
						else if (id == GameData.goal) {
							board.setGoal(grid[y][x]);
						}
					}
				}
			
				loadScanner.close();
			}
		} catch(Exception e) {}
	}
}