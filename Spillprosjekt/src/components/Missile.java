package components;

import backend.GameData;

public class Missile extends Ammo{

	public Missile(){
		super("Missile",25,0,25,25,25);
		name = "Missile";
		id = GameData.missile;
	}
}
