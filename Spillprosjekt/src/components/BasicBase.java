package components;

import java.awt.Graphics;
import java.awt.Rectangle;

import backend.GameData;
import backend.Tilesets;

public class BasicBase extends Base {

	public BasicBase() {
		//(String name, int price ,int textureIndex, double damage, double range, double firerate)
		super("Basic Base", 25,0,25,25,25);
		this.id = GameData.basicBase;
	}
}
