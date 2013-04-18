package components;

import backend.GameData;

//Lager masse deler

public class Inventory {
	
	
	
	public Inventory(){
		
		//double damage, double range, double firerate
		
		createAmmo("Basic Bullet","Bullet",0,0,1,25,550);
		createAmmo("Basic Slime","Slime",123,7,7,25,200,"glue");
		
		createAmmo( "Basic Missile", "Missile",  25 , 1,  1,  60, 20);
		createAmmo( "S.M.A.R.T", "Missile",  250 , 4,  150,  250, 200);
		createAmmo( "SlowPoke", "Missile",  750 , 6,  0,  250, 200, "glue");
		createAmmo("Basic Flame","Flame",100,2,200,25,75);
		createAmmo("Icy Flame","Flame",100,8,200,25,75, "glue");
		createAmmo("Basic Lightning","Lightning",100,5,200,25,75);
		createAmmo("High Caliber","Bullet",1000,3,200,25,150);

		String bullet = "Bullet";
		createBarrel("Basic Barrel",25, 3,  50,  100,  200, bullet, "shot2.wav");
		createBarrel("Anhilator",120, 0,  100,  200,  600,bullet, "shot.wav");
		createBarrel("Hyper Laser",200, 1,  1,  70,  40,bullet, "shot2.wav");
		createBarrel("Phase Barrel",250, 2,  200,  350,  200,bullet, "shot.wav");

		
		String flame = "Flame";
		createBarrel("Cremator",25, 4,  250,  25,  50, flame, "flame.wav");
		createBarrel("Hindenburner",500, 5,  50,  50,  100, flame, "flame.wav");
		
		String slimer = "Slime";
		createBarrel("Slimer",250, 8,  200,  350,  200,slimer, "glue.wav");
		
		String tesla = "Lightning";
		createBarrel("Tesla Coil",750, 6,  500,  100,  75,tesla, "shot.wav");
		

		String missile = "Missile";
		createBarrel("NASAMS",750, 7,  10,  250,  250,missile, "missile.wav");


		
		createBase("Concrete",50, 2,  0,  0,  0);
		createBase("Green Goblin",100, 3,  20,  75,  20);
		
		createBase("Presicion Base",250, 0,  55,  150,  200);
		createBase("Phaser Baser",150, 1,  55,  100,  100);
		
		createBase("Doomsday",150, 4,  55,  100,  100);
		createBase("Phantom",150, 5,  55,  100,  100);
		createBase("Flamers friend",150, 6,  25,  30,  10);
//		createBase("Basic Base",100, 0,  30,  100,  0);
		
	}
	
	private void createAmmo(String name,String ammoType, int price ,int textureIndex, double damage, double range, double firerate){
		Ammo new_ammo = new Ammo( name, ammoType,  price , textureIndex,  damage,  range, firerate);
		GameData.ammo.add(new_ammo);
		
	}
	
	private void createAmmo(String name,String ammoType, int price ,int textureIndex, double damage, double range, double firerate, String ability){
		Ammo new_ammo = new Ammo( name, ammoType,  price , textureIndex,  damage,  range, firerate,ability);
		GameData.ammo.add(new_ammo);
		
	}
	
	
	private void createBarrel(String name, int price ,int textureIndex, double damage, double range, double firerate, String allowedAmmo, String soundName){
		Barrel new_barrel = new Barrel( name,  price , textureIndex,  damage,  range,  firerate,allowedAmmo, soundName);
		GameData.barrels.add(new_barrel);
	}
	
	private void createBase(String name, int price ,int textureIndex, double damage, double range, double firerate){
		Base new_base = new Base( name,  price , textureIndex,  damage,  range,  firerate);
		GameData.bases.add(new_base);
	}
	

}
