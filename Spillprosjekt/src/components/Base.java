package components;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import backend.GameData;
import backend.Tilesets;

public class Base extends TowerComponent{
	
	//protected Image texture;
	
	public Base(int damage,boolean radar) {
		super(damage,0,0,false,false,radar);
		this.type = "base";
		this.id = GameData.basicBase;
		this.texture = Tilesets.base_tileset[id];
	}

	public void drawButton(Graphics g, Rectangle rect){
		g.drawImage(texture, rect.x, rect.y+10, 60, 60, null);
	}
	
	public void drawLargeImage(Graphics g, Rectangle rect){
		g.drawImage(texture, rect.x, rect.y+10, 120, 120, null);
	}
	
	public void draw(Graphics g, Rectangle rect){
		drawButton(g, rect);
	}
	
}
