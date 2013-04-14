package components;

import graphics.Enemy;
import backend.GameData;
import backend.Tilesets;

public class Missile extends Ammo{

	public Missile(){
		super("Missile",25,1,0,25,25);
		name = "Missile";
		id = GameData.missile;
		GameData.components_list.add(this);
	}
	
	public Missile(int x, int y, int textureIndex, double rotation, Enemy target, double damage){
		super(0, 0, 0, 0, "", textureIndex);
		this.texture = Tilesets.ammo_tileset[textureIndex];
		
		ammoX = x+30;
		ammoY = y+30;
		
		this.enemy = enemy;
		this.rotation = rotation;
		
		rotateAmmo(rotation);
	}
	
	
}
