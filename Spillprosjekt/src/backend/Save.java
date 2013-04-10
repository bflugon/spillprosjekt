package backend;

import graphics.Block;
import graphics.Screen;

import java.io.File;
import java.util.Scanner;

public class Save {
	public void loadSave(File loadPath, Screen screen) {
		try {
			Scanner loadScanner = new Scanner(loadPath);
			Block[][] grid = screen.getBoard().getGrid();
			while (loadScanner.hasNext()) {
				for (int y = 0; y < grid.length; y++) {
					for (int x = 0; x < grid[0].length; x++) {
						int id = loadScanner.nextInt();
						grid[y][x].setBlockID(id);
					}
				}
			
				loadScanner.close();
			}
		} catch(Exception e) {}
	}
}