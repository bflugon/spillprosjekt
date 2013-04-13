package components;

import graphics.Enemy;
import graphics.Tower;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import backend.GameData;
import backend.Tilesets;

public class Barrel extends TowerComponent{

	//protected Image barrelTexture;
	protected double rotation = 0;
	
								
	protected String[] allowed_ammo = {"Missiles", "Tacks", "Bullets"};
	
	
<<<<<<< HEAD
	protected Point center;
	
	public Barrel(String name, int price ,int textureIndex, double damage, double range, double firerate) {
		super(damage, range, firerate, price,name,textureIndex);
=======
	public Barrel(String name, int price ,double damage, double range, double firerate) {
		super(damage, range, firerate, false,false,false);
		this.name = name;
		this.price = price;
>>>>>>> LES BESKRIVELSE!! Fikset rotasjon og lagde en PopupWindow-klasse
		this.type = "barrel";
		this.id = GameData.basicBarrel;
		this.texture = Tilesets.barrel_tileset[textureIndex];
	}
	
	public String[] getAmmoTypes(){
		return allowed_ammo;
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
		g.drawImage(texture, (int)rect.getX()+15, (int)rect.getY()+10, 60, 60, null);
	}
	
	public void drawLargeImage(Graphics g, Rectangle rect){
		g.drawImage(texture, (int)rect.getX()+30, (int)rect.getY()+10, 120, 120, null);
	}
	
	public void drawShot(Graphics2D g2d, Tower tower){
		g2d.setColor(Color.ORANGE);
	    g2d.fillRect((int)tower.getX()+60, (int) (tower.getY()+30-3), 10, 6);
	}

	public double getRotation() {
		return rotation;
	}

}
