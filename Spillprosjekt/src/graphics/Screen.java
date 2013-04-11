package graphics;

import gui.BoardMouseListener;
import gui.MainMenuMouseListener;
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
	private MainMenu mainMenu;
	
	private JFrame frame;
	
	private BoardMouseListener boardMouseListener;
	private MenuMouseListener menuMouseListener;
	private MainMenuMouseListener mainMenuMouseListener;

	private int gameSpeed = 4;
	
	private boolean inGame,
					inStore;
	
	
	public Screen(JFrame frame) {

		setSize(frame.getSize());
		
		menu = new Menu();
		board = new Board();
		mainMenu = new MainMenu(this);
		
		boardMouseListener = new BoardMouseListener(this);
		menuMouseListener = new MenuMouseListener(this);
		mainMenuMouseListener = new MainMenuMouseListener(this);
		
		addMouseListener(boardMouseListener);
		addMouseListener(menuMouseListener);
		addMouseListener(mainMenuMouseListener);
		addMouseMotionListener(mainMenuMouseListener);
		addMouseMotionListener(boardMouseListener);
		addMouseMotionListener(menuMouseListener);
		
		CURSOR = new Point(10, 10);
		
		inGame = false;
		inStore = false;
				
		try {
			new BigSound(new File("resources/sounds/"+GameData.songs[GameData.songNum])).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		GameData.muted = true;
		thread.start();
	}
	
	public void goToBoard(){		
		menu.updateTower();
		board.updateButtons();
		
		inStore = false;
		inGame = true;
	}
	
	public void goToBoard(int mapNum){		
		board.createBoard(mapNum);
		
		inStore = false;
		inGame = true;
	}
	
	public void goToStore(){		
		inGame = false;
		inStore = true;
	}
	
	private void physics(){
		if(inGame)board.physics();
	}
	
	protected void paintComponent(Graphics g) {
		g.setColor(Colors.background);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		
		if(inGame)board.draw(g);
		else if(inStore)menu.draw(g);
		else mainMenu.draw(g);
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

	public void changeComponent() {
		menu.changeComponent();
	}

	public void changeActiveTowerMenu() {
		menu.changeActiveTower();
	}

	public void addTower() {
		menu.addTower();
	}

	public Board getBoard() {
		return board;
	}

	public boolean inGame() {
		return inGame;
	}
	
	public boolean inStore() {
		return inStore;
	}

	public Menu getStore() {
		return menu;
	}

	public MainMenu getMainMenu() {
		return mainMenu;
	}

	public boolean inMenu() {
		return !inGame && !inStore;
	}
}
