package components;

import graphics.Tower;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

public abstract class Barrel extends TowerComponent{

	protected Image barrelTexture;
	protected double rotation = 0;
	
	public Barrel(double damage, double range, double firerate) {
		super(damage, range, firerate, false,false,false);
	}
	
	public abstract void draw(Graphics2D g2d, Tower tower);
	public abstract void drawShot(Graphics2D g2d, Tower tower);
	public abstract void drawButton(Graphics g, Rectangle rect);
}
