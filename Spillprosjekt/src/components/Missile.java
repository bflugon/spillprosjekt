package components;

import backend.GameData;

public class Missile extends Ammo{

	public Missile(){
		super(1, true, false);
		name = "Missile";
		id = GameData.missile;
	}
}
