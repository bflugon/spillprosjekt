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
		g.drawString(String.valueOf(barrel.getName()), x, y+30);
		
		g.setFont(GameData.normal);
		g.drawString("Damage bonus: "+barrel.getDamage(), x+400, y+60);
		g.drawString("Range bonus: "+barrel.getRange(), x+400, y+80);
		g.drawString("Rate bonus: "+barrel.getFirerate(), x+400, y+100);
		
		g.drawString("Splash Damage: "+barrel.getSplashDamage(), x+400, y+120);
		g.drawString("Slow: "+barrel.getSlow(), x+400, y+140);
		g.drawString("Radar: "+barrel.getRadar(), x+400, y+160);
		
		//g.drawImage(textures[compIndex], x, y, 400, 200, null);
	}
	
	
	
}
