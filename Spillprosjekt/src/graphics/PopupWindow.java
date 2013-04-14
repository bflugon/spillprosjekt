package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import backend.Colors;
import backend.GameData;

public class PopupWindow extends Rectangle {
	
	public PopupWindow(){
		setBounds(70,50,680,560);
	}
	
	public void draw(Graphics g){
		g.setColor(Colors.popupTransparentBlack);
		g.fillRect(0,0,820,680);
		
		g.setColor(Colors.popupBackground);
		g.fillRect(x, y, width, height);
	}

}
