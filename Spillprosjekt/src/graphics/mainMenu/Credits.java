package graphics.mainMenu;

import graphics.PopupWindow;

import java.awt.Color;
import java.awt.Graphics;

import backend.GameData;

public class Credits extends PopupWindow {
	public void draw(Graphics g){
		super.draw(g);
		
		g.setColor(Color.WHITE);
		g.setFont(GameData.largeHeader);
		g.drawString("Why so Serious?", 180, 300);
	}
}
