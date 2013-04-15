package graphics.componentMenu;

import graphics.PopupWindow;
import graphics.Screen;
import graphics.Tower;

import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;

import components.Ammo;
import components.Barrel;
import components.Base;
import components.TowerComponent;

import backend.GameData;
import backend.Tilesets;

public class ComponentList extends PopupWindow{

	private ArrayList<ComponentListCell> list = new ArrayList<ComponentListCell>();
	
	private ComponentMenu menu;
	private TowerComponent currComponent;
	
	public ComponentList(TowerComponent currComponent, ComponentMenu menu){

		int counter = 0;
		if(currComponent instanceof Barrel){
			for(TowerComponent tc : GameData.barrels){
				if(tc != currComponent)
				list.add(new ComponentListCell(tc, currComponent, x, y+80*counter++, width, 80));
			}
		} else if(currComponent instanceof Ammo){
			for(TowerComponent tc : GameData.ammo){
				list.add(new ComponentListCell(tc, currComponent, x, y+80*counter++, width, 80));
			}
		} else if(currComponent instanceof Base){
			for(TowerComponent tc : GameData.bases){
				list.add(new ComponentListCell(tc, currComponent, x, y+80*counter++, width, 80));
			}
		}
		
		this.currComponent = currComponent;
		this.menu = menu;
	}
	
	public void draw(Graphics g){
		super.draw(g);
		for(ComponentListCell cell : list){
			cell.draw(g);
		}
	}

	public void clicked() {
		for(ComponentListCell cell : list){
			if(cell.contains(Screen.CURSOR)){
				Tower tower = GameData.modelTowers.get(menu.getActiveTowerIndex());
				
				if(currComponent instanceof Barrel)tower.setBarrel((Barrel)cell.getComponent());
				else if(currComponent instanceof Ammo)tower.setAmmo((Ammo)cell.getComponent());
				else if(currComponent instanceof Base) tower.setBase((Base)cell.getComponent());
				
			}
		}
		menu.closeList();
	}
}
