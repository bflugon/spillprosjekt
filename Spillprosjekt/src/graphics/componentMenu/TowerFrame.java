package graphics.componentMenu;

import graphics.Tower;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import backend.Colors;
import backend.GameData;

public class TowerFrame extends Rectangle {
	
	private ComponentMenu menu;
	
	public TowerFrame(ComponentMenu menu){
		setBounds(20, 20, 680, 260);
		this.menu = menu;
	}
	
	public void draw(Graphics g){
		Tower tower = GameData.modelTowers.get(menu.getActiveTowerIndex());
		
		g.setColor(Colors.transparentBlack);
		g.fillRect(x, y, width, height);
		
		tower.drawLargeImage(g, new Rectangle(x,y,height,height));
		
//		Draw properties
		g.setColor(Color.WHITE);
		g.setFont(GameData.header);
		
		g.drawString("Damage: "+tower.getDamage(), x+height+80, y+60);
		g.drawString("Range: "+tower.getRange(), x+height+80, y+112);
		g.drawString("Firerate: "+tower.getFireRate(), x+height+80, y+164);
		g.drawString("Price: "+tower.getPrice()+"$",x+height+80,y+220);
	}
}
