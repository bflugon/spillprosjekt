package graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import backend.Colors;
import backend.Tilesets;

import components.Ammo;
import components.Barrel;
import components.Base;
import components.BasicAmmo;
import components.BasicBarrel;
import components.BasicBase;

// Maa Extende Rectangle for aa gjore det lettere aa tegne i "draw"
public class Tower extends Rectangle{
	
//	Blokken taarnet staar paa
	private Block block;
	
//	Maa ha kontroll paa hvilken fiende det skyter paa for aa rotere mot maalet
	private Enemy target;
	
//	Multipelen oker basert paa komponentene
	private double 	damage = 1,
					range = 150,
					firerate = 400;
	
	private boolean	radar,
					splashDamage,
					glue;
		
	private Base base;
	private Ammo ammo;
	private Barrel barrel;

	private Board board;
	
//	Taarn som skal plasseres paa kartet trenger bare en possisjon(blokk)
//	Resten blir oppdatert i en copy-metoden som henter data fra et mal-taarn
	public Tower(Block block, Board board){		
		this.block = block;
		this.board = board;
		
		setBounds((int) block.getX(),(int) block.getY(),60,60);
		
		base = new BasicBase();
		barrel = new BasicBarrel();
		ammo = new BasicAmmo();
		fireFrame = (int)firerate;
		
		updateProperties();
	}

//	En mal trenger ikke possisjon, bare egenskaper og verdier
//	Disse blir oppdatert avhengig av hvilke komponenter man legger til
	public Tower(){
		barrel = new BasicBarrel();
		ammo = new BasicAmmo();
		base = new BasicBase();
		updateProperties();
	}
	
//	Skyter naar timeren i "physics" kaller metoden
	private void shoot(){
		Enemy[] enemies = board.getEnemies();
		
		double distX = 1000;
		double distY = 1000;
		
		if(target != null){
			distX = target.getX()-x;
			distY = target.getY()-y;
		
			if(Math.sqrt(distY*distY+distX*distX) <= range){
				if(!target.inGame()) target = null;
			} else {
				target = null;
			}
		} else if (target == null){
			for(int i = 0; i < enemies.length; i++){
				Enemy enemy = enemies[i];
				distX = enemy.getX()-x;
				distY = enemy.getY()-y;
				
				if(Math.sqrt(distY*distY+distX*distX) <= range && enemy.inGame()){
					if(target == null) {
						target = enemy;
					} else if(enemy.getDistanceTraveled() > target.getDistanceTraveled()){
						target = enemy;
					}
				}
			}
		}
		if(target != null){
			target.setLives(damage);
			System.out.println(damage);
			if(!target.inGame()) target = null;
			fireFrame = 0;
		}
	}

//	Gir taarnet som mates inn i metoden samme egenskaper som taarnet
	public void copyTower(Tower tower){		
		try {
			tower.setBarrel(barrel.getClass().newInstance());
			tower.setAmmo(ammo.getClass().newInstance());
			tower.setBase(base.getClass().newInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setBase(Base base) {
		this.base = base;
	}

	private void setAmmo(Ammo ammo) {
		this.ammo = ammo;
	}

	private void setBarrel(Barrel barrel) {
		this.barrel = barrel;
	}

	//	Oppdaterer egenskapene avhenging av komponenetene
	public void updateProperties(){
		getBarrelBonuses();
		getAmmoBonuses();
		getBaseBonuses();
	}
	private void getBarrelBonuses(){
		damage += barrel.getDamage();
		firerate += barrel.getFirerate();
		range += barrel.getRange();
	}
	private void getAmmoBonuses(){
		splashDamage = ammo.getSplashDamage();
		glue = ammo.getSlow();
		damage += ammo.getDamage();
	}
	private void getBaseBonuses(){
		damage += base.getDamage();
		range += base.getRange();
		firerate += base.getFirerate();
		radar = base.getRadar();
	}

//	Alt av timere ol skal kjores fra denne (vil kalles av gameloopen)
	private int fireFrame = (int) firerate;
	public void physics(){
		if(fireFrame >= firerate) {
			shoot();
		} else {
			fireFrame++;
		}
		
		
	}

	public Enemy getTarget() {
		return target;
	}

	public double getRange() {
		return range;
	}

	public void updateComponents(Barrel barrel, Base base) {
		this.barrel = barrel;
		this.base = base;
		updateProperties();
	}

	public Barrel getBarrel() {
		return barrel;
	}

	//	Tar imot et grafikkobjekt og tegner taarnet
	public void draw(Graphics g){
		g.drawImage(Tilesets.base_tileset[base.getID()], x, y, width, height, null);
		
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform oldtrans = new AffineTransform();
		barrel.draw(g2d, this);
	    if(fireFrame <= 10) barrel.drawShot(g2d, this); 
	    
//	    Reset transfomasjonene (kommenter ut denne for aa se hva som skjer uten naar du plasserer flere taarn)
	    g2d.setTransform(oldtrans);
	}
	
	public void drawButton(Graphics g, Rectangle rect){
		base.drawButton(g, rect);
		barrel.drawButton(g, rect);
	}
	
	public void drawRange(Graphics g){
		g.setColor(Colors.range);
//		Tegner rekkevidden rundt midten av taarnet
		g.fillOval((int)(x+30-range), (int)(y+30-range), (int)range*2, (int)range*2);
		
	}

	public Base getBase() {
		return base;
	}
}