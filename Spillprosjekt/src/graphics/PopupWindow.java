package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import backend.Colors;
import backend.GameData;

public class PopupWindow extends Rectangle {
	
	public PopupWindow(){
		setBounds(0,0,820,680);
	}
	
	public void draw(Graphics g){
		g.setColor(Colors.popupTransparentBlack);
		g.fillRect(x, y, width, height);
		
		g.setColor(Colors.popupBackground);
		g.fillRect(x+70, y+50, 680, height-120);
	}

}
