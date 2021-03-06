package components;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import backend.GameData;
import backend.Tilesets;

public class Base extends TowerComponent{
	
	//protected Image texture;
	
	public Base(String name, int price ,int textureIndex, double damage, double range, double firerate, int rankLimit) {
		super(damage, range, firerate, price,name,textureIndex);
		this.type = "base";
		this.id = GameData.basicBase;
		this.texture = Tilesets.base_tileset[textureIndex];
		this.rankLimit = rankLimit;
	}

	public void drawButton(Graphics g, Rectangle rect){
		g.drawImage(texture, rect.x, rect.y+10, 60, 60, null);
	}
	
	public void drawLargeImage(Graphics g, Rectangle rect){
		g.drawImage(texture, rect.x, rect.y, rect.width, rect.height, null);
	}
	
	public void draw(Graphics g, Rectangle rect){
		g.drawImage(texture, rect.x, rect.y, 60, 60, null);
	}
	
}
