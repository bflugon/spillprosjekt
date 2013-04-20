package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;

import sound.Sound;

import backend.Colors;
import backend.GameData;
import backend.FileManager;
import backend.Pathfinder;
import backend.Tilesets;
import backend.WaveControl;

public class Board {
	
	private int money = 0,
				lives = 20,
				activeTower;
	
	private final int 	worldHeight = 9,
						worldWidth = 13,
						blockSize = 60;
	
	private WaveControl waveControl;
	private Pathfinder pathfinder;

	private Block[][] grid;
	private Block 	start,
					goal;
	
	private Rectangle nextWave;
	private Rectangle muteGuns;
	private Rectangle muteMusic;
	
	private ArrayList<Rectangle> towerButtons;
	private ArrayList<Tower> towers;

	private Screen screen;
	
	private Enemy[] enemies;
	
	
	//Alle skuddene blir lagt til her når de skutt
	
	public Board(Screen screen){
		
		waveControl = new WaveControl(screen);
//		Lager listen som inneholder taarnene
		towers = new ArrayList<Tower>();
		
		this.screen = screen;
		
		nextWave = new Rectangle(720,570,80,80);
		muteGuns = new Rectangle(675,570,35,35);
		muteMusic = new Rectangle(675,615,35,35);
		
		activeTower = 0;
//		Fyller arrayet med fiender
		enemies = new Enemy[80];

		towerButtons = new ArrayList<Rectangle>();
//		Lager like mange knapper som taarn man kan plassere
		for(int i = 0; i < GameData.modelTowers.size();i++){
			towerButtons.add(new Rectangle(20+i*90,570, 80, 80));
		}
	}
	
	public void createBoard(int mapNum){
//		Lager brettet
		grid = new Block[worldHeight][worldWidth];
		towers = new ArrayList<Tower>();
		lives = 20;
		
		for(int i = 0; i<enemies.length; i++){
			enemies[i] = new Enemy(this);
		}
		
//		Legger til blokker i arrayet
		for(int y = 0; y < grid.length; y++){
			for(int x = 0; x < grid[0].length; x++){
				grid[y][x] = new Block(20+blockSize*x, 20+blockSize*y, x, y, blockSize);
			}
		}
		
		FileManager.loadBoard(new File("resources/maps/"+mapNum+".map"), this);
		
//		Finner korsteste vei on request
		pathfinder = new Pathfinder(this);
		pathfinder.findPath();
	}
	
	public void placeTower(){
//		Sjekker om musen er over en blokk
		for(Block[] row: grid){
			for(Block block: row){
				if(block.contains(Screen.CURSOR)){
					if(block.getBlockID() == GameData.foundation && block.isOpen()) {
						Tower newTower = new Tower(block, this);
						GameData.modelTowers.get(activeTower).copyTower(newTower);
						newTower.updateProperties();
						if(newTower.getPrice() > money) return;
						towers.add(newTower);	
						block.setOpen(false);
						
						addMoney(-(int)newTower.getPrice());
					}
				}
			}
		}
	}
	public void addFoundation() {
//		Sjekker om musen er over en blokk
		for(Block[] row: grid){
			for(Block block: row){
				if(block.getBlockID() != GameData.grass)continue;
				if(block.contains(Screen.CURSOR)){
//					Pass paa at ingen fiende staar på blokken
					for(Enemy enemy : enemies){
						if(enemy.inGame() && block.intersects(enemy)) return;
					}
					
					if(money >= 50){
						block.setBlockID(GameData.foundation);
						addMoney(-50);
						if(!pathfinder.findPath()) {
							block.setBlockID(GameData.grass);
							pathfinder.findPath();
							addMoney(50);
						}
					}
				}
			}
		}
	}
	
	public void nextWave(){
		if(nextWave.contains(Screen.CURSOR)) waveControl.nextWave();
	}
	
	public Block[][] getGrid(){
		return grid;
	}
	public Block getStart(){
		return start;
	}
	public Block getGoal(){
		return goal;
	}
	
	public int getBlockSize() {
		return blockSize;
	}
	
	public Enemy[] getEnemies(){
		return enemies;
	}
	
	public void physics() {
		waveControl.spawnTimer(this);
		
		for(Enemy enemy: enemies){
			if(enemy.inGame()) enemy.physics();
		}
		
		for(Tower tower: towers){
			tower.physics();
		}
		
		if(GameData.score >= GameData.rankUpLimit){
			GameData.rank++;
			GameData.score = 0;
			GameData.rankUpLimit *= 1.6;
			Sound.playSound("rankup.wav");
		}
	}
	
