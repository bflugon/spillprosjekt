package graphics;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;

import backend.Colors;
import backend.GameData;
import backend.Tilesets;

public class MainMenu extends Rectangle {

	private Screen screen;
	private Rectangle[] buttons;
	
	private BoardButton[] boards;
	
	private int selectedBoard = 0;
	
	private String[] buttonText = {"Start Game","Credits", "Exit"};
			
	public MainMenu(Screen screen){
		this.screen = screen;
		setBounds(screen.getBounds());
		
		buttons = new Rectangle[3];
		
		for(int i = 0; i < buttons.length; i++){
			buttons[i] = new Rectangle(40,370+75*i, 250,60);
		}
		
		String[] files = new File("./resources/maps").list();
		boards = new BoardButton[files.length];

		for(int i = 0; i < boards.length; i++){
			boards[i] = new BoardButton(40 + 130*i,225, 120, 120, new File("resources/maps/"+files[i]));
		}
	}
	
	public void draw(Graphics g){
		
		g.drawImage(Tilesets.openingImage, x, y, width, height, null);

		g.setFont(GameData.header);
		for(int i = 0; i < buttons.length; i++) {
			g.setColor(Colors.button);
			g.fillRect(buttons[i].x, buttons[i].y, buttons[i].width, buttons[i].height);
			g.setColor(Colors.pink);
			g.drawString(buttonText[i], buttons[i].x+30, buttons[i].y+40);
		}
		
		for(int i = 0; i < boards.length; i++){
			if(selectedBoard == i) boards[i].draw(g, true);
			else boards[i].draw(g, false);
		}
	}

	public void clickButton() {
		if(buttons[0].contains(Screen.CURSOR)) screen.goToBoard(selectedBoard);
//		else if(buttons[1].contains(Screen.CURSOR)) noe;
		else if(buttons[2].contains(Screen.CURSOR)) System.exit(0);

		for(int i = 0; i < boards.length; i++){
			if(boards[i].contains(Screen.CURSOR)) selectedBoard = i;
		}
	}
	
	public int getSelectedBoard() {
		return selectedBoard;
	}
	
	public void setSelectedBoard(int i) {
		selectedBoard = i;
	}
	
}
