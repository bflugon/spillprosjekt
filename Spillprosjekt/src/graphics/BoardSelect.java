package graphics;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;

public class BoardSelect extends Rectangle{
	
	private BoardButton[] boardMenu;
	private MainMenu menu;

	
	public BoardSelect(MainMenu menu){
		this.menu = menu;
		
		String[] files = new File("./resources/maps").list();
		boardMenu = new BoardButton[files.length];

		for(int i = 0; i < boardMenu.length; i++){
			boardMenu[i] = new BoardButton(40 + 130*i,215, 120, 120, new File("resources/maps/"+files[i]));
		}
	}
	
	public void draw(Graphics g){
		for(int i = 0; i < boardMenu.length; i++){
			if(menu.getSelectedBoard() == i) boardMenu[i].draw(g, true);
			else boardMenu[i].draw(g, false);
		}
	}

	public void clickButton() {
		for(int i = 0; i < boardMenu.length; i++){
			if(boardMenu[i].contains(Screen.CURSOR)){
				menu.setSelectedBoard(i);
			}
		}
	}
}
