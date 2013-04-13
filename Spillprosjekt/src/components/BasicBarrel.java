package components;

import graphics.Enemy;
import graphics.Tower;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import backend.GameData;
import backend.Tilesets;

public class BasicBarrel extends Barrel {
	
	
	public BasicBarrel() {
		//Barrel(String name, int price ,int textureIndex, double damage, double range, double firerate) 
		super("Basic barrel",25,0,25,25,25);
		this.id = GameData.basicBarrel;
	}
	//Tror ikke vi trenger dette?
	/*
	public void drawButton(Graphics g, Rectangle rect){
		g.drawImage(texture, (int)rect.getX()+15, (int)rect.getY()+10, 60, 60, null);
	}
	
	public void drawShot(Graphics2D g2d, Tower tower){
		g2d.setColor(Color.ORANGE);
	    g2d.fillRect((int)tower.getX()+60, (int) (tower.getY()+30-3), 10, 6);
	}
	*/
}
