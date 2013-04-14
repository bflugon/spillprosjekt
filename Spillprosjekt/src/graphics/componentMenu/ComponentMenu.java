package graphics.componentMenu;

import graphics.Screen;
import graphics.Tower;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

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
	
	public ComponentMenu(){
		
		modelTowers = GameData.modelTowers;
		modelTowers.add(new Tower());
		
		towerButtons = new ArrayList<Rectangle>();
		for(int i = 0; i < modelTowers.size(); i++){
			towerButtons.add(new Rectangle(720, 20+90*i, 80, 80));
		}
		
		newTower = new Rectangle(720, 20+90*towerButtons.size(), 80, 80);
		goToBoard = new Rectangle(720,560,80,80);
		
		activeTower = modelTowers.get(activeTowerIndex);
		
		componentList = new ComponentList(activeTower.getBarrel());
	}

	public void draw(Graphics g) {
		
		/*************************
		 *** Taarn og knapper ****
		 *************************/
		g.setColor(Colors.transparentBlack);
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

		
//		Liste, hvis det trengs
		if(componentList != null)componentList.draw(g);
		
	}

	public void changeActiveTower() {
		for(int i = 0; i < towerButtons.size(); i++){
			if(towerButtons.get(i).contains(Screen.CURSOR)){
				updateTower();
				activeTower = modelTowers.get(i);
				activeTowerIndex = i;
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
}
