package graphics.mainMenu;

import graphics.PopupWindow;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

import backend.GameData;

public class HowToPlay extends PopupWindow {
	public void draw(Graphics g){
		super.draw(g);
		
		int lineSpacing = 22;
		int offsetX = 170;
		int counter = 1;
		
		g.setColor(Color.WHITE);
		g.setFont(GameData.header);
		g.drawString("How to play", x+offsetX, y+50);
		
		g.setFont(GameData.small);
		g.drawString("You start the game by clicking START GAME and choosing a board.", x+offsetX, y+100);
		g.drawString("More boards will be unlocked as you improve your rank", x+offsetX, y+100+lineSpacing*counter++);
		
		g.drawString("In the component menu you can combine your own unique towers.", x+offsetX, y+120+lineSpacing*counter++);
		g.drawString("All towers consists of three main components: barrel, ammo and base.", x+offsetX, y+120+lineSpacing*counter++);
		g.drawString("As you improve your rank, more components will be avalible.", x+offsetX, y+120+lineSpacing*counter++);
		g.drawString("All components offer a bonus to damage, range or reload rate", x+offsetX, y+120+lineSpacing*counter++);
		g.drawString("Some components offers special abilities such as reducing enemy speed", x+offsetX, y+120+lineSpacing*counter++);
		g.drawString("Click + to add more towers", x+offsetX, y+120+lineSpacing*counter++);
		
		g.drawString("Click the green button to start a new wave of enemies", x+offsetX, y+140+lineSpacing*counter++);
		g.drawString("Place towers on the dark squares in strategic locations", x+offsetX, y+140+lineSpacing*counter++);
		g.drawString("You can convert a light square to a dark square for a small fee", x+offsetX, y+140+lineSpacing*counter++);
		g.drawString("The enemies will find the shortest possible path through the light squares", x+offsetX, y+140+lineSpacing*counter++);
		g.drawString("You will be earn money and xp by shooting enemies", x+offsetX, y+140+lineSpacing*counter++);
		g.drawString("On the top of your screen, you will find a green bar", x+offsetX, y+140+lineSpacing*counter++);
		g.drawString("When the bar reaches the top, you improve your rank", x+offsetX, y+140+lineSpacing*counter++);
		
		g.drawString("If an enemy gets through your defences, you will loose a life", x+offsetX, y+160+lineSpacing*counter++);
		g.drawString("When your lives reach zero, you will lose and be sendt back to the menu", x+offsetX, y+160+lineSpacing*counter++);
		
		g.drawString("All your game data will be stored when you close the application", x+offsetX, y+160+lineSpacing*counter++);

		
		g.drawImage(new ImageIcon("resources/textures/boardList.png").getImage(), x+20, y+90, 130, 108, null);
		g.drawImage(new ImageIcon("resources/textures/compMenu.png").getImage(), x+20, y+220, 130, 108, null);
		g.drawImage(new ImageIcon("resources/textures/board.png").getImage(), x+20, y+350, 130, 108, null);
	}
}
