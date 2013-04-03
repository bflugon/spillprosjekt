package components;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import backend.Tilesets;

public class Base extends TowerComponent{
	
	protected Image texture;
	
	public Base(int damage,boolean radar) {
		super(damage,0,0,false,false,radar);
	}

	public void drawButton(Graphics g, Rectangle rect){
		g.drawImage(texture, rect.x, rect.y+10, 60, 60, null);
	}
	public void draw(Graphics g, Rectangle rect){
		drawButton(g, rect);
	}
	
}
