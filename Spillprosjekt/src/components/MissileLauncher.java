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
		super("Big Mama",10,10,100,300);
		
		this.name = "Big Mama";
		this.id = GameData.bigMama;
		texture = Tilesets.barrel_tileset[this.id];	
	}

	public void draw(Graphics2D g2d, Tower tower) {
		
		double barrelX = tower.getX()+tower.getWidth()/2;
		double barrelY = tower.getY()+tower.getHeight()/2;
		int barrelWidth = 60;

		Enemy target =tower.getTarget();
		
//		Hvis det finnes et maal og det er innenfor rekkevidden, roter lopet mot det
		if(target != null){
			double distX = target.getX()-tower.getX();
			double distY = target.getY()-tower.getY();
			if(Math.sqrt(distY*distY+distX*distX) <= tower.getRange()){
//				Pytttthugaros
				this.rotation = Math.atan(((barrelY-target.getY()-30) / (barrelX-target.getX()-30) ));
//				Legg til en pi for aa rotere i 2. og 3. kvadrant hvis fienden er til venste for taarnet
				if(target.getX()+30 <= barrelX) this.rotation += Math.PI;
			}
		}
		
		AffineTransform trans = new AffineTransform();
//		Roter lop rundt midten av taarnet
	    trans.rotate(this.rotation,barrelX,barrelY);
//	    Flytt barrel over rotasjonspunktet
	    trans.translate(tower.getWidth()/2-barrelWidth/2,tower.getHeight()/2-barrelWidth/2);
	    
//	    Oppdater grafikkobjektet med den nye transformasjonen
	    g2d.setTransform(trans);
	    
//	    Tegn barrel
	    g2d.drawImage(texture, (int)tower.getX(), (int)tower.getY(), (int)tower.getWidth(), barrelWidth, null);
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
