package graphics;

import graphics.componentMenu.ComponentMenu;
import graphics.mainMenu.MainMenu;
import gui.BoardMouseListener;
import gui.MainMenuMouseListener;
import gui.ComponentMenuMouseListener;

import java.awt.Graphics;
import java.awt.Point;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

import sound.BigSound;
import sound.Sound;

import backend.Colors;
import backend.FileManager;
import backend.GameData;
import backend.Tilesets;

public class Screen extends JPanel implements Runnable {
	
	public static Point CURSOR;
	
	Tilesets ts = new Tilesets();
	
	public Thread thread = new Thread(this);
	
	private Board board;
	private ComponentMenu menu;
	private MainMenu mainMenu;
	
	private JFrame frame;
	
	private BoardMouseListener boardMouseListener;
	private ComponentMenuMouseListener menuMouseListener;
	private MainMenuMouseListener mainMenuMouseListener;

	private int gameSpeed = 4;
	
	private boolean inGame,
					inStore;
	
	
	public Screen(JFrame frame) {

		setSize(frame.getSize());
		
//		Laster inn meny-penger og rank fra tidligere;
		FileManager.loadGameData();
		
		menu = new ComponentMenu();
		board = new Board(this);
		mainMenu = new MainMenu(this);
		
		boardMouseListener = new BoardMouseListener(this);
		menuMouseListener = new ComponentMenuMouseListener(this);
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

		thread.start();
	}
	
	public void goToBoard(){		
		board.updateButtons();
		
		inStore = false;
		inGame = true;
	}
	
	public void newBoard(int mapNum){		
		board.createBoard(mapNum);
		
		goToStore();
	}
	
	public void goToStore(){		
		inGame = false;
		inStore = true;
	}
	
	public void goToMainMenu(){
		inGame = false;
		inStore = false;
		FileManager.saveGameData();
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
				Thread.sleep(gameSpeed);
				physics();
			} catch (Exception e) {}
		}
	}

	public void changeActiveTowerMenu() {
		menu.changeActiveTower();
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

	public ComponentMenu getStore() {
		return menu;
	}

	public MainMenu getMainMenu() {
		return mainMenu;
	}

	public boolean inMenu() {
		return !inGame && !inStore;
	}
}
