package components;

import graphics.Enemy;
import graphics.Tower;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import backend.GameData;
import backend.Tilesets;

public class MissileLauncher extends Barrel {

	public MissileLauncher(){
//		Damage, range, firerate
		super("Big mama",25,0,25,25,25);
		
		this.name = "Big Mama";
		this.id = GameData.bigMama;
		texture = Tilesets.barrel_tileset[this.id];	
	}

	public void draw(Graphics2D g2d, int x, int y, double rotation) {
		
		double barrelX = x+30;
		double barrelY = y+30;
		int barrelWidth = 60;

		AffineTransform trans = new AffineTransform();
//		Roter lop rundt midten av taarnet
	    trans.rotate(rotation,barrelX,barrelY);
//	    Flytt barrel over rotasjonspunktet
//	    trans.translate(0,0);
	    
//	    Oppdater grafikkobjektet med den nye transformasjonen
	    g2d.setTransform(trans);
	    
//	    Tegn barrel
	    g2d.drawImage(texture, x, y, 60, 60, null);
	}

	@Override
	public void drawShot(Graphics2D g2d, Tower tower) {
		
		g2d.setColor(Color.ORANGE);
	    g2d.fillRect((int)tower.getX()+55, (int) (tower.getY()+10), 17, 10);
	    g2d.fillRect((int)tower.getX()+55, (int) (tower.getY()+45), 17, 10);	
	}

	@Override
	public void drawButton(Graphics g, Rectangle rect) {
		g.drawImage(texture, (int)rect.getX(), (int)rect.getY()+10, 60, 60, null);
	}
	
}
