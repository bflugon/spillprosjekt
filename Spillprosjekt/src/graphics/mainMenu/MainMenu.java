package graphics.mainMenu;

import graphics.Screen;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;

import backend.Colors;
import backend.FileManager;
import backend.GameData;
import backend.Tilesets;

public class MainMenu extends Rectangle {

	private HowToPlay howToPlay;
	private Screen screen;
	private Rectangle[] buttons;
	
	private BoardList boardList = null;
	
	private boolean inCredits = false;
	
	private String[] buttonText = {"Start Game","Reset game", "How to play", "Exit"};
			
	public MainMenu(Screen screen){
		this.screen = screen;
		setBounds(screen.getBounds());
		
		howToPlay = new HowToPlay();
		
		buttons = new Rectangle[4];
		
		for(int i = 0; i < buttons.length; i++){
			buttons[i] = new Rectangle(40,295+75*i, 250,60);
		}
	}
	
	public void draw(Graphics g){
		
		g.drawImage(Tilesets.openingImage, x, y, width, height, null);

		g.setFont(GameData.header);
		for(int i = 0; i < buttons.length; i++) {
			if(buttons[i].contains(Screen.CURSOR))g.setColor(Colors.transparentRed);
			else g.setColor(Colors.transparentBlack);
			g.fillRect(buttons[i].x, buttons[i].y, buttons[i].width, buttons[i].height);
			g.setColor(Colors.red);
			g.drawString(buttonText[i], buttons[i].x+20, buttons[i].y+40);
		}
		
		g.setFont(GameData.largeHeader);
		g.drawString("<name here>", 40, 120);

		if(inCredits)howToPlay.draw(g);
		if(boardList != null)boardList.draw(g);
		
	}

	public void clickButton() {
		if(boardList == null){
			if(buttons[0].contains(Screen.CURSOR) && !inCredits) boardList = new BoardList(this);
			else if(buttons[1].contains(Screen.CURSOR) && !inCredits) screen.newGame();
			else if(buttons[2].contains(Screen.CURSOR) && !inCredits) inCredits = true;
			else if(buttons[3].contains(Screen.CURSOR) && !inCredits) System.exit(0);
			else if(!howToPlay.contains(Screen.CURSOR))inCredits = false;
		} else {
			boardList.clickedButton();
		}
	}
	
	public Screen getScreen(){
		return screen;
	}

	public BoardList getBoardList() {
		return boardList;
	}
	
	public void closeList(){
		boardList = null;
	}
}
