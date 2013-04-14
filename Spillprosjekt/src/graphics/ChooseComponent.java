package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import backend.GameData;

import components.Ammo;
import components.Barrel;
import components.Base;
import components.TowerComponent;

public class ChooseComponent extends PopupWindow {
	
	private TowerComponent activeComponent;
	private String current_type;
	private int activeTowerIndex;
	
	private ArrayList<Rectangle> componentButtons;
	private ArrayList<TowerComponent> components;
	private ComponentList selectedItem;
	
	public ChooseComponent(TowerComponent activeComponent,int activeTowerIndex){
		this.current_type = activeComponent.getType();
		this.activeComponent = activeComponent;
		this.activeTowerIndex = activeTowerIndex;
		
		componentButtons = new ArrayList<Rectangle>();
		components = new ArrayList<TowerComponent>();
		
	}
	
	
	public void draw(Graphics g) {
		super.draw(g);
		
		ComponentList selectedItem = new ComponentList(70, 80, activeComponent, true );
		componentButtons.add(new Rectangle(70,80 , 680, 50));
		components.add(activeComponent);
		selectedItem.draw(g);
		
		g.setFont(GameData.normal);
		g.setColor(Color.WHITE);
		

		g.drawString("Compatible " + current_type + "s",70,159);
		int teller = 0;
		for (TowerComponent towercomp : GameData.components_list) {
			if(towercomp.getType().equals(current_type) && !towercomp.getName().equals(activeComponent.getName()) ){
				
				int y = 70 * teller + 160;
				
				componentButtons.add(new Rectangle(70, y, 680, 50));
				ComponentList new_item = new ComponentList(70, y,towercomp, false );
				new_item.setcompareCompo(activeComponent);
				
				components.add(towercomp);
				new_item.draw(g);
				teller ++;
			}
		}
		
		
		
		
	}

	
	
	
	public boolean componentClicked() {
		for(int i = 0; i < componentButtons.size(); i++){
			if(componentButtons.get(i).contains(Screen.CURSOR)){
				
				if(current_type.equals("barrel")){
					Barrel new_barrel = (Barrel) components.get(i);
					GameData.modelTowers.get(activeTowerIndex).setBarrel(new_barrel);
				}
				if(current_type.equals("ammo")){
					Ammo new_barrel = (Ammo) components.get(i);
					GameData.modelTowers.get(activeTowerIndex).setAmmo(new_barrel);
				}
				if(current_type.equals("base")){
					Base new_barrel = (Base) components.get(i);
					GameData.modelTowers.get(activeTowerIndex).setBase(new_barrel);
				}
				
				GameData.modelTowers.get(activeTowerIndex).updateProperties();
				
				return true;
			}
		}
		return false;
	}
	
	
}
