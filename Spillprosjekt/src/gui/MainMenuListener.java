package gui;

import graphics.Screen;
import graphics.mainMenu.MainMenu;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MainMenuListener implements MouseListener, MouseMotionListener, KeyListener {

	private MainMenu menu;
	private Screen screen;
	
	public MainMenuListener(Screen screen){
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

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyChar() == 'q'){
			screen.goToMainMenu();
		}
	}

	public void keyTyped(KeyEvent e) {
	}


}
