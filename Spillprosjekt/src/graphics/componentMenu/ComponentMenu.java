package graphics.componentMenu;

import graphics.Screen;
import graphics.Tower;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import components.Barrel;

import backend.Colors;
import backend.GameData;
import backend.Tilesets;

public class ComponentMenu {

//	Taarnene som kan oppgraderes
	private ArrayList<Tower> modelTowers;
	
//	The tower beeing updated
	private Tower activeTower;
	private int activeTowerIndex = 0;
	
//	Knappene som representerer taarnene
	private ArrayList<Rectangle> towerButtons;
	private String[] compType = {"barrel", "ammo", "base"};
	
	private Rectangle 	goToBoard,
						newTower;
	
	private ComponentList componentList;
	
	private ComponentCell 	barrelCell,
							ammoCell,
							baseCell;
	
	private TowerFrame towerFrame;
	
	public ComponentMenu(){
		
		modelTowers = GameData.modelTowers;			
		if(modelTowers.size() == 0){
			modelTowers.add(new Tower());
		}
			
		towerButtons = new ArrayList<Rectangle>();
		for(int i = 0; i < modelTowers.size(); i++){
			towerButtons.add(new Rectangle(720, 20+90*i, 80, 80));
		}
		if(towerButtons.size() < 6){
			newTower = new Rectangle(720, 20+90*towerButtons.size(), 80, 80);
		}
		goToBoard = new Rectangle(720,560,80,80);
		
		activeTower = modelTowers.get(activeTowerIndex);
		
		barrelCell = new ComponentCell(activeTower.getBarrel(), activeTower.getBarrel(), 20, 290, 680, 80);
		ammoCell = new ComponentCell(activeTower.getAmmo(), activeTower.getAmmo(), 20, 380, 680, 80);
		baseCell = new ComponentCell(activeTower.getBase(), activeTower.getBase(), 20, 470, 680, 80);

		towerFrame = new TowerFrame(this);
	}

	public void draw(Graphics g) {
		
		towerFrame.draw(g);
		
		barrelCell.draw(g);
		ammoCell.draw(g);
		baseCell.draw(g);
		
		/*************************
		 *** Taarn og knapper ****
		 *************************/
		for(int i = 0; i < towerButtons.size(); i++){
			if(i == activeTowerIndex)g.setColor(Colors.pink);
			else if(towerButtons.get(i).contains(Screen.CURSOR))g.setColor(Colors.transparentPink);
			else g.setColor(Colors.transparentBlack);
			Rectangle towerButton = towerButtons.get(i);
			g.fillRect(towerButton.x, towerButton.y, towerButton.width, towerButton.height);
			GameData.modelTowers.get(i).drawButton(g, towerButton);
		}
		
		if(newTower != null){
			if(newTower.contains(Screen.CURSOR))g.setColor(Colors.transparentPink);
			else g.setColor(Colors.transparentBlack);
			
			g.fillRect(newTower.x, newTower.y, newTower.width, newTower.height);
			g.drawImage(Tilesets.button_tileset[GameData.newTower], newTower.x, newTower.y, newTower.width, newTower.height, null);
		}

		if(goToBoard.contains(Screen.CURSOR))g.setColor(Colors.transparentPink);
		else g.setColor(Colors.transparentBlack);
		g.fillRect(goToBoard.x, goToBoard.y, goToBoard.width, goToBoard.height);
		g.drawImage(Tilesets.button_tileset[GameData.goToBoard], goToBoard.x, goToBoard.y, goToBoard.width, goToBoard.height, null);
		

		
//		Liste, hvis det trengs
		if(componentList != null)componentList.draw(g);
		
	}

	public void changeActiveTower() {
		for(int i = 0; i < modelTowers.size(); i++){
			if(towerButtons.get(i).contains(Screen.CURSOR)){
				activeTower = modelTowers.get(i);
				activeTowerIndex = i;
				
				barrelCell = new ComponentCell(activeTower.getBarrel(), activeTower.getBarrel(), 20, 290, 680, 80);
				ammoCell = new ComponentCell(activeTower.getAmmo(), activeTower.getAmmo(), 20, 380, 680, 80);
				baseCell = new ComponentCell(activeTower.getBase(), activeTower.getBase(), 20, 470, 680, 80);
				
				break;
			}
		}
	}
	
	public boolean boardClicked() {
		return goToBoard.contains(Screen.CURSOR);
	}
	
	public void addTower(){
		if(newTower == null)return;
		if(!newTower.contains(Screen.CURSOR)) return;
		GameData.modelTowers.add(new Tower());
		towerButtons.add(new Rectangle(720, 20+90*towerButtons.size(), 80, 80));
		
		if(GameData.modelTowers.size() > 5) newTower = null;
		else newTower = new Rectangle(720, 20+90*towerButtons.size(), 80, 80);
	}

	public boolean goToBoard() {
		return goToBoard.contains(Screen.CURSOR);
	}
	
	public ComponentList getList(){
		return componentList;
	}
	
	public int getActiveTowerIndex(){
		return activeTowerIndex;
	}

	public void closeList() {
		componentList = null;
		
		barrelCell = new ComponentCell(activeTower.getBarrel(), activeTower.getBarrel(), 20, 290, 680, 80);
		ammoCell = new ComponentCell(activeTower.getAmmo(), activeTower.getAmmo(), 20, 380, 680, 80);
		baseCell = new ComponentCell(activeTower.getBase(), activeTower.getBase(), 20, 470, 680, 80);
		
		activeTower.updateProperties();
	}

	public void clickComponent() {
		if(barrelCell.contains(Screen.CURSOR)) componentList = new ComponentList(activeTower.getBarrel(), this);
		else if(ammoCell.contains(Screen.CURSOR)) componentList = new ComponentList(activeTower.getAmmo(), this);
		else if(baseCell.contains(Screen.CURSOR)) componentList = new ComponentList(activeTower.getBase(), this);
	}

	public Tower getActiveTower() {
		return activeTower;
	}
}
