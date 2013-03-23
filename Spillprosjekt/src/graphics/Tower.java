package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import backend.Colors;
import backend.GameData;
import backend.Tilesets;

import components.BasicBarrel;
import components.BasicBase;
import components.TowerComponent;

// Maa Extende Rectangle for aa gjore det lettere aa tegne i "draw"
public class Tower extends Rectangle{
	
//	Blokken taarnet staar paa
	private Block block;
	
//	Maa ha kontroll paa hvilken fiende det skyter paa for aa rotere mot maalet
	private Enemy target;
	
//	Multipelen oker basert paa komponentene
	private double 	damage = 2,
					range = 200,
					firerate = 400;
	
	private boolean	radar,
					splashDamage,
					glue;
		
	private TowerComponent 	base,
							barrel,
							aim;

	private Board board;
	
	private double rotation = 0;
	
//	Taarn som skal plasseres paa kartet trenger bare en possisjon(blokk)
//	Resten blir oppdatert i en copy-metoden som henter data fra et mal-taarn
	public Tower(Block block, Board board){		
		this.block = block;
		this.board = board;
		
		setBounds((int) block.getX(),(int) block.getY(),60,60);
		
		base = new BasicBase();
		barrel = new BasicBarrel();
	}

//	En mal trenger ikke possisjon, bare egenskaper og verdier
//	Disse blir oppdatert avhengig av hvilke komponenter man legger til
	public Tower(){
		damage = 2;
		range = 150;
		firerate = 500;
		
		radar = false;
		splashDamage = false;
		glue = false;
	}
	
//	Skyter naar timeren i "physics" kaller metoden
	private void shoot(){
		Enemy[] enemies = board.getEnemies();
		target = null;
		for(int i = 0; i < enemies.length; i++){
			Enemy enemy = enemies[i];
			double distX = enemy.getX()-x;
			double distY = enemy.getY()-y;
			
			if(Math.sqrt(distY*distY+distX*distX) <= range){
				if(target == null) {
					target = enemy;
				} else if(enemy.getDistanceTraveled() > target.getDistanceTraveled()){
					target = enemy;
				}
			}
		}
		if(target != null){
			target.setLives(1);
			if(!target.inGame()) target = null;
		}
	}

//	Gir taarnet som mates inn i metoden samme egenskaper som taarnet
	public void copyTower(Tower tower){
		tower.setDamage(damage);
		tower.setRange(range);
		tower.setFirerate(firerate);
		
		tower.setSplash(splashDamage);
		tower.setRadar(radar);
		tower.setGlue(glue);
	}
	
//	Oppdaterer egenskapene avhenging av komponenetene
	private void updateProperties(){
		getBarrelBonuses();
		getAimBonuses();
//		getBaseBonuses();
	}
	private void getBarrelBonuses(){
		damage += barrel.getDamage();
		firerate += barrel.getFirerate();
		range += barrel.getRange();
	}
	private void getAimBonuses(){
		if(aim.getRadar()) radar = true;
		if(aim.getSplashDamage()) splashDamage = true;
		if(aim.getSlow()) glue = true;
	}
	private void getBaseBonuses(){
		
	}

	private void setRadar(boolean radar) {
		this.radar = radar;
	}
	private void setGlue(boolean glue) {
		this.glue = glue;
	}
	private void setSplash(boolean splashDamage) {
		this.splashDamage = splashDamage;
	}
	private void setFirerate(double firerate) {
		this.firerate = firerate;
	}
	private void setRange(double range) {
		this.range = range;
	}
	private void setDamage(double damage) {
		this.damage = damage;
	}

	//	Tar imot et grafikkobjekt og tegner taarnet
	public void draw(Graphics g){
		g.drawImage(Tilesets.base_tileset[base.getID()], x, y, width, height, null);
		
		Image barrelTexture = Tilesets.barrel_tileset[barrel.getID()];
		
		
		AffineTransform identity = new AffineTransform();
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform trans = new AffineTransform();
			
		int barrelX = x+width/2;
		int barrelY = y+height/2;
		identity.setToTranslation(barrelX, barrelY);
		trans.setTransform(identity);

		if(target != null){
			double distX = target.getX()-x;
			double distY = target.getY()-y;
			if(Math.sqrt(distY*distY+distX*distX) <= range){
				rotation = Math.atan(((barrelY-target.getY()) / (barrelX-target.getX()) ));
				if(target.getX() <= barrelX) rotation += Math.PI;
			}
		}
		
		trans.rotate(rotation);
		g2d.drawImage(barrelTexture, trans, null);
	
	}
	
//	Alt av timere ol skal kjores fra denne (vil kalles av gameloopen)
	private int fireFrame = 0;
	public void physics(){
		if(fireFrame == firerate) {
			System.out.println("Tower.physics()");
			shoot();
			fireFrame = 0;
		} else {
			fireFrame++;
		}
		
		
	}
}