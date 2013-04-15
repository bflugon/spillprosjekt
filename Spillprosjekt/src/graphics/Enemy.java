package graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import sound.Sound;

import backend.GameData;
import backend.Tilesets;

public class Enemy extends Rectangle{
	
	public final int	upward = 0, 
						downward = 1, 
						right = 2,
						left = 3;
	
	private int	xc = 0,
				yc = 0, 
				mobWalk = 0, 
				mobSize = 50,
				direction = downward,
				distanceTraveled = 0,
				walkSpeed,
				value;
	
	private double lives;
	
	private boolean inGame,
					slowed;
				
	private Block currentBlock;

	private Board board;
	
	public Enemy(Board board){
		this.board =board;
	}
	
//	Spawner fiende naar timeren kaller metoden fra board
	public void spawnEnemy(int lives, int walkSpeed, Block currentBlock) {
		this.lives = lives;
		value = (int) 1;
		
		setCurrentBlock(currentBlock);
	
		this.walkSpeed = walkSpeed;
		distanceTraveled = 0;
		
		setBounds(currentBlock.x, currentBlock.y, currentBlock.width, currentBlock.height);

		this.inGame = true;
		
		//Start moving in the right direction
		if(currentBlock.getPrev().getIndY() > yc) {
			direction = downward;
		} else if(currentBlock.getPrev().getIndY() < yc) {
			direction = upward;
		} else if(currentBlock.getPrev().getIndX() > xc) {
			direction = right;
		} else if (currentBlock.getPrev().getIndX() < xc) {
			direction = left;
		}
	}
	
//	reduserer livene naar den blir skutt
	public void setLives(double damage) {
		lives -= damage;
		GameData.money += (int)damage;

		if (lives <= 0) {
			inGame = false;
			mobWalk = 0;
			Sound.playSound("explosion.wav");
			GameData.money += value;
		}
	}
	
//	Sett current og flytt objektet paa skjermen
	private void setCurrentBlock(Block currentBlock) {
		this.currentBlock = currentBlock;
		xc = currentBlock.getIndX();
		yc = currentBlock.getIndY();
		x = (int) currentBlock.getX();
		y = (int) currentBlock.getY();
	}
	
//	Er fienden spawnet
	public boolean inGame(){
		return inGame;
	}

//	Avstanden de har reist blir lagret for aa kunne skyte paa den fienden som ligger forst
	public int getDistanceTraveled() {
		return distanceTraveled;
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
//		AffineTransform old = new AffineTransform();
//		AffineTransform trans = new AffineTransform();
//
//		switch (direction){
//		case upward:
//			trans.rotate(-3.14/2, x+30, y+30);
//			break;
//		case left:
//			trans.rotate(3.14, x+30, y+30);
//			break;
//		case downward:
//			trans.rotate(3.14/2, x+30, y+30);
//			break;
//		}
//		
//		g2d.setTransform(trans);
		
		g2d.drawImage(Tilesets.enemy_tileset[GameData.spaceInvader], x, y, width, height, null);

//		g2d.setTransform(old);
	}
	
	public int walkFrame = 0;
	public void physics() {
		if(walkFrame >= walkSpeed) {
			switch (direction) {
			case upward:
				y--;
				break;
			case right:
				x++;
				break;
			case downward:
				y++;
				break;
			case left:
				x--;
			default:
				break;
			}
			
			mobWalk ++;
			
			if(mobWalk == board.getBlockSize()) {
				if(currentBlock.getPrev() == board.getGoal()) {
					inGame = false;
					GameData.lives -= lives;
					mobWalk = 0;
				} else{
					
					switch (direction) {
					case upward:
						yc--;
						break;
					case right:
						xc++;
						break;
					case downward:
						yc++;
						break;
					case left:
						xc--;
						break;
					default:
						break;
					}
					distanceTraveled++;
					currentBlock = board.getGrid()[yc][xc];
					try{
						if(currentBlock.getPrev().getIndY() > yc) {
							direction = downward;
						} else if(currentBlock.getPrev().getIndY() < yc) {
							direction = upward;
						} else if(currentBlock.getPrev().getIndX() > xc) {
							direction = right;
						} else if (currentBlock.getPrev().getIndX() < xc) {
							direction = left;
						}
					}catch (Exception e) {
						inGame = false;
					}
										
					mobWalk = 0;
				}
			}
			walkFrame = 0;
		} else {
			walkFrame++;
		}
		
	}
	
}
