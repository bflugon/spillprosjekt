package components;

import graphics.Board;
import graphics.Enemy;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import backend.Tilesets;

public class Projectile extends Rectangle{
	protected Image texture;
	
	protected int ammoTimeOut;
	
	protected double rotation,x,y,speed,damage,startX,startY;
	
	protected Enemy target;
	protected Board board;
	
	protected String ammoType;
	
	
	
	public Projectile(double x, double y, int textureIndex, double rotation, Enemy target, double damage, Board board, String ammoType){
		this.texture = Tilesets.ammo_tileset[textureIndex];
		this.board = board;
		
		this.ammoType = ammoType;
		
		this.x = x+30;
		this.y = y+30;
		
		this.rotation = rotation;
		//rotate();
		
		this.damage = damage;
		this.speed = 1;
		this.target = target;
		
		x += Math.cos(rotation) *16;
    	y += Math.sin(rotation) *16;
	}
	
	private void rotate(){
		if(target == null)return;
		double barrelX = x;
		double barrelY = y;
		rotation = Math.atan(((barrelY-target.getCenterY()) / (barrelX-target.getCenterX()) ));
		if(target.getX() <= barrelX) rotation += Math.PI;
	}
	

	
	public void drawProjectile(Graphics2D g2d){
	    if(ammoTimeOut < 1000){
	    	if(target != null && ammoType.equals("Missile")){
	    		rotate();
	    	}
	    		
	    	
	    	AffineTransform trans = new AffineTransform();
	    	trans.rotate(rotation,x,y);
	    	trans.translate(15,0);
	    	g2d.setTransform(trans);
	    	
	    	g2d.drawImage(texture, (int)(x) -10, (int)(y)-10, (int)20, 20, null);
	    
	    	x += Math.cos(rotation) * speed;
	    	y += Math.sin(rotation) * speed;
	    	ammoTimeOut ++;
	    	
	    	setBounds((int)this.x,(int)this.y,20,20);
	    	checkHit();

	    }

	    
	}
	
	private void checkHit(){
		for(Enemy enemy : board.getEnemies()){
			if(this.intersects(enemy)){
				ammoTimeOut = 100000;
				enemy.setLives(damage);
				//System.out.println("Hit");
			}
			
		}
		

	}

}
