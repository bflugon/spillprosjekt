package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import backend.Colors;
import backend.GameData;

import components.Barrel;
import components.TowerComponent;

public class ComponentList extends Rectangle{
	
	private TowerComponent barrel;

	public ComponentList(int x, int y, int width, int height, TowerComponent barrel) {
		setBounds(x,y,width,height);
		
		this.barrel =  barrel;

		//new ComponentMenu(20, 20+210*i, 200, 200, compType[i]);
	}
	
	
	
	
	
	public void draw(Graphics g){
		
		g.setColor(Colors.range);
		g.fillRect(x, y, width, height);
		
		g.setColor(Color.WHITE);

		g.setFont(GameData.header);
		g.drawString(String.valueOf(barrel.getName()), x+20, y+30);
		
		g.setFont(GameData.normal);
		g.drawString("Damage: "+barrel.getDamage(), x+150+75 + 35, y+25);
		g.drawString("Range: "+barrel.getRange(), x+275 + 85 + 50, y+25);
		g.drawString("Firerate: "+barrel.getFirerate(), x+400 + 75 + 70, y+25);
		
		//g.drawString("Splash Damage: "+barrel.getSplashDamage(), x+400, y+120);
		//g.drawString("Slow: "+barrel.getSlow(), x+400, y+140);
		//g.drawString("Radar: "+barrel.getRadar(), x+400, y+160);
		
		//g.drawImage(textures[compIndex], x, y, 400, 200, null);
	}
	
	
	
}
