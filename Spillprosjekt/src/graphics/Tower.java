package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import components.TowerComponent;

// Maa Extende Rectangle for aa gjore det lettere aa tegne i "draw"
public class Tower extends Rectangle{
	
//	Multipelen oker basert paa komponentene
	private double 	damage,
					range,
					firerate;
	
	private boolean	radar,
					splashDamage,
					glue;
		
	private Image texture;
	
	private TowerComponent 	base,
							barrel,
							aim;
//	Antar du mener naar det plasseres? :) Vi maa vaere flinke til aa kommentere hvis de andre skal forstaa
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
		
//	Tar imot et grafikkobjekt og tegner taarnet
	public void draw(Graphics g){
		g.setColor(Color.BLUE);
		if(texture != null) g.drawImage(texture, x, y, width, height, null);
		else g.fillRect(x, y, width, height);
	}
	
//	Alt av timere ol skal kjores fra denne (vil kalles av gameloopen)
	public void physics(){}
}