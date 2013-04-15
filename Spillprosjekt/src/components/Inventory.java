package components;

import backend.GameData;

//Lager masse deler

public class Inventory {
	
	
	
	public Inventory(){
		
		//double damage, double range, double firerate
		
		createAmmo("Basic ammo","Bullet",0,0,1,25,550);

		createAmmo( "kim jong un", "Missile",  25 , 1,  1,  60, 20);
		createAmmo("Propan","Flamme",100,2,200,25,75);

		String[] bullet = {"Bullet"};
		createBarrel("Basic barrel",25, 3,  50,  50,  50, bullet);
		createBarrel("Anhilator",120, 0,  100,  200,  500,bullet);
		createBarrel("Hyper laser",200, 1,  1,  50,  40,bullet);
		createBarrel("Phase barrel",250, 2,  200,  200,  200,bullet);
		
		String[] flamme = {"Flamme"};
		createBarrel("Cremator",25, 4,  250,  25,  50, flamme);
		createBarrel("Hindenburner",500, 5,  50,  50,  45, flamme);
		
		createBase("Basic Base",100, 0,  30,  30,  0);
		createBase("Doomsday",200, 1,  75,  75,  75);
		
	}
	
	private void createAmmo(String name,String ammoType, int price ,int textureIndex, double damage, double range, double firerate){
		Ammo new_ammo = new Ammo( name, ammoType,  price , textureIndex,  damage,  range, firerate);
		GameData.ammo.add(new_ammo);
	}
	
	
	private void createBarrel(String name, int price ,int textureIndex, double damage, double range, double firerate, String[] allowedAmmo){
		Barrel new_barrel = new Barrel( name,  price , textureIndex,  damage,  range,  firerate,allowedAmmo);
		GameData.barrels.add(new_barrel);
	}
	
	private void createBase(String name, int price ,int textureIndex, double damage, double range, double firerate){
		Base new_base = new Base( name,  price , textureIndex,  damage,  range,  firerate);
		GameData.bases.add(new_base);
	}
	

}
