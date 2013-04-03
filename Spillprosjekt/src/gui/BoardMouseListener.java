package gui;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import graphics.Screen;

public class BoardMouseListener implements MouseListener, MouseMotionListener {
	private Screen screen;

	public BoardMouseListener(Screen screen){
		this.screen = screen;
	}
	
	public void mouseMoved(MouseEvent e) {
		Screen.CURSOR = new Point(e.getX(), e.getY()-25);
	}
	public void mouseReleased(MouseEvent e) {
		screen.placeTower();
		screen.addFoundation();
		screen.changeActiveTowerBoard();
	}
	
	public void mouseDragged(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {
		screen.goToMenu();
	}
	public void mousePressed(MouseEvent e) {}
}
