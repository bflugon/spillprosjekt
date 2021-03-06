package graphics.mainMenu;

import graphics.Block;
import graphics.Screen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.util.Scanner;

import backend.Colors;
import backend.GameData;

public class BoardButton extends Rectangle {

	private Block[][] grid;
	private int index;
	public BoardButton(int index, int x, int y, int width, int heigth, File file) {
		setBounds(x,y,width,heigth);
		this.index = index;

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
	
	public void draw(Graphics g){
		
		if(contains(Screen.CURSOR)) g.setColor(Colors.transparentRed);
		else g.setColor(Colors.transparentBlack);
		
		g.fillRect(x, y, width, height);

		for(Block[] row: grid){
			for(Block block : row){
				block.draw(g);
			}
		}
		
		if(GameData.rank < index/2){
			g.setColor(Colors.popupTransparentBlack);
			g.fillRect(x, y, width, height);
			g.setColor(Color.GRAY);
			g.setFont(GameData.small);
			g.drawString("Rank "+(int)index/2+" needed", x+10, y+65);
		}
	}
}
