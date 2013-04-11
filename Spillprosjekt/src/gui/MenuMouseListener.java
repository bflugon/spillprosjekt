package gui;

import graphics.Menu;
import graphics.Screen;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MenuMouseListener implements MouseListener, MouseMotionListener {
	private Screen screen;
	private Menu menu;
	
	public MenuMouseListener(Screen screen) {
		this.screen = screen;
		menu = screen.getStore();
	}

	public void mouseMoved(MouseEvent e) {
		Screen.CURSOR = new Point(e.getX(), e.getY());
	}

	public void mouseReleased(MouseEvent e) {
		if(!screen.inStore()) return;
		menu.changeActiveTower();
		menu.addTower();
		if(menu.goToBoard()){
			screen.goToBoard();
		}
	}

	public void mouseDragged(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
}
