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

public class Menu {

	private ComponentMenu[] compMenus;
	
//	Taarnene som kan oppgraderes
	private ArrayList<Tower> modelTowers;
	
//	The tower beeing updated
	private Tower activeTower;
	
//	Knappene som representerer taarnene
	private ArrayList<Rectangle> towerButtons;
	private String[] compType = {"barrel", "ammo", "base"};
	
	private Rectangle 	goToBoard,
						newTower;
	
	public Menu(){
		
		Barrel barrel_1 = new Barrel("Yor", 999, 999, 999, 34);
		Barrel barrel_2 = new Barrel("Short round", 20, 14, 45, 34);
		Barrel barrel_3 = new Barrel("Indy", 20, 14, 45, 34);
		Barrel barrel_4 = new Barrel("TB", 20, 14, 45, 34);
		Barrel barrel_5 = new Barrel("Mongo bollefjes", 20, 14, 45, 34);	
		
		System.out.println(GameData.components_list);
		

			
		
		
		modelTowers = GameData.modelTowers;
		modelTowers.add(new Tower());
		
		compMenus = new ComponentMenu[3];
		for(int i = 0; i < compMenus.length; i++){
			compMenus[i] = new ComponentMenu(20, 20+210*i, 680, 200, compType[i]);
		}
		

		towerButtons = new ArrayList<Rectangle>();
		for(int i = 0; i < modelTowers.size(); i++){
			towerButtons.add(new Rectangle(720, 20+90*i, 80, 80));
		}
		
		newTower = new Rectangle(720, 20+90*towerButtons.size(), 80, 80);
		goToBoard = new Rectangle(720,560,80,80);
		
		activeTower = modelTowers.get(0);
	}
	
	public void drawTower(Tower tower){
		
	}
	

	public void draw(Graphics g) {
		for (ComponentMenu compMenu : compMenus) {
			//compMenu.draw(g);
		}
		

		
		
		int teller = 0;
		for (TowerComponent towercomp : GameData.components_list) {
			if(towercomp instanceof Barrel){
				ComponentList new_item = new ComponentList(20, 10+60 * teller, 680, 40,towercomp );
				//new_item.draw(g);
				teller ++;
			}
		}
		
		
		if(true){
			Rectangle towerButton = new Rectangle(50, 50, 300, 300);
			activeTower.drawLargeImage(g, towerButton);
			

			g.setColor(Color.WHITE);
			g.setFont(GameData.header);
			g.drawString(String.valueOf(activeTower.getName()),20,30);
			
			
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

	public void changeComponent() {
		for(ComponentMenu compMenu: compMenus){
			if(compMenu.contains(Screen.CURSOR)){
				compMenu.changeCurrentComponent();
			}
		}
	}

	public void changeActiveTower() {
		for(int i = 0; i < towerButtons.size(); i++){
			if(towerButtons.get(i).contains(Screen.CURSOR)){
				updateTower();
				activeTower = modelTowers.get(i);
				
				compMenus[0].updateComponent(activeTower.getBarrel());
				compMenus[2].updateComponent(activeTower.getBase());
			}
		}
	}
	
	public void updateTower(){
		activeTower.updateComponents((Barrel)compMenus[0].getCurrentComponent(),(Base)compMenus[2].getCurrentComponent());
	}

	public boolean boardClicked() {
		return goToBoard.contains(Screen.CURSOR);
	}
	
	public void addTower(){
		if(newTower == null)return;
		if(!newTower.contains(Screen.CURSOR)) return;

		GameData.modelTowers.add(new Tower());
		towerButtons.add(new Rectangle(720, 20+90*towerButtons.size(), 80, 80));
		
		if(GameData.modelTowers.size() == 6) newTower = null;
		else newTower = new Rectangle(720, 20+90*towerButtons.size(), 80, 80);
	}
	
}
