package graphics;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import components.Barrel;

import backend.Colors;
import backend.GameData;

public class Menu {

	private ComponentMenu[] compMenus;
	
//	Taarnene som kan oppgraderes
	private ArrayList<Tower> modelTowers;
	
//	The tower beeing updated
	private Tower activeTower;
	
//	Knappene som representerer taarnene
	private ArrayList<Rectangle> towerButtons;
	private String[] compType = {"barrel", "ammo", "base"};
	
	public Menu(){
		compMenus = new ComponentMenu[3];
		for(int i = 0; i < compMenus.length; i++){
			compMenus[i] = new ComponentMenu(200, 20+210*i, 600, 200, compType[i]);
		}
		
		modelTowers = GameData.modelTowers;
		towerButtons = new ArrayList<Rectangle>();
		for(int i = 0; i < modelTowers.size(); i++){
			towerButtons.add(new Rectangle(20, 20+130*i, 100, 100));
		}
		
		modelTowers.add(new Tower());
		activeTower = modelTowers.get(0);
	}

	public void draw(Graphics g) {
		for (ComponentMenu compMenu : compMenus) {
			compMenu.draw(g);
		}
		
		g.setColor(Colors.range);
		for(Rectangle towerButton: towerButtons){
			g.fillRect(towerButton.x, towerButton.y, towerButton.width, towerButton.height);
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
				activeTower.updateComponents((Barrel)compMenus[0].getCurrentComponent());
				activeTower = modelTowers.get(i);
				
				compMenus[0].updateComponent(activeTower.getBarrel());
			}
		}
	}
	
}
