package components;

import graphics.Enemy;
import graphics.Tower;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import backend.Tilesets;




public class Ammo extends TowerComponent {
	
	protected double rotation = 0;
	protected int ammoTimeOut;
	private double ammoX,ammoY,speedX, speedY;
	protected Enemy enemy;
	
	protected String ammoType; 

	public Ammo(String name, int price ,int textureIndex, double damage, double range, double firerate) {
		super(damage, range, firerate, price,name,textureIndex);
		this.type = "ammo";
		
		this.ammoType = ammoType;
		this.textureIndex = textureIndex;
		this.id = 3;
		this.texture = Tilesets.ammo_tileset[textureIndex];
		
	}
	
	public Ammo(int x, int y, int textureIndex, double rotation){
		super(0, 0, 0, 0, "", textureIndex);
		this.texture = Tilesets.ammo_tileset[textureIndex];
		
		ammoX = x;
		ammoY = y;
		
		rotateAmmo(rotation);
	}
	
	public void drawButton(Graphics g, Rectangle rect){
		g.drawImage(texture, rect.x, rect.y+10, 60, 60, null);
	}
	
	public String getAmmoType(){
		return ammoType;
	}
	
	
	// N�r et t�rn skyter, lager den et nytt ammo-objekt
	//N�r ammo-objektet blir laget, kj�res rotateAmmo. Da regnes det ut hvilken vinkel ammoen skal f�lge
//	Vi har rotasjonen fra taarnet
	
	//Hver gang t�rnet kj�rer draw metoden sin, kj�rer den drawProjectile metoden her i ammoklassen.
	//
	
	
	
	
	
	public void rotateAmmo(double rotation){
		speedX = Math.cos(rotation) * 5;
		speedY = Math.sin(rotation) * 5;
		
	}
	
	public void drawProjectile(Graphics2D g2d, Tower tower){
	    if(ammoTimeOut < 800){
	    	
	    	AffineTransform trans = new AffineTransform();
	    	trans.rotate(rotation,ammoX,ammoY);
	    	trans.translate(0,0);
	    	g2d.setTransform(trans);
	    	
	    	g2d.drawImage(texture, (int)(ammoX), (int)(ammoY), (int)20, 20, null);
	    
	    	ammoX += speedX;
	    	ammoY += speedY;
	    	ammoTimeOut ++;
	    }

	    
	}
	

	

}
