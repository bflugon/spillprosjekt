package graphics;

import gui.BoardKeyListener;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Screen extends JPanel implements Runnable {
	
//	Possisjonen til musen
	public static Point CURSOR;
	
//	Threaden som kjorer gameloopen
	public Thread thread = new Thread(this);
	
//	Brettet som holder et grid med blokker
	private Board board;
	
//	Tar vare p� rammen saa vi kan legge lyttere paa den
	private JFrame frame;
	
//	Lyttere
	private BoardKeyListener boardMouseListener;
	
//	Starter threaden
	public Screen(JFrame frame) {
		this.frame = frame;
		setSize(frame.getSize());
		
		board = new Board();
		
		boardMouseListener = new BoardKeyListener(this);
		frame.addMouseListener(boardMouseListener);
		frame.addMouseMotionListener(boardMouseListener);
		
		thread.start();
	}
	
//	Kontrollerer alle bevegelser og timere
	private void physics(){
		board.physics();
	}
	
//	Tegner alt som skal tegnes
	protected void paintComponent(Graphics g) {
//		Nullstiller bildet
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		
//		Tegner brettet
		board.draw(g);
	}	

//	Kaller metodene som kontrollerer tegning og bevegelser
	public void run() {
		while(true) {
			repaint();
			physics();
			try {
				Thread.sleep(3);
			} catch (Exception e) {}
		}
	}

	public void placeTower() {
		board.placeTower();
	}
}
