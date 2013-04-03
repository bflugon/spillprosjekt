package gui;

import graphics.Screen;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MenuMouseListener implements MouseListener, MouseMotionListener {
	private Screen screen;

	public MenuMouseListener(Screen screen) {
		this.screen = screen;
	}

	public void mouseMoved(MouseEvent e) {
		Screen.CURSOR = new Point(e.getX(), e.getY() - 25);
	}

	public void mouseReleased(MouseEvent e) {
		screen.changeComponent();
		screen.changeActiveTowerMenu();
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
		screen.goToBoard();
	}

	public void mousePressed(MouseEvent e) {
	}
}
