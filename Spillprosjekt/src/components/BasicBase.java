package components;

import java.awt.Graphics;
import java.awt.Rectangle;

import backend.GameData;
import backend.Tilesets;

public class BasicBase extends Base {

	public BasicBase() {
		super(0,false);
		this.name = "Basic Base";
		this.id = GameData.basicBase;
		texture = Tilesets.base_tileset[id];
	}
}