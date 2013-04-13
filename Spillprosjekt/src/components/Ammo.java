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
	private double ammoX,ammoY;
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
	
	public void drawButton(Graphics g, Rectangle rect){
		g.drawImage(texture, rect.x, rect.y+10, 60, 60, null);
	}
	
	public String getAmmoType(){
		return ammoType;
	}
	
	
	// N똱 et t똱n skyter, lager den et nytt ammo-objekt
	//N똱 ammo-objektet blir laget, kj퓊es rotateAmmo. Da regnes det ut hvilken vinkel ammoen skal f퓄ge
	
	//Hver gang t똱net kj퓊er draw metoden sin, kj퓊er den drawProjectile metoden her i ammoklassen.
	//
	
	
	
	
	
	public void rotateAmmo(Tower tower){
		
		
		double barrelX = tower.getCenterX();
		double barrelY = tower.getCenterY();

		Enemy target = tower.getTarget();
		
//		Hvis det finnes et maal og det er innenfor rekkevidden
		if(target != null){
			double distX = target.getX()-tower.getX();
			double distY = target.getY()-tower.getY();
			if(Math.sqrt(distY*distY+distX*distX) <= tower.getRange()){
//				Pytttthugaros
				rotation = Math.atan(((barrelY-target.getY()) / (barrelX-target.getX()) ));
//				Legg til en pi for aa rotere i 2. og 3. kvadrant hvis fienden er til venste for taarnet
				if(target.getX() <= barrelX) rotation += Math.PI;
			}
		}
		
		
		ammoX = tower.getCenterX();
		ammoY = tower.getCenterY();
		
	}
	
	public void drawProjectile(Graphics2D g2d, Tower tower){
	    if(ammoTimeOut < 800){
	    	
	    	AffineTransform trans = new AffineTransform();
	    	trans.rotate(rotation,ammoX,ammoY);
	    	trans.translate(0,0);
	    	g2d.setTransform(trans);
	    	
	    	
	    	
	    	g2d.drawImage(texture, (int)(ammoX), (int)(ammoY), (int)20, 20, null);
	    	ammoX += Math.cos(rotation) * 5;
	    	ammoY += Math.sin(rotation) * 5;
	    	ammoTimeOut ++;
	    }

	    
	}
	

	

}
