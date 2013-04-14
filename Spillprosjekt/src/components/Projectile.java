package components;

import graphics.Enemy;
import graphics.Tower;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import backend.Tilesets;

public class Projectile {
	protected Image texture;
	
	protected int ammoTimeOut;
	
	protected double rotation,x,y,speed,damage;
	
	protected Enemy target;
	
	
	
	public Projectile(double x, double y, int textureIndex, double rotation, Enemy target, double damage){
		this.texture = Tilesets.ammo_tileset[textureIndex];
		
		this.x = x+30;
		this.y = y+30;
		this.rotation = rotation;
		//rotate();
		
		this.damage = damage;
		this.speed = 1;
		this.target = target;
		
		x += Math.cos(rotation) *8;
    	y += Math.sin(rotation) *8;
	}
	
	private void rotate(){
		if(target == null)return;
		double barrelX = x;
		double barrelY = y;
		rotation = Math.atan(((barrelY-target.getCenterY()) / (barrelX-target.getCenterX()) ));
		if(target.getX() <= barrelX) rotation += Math.PI;
	}
	

	
	public void drawProjectile(Graphics2D g2d){
	    if(ammoTimeOut < 600){
	    	//rotate();
	    	
	    	AffineTransform trans = new AffineTransform();
	    	trans.rotate(rotation,x,y);
	    	trans.translate(15,0);
	    	g2d.setTransform(trans);
	    	
	    	g2d.drawImage(texture, (int)(x) -10, (int)(y)-10, (int)20, 20, null);
	    
	    	x += Math.cos(rotation) * speed;
	    	y += Math.sin(rotation) * speed;
	    	//ammoTimeOut ++;
	    	
	    	

	    }

	    
	}
	
	private void checkHit(){
		if(this.x == target.getCenterX() && this.y == target.getCenterY()){
			ammoTimeOut = 1000;
			System.out.println("Hit");
		}
	}

}
