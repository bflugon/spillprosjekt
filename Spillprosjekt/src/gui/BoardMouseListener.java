package gui;

import graphics.Board;
import graphics.Screen;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class BoardMouseListener implements MouseListener, MouseMotionListener {
	private Board board;
	private Screen screen;
	
	public BoardMouseListener(Screen screen){
		this.screen = screen;
		this.board = screen.getBoard();
	}
	
	public void mouseMoved(MouseEvent e) {
		Screen.CURSOR = new Point(e.getX(), e.getY());
	}
	public void mouseReleased(MouseEvent e) {
		if(!screen.inGame())return;
		board.placeTower();
		board.addFoundation();
		board.changeActiveTower();
		board.nextWave();
		board.mute();
		
		if(board.goToStore()){
			screen.goToStore();
		}
	}
	
	public void mouseDragged(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
}
