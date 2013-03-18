package graphics;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Screen extends JPanel implements Runnable {
	
//	Threaden som kjorer gameloopen
	public Thread thread = new Thread(this);
	
//	Brettet som holder et grid med blokker
	private Board board;
	
//	Tar vare på rammen saa vi kan legge lyttere paa den
	private JFrame frame;
	
//	Starter threaden
	public Screen(JFrame frame) {
		this.frame = frame;
		setSize(frame.getSize());
		
		thread.start();
	}
	
//	Kontrollerer alle bevegelser og timere
	private void physics(){
//		board.physics();
	}
	
//	Tegner alt som skal tegnes
	protected void paintComponent(Graphics g) {
//		Nullstiller bildet
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		
//		Tegner brettet
//		board.draw(g);
	}	

//	Kaller metodene som kontrollerer tegning og bevegelser
	public void run() {
		while(true) {
			repaint();
			physics();
			System.out.println("Hei");
			try {
				Thread.sleep(3);
			} catch (Exception e) {}
		}
	}
}
