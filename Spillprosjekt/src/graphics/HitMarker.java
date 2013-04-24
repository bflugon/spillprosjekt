package graphics;

import java.awt.Graphics;
import java.awt.Rectangle;

import backend.GameData;
import backend.Tilesets;

public class HitMarker extends Rectangle {
	
	private int timeout = 0;
	
	public HitMarker(int x, int y){
		setBounds(x, y, 30, 30);
	}
	
	public void draw(Graphics g){
		g.drawImage(Tilesets.hitMarker, x, y, width, height, null);
		timeout++;
		if(timeout == 8){
			Board.hitMarkers.remove(this);
		}
	}
	
}
