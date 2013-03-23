package components;

import backend.GameData;

public class BasicBarrel extends TowerComponent {

	public BasicBarrel() {
		super(0,0,0,false,false,false);
		name = "Nothing much";
		this.id = GameData.basicBarrel;
	}

}
