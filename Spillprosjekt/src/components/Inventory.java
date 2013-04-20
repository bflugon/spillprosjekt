package components;

import backend.GameData;

//Lager masse deler

public class Inventory {
	
	
	
	public Inventory(){
		
		//name, ammotype, cost, index, double damage, double range, double firerate, ability, rank
		
		createAmmo("Basic Bullet","Bullet",15,0,0,25,200,0);
		createAmmo("Basic Slime","Slime",123,7,7,25,200,"glue",0);
		
		createAmmo( "Basic Missile", "Missile",  25 , 1,  1,  60, 20,0);
		createAmmo("Basic Flame","Flame",100,2,200,25,75,0);
		createAmmo("Basic Lightning","Lightning",100,5,200,25,75,0);
		
		createBarrel("Heavy Barrel",25, 12,  10,  100,  520, "Bullet", "shot2.wav",0);
		createBarrel("Fast Barrel",25, 13,  5,  75,  25, "Bullet", "shot2.wav",0);
		createBase("Old base",25, 7,  0,  5,  5,0);
		
		//Rank 1
		String bullet = "Bullet";
		createBarrel("Dragvolly",25, 3,  20,  50,  100, bullet, "shot2.wav",1);
		createBase("Concrete",50, 2,  0,  0,  0,1);
		//Ny kule
		
		//Rank 2
		createBase("Green Goblin",100, 3,  20,  75,  20,2);
		String flame = "Flame";
		createBarrel("Cremator",25, 4,  250,  25,  80, flame, "flame.wav",2);
		
		//Rank 4
		createBarrel("Hindenburner",500, 5,  50,  50,  100, flame, "flame.wav",8);
		
		
		createAmmo( "S.M.A.R.T", "Missile",  250 , 4,  150,  250, 200,4);
		createAmmo( "SlowPoke", "Missile",  750 , 6,  0,  250, 200, "glue",6);
		createAmmo("Icy Flame","Flame",100,8,200,25,75, "glue",5);
		createAmmo("High Caliber","Bullet",1000,3,200,25,150,7);


		createBarrel("Anhilator",120, 0,  100,  200,  600,bullet, "shot.wav",2);
		createBarrel("Hyper Laser",200, 1,  1,  70,  40,bullet, "shot2.wav",4);
		createBarrel("Phase Barrel",250, 2,  200,  350,  200,bullet, "shot.wav",5);

		

		
		String slimer = "Slime";
		createBarrel("Slimer",250, 8,  200,  350,  200,slimer, "glue.wav",5);
		
		String tesla = "Lightning";
		createBarrel("Tesla Coil",750, 6,  500,  100,  75,tesla, "shot.wav",3);
		

		String missile = "Missile";
		createBarrel("NASAMS",750, 7,  10,  250,  250,missile, "missile.wav",7);



		
		
		
		createBase("Presicion Base",250, 0,  55,  150,  200,5);
		createBase("Phaser Baser",150, 1,  55,  100,  100,4);
		
		createBase("Doomsday",150, 4,  55,  100,  100,7);
		createBase("Phantom",150, 5,  55,  100,  100,6);
		createBase("Flamers friend",150, 6,  25,  30,  10,8);
//		createBase("Basic Base",100, 0,  30,  100,  0);
		
	}
	
	private void createAmmo(String name,String ammoType, int price ,int textureIndex, double damage, double range, double firerate, int rankLimit){
		Ammo new_ammo = new Ammo( name, ammoType,  price , textureIndex,  damage,  range, firerate, rankLimit);
		GameData.ammo.add(new_ammo);
		
	}
	
	private void createAmmo(String name,String ammoType, int price ,int textureIndex, double damage, double range, double firerate, String ability, int rankLimit){
		Ammo new_ammo = new Ammo( name, ammoType,  price , textureIndex,  damage,  range, firerate,ability, rankLimit);
		GameData.ammo.add(new_ammo);
		
	}
	
	
	private void createBarrel(String name, int price ,int textureIndex, double damage, double range, double firerate, String allowedAmmo, String soundName, int rankLimit){
		Barrel new_barrel = new Barrel( name,  price , textureIndex,  damage,  range,  firerate,allowedAmmo, soundName, rankLimit);
		GameData.barrels.add(new_barrel);
	}
	
	private void createBase(String name, int price ,int textureIndex, double damage, double range, double firerate, int rankLimit){
		Base new_base = new Base( name,  price , textureIndex,  damage,  range,  firerate, rankLimit);
		GameData.bases.add(new_base);
	}
	

}
