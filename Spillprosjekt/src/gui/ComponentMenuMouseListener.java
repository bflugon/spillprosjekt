package gui;

import graphics.Screen;
import graphics.componentMenu.ComponentMenu;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ComponentMenuMouseListener implements MouseListener, MouseMotionListener {
	private Screen screen;
	private ComponentMenu menu;
	
	public ComponentMenuMouseListener(Screen screen) {
		this.screen = screen;
		menu = screen.getStore();
	}

	public void mouseMoved(MouseEvent e) {
		Screen.CURSOR = new Point(e.getX(), e.getY());
	}

	public void mouseReleased(MouseEvent e) {
		if(!screen.inStore()) return;
		
		
//		Trykker paa listen hvis den er aapen
		if(menu.getList() != null){
			menu.getList().clicked();
		}
		else {
			menu.clickComponent();
			menu.changeActiveTower();
			menu.addTower();
		
			if(menu.goToBoard()){
				screen.goToBoard();
			}
		}
	}

	public void mouseDragged(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
}
