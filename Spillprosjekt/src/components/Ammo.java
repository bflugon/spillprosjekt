package components;

import java.awt.Graphics;
import java.awt.Rectangle;

import backend.Tilesets;

public class Ammo extends TowerComponent {
	
	protected String ammoType; 

	public Ammo(String name, int price ,int textureIndex, double damage, double range, double firerate) {
		super(damage, range, firerate, price,name,textureIndex);
		this.type = "ammo";
		
		this.ammoType = ammoType;
		this.textureIndex = textureIndex;
		this.id = 3;
		this.texture = Tilesets.block_tileset[id];
		
	}
	
	public void drawButton(Graphics g, Rectangle rect){
		g.drawImage(texture, rect.x, rect.y+10, 60, 60, null);
	}
	
	public String getAmmoType(){
		return ammoType;
	}
	

}
