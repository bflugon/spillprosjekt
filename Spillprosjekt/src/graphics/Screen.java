package graphics;

import gui.BoardMouseListener;
import gui.MenuMouseListener;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

import sound.Sound;

import backend.Colors;
import backend.Save;
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
	
//	Tar vare p� rammen saa vi kan legge lyttere paa den
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
		
		menu = new Menu();
		board = new Board();
		
//		Legger til lyttere p� rammen
		boardMouseListener = new BoardMouseListener(this);
		menuMouseListener = new MenuMouseListener(this);
		
		CURSOR = new Point(10, 10);
		
//		Kommenter ut den du ikke vil til
		frame.addMouseListener(boardMouseListener);
		frame.addMouseMotionListener(boardMouseListener);
		inGame = true;
				
//		Starter gameloopen
		thread.start();
	}
	
//	Tegner brettet og legger til lyttere
	public void goToBoard(){
		if(!menu.boardClicked()) return;
		menu.updateTower();
		board.updateButtons();
		
		frame.removeMouseListener(menuMouseListener);
		frame.removeMouseMotionListener(menuMouseListener);
		
		frame.addMouseListener(boardMouseListener);
		frame.addMouseMotionListener(boardMouseListener);
		
		
		inGame = true;
	}
	
	public void goToMenu(){
		if(!board.menuClicked()) return;
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
			try {
				repaint();
				physics();
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

	public void changeActiveTowerMenu() {
		menu.changeActiveTower();
	}

	public void changeActiveTowerBoard() {
		board.changeActiveTower();
	}

	public void nextWave() {
		board.nextWave();
	}

	public void addTower() {
		menu.addTower();
	}

	public Board getBoard() {
		return board;
	}
}
