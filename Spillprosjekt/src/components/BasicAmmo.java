package components;

import backend.GameData;

public class BasicAmdmo extends Ammo {

	public BasicAmmo() {
		//Ammo(String name,String ammoType, int price ,int textureIndex, double damage, double range, double firerate)
		super("Basic ammo","Bullet",0,0,1,25,550);
		this.id = GameData.basicAmmo;
		//GameData.components_list.add(this);
		
	}

}
