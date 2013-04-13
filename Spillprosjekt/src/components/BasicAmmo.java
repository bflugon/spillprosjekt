package components;

import backend.GameData;

public class BasicAmmo extends Ammo {

	public BasicAmmo() {
		//Ammo(String name,String ammoType, int price ,int textureIndex, double damage, double range, double firerate)
		super("Basic ammo",0,0,0,25,25);
		this.id = GameData.basicAmmo;
	}

}
