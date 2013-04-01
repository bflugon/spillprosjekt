package components;

import backend.GameData;

public class BasicAmmo extends Ammo {

	public BasicAmmo() {
		//damage, splash, glue
		super(0, false, false);
		name = "Basic Ammo";
		this.id = GameData.basicAmmo;
	}

}
