package graphics;

import gui.BoardMouseListener;
import gui.MenuMouseListener;

import java.awt.Graphics;
import java.awt.Point;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

import sound.BigSound;
import sound.Sound;

import backend.Colors;
import backend.GameData;
import backend.Tilesets;

public class Screen extends JPanel implements Runnable {
	
	public static Point CURSOR;
	
	Tilesets ts = new Tilesets();
	
	public Thread thread = new Thread(this);
	
	private Board board;
	
	private Menu menu;
	
	private JFrame frame;
	
	private BoardMouseListener boardMouseListener;
	private MenuMouseListener menuMouseListener;

	private int gameSpeed = 4;
	
	private boolean inGame;
	
	
	public Screen(JFrame frame) {
		this.frame = frame;

		setSize(frame.getSize());
		
		menu = new Menu();
		board = new Board();
		
		boardMouseListener = new BoardMouseListener(this);
		menuMouseListener = new MenuMouseListener(this);
		
		CURSOR = new Point(10, 10);
		
		frame.addMouseListener(boardMouseListener);
		frame.addMouseMotionListener(boardMouseListener);
		inGame = true;
				
		try {
			new BigSound(new File("resources/sounds/backgroundMusic.wav")).start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		thread.start();
	}
	
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
		if(GameData.muted) GameData.muted = false;
		else GameData.muted = true;
		System.out.println(GameData.muted);
		
		if(!board.menuClicked()) return;
		frame.removeMouseListener(boardMouseListener);
		frame.removeMouseMotionListener(boardMouseListener);
		
		frame.addMouseListener(menuMouseListener);
		frame.addMouseMotionListener(menuMouseListener);
		
		inGame = false;
	}
	
	private void physics(){
		if(inGame)board.physics();
	}
	
	protected void paintComponent(Graphics g) {
		g.setColor(Colors.background);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		if(inGame)board.draw(g);
		else menu.draw(g);
	}	

	public void run() {
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
