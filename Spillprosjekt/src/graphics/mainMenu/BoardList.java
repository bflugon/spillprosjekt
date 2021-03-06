package graphics.mainMenu;

import graphics.PopupWindow;
import graphics.Screen;

import java.awt.Graphics;
import java.io.File;

import backend.GameData;

public class BoardList extends PopupWindow {

	private BoardButton[] boards;
	private Screen screen;
	private MainMenu menu;
	public BoardList(MainMenu menu){
		this.menu = menu;
		this.screen = menu.getScreen();
		
		String[] files = new File("./resources/maps").list();
		boards = new BoardButton[files.length];

		for(int i = 0; i < boards.length; i++){
			boards[i] = new BoardButton(i,x+20 + 130*(i%5),y+20 + (130*(int)(i/5)), 120, 120, new File("resources/maps/"+i+".map"));
		}
	}
	
	public void draw(Graphics g){
		super.draw(g);
		
		for(BoardButton button : boards){
			button.draw(g);
		}
	}
	
	public void clickedButton(){
		for(int i = 0; i < boards.length; i++){
			if(boards[i].contains(Screen.CURSOR)) {
				if(GameData.rank >= i/2){
					screen.newBoard(i);
					menu.closeList();
				}
			}
		}
		
		if(!contains(Screen.CURSOR)){
			menu.closeList();
		}
	}
	
}
