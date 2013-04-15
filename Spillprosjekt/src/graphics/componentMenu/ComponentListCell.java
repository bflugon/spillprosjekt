package graphics.componentMenu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import backend.GameData;

import components.TowerComponent;

public class ComponentListCell extends ComponentCell{

	public ComponentListCell(TowerComponent component, TowerComponent currComponent, int x, int y, int width, int height){
		super(component, currComponent, x, y, width, height);
	}
	
	public void draw(Graphics g){
		super.draw(g);
		
		g.setColor(Color.WHITE);
		g.setFont(GameData.header);
		g.drawString(component.getPrice() + " $", x+590, y+60);
		
//		Draw border
		g.setColor(Color.DARK_GRAY);
		g.drawLine(x, y+height, x+width, y+height);
	}
}
