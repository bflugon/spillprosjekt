package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import backend.Colors;
import backend.GameData;

public class UpgradeMenu extends Rectangle{

	private Tower tower;
	private Board board;
	
	private int level,
				cost,
				value;
	private double multiplier;
	
	private Rectangle 	upgradeButton,
						sellButton;
	
	public UpgradeMenu(Tower tower, Board board){
		this.tower = tower;
		this.board = board;
		setBounds((int)tower.getX()-15,(int)tower.getY()-25,95,30);
		multiplier = tower.getMultiplier();
		
		level = (int) Math.round((tower.getMultiplier()-1)*5+1);
		
		upgradeButton = new Rectangle(x+5,y+5,40,20);
		sellButton = new Rectangle(x+50,y+5, 40,20);
		
		cost = (int)((tower.getPrice()/2)*tower.getMultiplier());
		value = (int)(tower.getPrice()/2 + (tower.getPrice()/3)*tower.getMultiplier());
	}
	
	public void draw(Graphics g){
		g.setColor(Colors.popupTransparentBlack);
		g.fillRect(x, y, width, height);

		g.setColor(Colors.green);
		g.fillRect(upgradeButton.x,upgradeButton.y,upgradeButton.width,upgradeButton.height);
		g.setColor(Color.WHITE);
		if(tower.getMultiplier() < 1.4){
			g.drawString("$"+cost,upgradeButton.x+3, upgradeButton.y+15);
		}else g.drawString("Full", upgradeButton.x+3, upgradeButton.y+15);
		
		g.setColor(Colors.red);
		g.fillRect(sellButton.x,sellButton.y,sellButton.width,sellButton.height);
		g.setColor(Color.WHITE);
		g.drawString("$"+value,sellButton.x+3, sellButton.y+15);
		
		
		
		for(int i = 0; i < level; i++){
			g.drawImage(new ImageIcon("resources/textures/star.png").getImage(), x+15 + 20*i, y+65 + (10*(i%2)), 20, 20, null);
		}
	}
	
	public void clickButton(){
		if(upgradeButton.contains(Screen.CURSOR)){
			if(tower.getMultiplier() < 1.4 && cost <= board.getMoney()){
				tower.upgrade();
				board.addMoney(-cost);
				cost = (int)((tower.getPrice()/2)*tower.getMultiplier());
				value = (int)(tower.getPrice()/2 + (tower.getPrice()/3)*tower.getMultiplier());
				level++;	
			}
		}else if(sellButton.contains(Screen.CURSOR)){
			board.sellTower(tower);
			board.closeUpgradeMenu();
			board.addMoney(value);
		}else if(!contains(Screen.CURSOR)) board.closeUpgradeMenu();
	}
	
	
	
		
}
