package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import components.Barrel;
import components.Base;
import components.TowerComponent;

import backend.Colors;
import backend.GameData;
import backend.Tilesets;

public class EditTower {


	
//	The tower beeing updated
	private Tower activeTower;
	
//	Knappene som representerer taarnene


	
	private Rectangle 	goToBoard;
						
	
	public EditTower(){
		
		Barrel barrel_1 = new Barrel("Yor", 999, 999, 999, 34);
		Barrel barrel_2 = new Barrel("Short round", 20, 14, 45, 34);
		Barrel barrel_3 = new Barrel("Indy", 20, 14, 45, 34);
		Barrel barrel_4 = new Barrel("TB", 20, 14, 45, 34);
		Barrel barrel_5 = new Barrel("Mongo bollefjes", 20, 14, 45, 34);	
		

		
		
		goToBoard = new Rectangle(720,560,80,80);
		
		activeTower = GameData.modelTowers.get(0);
	}

	public void draw(Graphics g) {

		

		
		
		int teller = 0;
		for (TowerComponent towercomp : GameData.components_list) {
			if(towercomp instanceof Barrel){
				ComponentList new_item = new ComponentList(20, 10+60 * teller,towercomp, false );
				//new_item.draw(g);
				teller ++;
			}
		}
		
		//Viser det aktive tower
		if(true){
			Rectangle towerButton = new Rectangle(50, 25, 300, 300);
			activeTower.drawLargeImage(g, towerButton);
			

			g.setColor(Color.WHITE);
			g.setFont(GameData.large_header);
			g.drawString(String.valueOf(activeTower.getName()),250,100);
			
			
			g.setFont(GameData.normal_large);
			g.drawString("Price: " + String.valueOf(activeTower.getPrice()) + "0 $",250,150);
			
			g.setColor(Colors.pink);
			g.setFont(GameData.header);
			g.drawString("Damage: " + String.valueOf(activeTower.getDamage()),30,230);
			g.drawString("Range: " + String.valueOf(activeTower.getRange()),260,230);
			g.drawString("Firerate: " + String.valueOf(activeTower.getFireRate()),500,230);
			
			g.setFont(GameData.normal);
			
			
			
			g.setColor(Color.WHITE);
			
			ComponentList barrel = new ComponentList(100, 300, activeTower.getBarrel(), true );
			barrel.draw(g);
			
			
			ComponentList base = new ComponentList(100, 380, activeTower.getBase(), true );
			base.draw(g);
			
			ComponentList ammo = new ComponentList(100, 460, activeTower.getAmmo(), true );
			ammo.draw(g);
			
			
			//Egenskaper
			g.drawString("Abilities",120,578);
			g.setColor(Colors.range);
			g.fillRect(100, 580, 600,40);
			g.setColor(Color.WHITE);
			String abilits = "";
			
			if(!activeTower.getAmmo().getSplashDamage()){
				abilits += "Splash damage "; 
			}
			
			if(!activeTower.getAmmo().getSlow()){
				abilits += " Slows down enemies "; 
			}
			
			if(!activeTower.getAmmo().getRadar()){
				abilits += " Can spot hidden baloon "; 
			}
				
			if(abilits.equals("")){
				abilits += " None ";
			}
			g.drawString(abilits, 120, 605);
			
			
			
			
			
		}

	}


	


	public boolean boardClicked() {
		return goToBoard.contains(Screen.CURSOR);
	}
	


	public boolean goToBoard() {
		return goToBoard.contains(Screen.CURSOR);
	}
	
}
