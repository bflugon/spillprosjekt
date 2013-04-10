package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import backend.Colors;
import backend.GameData;

import components.Barrel;
import components.TowerComponent;

public class ComponentList extends Rectangle{
	
	private TowerComponent towercomp;

	public ComponentList(int x, int y, int width, int height, TowerComponent towercomp) {
		setBounds(x,y,width,height);
		
		this.towercomp =  towercomp;

		//new ComponentMenu(20, 20+210*i, 200, 200, compType[i]);
	}
	
	
	
	
	
	public void draw(Graphics g){
		
		g.setColor(Colors.range);
		g.fillRect(x, y, width, height);
		
		g.setColor(Color.WHITE);

		g.setFont(GameData.header);
		g.drawString(String.valueOf(towercomp.getName()), x+20, y+30);
		
		g.setFont(GameData.normal);

	
			g.drawString("Damage: "+towercomp.getDamage(), x+150+55 + 5, y+25);
			g.drawString("Range: "+towercomp.getRange(), x+275 + 65 + 20, y+25);
			g.drawString("Firerate: "+towercomp.getFirerate(), x+400 + 15 + 70, y+25);
			
		
		
		//g.drawImage(textures[compIndex], x, y, 400, 200, null);
	}
	
	
	
}
