package components;

import backend.GameData;

//Lager masse deler

public class Inventory {
	
	
	
	public Inventory(){
		
		createAmmo( "kim jong un", "Missile",  25 , 1,  1,  60, 20);
		createAmmo("Basic ammo","Bullet",0,0,1,25,550);
		
		createBarrel("Anhilator",120, 4,  100,  200,  500);
		createBarrel("Hyper laser",200, 3,  1,  50,  40);
		createBarrel("Big mama",120, 2,  75,  200,  300);
		createBarrel("Phase barrel",250, 1,  200,  200,  200);
		createBarrel("Basic barrel",25, 0,  50,  50,  50);
		
		createBase("Basic Base",100, 0,  30,  30,  0);
		createBase("Doomsday",200, 1,  75,  75,  75);
		
	}
	
	private void createAmmo(String name,String ammoType, int price ,int textureIndex, double damage, double range, double firerate){
		Ammo new_ammo = new Ammo( name, ammoType,  price , textureIndex,  damage,  range, firerate);
		GameData.components_list.add(new_ammo);
	}
	
	
	private void createBarrel(String name, int price ,int textureIndex, double damage, double range, double firerate){
		Barrel new_barrel = new Barrel( name,  price , textureIndex,  damage,  range,  firerate);
		GameData.components_list.add(new_barrel);
	}
	
	private void createBase(String name, int price ,int textureIndex, double damage, double range, double firerate){
		Base new_base = new Base( name,  price , textureIndex,  damage,  range,  firerate);
		GameData.components_list.add(new_base);
	}
	

}
