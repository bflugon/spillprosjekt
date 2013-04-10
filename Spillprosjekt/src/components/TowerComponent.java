package components;

import backend.GameData;
//Hei

public class TowerComponent {
	protected String 	name,
						type;
	
	private double 	damage,
					firerate,
					range;
	
	protected int 	id,
					price;
	
	private boolean splashDamage,
					slow, 
					radar;
	
	public TowerComponent(double damage, double range, double firerate, boolean splashDamage, boolean slow, boolean radar){
		this.damage = damage;
		this.firerate = firerate;
		this.range = range;
		
		this.splashDamage = splashDamage;
		this.slow = slow;
		this.radar = radar;
		GameData.components_list.add(this);
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
