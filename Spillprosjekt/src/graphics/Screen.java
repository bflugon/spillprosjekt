package graphics;

import gui.BoardMouseListener;
import gui.MenuMouseListener;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

import backend.Colors;
import backend.Tilesets;

public class Screen extends JPanel implements Runnable {
	
//	Possisjonen til musen
	public static Point CURSOR;
	
//	Holder all grafikk som static-variabler
//	Maa initialiseres for aa lage tilesetene i konstruktoren, men kan senere reffereres til som "Tilesets"
	Tilesets ts = new Tilesets();
	
//	Threaden som kjorer gameloopen
	public Thread thread = new Thread(this);
	
//	Brettet som holder et grid med blokker
	private Board board;
	
//	Menyen der taarnene oppgraderes
	private Menu menu;
	
//	Tar vare på rammen saa vi kan legge lyttere paa den
	private JFrame frame;
	
//	Lyttere
	private BoardMouseListener boardMouseListener;
	private MenuMouseListener menuMouseListener;
//	Lavere gir raskere loop
	private int gameSpeed = 3;
	
//	Skal brettet eller menyen tegnes?
	private boolean inGame;
	
	
//	Starter threaden
	public Screen(JFrame frame) {
		this.frame = frame;

//		Setter storrelsen til skjermen lik storrelsen til rammen
		setSize(frame.getSize());
		
		board = new Board();
		menu = new Menu();
		
//		Legger til lyttere på rammen
		boardMouseListener = new BoardMouseListener(this);
		menuMouseListener = new MenuMouseListener(this);
		
		goToBoard();
		goToMenu();
		
//		Starter gameloopen
		thread.start();
	}
	
//	Tegner brettet og legger til lyttere
	public void goToBoard(){
		frame.removeMouseListener(menuMouseListener);
		frame.removeMouseMotionListener(menuMouseListener);
		
		frame.addMouseListener(boardMouseListener);
		frame.addMouseMotionListener(boardMouseListener);
		
		inGame = true;
	}
	
	public void goToMenu(){
		frame.removeMouseListener(boardMouseListener);
		frame.removeMouseMotionListener(boardMouseListener);
		
		frame.addMouseListener(menuMouseListener);
		frame.addMouseMotionListener(menuMouseListener);
		
		inGame = false;
	}
	
//	Kontrollerer alle bevegelser og timere
	private void physics(){
		if(inGame)board.physics();
	}
	
//	Tegner alt som skal tegnes
	protected void paintComponent(Graphics g) {
//		Nullstiller bildet
		g.setColor(Colors.background);
		g.fillRect(0, 0, getWidth(), getHeight());
		
//		Tegner brettet eller menyen
		if(inGame)board.draw(g);
		else menu.draw(g);
	}	

//	Kaller metodene som kontrollerer tegning og bevegelser
	public void run() {
		//Gameloop
		while(true) {
			repaint();
			physics();
			try {
				Thread.sleep(gameSpeed);
			} catch (Exception e) {}
		}
	}

	public void placeTower() {
		board.placeTower();
	}

	public void addFoundation() {
		board.addFoundation();
	}

	public void changeComponent() {
		menu.changeComponent();
	}

	public void changeActiveTower() {
		menu.changeActiveTower();
	}
}
