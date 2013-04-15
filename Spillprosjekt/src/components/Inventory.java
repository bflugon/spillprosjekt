package components;

import backend.GameData;

//Lager masse deler

public class Inventory {
	
	
	
	public Inventory(){
		
		createAmmo("Basic ammo","Bullet",0,0,1,25,550);

		createAmmo( "kim jong un", "Missile",  25 , 1,  1,  60, 20);

		createAmmo("Hindenburd","Flamme",100,2,200,25,200);

		String[] ammotyper = {"Bullet"};
		createBarrel("Basic barrel",25, 0,  50,  50,  50, ammotyper);
		createBarrel("Anhilator",120, 4,  100,  200,  500,ammotyper);
		createBarrel("Hyper laser",200, 3,  1,  50,  40,ammotyper);
		createBarrel("Big mama",120, 2,  75,  200,  300,ammotyper);
		createBarrel("Phase barrel",250, 1,  200,  200,  200,ammotyper);
		
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
