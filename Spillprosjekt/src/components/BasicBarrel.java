package components;

import backend.GameData;

public class BasicBarrel extends Barrel {
	
	
	public BasicBarrel() {
		//Barrel(String name, int price ,int textureIndex, double damage, double range, double firerate) 
		super("Basic barrel",0,0,0,400,500);
		this.id = GameData.basicBarrel;
		GameData.components_list.add(this);
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
