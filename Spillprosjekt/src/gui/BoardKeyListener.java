package gui;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import graphics.Screen;

public class BoardKeyListener implements MouseListener, MouseMotionListener {
	private Screen screen;

	public BoardKeyListener(Screen screen){
		this.screen = screen;
	}
	
	public void mouseMoved(MouseEvent e) {
		Screen.CURSOR = new Point(e.getX(), e.getY()-25);
	}
	public void mouseReleased(MouseEvent e) {
		screen.placeTower();
	}
	
	public void mouseDragged(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
}
