package graphics;

import gui.BoardMouseListener;

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
	
//	Tar vare på rammen saa vi kan legge lyttere paa den
	private JFrame frame;
	
//	Lyttere
	private BoardMouseListener boardMouseListener;
	
//	Starter threaden
	public Screen(JFrame frame) {
		this.frame = frame;
		
//		Setter storrelsen til skjermen lik storrelsen til rammen
		setSize(frame.getSize());
		
		board = new Board();
		
//		Legger til lyttere på rammen
		boardMouseListener = new BoardMouseListener(this);
		frame.addMouseListener(boardMouseListener);
		frame.addMouseMotionListener(boardMouseListener);
		
//		Starter gameloopen
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
