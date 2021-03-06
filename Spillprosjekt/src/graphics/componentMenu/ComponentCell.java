package graphics.componentMenu;

import graphics.Screen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import backend.Colors;
import backend.GameData;
import backend.Tilesets;

import components.Ammo;
import components.Barrel;
import components.Base;
import components.TowerComponent;

public class ComponentCell extends Rectangle{
	
	protected TowerComponent component;
	private int padding = 15;
	private TowerComponent currComponent;
	private Image[] textures;
	
	public ComponentCell(TowerComponent component, TowerComponent currComponent, int x, int y, int width, int height){
		this.component = component;
		this.currComponent = currComponent;
		
		if(currComponent instanceof Barrel) textures = Tilesets.menu_barrel_tileset;
		else if(currComponent instanceof Ammo) textures = Tilesets.ammo_tileset;
		else if(currComponent instanceof Base) textures = Tilesets.base_tileset;
			
		setBounds(x, y, width, height);
	}
	
	public void draw(Graphics g){
		if(contains(Screen.CURSOR)) g.setColor(Colors.transparentRed);
		else g.setColor(Colors.transparentBlack);

		g.fillRect(x, y, width, height);
		g.drawImage(textures[component.getTextureIndex()], x+padding, y+padding, 60, 60, null);
		
//		Draw name
		g.setFont(GameData.header);
		g.setColor(Colors.red);
		g.drawString(component.getName(), x+100, y+50);
		
//		Draw properties
		g.setFont(GameData.small);
		
		int offsetX = 400;
		g.setColor(Color.WHITE);
		g.drawString("Damage:", x+offsetX, y+15);
		g.setColor(compare(currComponent.getDamage(), component.getDamage()));
		g.drawString(String.valueOf(component.getDamage()), x + offsetX+120, y + 15);
		
		
		g.setColor(Color.WHITE);
		g.drawString("Range:", x+offsetX, y+35);
		g.setColor(compare(currComponent.getRange(), component.getRange()));
		g.drawString(String.valueOf(component.getRange()), x+offsetX+120, y+35);
		
		g.setColor(Color.WHITE);
		g.drawString("Firerate:", x+offsetX, y+55);
		g.setColor(compare(component.getFirerate(),currComponent.getFirerate()));
		g.drawString(String.valueOf(component.getFirerate()), x+offsetX+120, y+55);
		
//		Draw extra 
		if(component instanceof Barrel) drawBarrel(g);
		else if(component instanceof Base) drawBase(g);
		else drawAmmo(g);
		
		if(component.getRankLimit() > GameData.rank){
			g.setColor(Colors.popupTransparentBlack);
			
			g.drawImage(textures[component.getTextureIndex()], x+padding, y+padding, 60, 60, null);
			g.fillRect(x, y, width, height);
			
			g.setFont(GameData.normal);
			g.setColor(Color.GRAY);
			g.drawString("Not avalible until rank "+component.getRankLimit(), x+200, y+50);
		}
	}
	
	public void drawBarrel(Graphics g){
		String allowedAmmo = ((Barrel)component).getAmmoType();
		
		g.setColor(Color.WHITE);
		g.drawString("Allowed Ammo:", x+400, y+75);
		g.drawString(allowedAmmo, x+520, y+75);
	}
	
	public void drawAmmo(Graphics g){
		g.setColor(Color.WHITE);
		g.drawString("Ammo Type:", x+300, y+75);
		
		
		if(((Ammo)component).getAbility() != null){
			g.setColor(Color.WHITE);
			g.drawString(((Ammo)component).getAmmoType() + ((Ammo)component).abilityToString(), x+420, y+75);
		}
		else{
			g.drawString(((Ammo)component).getAmmoType(), x+420, y+75);
		}
		
	}
	
	public void drawBase(Graphics g){
		g.setColor(Color.WHITE);
		g.drawString("Base Type:", x+300, y+75);
		g.drawString(((Base)component).getType(), x+420, y+75);
	}
	
	public TowerComponent getComponent(){
		return component;
	}
	
	public Color compare(double d, double e){

		if(d < e) return Color.GREEN;
		if(d > e) return Color.RED;
		
		return Color.WHITE;
	}
}
