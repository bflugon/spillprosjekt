package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import backend.Colors;
import backend.GameData;

import components.Ammo;
import components.Barrel;
import components.TowerComponent;

public class ComponentList extends Rectangle{
	
	private TowerComponent towercomp;
	private boolean more_info;
	
	private int width = 600;
	private int height = 50;
				

	public ComponentList(int x, int y, TowerComponent towercomp, boolean more_info) {
		setBounds(x,y,width,height);
		
		this.towercomp =  towercomp;
		this.more_info = more_info;

		//new ComponentMenu(20, 20+210*i, 200, 200, compType[i]);
	}
	
	
	
	
	
	public void draw(Graphics g){
		
		g.setColor(Colors.range);
		g.fillRect(x, y, width, height);
		
		g.setColor(Colors.pink);

		g.setFont(GameData.header);
		g.drawString(String.valueOf(towercomp.getName()), x+20, y+35);
		
		g.setColor(Color.WHITE);
		
		g.setFont(GameData.normal);
		
	//Gjelder kun Barrel
		if(towercomp.getType().equals("barrel")){
			String typeString = "Can shoot: ";
			
			for(String typer : ((Barrel) towercomp).getAmmoTypes()){
				typeString += typer;
				typeString += ", ";
			}
			typeString = typeString.substring(0,typeString.length()-2);
			g.drawString(typeString, x+150+60 , y+45);
		}
		
	//Gjelder kun Ammo
		if(towercomp.getType().equals("ammo")){
			String typeString = ((Ammo) towercomp).getAmmoType();
			g.drawString("Type: " + typeString, x+150+60 , y+45);
		}

	
			g.drawString("Damage: "+towercomp.getDamage(), x+150+55 + 5, y+20);
			g.drawString("Range: "+towercomp.getRange(), x+275 + 65 + 20, y+20);
			g.drawString("Firerate: "+towercomp.getFirerate(), x+400 + 15 + 70, y+20);
			
			
			towercomp.drawButton(g, new Rectangle(15, y-20, 300, 300));
		
			//Viser flere ting: litt tekst
		if(more_info){
			g.drawString("Selected " + towercomp.getType(),x+20,y-2);
			
		}
	}
	
	
	
}
