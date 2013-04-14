package components;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import backend.GameData;
//Hei

public class TowerComponent {
	protected Image texture;
	
	protected String 	name,
						type;
	
	private double 	damage,
					firerate,
					range;
	
	protected int 	id,
					textureIndex,
					price;
	
	private boolean splashDamage,
					slow, 
					radar;
	
	public TowerComponent(double damage, double range, double firerate,int price, String name,int textureIndex){
		this.damage = damage;
		this.firerate = firerate;
		this.range = range;
		this.name = name;
		this.price = price;
		this.textureIndex = textureIndex;

		//GameData.components_list.add(this);
	}
	
	public void drawButton(Graphics g, Rectangle rect){
		g.drawImage(texture, rect.x, rect.y+10, 60, 60, null);
	}
	
	public void setSplash(){
		splashDamage = true;
	}
	
	public boolean getSplash(){
		return splashDamage;
	}
	
	
	public int getPrice() {
		return price;
	}
	
	public double getDamage(){
		return damage;
	}
	
	public double getFirerate(){
		return firerate;
	}
	
	public double getRange(){
		return range;
	}
	
	public int getTextureIndex(){
		return textureIndex;
	}
	
	public boolean getSplashDamage(){
		return splashDamage;
	}

	public boolean getSlow(){
		return slow;
	}
	
	public boolean getRadar(){
		return radar;
	}
	
	public String getName(){
		return name;
	}
	
	public int getID(){
		return id;
	}
	
	public String getType(){
		return type;
	}
}
