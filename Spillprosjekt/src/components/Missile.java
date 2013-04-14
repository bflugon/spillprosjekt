package components;

import backend.GameData;

public class Missile extends Ammo{

	public Missile(){
		super("Missile","Missile",25,1,0,25,25);
		name = "Missile";
		id = GameData.missile;
		//GameData.components_list.add(this);
	}
	

	
	
}
