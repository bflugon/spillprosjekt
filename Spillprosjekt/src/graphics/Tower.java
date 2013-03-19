package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import components.TowerComponent;

// Maa Extende Rectangle for aa gjore det lettere aa tegne i "draw"
public class Tower extends Rectangle{
	
//	Multipelen oker basert paa komponentene
	private double 	damage,
					range,
					rate;
	
	private boolean	radar,
					splashDamage,
					glue;
		
	private Image texture;
	
	private ArrayList<TowerComponent> components;
	
//	Antar du mener naar det plasseres? :) Vi maa vaere flinke til aa kommentere hvis de andre skal forstaa
//	public void create(){}
	public Tower(){
		
		components = new ArrayList<TowerComponent>();
		
		damage = 2;
		range = 150;
		rate = 500;
		
		radar = false;
		splashDamage = false;
		glue = false;

	}

//	Skyter naar timeren i "physics" kaller metoden
	private void shoot(){
		
	}
	
//	Legge til en komponent?
	public void addComponent(TowerComponent component){
		components.add(component);
	}
	
//	Oppdaterer multiplene basert paa komponentene
	private void updateFields(){
		for(TowerComponent towerComp: components){
			damage += towerComp.getDamage();
			range += towerComp.getRange();
			rate += towerComp.getFirerate();
			
			if(towerComp.getSplashDamage()) splashDamage = true;
			if(towerComp.getRadar()) radar = true;
			if(towerComp.getSlow()) glue = true;
		}
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