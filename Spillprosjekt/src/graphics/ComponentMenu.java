package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import backend.Colors;
import backend.GameData;
import backend.Tilesets;

import components.Ammo;
import components.Barrel;
import components.Base;
import components.TowerComponent;

public class ComponentMenu extends Rectangle{
	
	private ArrayList<TowerComponent> components;
	private Image[] textures;
	private int compIndex;
	
	public ComponentMenu(int x, int y, int width, int height, String type) {
		setBounds(x,y,width,height);
		
		components = new ArrayList<TowerComponent>();
		
		if(type == "barrel"){
			textures = Tilesets.barrelMenu_tileset;
			for(TowerComponent towerComp: GameData.components){
				if(towerComp instanceof Barrel) components.add(towerComp);
			}
		} else if(type == "ammo"){
			textures = Tilesets.ammoMenu_tileset;
			for(TowerComponent towerComp: GameData.components){
				if(towerComp instanceof Ammo) components.add(towerComp);
			}
		} else if(type == "base"){
			textures = Tilesets.baseMenu_tileset;
			for(TowerComponent towerComp: GameData.components){
				if(towerComp instanceof Base) components.add(towerComp);
			}
		}
		
		compIndex = 0;
	}
	
	public TowerComponent getCurrentComponent(){
		return components.get(compIndex);
	}
	
	public void changeCurrentComponent(){
		compIndex++;
		if(compIndex == components.size()) compIndex = 0;
	}
	
	public void updateComponent(TowerComponent towerComp){
		for(int i = 0; i < components.size(); i++){
			if(components.get(i).getClass() == towerComp.getClass()){
				compIndex = i;
			}
		}
	}

	public void draw(Graphics g){
		g.setColor(Colors.range);
		g.fillRect(x, y, width, height);
		
		g.setColor(Color.WHITE);

		g.setFont(GameData.header);
		g.drawString(String.valueOf(components.get(compIndex).getName()), x+400, y+30);
		
		g.setFont(GameData.normal);
		g.drawString("Damage bonus: "+components.get(compIndex).getDamage(), x+400, y+60);
		g.drawString("Range bonus: "+components.get(compIndex).getRange(), x+400, y+90);
		g.drawString("Rate bonus: "+components.get(compIndex).getFirerate(), x+400, y+120);
		
		g.drawImage(textures[compIndex], x, y, 400, 200, null);
	}
	
}
