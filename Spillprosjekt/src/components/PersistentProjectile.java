package components;

import graphics.Board;
import graphics.Enemy;
import graphics.Tower;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import sound.Sound;

public class PersistentProjectile extends Projectile{
	
	protected double range;
	protected double fireRate;
	protected int sX, sY;

	public PersistentProjectile(Tower tower, double x, double y,
			double rotation, Enemy target, Board board, Ammo ammo) {
		super(tower, x, y, rotation, target, board, ammo);
		
//		this.range = ammo.getRange();
		this.range = tower.getRange();
		this.speed = 0;
		this.fireRate = tower.getFireRate();
		this.damage = tower.getDamage()/fireRate;
	}
	
	public void rotate(){
		if(target == null)return;
		rotation = Math.atan(((y)-target.getCenterY()) / ((x)-target.getCenterX()) );
		if(target.getCenterX() <= x) rotation += Math.PI;
	}
	
	
	
	public void physics(){
		target = tower.getTarget();
System.out.println(damage);
		ammoTimeOut ++;
    	if(ammoTimeOut > fireRate){
    		tower.removeFiredAmmo(this);
    	}
		
		rotate();
		if(ammoType == "Flame"){
			if(sX<range){
				sX +=4;
				sY +=4;
			} else{
				sX -=5;
				sY -=8;
			}
		} else {
			sX = (int)range;
			sY = 60;
		}

    	checkHit();
	}

	public void drawProjectile(Graphics2D g2d){
    	
    	AffineTransform trans = new AffineTransform();
    	trans.rotate(rotation,x,y);
    	trans.translate(15,0);
    	g2d.setTransform(trans);
    	
    	g2d.drawImage(texture, (int)(x)+5, (int)(y)-sY/2, (int)sX, sY, null);
}
	
	public void checkHit(){
		if(target == null)return;
		for(Enemy enemy : board.getEnemies()){
			double enemyRotation = Math.atan(((y-enemy.getCenterY()) / (x-enemy.getCenterX()) ));
			
			if(target.getCenterX() <= x) enemyRotation += Math.PI;

			if(Math.abs(rotation-enemyRotation) < 0.3){
				double distX = enemy.getCenterX()-x;
				double distY = enemy.getCenterY()-y;
				if(Math.sqrt(distY*distY+distX*distX) <= range && enemy.inGame()){
					enemy.setLives(damage);
					if(ammoAbility != null){
						if(ammoAbility.equals("glue")) enemy.slowDownEnemy();
					}
					
					if(ammoType.equals("Lightning")) {
						ammoTimeOut = (int) (fireRate - 30);
					}
				}
			}
			
		}
		

	}
	
	
	
	

}
