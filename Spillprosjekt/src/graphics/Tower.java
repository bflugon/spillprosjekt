package graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import sound.Sound;
import backend.Colors;
import backend.Tilesets;

import components.Ammo;
import components.Barrel;
import components.Base;
import components.BasicAmmo;
import components.BasicBarrel;
import components.BasicBase;
import components.Projectile;

// Maa Extende Rectangle for aa gjore det lettere aa tegne i "draw"
public class Tower extends Rectangle{
	
//	Blokken taarnet staar paa
	private Block block;
	
//	Maa ha kontroll paa hvilken fiende det skyter paa for aa rotere mot maalet
	private Enemy target;
	
	private ArrayList<Projectile> firedAmmo;
	
	
//	Multipelen oker basert paa komponentene
	private double 	damage = 1,
					range = 150,
					price = 10,
					firerate = 400,
					rotation = 0;
	
	private boolean	radar,
					splashDamage,
					glue;
	
	private String name;
		
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
		
		firedAmmo = new ArrayList<Projectile>();
		
		updateProperties();
	}

//	En mal trenger ikke possisjon, bare egenskaper og verdier
//	Disse blir oppdatert avhengig av hvilke komponenter man legger til
	public Tower(){
		barrel = new BasicBarrel();
		ammo = new BasicAmmo();
		base = new BasicBase();
		updateProperties();
		this.name ="Mongo bollefjes";
	}
	
	
	private void findTarget(){
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
		} else {
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
		

	}
	
	
	private void rotate(){
		if(target == null)return;
		double barrelX = x+30;
		double barrelY = y+30;
		rotation = Math.atan(((barrelY-target.getY()-30) / (barrelX-target.getX()-30) ));
		if(target.getX()+30 <= barrelX) rotation += Math.PI;
	}
	
//	Skyter naar timeren i "physics" kaller metoden
	private void shoot(){
		if(target != null){
			
			firedAmmo.add(new Projectile(x, y, ammo.getTextureIndex(), rotation, this.getTarget(),ammo.getDamage()));
			
			
			if(firedAmmo.size() > 10){
				//firedAmmo.remove(0);
			}
			
			target.setLives(damage);
			Sound.playSound("shot.wav");
			if(!target.inGame()) target = null;
			fireFrame = 0;
		}
	}

//	Gir taarnet som mates inn i metoden samme egenskaper som taarnet
//Fra Simen: Er noe galt med denne
//	Fra Oyvind: Ikke nŒ lenger :)
	public void copyTower(Tower tower){	
		Barrel newBarrel = new Barrel(barrel.getName(), barrel.getPrice(),barrel.getTextureIndex(), barrel.getDamage(), barrel.getRange(), barrel.getFirerate());
		Ammo newAmmo = new Ammo(ammo.getName(), ammo.getPrice(),ammo.getTextureIndex(), ammo.getDamage(), ammo.getRange(), ammo.getFirerate());
		Base newBase = new Base(base.getName(), base.getPrice(),base.getTextureIndex(), base.getDamage(), base.getRange(), base.getFirerate());
		
		tower.setBarrel(newBarrel);
		tower.setAmmo(newAmmo);
		tower.setBase(newBase);
	}
	
	public void setBase(Base base) {
		this.base = base;
	}

	public void setAmmo(Ammo ammo) {
		this.ammo = ammo;
	}

	public void setBarrel(Barrel barrel) {
		this.barrel = barrel;
	}

	//	Oppdaterer egenskapene avhenging av komponenetene
	public void updateProperties(){
		damage = 0;
		range = 0;
		firerate = 0;
		
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
		rotate();
		if(fireFrame >= firerate) {
			shoot();
			findTarget();
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
		
		
		for(int i = 0; i<(firedAmmo.size()); i ++){
			firedAmmo.get(i).drawProjectile(g2d);
		}
		
		
		barrel.draw(g2d, x, y, rotation);

		if(fireFrame <= 10) barrel.drawShot(g2d, this); 
	    
		

		
		
//	    Reset transfomasjonene (kommenter ut denne for aa se hva som skjer uten naar du plasserer flere taarn)
	    g2d.setTransform(oldtrans);
	    
	}
	
	public void drawButton(Graphics g, Rectangle rect){
		base.drawButton(g, rect);
		barrel.drawButton(g, rect);
	}
	
	public void drawLargeImage(Graphics g, Rectangle rect){
		base.drawLargeImage(g, rect);
		barrel.drawLargeImage(g, rect);
	}
	
	public void drawRange(Graphics g){
		g.setColor(Colors.transparentBlack);
//		Tegner rekkevidden rundt midten av taarnet
		g.fillOval((int)(x+30-range), (int)(y+30-range), (int)range*2, (int)range*2);
		
	}

	public Base getBase() {
		return base;
	}
	
	public double getPrice() {
		return price;
	}
	
	public Ammo getAmmo(){
		return this.ammo;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public double getDamage(){
		return damage;
	}
	
	public double getFireRate(){
		return firerate;
	}
	
	public void removeFiredAmmo(){
		this.firedAmmo.remove(0);
	}
}