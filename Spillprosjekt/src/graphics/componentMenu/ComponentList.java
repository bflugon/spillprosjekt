package graphics.componentMenu;

import graphics.PopupWindow;
import graphics.Screen;
import graphics.Tower;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;

import components.Ammo;
import components.Barrel;
import components.Base;
import components.Inventory;
import components.TowerComponent;

import backend.Colors;
import backend.GameData;
import backend.Tilesets;

public class ComponentList extends PopupWindow{

	private ArrayList<ComponentListCell> list;
	
	private ComponentMenu menu;
	private TowerComponent currComponent;
	
	private Rectangle 	next, 
						previous;
	
	private int firstIndex = 0;
	
	public ComponentList(TowerComponent currComponent, ComponentMenu menu){
		this.currComponent = currComponent;
		this.menu = menu;
		
		previous = new Rectangle(20,290,40,80);
		next = new Rectangle(760,290,40,80);
		
		updateList(0);
	}
	
	private void nextPage(){
		updateList(7);
	}
	
	private void previousPage(){
		updateList(-7);
	}
	
 	public void updateList(int change){
		list = new ArrayList<ComponentListCell>();
		
		int posMultiplier = 0;
		int counter = firstIndex;
		
		
		if(currComponent instanceof Barrel){
			if(firstIndex + (change+change/7) < GameData.barrels.size()) firstIndex +=  (change+change/7);
			if(firstIndex < 0) firstIndex = 0;
			counter = firstIndex;

			while(posMultiplier < 7 && counter < GameData.barrels.size() && firstIndex+posMultiplier < GameData.barrels.size()){	
				TowerComponent barrel = GameData.barrels.get(counter);
				if(barrel != currComponent){
					list.add(new ComponentListCell(barrel, currComponent, x, y+80*posMultiplier, width, 80));
					posMultiplier++;
				}
				counter++;
			}
		} else if(currComponent instanceof Ammo){
			if(firstIndex + (change+change/7) < GameData.ammo.size()) firstIndex +=  (change+change/7);
			if(firstIndex < 0) firstIndex = 0;
			counter = firstIndex;
			
			while(posMultiplier < 7 && counter < GameData.ammo.size() && firstIndex+posMultiplier < GameData.ammo.size()){
				Ammo ammo = (Ammo) GameData.ammo.get(counter);
				String ammoType = menu.getActiveTower().getBarrel().getAmmoType();
				if(ammo.getAmmoType() == ammoType && ammo != currComponent){
					list.add(new ComponentListCell(ammo, currComponent, x, y+80*posMultiplier, width, 80));
					posMultiplier++;
				}
				counter++;
			}
		} else if(currComponent instanceof Base){
			if(firstIndex + (change+change/7) < GameData.bases.size()) firstIndex +=  (change+change/7);
			if(firstIndex < 0) firstIndex = 0;
			counter = firstIndex;
			
			while(posMultiplier < 7 && counter < GameData.bases.size() && firstIndex+posMultiplier < GameData.bases.size()){
				TowerComponent base = GameData.bases.get(counter);
					if(base != currComponent){
						list.add(new ComponentListCell(base, currComponent, x, y+80*posMultiplier, width, 80));
						posMultiplier++;
					}
				counter++;
			}
		}
	}
	
	public void draw(Graphics g){
		super.draw(g);
		for(ComponentListCell cell : list){
			cell.draw(g);
		}
		g.setColor(Colors.red);
		g.fillRect(next.x, next.y, next.width, next.height);
		g.fillRect(previous.x, previous.y, previous.width, previous.height);
		g.drawImage(Tilesets.button_tileset[GameData.next], next.x, next.y, next.width, next.height, null);
		g.drawImage(Tilesets.button_tileset[GameData.previous], previous.x, previous.y, previous.width, previous.height, null);
	}

	public void clicked() {
		for(ComponentListCell cell : list){
			if(cell.contains(Screen.CURSOR)){
				Tower tower = GameData.modelTowers.get(menu.getActiveTowerIndex());
				if(cell.getComponent().getRankLimit() > GameData.rank)return;
				if(currComponent instanceof Barrel){
					Barrel barrel = (Barrel) cell.getComponent();
					tower.setBarrel(barrel);

//					Bytter til tilsvarende ammo
					for(Ammo ammo : GameData.ammo){
						if(ammo.getName().equals("Basic "+barrel.getAmmoType())) tower.setAmmo(ammo);
					}
					
				}
				else if(currComponent instanceof Ammo)tower.setAmmo((Ammo)cell.getComponent());
				else if(currComponent instanceof Base) tower.setBase((Base)cell.getComponent());
				
				menu.closeList();
				break;
			}
		}
		
		if(next.contains(Screen.CURSOR)) nextPage();
		else if(previous.contains(Screen.CURSOR)) previousPage();
		else if(!contains(Screen.CURSOR)) menu.closeList();
	}
}
