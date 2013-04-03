package components;

import java.awt.Graphics;
import java.awt.Rectangle;

import backend.GameData;
import backend.Tilesets;

public class Doomsday extends Base {

	public Doomsday() {
		super(10, false);
		name = "Doomsday";
		id = GameData.doomsday;
		texture = Tilesets.base_tileset[id];
	}
}
