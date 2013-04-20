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
		
		boards = new BoardButton[GameData.rank+8];

		for(int i = 0; i < boards.length; i++){
			boards[i] = new BoardButton(x+20 + 130*(i%5),y+20 + (130*(int)(i/5)), 120, 120, new File("resources/maps/"+i+".map"));
			if(i/2 == GameData.rank+8) break;
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
				screen.newBoard(i);
				menu.closeList();
			}
		}
		
		if(!contains(Screen.CURSOR)){
			menu.closeList();
		}
	}
	
}
