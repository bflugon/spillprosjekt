package graphics;

import java.awt.Graphics;
import java.awt.Rectangle;

import backend.GameData;
import backend.Tilesets;

public class HitMarker extends Rectangle {
	
	private int timeout = 0;
	private int index,
				limit;;
	
	public HitMarker(int x, int y, int index){
		this.index = index;
		if(index == 0) {
			setBounds(x, y, 30, 30);
			limit = 9;
		}
		else {
			setBounds(x, y, 100, 100);
			limit = 15;
		}
	}
	
	public void draw(Graphics g){
		g.drawImage(Tilesets.hitMarker_tileset[index], x, y, width, height, null);
		timeout++;
		if(timeout == limit){
			Board.hitMarkers.remove(this);
		}
	}
	
}
