package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import components.TowerComponent;

// Maa Extende Rectangle for aa gjore det lettere aa tegne i "draw"
public class Tower extends Rectangle{
	
//	Blokken taarnet staar paa
	private Block block;
	
//	Multipelen oker basert paa komponentene
	private double 	damage,
					range,
					firerate;
	
	private boolean	radar,
					splashDamage,
					glue;
		
	private Image texture;
	
	private TowerComponent 	//base,
							barrel,
							aim;
	
//	Antar du mener naar det plasseres? :) Vi maa vaere flinke til aa kommentere hvis de andre skal forstaa
	public Tower(Block block){		
		this.block = block;
		
		setBounds((int) block.getX(),(int) block.getY(),60,60);
	}

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
		
	}
	
	public void copyTower(Tower tower){
		tower.setDamage(damage);
		tower.setRange(range);
		tower.setFirerate(firerate);
		
		tower.setSplash(splashDamage);
		tower.setRadar(radar);
		tower.setGlue(glue);
		
		tower.setTexture(texture);
	}
	
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
	
	private void setTexture(Image texture) {
		this.texture = texture;
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
		g.setColor(Color.BLUE);
		if(texture != null) {
			System.out.println("Tower.draw()");
			g.drawImage(texture, x, y, width, height, null);
		}
		else {
			System.out.println("Tower.draw()");
			g.fillRect(x, y, width, height);
		}
	}
	
//	Alt av timere ol skal kjores fra denne (vil kalles av gameloopen)
	public void physics(){}
}