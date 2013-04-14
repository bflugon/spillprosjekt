package graphics.componentMenu;

import graphics.PopupWindow;

import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;

import components.Ammo;
import components.Barrel;
import components.Base;
import components.TowerComponent;

import backend.GameData;

public class ComponentList extends PopupWindow{

	private ArrayList<ComponentListCell> list = new ArrayList<ComponentListCell>();
	
	public ComponentList(TowerComponent currComponent){

		int counter = 0;
		if(currComponent instanceof Barrel){
			for(TowerComponent tc : GameData.barrels){
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
	}
	
	public void draw(Graphics g){
		super.draw(g);
		for(ComponentListCell cell : list){
			cell.draw(g);
		}
	}
}
