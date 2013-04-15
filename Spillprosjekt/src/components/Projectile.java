package components;

import graphics.Board;
import graphics.Enemy;
import graphics.Tower;

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
	
	private Tower tower;
	
	public Projectile(Tower tower, double x, double y, int textureIndex, double rotation, Enemy target, double damage, Board board, String ammoType){
		this.texture = Tilesets.ammo_tileset[textureIndex];
		this.board = board;
		
		this.tower = tower;
		
		this.ammoType = ammoType;
		
		this.x = x+30;
		this.y = y+30;
		
		this.rotation = rotation;
		//rotate();
		
		this.damage = damage;
		this.speed = 1;
		this.target = target;
	}
	
	private void rotate(){
		if(target == null)return;
		double barrelX = x;
		double barrelY = y;
		rotation = Math.atan(((barrelY-target.getCenterY()) / (barrelX-target.getCenterX()) ));
		if(target.getCenterX() <= barrelX) rotation += Math.PI;
	}
	
	public void physics(){
		if(target != null && ammoType.equals("Missile")){
    		rotate();
    	}
		x += Math.cos(rotation) * speed;
    	y += Math.sin(rotation) * speed;
    	checkHit();
    	
    	ammoTimeOut ++;
    	if(ammoTimeOut > 1000 || !target.inGame()){
    		tower.removeFiredAmmo(this);
    	}
	}

	
	public void drawProjectile(Graphics2D g2d){
	    	
	    	AffineTransform trans = new AffineTransform();
	    	trans.rotate(rotation,x,y);
	    	trans.translate(15,0);
	    	g2d.setTransform(trans);
	    	
	    	g2d.drawImage(texture, (int)(x) -10, (int)(y)-10, (int)20, 20, null);
	}
	
	private void checkHit(){
		setBounds((int)x, (int)y, 20, 20);
		for(Enemy enemy : board.getEnemies()){
			if(this.intersects(enemy) && enemy.inGame()){
				ammoTimeOut = 100000;
				enemy.setLives(damage);
			}
			
		}
		

	}

}
