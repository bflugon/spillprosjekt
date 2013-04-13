package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import backend.Colors;
import backend.GameData;

public class Credits extends Rectangle {
	
	public Credits(Rectangle bounds){
		setBounds(bounds);
	}
	
	public void draw(Graphics g){
		g.setColor(Colors.fadeBlack);
		g.fillRect(x, y, width, height);
		
		g.setColor(Colors.creditsBackground);
		g.fillRect(x+100, y+100, width-200, height-200);
		
		g.setColor(Color.WHITE);
		g.setFont(GameData.largeHeader);
		g.drawString("Why so Serious?", 180, 300);
	}

}
