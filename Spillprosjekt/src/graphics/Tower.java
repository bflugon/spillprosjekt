package graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import sound.Sound;
import backend.Colors;
import backend.GameData;
import backend.Tilesets;

import components.Ammo;
import components.Barrel;
import components.Base;
import components.PersistentProjectile;
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
					slow;
	
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
		
		barrel = GameData.barrels.get(0);
		ammo = GameData.ammo.get(0);
		base = GameData.bases.get(0);
		fireFrame = (int)firerate;
		
		firedAmmo = new ArrayList<Projectile>();
		
		updateProperties();
	}

//	En mal trenger ikke possisjon, bare egenskaper og verdier
//	Disse blir oppdatert avhengig av hvilke komponenter man legger til
	public Tower(){
		barrel = GameData.barrels.get(0);
		ammo = GameData.ammo.get(0);
		base = GameData.bases.get(0);
		updateProperties();
		this.name ="Mongo bollefjes";
	}
	
	
	private void findTarget(){
		Enemy[] enemies = board.getEnemies();
		
		double distX = 1000;
		double distY = 1000;
		
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
			
			addFiredAmmo();
			
			Sound.playSound("shot.wav");
			target = null;
			fireFrame = 0;
		}
	}

//	Gir taarnet som mates inn i metoden samme egenskaper som taarnet
	public void copyTower(Tower tower){	
		Barrel newBarrel = new Barrel(barrel.getName(), barrel.getPrice(),barrel.getTextureIndex(), barrel.getDamage(), barrel.getRange(), barrel.getFirerate(), barrel.getAmmoTypes());
		Ammo newAmmo = new Ammo(ammo.getName(),ammo.getAmmoType(), ammo.getPrice(),ammo.getTextureIndex(), ammo.getDamage(), ammo.getRange(), ammo.getFirerate());
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
	
	public void addFiredAmmo(){
		if(ammo.getAmmoType().equals("Flamme") || ammo.getAmmoType().equals("Beam")){
			firedAmmo.add(new PersistentProjectile(this, x, y,  rotation, this.getTarget(),this.board, this.getAmmo()));
		}
		
		if(ammo.getAmmoType().equals("Bullet") || ammo.getAmmoType().equals("Missile")){
			firedAmmo.add(new Projectile(this, x, y,  rotation, this.getTarget(),this.board, this.getAmmo()));
		}
		
		
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
		slow = ammo.getSlow();
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
			findTarget();
			rotate();
			shoot();
			findTarget();
		} else {
			fireFrame++;
		}
		
		for(Projectile proj : firedAmmo){
			proj.physics();
		}
		
	}

	public Enemy getTarget() {
		return target;
	}

	public double getRange() {
		return range;
	}

	public Barrel getBarrel() {
		return barrel;
	}

//	Tar imot et grafikkobjekt og tegner taarnet
	public void draw(Graphics g){
		g.drawImage(Tilesets.base_tileset[base.getTextureIndex()], x, y, width, height, null);
		
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform oldtrans = new AffineTransform();
		
 		for(int i = 0; i<(firedAmmo.size()); i ++){
			firedAmmo.get(i).drawProjectile(g2d);
		}
		
		barrel.draw(g2d, x, y, rotation);

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
	
	public String getName(){
		return name;
	}
	
	public double getDamage(){
		return damage;
	}
	
	public double getFireRate(){
		return firerate;
	}
	
	public void removeFiredAmmo(Projectile proj){
		this.firedAmmo.remove(proj);
	}

	public boolean getSlow() {
		return slow;
	}

	public boolean getRadar() {
		return radar;
	}

	public boolean getSplashDamage() {
		return splashDamage;
	}
}