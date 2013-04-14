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
	
	private TowerComponent compareCompo;
	
	private int width = 600;
	private int imageX = 80;
	private int height = 50;
				

	public ComponentList(int x, int y, TowerComponent towercomp, boolean more_info) {
		setBounds(x,y,width  + imageX,height);
		
		this.towercomp =  towercomp;
		this.more_info = more_info;
		
		this.compareCompo = towercomp;
	}
	
	
	
	public void setcompareCompo(TowerComponent compareCompo){
		this.compareCompo = compareCompo;
	}
	
	public void draw(Graphics g){
		
		
		if(contains(Screen.CURSOR)){
			g.setColor(Colors.transparentPink);
			if(true){
				
				g.fillRect(x + imageX, y, width,height);
			}
			
			
		}
		
		
		
		
		g.setColor(Colors.transparentBlack);
		g.fillRect(x + imageX, y, width, height);
		
		imageX -= 15;
		
		g.setColor(Colors.pink);

		g.setFont(GameData.header);
		g.drawString(String.valueOf(towercomp.getName()), x+20 + imageX, y+35);
		
		g.setColor(Color.WHITE);
		
		imageX += 30;
		g.setFont(GameData.list_stats);
		
	//Gjelder kun Barrel
		if(towercomp.getType().equals("barrel")){
			String typeString = "Can shoot: ";
			
			for(String typer : ((Barrel) towercomp).getAmmoTypes()){
				typeString += typer+", ";
			}
			
			typeString = typeString.substring(0,typeString.length()-2);
			g.drawString(typeString, x+150+60 + imageX , y+45);
		}
		
	//Gjelder kun Ammo
		else if(towercomp.getType().equals("ammo")){
			String typeString = ((Ammo) towercomp).getAmmoType();
			g.drawString("Type: " + typeString, x+150+60  + imageX, y+45);
		}

		g.setColor(compareDamage());
		g.drawString("Damage: "+towercomp.getDamage(), x+150+55 + 5 + imageX, y+20);
		g.setColor(compareRange());
		g.drawString("Range: "+towercomp.getRange(), x+275 + 65 + 20 + imageX, y+20);
		g.setColor(compareRange());
		g.drawString("Firerate: "+towercomp.getFirerate(), x+400 + 15 + 70 + imageX, y+20);
		
		
		towercomp.drawButton(g, new Rectangle(x, y-20, 300, 300));
		
//		Viser flere ting: litt tekst
		if(more_info){
			g.setFont(GameData.normal);
			g.setColor(Color.WHITE);
			g.drawString("Selected " + towercomp.getType(),x+20 + imageX,y-2);
			
		}
	}
	
	public Color compareDamage(){
		
		if(towercomp.getDamage() > compareCompo.getDamage()){
			return Color.GREEN;
		}
		else if(towercomp.getDamage() < compareCompo.getDamage()){
			return Color.RED;
		}
		
		return Color.WHITE;
	}
	
	
	public Color compareRange(){
		
		if(towercomp.getRange() > compareCompo.getRange()){
			return Color.GREEN;
		} 
		else if(towercomp.getRange() < compareCompo.getRange()){
			return Color.RED;
		} 
		
		return Color.WHITE;
	}
	
	
	public Color compareFireRate(){
		
		if(towercomp.getFirerate() > compareCompo.getFirerate()){
			return Color.GREEN;
		} 
		else if(towercomp.getFirerate() < compareCompo.getFirerate()){
			return Color.RED;
		}
		
		return Color.WHITE;
	}
}
