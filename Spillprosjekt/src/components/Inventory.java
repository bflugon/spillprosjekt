package components;

import backend.GameData;

//Lager masse deler

public class Inventory {
	
	
	
	public Inventory(){
		
		//name, ammotype, cost, index, double damage, double range, double firerate, ability, rank
		
		createAmmo("Basic Bullet","Bullet",15,0,0,25,100,0);
		createAmmo("Basic Slime","Slime",123,7,7,25,200,"glue",0);
		
		createAmmo("Basic Missile", "Missile",  25 , 1,  1,  60, 20,0);
		createAmmo("Basic Flame","Flame",200,2,20,25,75,0);
		createAmmo("Basic Lightning","Lightning",100,5,100,25,75,0);
		
		createBarrel("Heavy Barrel",100, 12,  10,  100,  350, "Bullet", "shot2.wav",0);
		createBarrel("Fast Barrel",100, 13,  3,  75,  0, "Bullet", "fastShot.wav",0);
		createBase("Old base",25, 7,  0,  5,  5,0);
		
		//Rank 1
		String bullet = "Bullet";
		createBarrel("Dragvolly",160, 3,  16,  50,  100, bullet, "shot2.wav",1);
		createBase("Concrete",50, 2,  0,  0,  0,1);
		createAmmo("Copper Bullet","Bullet",15,10,0,25,200,1);
		//Ny kule
		
		//Rank 2
		createBase("Green Goblin",100, 3,  20,  75,  20,2);
		createBarrel("Cremator",200, 4,  40,  55,  80, "Flame", "flame.wav",2);
		createBase("Phaser Baser",300, 1,  8,  60,  50,2);

		//Rank 3
		createAmmo("High Caliber","Bullet",800,3,30,25,150,3);
		createBarrel("Slimer",350, 8,  30,  200,  200,"Slime", "glue.wav",5);
		createBase("Presicion Base",400, 0,  0,  130,  150,53);
		
		//Rank 4
		createBarrel("Hindenburner",500, 5, 80,  50,  100, "Flame", "flame.wav",4);
		createAmmo("Icy Flame","Flame",100,8,20,25,75, "glue",4);
		createBarrel("Hyper Laser",300, 1,  1,  70,  40,bullet, "shot2.wav",4);
		
		//Rank 5
		createBarrel("Hyper Laser",200, 1,  1,  70,  40,bullet, "shot2.wav",5);
		createBarrel("Phase Barrel",250, 2,  200,  350,  200,bullet, "shot.wav",5);
		createBase("Phantom",800, 5,  15,  100,  50,5);
		createBarrel("Tesla Coil",750, 6,  550,  100,  75,"Lightning", "shot.wav",5);

		
		//Rank 6
		createAmmo("Doom Charge","Lightning",500,14,1500,25,75,6);
		createBarrel("Hyper Laser",600, 1,  1,  70,  40,bullet, "shot2.wav",6);
		createBarrel("Phase Barrel",800, 2,  100,  350,  300,bullet, "shot.wav",6);
		createBase("Flamers friend",400, 6,  50,  10,  10,6);
		createBarrel("NASAMS",750, 7,  10,  250,  250,"Missile", "missile.wav",6);
		createAmmo( "SlowPoke", "Missile",  550 , 6,  20,  250, 300, "glue",6);
		
		//Rank 7
		createBarrel("Annihilator",1500, 0,  400,  200,  600,bullet, "shot.wav",7);
		createBase("Doomsday",600, 4,  20,  30,  150,7);
		createAmmo( "S.M.A.R.T", "Missile",  500 , 4,  100,  250, 200,7);

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