	public void draw(Graphics g) {
//		Tegner blokkene
		for(Block[] row: grid){
			for(Block block: row){
				block.draw(g);
			}
		}
		
//		Tegner knappene
		for(int i = 0; i < GameData.modelTowers.size(); i++){
			
			if(i == activeTower)g.setColor(Colors.red);
			else if(towerButtons.get(i).contains(Screen.CURSOR))g.setColor(Colors.transparentRed);
			else g.setColor(Colors.transparentBlack);
			
			g.fillRect(towerButtons.get(i).x, towerButtons.get(i).y, towerButtons.get(i).width, towerButtons.get(i).height);
			GameData.modelTowers.get(i).drawButton(g, towerButtons.get(i));

			g.setColor(Color.WHITE);
			g.drawString(""+GameData.modelTowers.get(i).getPrice(), towerButtons.get(i).x+5, towerButtons.get(i).y+15);
		}
		
//		Draw enemies
		for(Enemy enemy: enemies){
			if(enemy.inGame()) enemy.draw(g);
		}

//		Tegner taarnene og rekkevidden hvis musen er over et av dem
		Tower mouseOver = null;
		for(Tower tower: towers){
			tower.draw(g);
			if(tower.contains(Screen.CURSOR)) mouseOver = tower;
		}
		if(mouseOver!=null)mouseOver.drawRange(g);
		
//		Tegn knapper
		g.setColor(Colors.transparentBlack);
		
		
		if(!WaveControl.canContinue){
			g.setColor(Colors.red);
		} else{
			g.setColor(Colors.green);
		}
		g.fillRect(nextWave.x, nextWave.y, nextWave.width, nextWave.height);
		
		g.drawImage(Tilesets.button_tileset[GameData.nextWave], nextWave.x, nextWave.y, nextWave.width, nextWave.height, null);
		
		if(GameData.gunsMuted)g.setColor(Colors.red);
		else g.setColor(Colors.green);
		g.fillRect(muteGuns.x, muteGuns.y, muteGuns.width, muteGuns.height);
		g.drawImage(Tilesets.button_tileset[GameData.mute], muteGuns.x, muteGuns.y, muteGuns.width, muteGuns.height, null);
		
		if(GameData.gunsMuted)g.setColor(Colors.red);
		else g.setColor(Colors.green);
		g.fillRect(muteMusic.x, muteMusic.y, muteMusic.width, muteMusic.height);
		g.drawImage(Tilesets.button_tileset[GameData.save], muteMusic.x, muteMusic.y, muteMusic.width, muteMusic.height, null);
		
//		Tegn penger og liv
		g.setFont(GameData.normal);
		g.setColor(Color.WHITE);
		g.drawString("$ "+money, 560, 590);
		g.drawString("Lives: "+lives, 560, 615);
		g.drawString("Rank: "+GameData.rank, 560, 645);

		for(Block[] row: grid){
			for(Block block: row){
				if(block.contains(Screen.CURSOR)){
					if(block.getBlockID() == 1 && block.isOpen()){
						GameData.modelTowers.get(activeTower).setBounds((int)block.getX(), (int)block.getY(), (int)block.getWidth(), (int)block.getHeight());
						GameData.modelTowers.get(activeTower).draw(g);
						GameData.modelTowers.get(activeTower).drawRange(g);
					} else if(block.getBlockID() == 0){
						g.drawImage(Tilesets.block_tileset[1],(int)block.getX()+5, (int)block.getY()+5, (int)block.getWidth()-10, (int)block.getHeight()-10, null);
					}
				}
			}
		}
		
//		Draw score bar
		g.setColor(Colors.transparentBlack);
		g.fillRect(0, 0, 820, 10);
		g.setColor(Colors.green);
		g.fillRect(0, 0, (int)((820.0/GameData.rankUpLimit)*GameData.score), 10);
		
	}
	
	public void changeActiveTower() {
		for(int i = 0; i < towerButtons.size(); i++){
			if(towerButtons.get(i).contains(Screen.CURSOR)){
				activeTower = i;
			}
		}
	}

	public void updateButtons(){
		towerButtons = new ArrayList<Rectangle>();
//		Lager like mange knapper som taarn man kan plassere
		for(int i = 0; i < GameData.modelTowers.size();i++){
			towerButtons.add(new Rectangle(20+i*90,570, 80, 80));
		}
	}
	
	public void muteGuns(){
		if(muteGuns.contains(Screen.CURSOR)){
			if(GameData.gunsMuted)GameData.gunsMuted = false;
			else GameData.gunsMuted = true;
		}
	}
	
	public void muteMusic(){
		if(muteMusic.contains(Screen.CURSOR)){
			if(GameData.musicMuted)GameData.musicMuted = false;
			else GameData.musicMuted = true;
		}
	}
	
	public void setStart(Block start) {
		this.start = start;
	}

	public void setGoal(Block goal) {
		this.goal = goal;
	}

	public int getMoney() {
		return money;
	}

	public void addMoney(int money) {
		this.money += money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void decreaseLives(int damage) {
		this.lives -= damage;
		if(this.lives < 1) screen.goToMainMenu();
	}
}
