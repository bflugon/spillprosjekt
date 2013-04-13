package graphics;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.util.Scanner;

import backend.Colors;

public class BoardButton extends Rectangle {

	private Block[][] grid;

	public BoardButton(int x, int y, int width, int heigth, File file) {
		setBounds(x,y,width,heigth);
		
		grid = new Block[9][13];
		int blockSize = 8;
		for (int yp = 0; yp < grid.length; yp++) {
			for (int xp = 0; xp < grid[0].length; xp++) {
				grid[yp][xp] = new Block(8+x + blockSize * xp, 23+y + blockSize * yp, xp, yp, blockSize);
			}
		}

		try {
			Scanner loadScanner = new Scanner(file);
			while (loadScanner.hasNext()) {
				for (int yc = 0; yc < grid.length; yc++) {
					for (int xc = 0; xc < grid[0].length; xc++) {
						int id = loadScanner.nextInt();
						grid[yc][xc].setBlockID(id);
					}
				}

				loadScanner.close();
			}
		} catch (Exception e) {e.getStackTrace();}
	}
	
	public void draw(Graphics g, boolean selected){
		
		if(selected)g.setColor(Colors.pink);
		else g.setColor(Colors.transparentBlack);
		
		g.fillRect(x, y, width, height);

		for(Block[] row: grid){
			for(Block block : row){
				block.draw(g);
			}
		}
	}
}
