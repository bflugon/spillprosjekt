package graphics;

import java.awt.Color;
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
				value,
				enemyID;
	
	private double lives;
	
	private boolean inGame,
					slowed;
				
	private Block currentBlock;

	private Board board;
	
	public Enemy(Board board){
		this.board =board;
	}
	
//	Spawner fiende naar timeren kaller metoden fra board
	public void spawnEnemy(int lives, int walkSpeed, int enemyID, Block currentBlock) {
		this.lives = lives;
		this.enemyID = enemyID;
		value = (int) 1;
		
		setCurrentBlock(currentBlock);
	
		slowed = false;
		
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
		GameData.score += damage;
		board.addMoney((int)(damage*0.2));
		if (lives <= 0) {
			GameData.totEnemiesKilled++;
			inGame = false;
			mobWalk = 0;
			Sound.playSound("die.wav");
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
	
	public void slowDownEnemy(){
		if(slowed) return;
		walkSpeed *= 2; 
		slowed = true;
		
	}
	
	public double getLives(){
		return lives;
	}

//	Avstanden de har reist blir lagret for aa kunne skyte paa den fienden som ligger forst
	public int getDistanceTraveled() {
		return distanceTraveled;
	}
	
	public void draw(Graphics g) {
		g.drawImage(Tilesets.enemy_tileset[enemyID], x, y, width, height, null);
		
		if(slowed) {
			g.setColor(new Color(10,190,80,150));
			g.fillRect(x, y, width, height);
		}
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
