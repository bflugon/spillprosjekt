package components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import backend.GameData;
import backend.Tilesets;

public class Barrel extends TowerComponent{

	//protected Image barrelTexture;
	protected String allowed_ammo;
	protected String soundName;
	
	public Barrel(String name, int price ,int textureIndex, double damage, double range, double firerate, String allowed_ammo, String soundName, int rankLimit) {
		super(damage, range, firerate, price,name,textureIndex);
		this.name = name;
		this.price = price;
		this.type = "barrel";
		this.id = GameData.basicBarrel;
		this.texture = Tilesets.barrel_tileset[textureIndex];
		this.allowed_ammo = allowed_ammo;
		this.soundName = soundName;
		this.rankLimit = rankLimit;
	}
	
	public String getAmmoType(){
		return allowed_ammo;
	}
	
	public String getSoundName(){
		return soundName;
	}
	
	public void draw(Graphics2D g2d, int tx, int ty, double rotation){
		AffineTransform trans = new AffineTransform();
		
		double barrelX = tx+30;
		double barrelY = ty+30;
		

//		Roter lop rundt midten av taarnet
	    trans.rotate(rotation,barrelX,barrelY);
//	    Flytt barrel over rotasjonspunktet
	    trans.translate(15,0);
	    
//	    Oppdater grafikkobjektet med den nye transformasjonen
	    g2d.setTransform(trans);
	    
//	    Tegn barrel
	    g2d.drawImage(texture, tx, ty, 60, 60, null);
	}
	public void drawButton(Graphics g, Rectangle rect){
		g.drawImage(Tilesets.menu_barrel_tileset[textureIndex], (int)rect.getX()+10, (int)rect.getY()+5, 60, 60, null);
	}
	
	public void drawLargeImage(Graphics g, Rectangle rect){
		g.drawImage(Tilesets.menu_barrel_tileset[textureIndex], (int)rect.getX()+50, (int)rect.getY() - 30, rect.width, rect.height, null);
	}
}
