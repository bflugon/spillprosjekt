package gui;

import graphics.BoardButton;
import graphics.MainMenu;
import graphics.Screen;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MainMenuMouseListener implements MouseListener, MouseMotionListener {

	private MainMenu menu;
	private Screen screen;
	
	public MainMenuMouseListener(Screen screen){
		this.screen = screen;
		menu = screen.getMainMenu();
	}
	
	public void mouseReleased(MouseEvent e) {
		if(!screen.inMenu()) return;
		
		menu.clickButton();
	}

	public void mouseMoved(MouseEvent e) {
		Screen.CURSOR = new Point(e.getX(), e.getY());
	}
	
	public void mouseDragged(MouseEvent arg0) {}
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}


}
