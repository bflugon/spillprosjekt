package components;

import graphics.Board;
import graphics.Enemy;
import graphics.Tower;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import sound.Sound;

import backend.GameData;
import backend.Tilesets;

public class Projectile extends Rectangle{
	protected Image texture;
	
	protected int ammoTimeOut;
	
	protected double rotation,x,y,speed,damage,startX,startY;
	
	protected Enemy target;
	protected Board board;
	
	protected String ammoType,ammoAbility;
	
	protected Tower tower;
	
	public Projectile(Tower tower, double x, double y, double rotation, Enemy target , Board board,  Ammo ammo){
		this.texture = Tilesets.ammo_tileset[ammo.getTextureIndex()];
		this.board = board;
		
		this.tower = tower;
		
		this.ammoType = ammo.getAmmoType();
		this.ammoAbility = ammo.getAbility();
		
		
		this.x = x+30;
		this.y = y+30;
		
		this.rotation = rotation;
		//rotate();
		
		this.damage = tower.getDamage();
		this.speed = 1;
		this.target = target;
	}
	
	public void rotate(){
		if(target == null)return;
		double barrelX = x;
		double barrelY = y;
		rotation = Math.atan(((barrelY-target.getCenterY()) / (barrelX-target.getCenterX()) ));
		if(target.getCenterX() <= barrelX) rotation += Math.PI;
	}
	
	public void physics(){
		if(target.inGame() && target != null && ammoType.equals("Missile")){
    		rotate();
    	}
		x += Math.cos(rotation) * speed;
    	y += Math.sin(rotation) * speed;
    	
    	
    	checkHit();
    	
    	ammoTimeOut ++;
    	if(ammoTimeOut > 1000){
    		tower.removeFiredAmmo(this);
    	}
    	
    	if(x < 0 || x > 680 || y < 0 || y > 720) tower.removeFiredAmmo(this);
	}

	
	public void drawProjectile(Graphics2D g2d){
	    	
    	AffineTransform trans = new AffineTransform();
    	trans.rotate(rotation,x,y);
    	trans.translate(15,0);
    	g2d.setTransform(trans);
    	
    	g2d.drawImage(texture, (int)(x) -10, (int)(y)-10, (int)20, 20, null);
	}
	
	public void checkHit(){
		setBounds((int)x, (int)y, 20, 20);
		for(Enemy enemy : board.getEnemies()){
			if(this.intersects(enemy) && enemy.inGame()){
				
				if(ammoAbility != null && ammoAbility.equals("glue")) enemy.slowDownEnemy();
				enemy.setLives(damage);

				
//				add splash damage to missiles
				if(ammoType == "Missile"){

					if(ammoType.equals("Missile")){
						Sound.playSound("explosion.wav");
					}
					
					setBounds((int)x-30, (int)y-30, 80, 80);
					for(int i = 0; i < board.getEnemies().length; i++){
						Enemy enemyNearby = board.getEnemies()[i];
						if(enemyNearby.intersects(this) && enemy.inGame()){
							enemy.setLives(damage/2);
						}
					}
				}
				
				
				tower.removeFiredAmmo(this);
				break;
			}
			
		}
		

	}
	


}
