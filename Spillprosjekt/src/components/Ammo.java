package components;

import java.awt.Graphics;
import java.awt.Rectangle;

import backend.Tilesets;

public class Ammo extends TowerComponent {
	
	protected String ammoType; 

	public Ammo(double damage, boolean splashDamage, boolean slow) {
		super(damage, 0, 0, splashDamage, slow, false);
		this.type = "ammo";
		
		this.ammoType = "Bullet";
		
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
