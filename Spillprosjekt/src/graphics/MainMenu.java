package graphics;

import java.awt.Graphics;
import java.awt.Rectangle;

import backend.Colors;
import backend.GameData;
import backend.Tilesets;

public class MainMenu extends Rectangle {

	private Screen screen;
	
	private Rectangle[] buttons;
	
	private String[] buttonText = {"Start Game", "Credits", "Quit"};
			
	public MainMenu(Screen screen){
		this.screen = screen;
		setBounds(screen.getBounds());
		
		buttons = new Rectangle[3];
		
		for(int i = 0; i < buttons.length; i++){
			buttons[i] = new Rectangle(20,370+75*i, 250,60);
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
		
	}

	public boolean startGame() {
		return buttons[0].contains(Screen.CURSOR);
	}
	
}
