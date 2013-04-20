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
		
		this.range = ammo.getRange();
		this.range = tower.getRange();
		this.speed = 0;
		this.fireRate = tower.getFireRate();
		this.damage = ammo.getDamage()/fireRate;
		
//		Vet ikke hvorfor denne var her? kanskje noe gammelt?
//		Det var den som gjorde at lynet ikke kom fra tårnet
		
//		if(ammo.getAmmoType().equals("Lightning")){
//			this.x -= 27;
//		}
		
	}
	
	public void rotate(){
		if(target == null)return;
		rotation = Math.atan(((y-target.getCenterY()) / (x-target.getCenterX()) ));
		if(target.getCenterX() <= x) rotation += Math.PI;
	}
	
	
	
	public void physics(){
		target = tower.getTarget();
		if(target != null && ammoType.equals("Beam")){
    		rotate();
    	} 

		ammoTimeOut ++;
    	if(ammoTimeOut > fireRate || !target.inGame()){
    		tower.removeFiredAmmo(this);
    	}
		
		rotate();
		
		if(sX<range){
				
			sX +=4;
			sY +=4;
		} else{
			sX -=5;
			sY -=8;
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
		for(Enemy enemy : board.getEnemies()){
			double enemyRotation = Math.atan(((y-enemy.getCenterY()) / (x-enemy.getCenterX()) ));
			
			if(target.getCenterX() <= x) enemyRotation += Math.PI;

			if(Math.abs(rotation-enemyRotation) < 0.3){
				double distX = enemy.getX()-x;
				double distY = enemy.getY()-y;
					
				if(Math.sqrt(distY*distY+distX*distX) <= range && enemy.inGame()){
					enemy.setLives(damage);
					if(ammoAbility != null && ammoAbility.equals("glue")) enemy.slowDownEnemy();
					
					if(ammoType.equals("Lightning") && enemy.getLives() < 2) {
						tower.removeFiredAmmo(this);
					}
				}
			}
			
		}
		

	}
	
	
	
	

}
