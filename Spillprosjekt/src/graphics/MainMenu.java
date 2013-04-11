package graphics;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import backend.Colors;
import backend.GameData;
import backend.Tilesets;

public class MainMenu extends Rectangle {

	private Screen screen;
	private BoardSelect boardSelect;
	private Rectangle[] buttons;
	
	private int selectedBoard = 0;
	
	private String[] buttonText = {"Start Game", "Credits", "Exit"};
			
	public MainMenu(Screen screen){
		this.screen = screen;
		setBounds(screen.getBounds());
		
		boardSelect = new BoardSelect(this);
		buttons = new Rectangle[3];
		
		for(int i = 0; i < buttons.length; i++){
			buttons[i] = new Rectangle(40,370+75*i, 250,60);
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
		
		boardSelect.draw(g);
	}

	public void clickButton() {
		if(buttons[0].contains(Screen.CURSOR)) screen.goToBoard(selectedBoard);
//		else if(buttons[1].contains(Screen.CURSOR)) noe;
		else if(buttons[2].contains(Screen.CURSOR)) System.exit(0);
	}

	public int getSelectedBoard() {
		return selectedBoard;
	}
	
	public BoardSelect getBoardSelect(){
		return boardSelect;
	}

	public void setSelectedBoard(int i) {
		selectedBoard = i;
	}
	
}
