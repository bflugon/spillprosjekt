package gui;

import editor.Editor;
import graphics.Block;
import graphics.Screen;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.SwingUtilities;

public class EditorMouseListener implements MouseListener, MouseMotionListener {

	int id = -1;
	Editor editor = new Editor();
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Screen.CURSOR = new Point(e.getX(), e.getY()-25);		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Object source = e.getSource();
		if (SwingUtilities.isRightMouseButton(e)) {
			editor.setID((Block) source);
		}
		else if (SwingUtilities.isLeftMouseButton(e)) {
			editor.setBlock((Block) source);
		}
	}
}
