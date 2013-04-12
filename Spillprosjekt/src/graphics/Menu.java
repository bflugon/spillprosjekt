package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import backend.Colors;
import backend.GameData;
import backend.Tilesets;

import components.Barrel;
import components.TowerComponent;

public class Menu {

//	Taarnene som kan oppgraderes
	private ArrayList<Tower> modelTowers;
	
//	The tower beeing updated
	private Tower activeTower;
	private int activeTower_index = 0;
	
//	Knappene som representerer taarnene
	private ArrayList<Rectangle> towerButtons;
	private String[] compType = {"barrel", "ammo", "base"};
	
	private ChooseComponent openComponentList;
	private ComponentList barrelList,baseList,ammoList;
	
	private Rectangle 	goToBoard,
						newTower;
	
	public Menu(){
		
		Barrel barrel_1 = new Barrel("Yor", 999, 999, 999, 34);
		Barrel barrel_2 = new Barrel("Short round", 1, 150, 10, 10);
		Barrel barrel_3 = new Barrel("Indy", 20, 14, 45, 34);
		Barrel barrel_4 = new Barrel("TB", 20, 14, 45, 34);
		Barrel barrel_5 = new Barrel("Mongo bollefjes", 20, 14, 45, 34);	
		

			
		
		
		modelTowers = GameData.modelTowers;
		modelTowers.add(new Tower());
		

		towerButtons = new ArrayList<Rectangle>();
		for(int i = 0; i < modelTowers.size(); i++){
			towerButtons.add(new Rectangle(720, 20+90*i, 80, 80));
		}
		
		newTower = new Rectangle(720, 20+90*towerButtons.size(), 80, 80);
		goToBoard = new Rectangle(720,560,80,80);
		
		activeTower = modelTowers.get(activeTower_index);
		

		
		
		barrelList = new ComponentList(100, 300, activeTower.getBarrel(), true );
		baseList = new ComponentList(100, 380, activeTower.getBase(), true );
		ammoList = new ComponentList(100, 460, activeTower.getAmmo(), true );
		
		
		
		
		
	}

	public void draw(Graphics g) {
		
		if(openComponentList != null){
			openComponentList.draw(g);
			
		}
		
		else if(openComponentList == null){
		
			
			
			 
		
			
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
				
				ComponentList barrelList = new ComponentList(100, 300, activeTower.getBarrel(), true );
				barrelList.draw(g);
				
				
				ComponentList baseList = new ComponentList(100, 380, activeTower.getBase(), true );
				baseList.draw(g);
				
				ComponentList ammoList = new ComponentList(100, 460, activeTower.getAmmo(), true );
				ammoList.draw(g);
				
				
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
			
			
			g.setColor(Colors.range);
			for(int i = 0; i < towerButtons.size(); i++){
				Rectangle towerButton = towerButtons.get(i);
				g.fillRect(towerButton.x, towerButton.y, towerButton.width, towerButton.height);
				GameData.modelTowers.get(i).drawButton(g, towerButton);
	
			}
			
			g.fillRect(goToBoard.x, goToBoard.y, goToBoard.width, goToBoard.height);
			g.drawImage(Tilesets.button_tileset[GameData.goToBoard], goToBoard.x, goToBoard.y, goToBoard.width, goToBoard.height, null);
			
			if(newTower != null){
				g.fillRect(newTower.x, newTower.y, newTower.width, newTower.height);
				g.drawImage(Tilesets.button_tileset[GameData.newTower], newTower.x, newTower.y, newTower.width, newTower.height, null);
			}
		
		}
	}

	public void changeActiveTower() {
		for(int i = 0; i < towerButtons.size(); i++){
			if(towerButtons.get(i).contains(Screen.CURSOR)){
				updateTower();
				activeTower = modelTowers.get(i);
				activeTower_index = i;
			}
		}
	}
	
	public void updateTower(){
		//activeTower.updateComponents((Barrel)compMenus[0].getCurrentComponent(),(Base)compMenus[2].getCurrentComponent());
	}

	public boolean boardClicked() {
		return goToBoard.contains(Screen.CURSOR);
	}
	
	public void addTower(){
		if(newTower == null)return;
		if(!newTower.contains(Screen.CURSOR)) return;

		GameData.modelTowers.add(new Tower());
		towerButtons.add(new Rectangle(720, 20+90*towerButtons.size(), 80, 80));
		
		if(GameData.modelTowers.size() == 5) newTower = null;
		else newTower = new Rectangle(720, 20+90*towerButtons.size(), 80, 80);
	}

	public boolean goToBoard() {
		return goToBoard.contains(Screen.CURSOR);
	}
	
	public void closeComponentMenu(){
		if(openComponentList != null){
			if(openComponentList.componentClicked()){
				openComponentList = null;
			}
			
		}
	}
	
	public void openComponentMenu(){
		if(barrelList.checkIfClicked()){
			openComponentList = new ChooseComponent(activeTower.getBarrel(),activeTower_index);
		} 
		else if(baseList.checkIfClicked()){
			openComponentList = new ChooseComponent(activeTower.getBase(),activeTower_index);
		} 
		
		else if(ammoList.checkIfClicked()){
			openComponentList = new ChooseComponent(activeTower.getAmmo(),activeTower_index);
		} 
	}
	
	
	public boolean isComponentsOpen(){
		return openComponentList != null;
	}
	
}
