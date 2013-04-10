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

public class Barrel extends TowerComponent{

	protected Image barrelTexture;
	protected double rotation = 0;
	
	protected Point center;
	
	public Barrel(String name, int price ,double damage, double range, double firerate) {
		super(damage, range, firerate, false,false,false);
		this.name = name;
		this.price = price;
	}
	
	public void draw(Graphics2D g2d, Tower tower){
		AffineTransform trans = new AffineTransform();
		
		double barrelX = tower.getX()+tower.getWidth()/2;
		double barrelY = tower.getY()+tower.getHeight()/2;
		int barrelWidth = 60;

		Enemy target = tower.getTarget();
		
//		Hvis det finnes et maal og det er innenfor rekkevidden
		if(target != null){
			double distX = target.getX()-tower.getX();
			double distY = target.getY()-tower.getY();
			if(Math.sqrt(distY*distY+distX*distX) <= tower.getRange()){
//				Pytttthugaros
				rotation = Math.atan(((barrelY-target.getY()-30) / (barrelX-target.getX()-30) ));
//				Legg til en pi for aa rotere i 2. og 3. kvadrant hvis fienden er til venste for taarnet
				if(target.getX()+30 <= barrelX) rotation += Math.PI;
			}
		}
		
//		Roter lop rundt midten av taarnet
	    trans.rotate(rotation,barrelX,barrelY);
//	    Flytt barrel over rotasjonspunktet
	    trans.translate(tower.getWidth()/2-15,tower.getHeight()/2-barrelWidth/2);
	    
//	    Oppdater grafikkobjektet med den nye transformasjonen
	    g2d.setTransform(trans);
	    
//	    Tegn barrel
	    g2d.drawImage(barrelTexture, (int)tower.getX(), (int)tower.getY(), (int)tower.getWidth(), barrelWidth, null);
	}
	public void drawButton(Graphics g, Rectangle rect){
		g.drawImage(barrelTexture, (int)rect.getX()+15, (int)rect.getY()+10, 60, 60, null);
	}
	
	public void drawShot(Graphics2D g2d, Tower tower){
		g2d.setColor(Color.ORANGE);
	    g2d.fillRect((int)tower.getX()+60, (int) (tower.getY()+30-3), 10, 6);
	}
}
