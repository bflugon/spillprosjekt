package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;

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
	
	private WaveControl waveControl = new WaveControl();
	private Pathfinder pathfinder;

	private Block[][] grid;
	private Block 	start,
					goal;
	
	private Rectangle nextWave;
	private Rectangle goToStore;
	private Rectangle mute;
	private Rectangle save;
	
	private ArrayList<Rectangle> towerButtons;
	private ArrayList<Tower> towers;

	private Enemy[] enemies;
	
	
	//Alle skuddene blir lagt til her når de skutt
	
	private FileManager loader = new FileManager();

	public Board(){
//		Lager listen som inneholder taarnene
		towers = new ArrayList<Tower>();
		
		nextWave = new Rectangle(720,570,80,80);
		goToStore = new Rectangle(630,570,80,80);
		mute = new Rectangle(585,570,35,35);
		save = new Rectangle(585,615,35,35);
		
		activeTower = 0;
//		Fyller arrayet med fiender
		enemies = new Enemy[80];
		GameData.enemies = new Enemy[80];
		for(int i = 0; i<enemies.length; i++){
			enemies[i] = new Enemy(this);
			GameData.enemies[i] = new Enemy(this);
		}
		
		towerButtons = new ArrayList<Rectangle>();
//		Lager like mange knapper som taarn man kan plassere
		for(int i = 0; i < GameData.modelTowers.size();i++){
			towerButtons.add(new Rectangle(20+i*90,570, 80, 80));
		}
	}
	
	public void createBoard(int mapNum){
//		Lager brettet
		grid = new Block[worldHeight][worldWidth];
		
//		Legger til blokker i arrayet
		for(int y = 0; y < grid.length; y++){
			for(int x = 0; x < grid[0].length; x++){
				grid[y][x] = new Block(20+blockSize*x, 20+blockSize*y, x, y, blockSize);
			}
		}
		
		loader.loadBoard(new File("resources/maps/"+mapNum+".map"), this);
		
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
						towers.add(newTower);	
						block.setOpen(false);
						String id = "";
						id += String.valueOf(GameData.barrels.indexOf(newTower.getBarrel())+1);
						id += String.valueOf(GameData.ammo.indexOf(newTower.getAmmo())+1);
						id += String.valueOf(GameData.bases.indexOf(newTower.getBase())+1);
						int blockID = Integer.parseInt(id);
						block.setBlockID(blockID);
					}
				}
			}
		}
	}
	public void addFoundation() {
//		Sjekker om musen er over en blokk
		for(Block[] row: grid){
			for(Block block: row){
				if(block.contains(Screen.CURSOR)){
					
//					Pass paa at ingen fiende staar på blokken
					for(Enemy enemy : enemies){
						if(enemy.inGame() && block.intersects(enemy)) return;
					}
					
					if(block.getBlockID() == GameData.grass){
						block.setBlockID(GameData.foundation);
						if(!pathfinder.findPath()) {
							block.setBlockID(GameData.grass);
							pathfinder.findPath();
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
	}
	
	public void draw(Graphics g) {
//		Tegner blokkene
		for(Block[] row: grid){
			for(Block block: row){
				block.draw(g);
			}
		}
		
//		Tegner knappene
		for(int i = 0; i < towerButtons.size(); i++){
			
			if(i == activeTower)g.setColor(Colors.pink);
			else if(towerButtons.get(i).contains(Screen.CURSOR))g.setColor(Colors.transparentPink);
			else g.setColor(Colors.transparentBlack);
			
			g.fillRect(towerButtons.get(i).x, towerButtons.get(i).y, towerButtons.get(i).width, towerButtons.get(i).height);
			GameData.modelTowers.get(i).drawButton(g, towerButtons.get(i));
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
		
		g.fillRect(nextWave.x, nextWave.y, nextWave.width, nextWave.height);
		g.drawImage(Tilesets.button_tileset[GameData.nextWave], nextWave.x, nextWave.y, nextWave.width, nextWave.height, null);
		
		g.fillRect(goToStore.x, goToStore.y, goToStore.width, goToStore.height);
		g.drawImage(Tilesets.button_tileset[GameData.goToShop], goToStore.x, goToStore.y, goToStore.width, goToStore.height, null);

		g.fillRect(mute.x, mute.y, mute.width, mute.height);
		g.drawImage(Tilesets.button_tileset[GameData.mute], mute.x, mute.y, mute.width, mute.height, null);
		
		g.fillRect(save.x, save.y, save.width, save.height);
		g.drawImage(Tilesets.button_tileset[GameData.save], save.x, save.y, save.width, save.height, null);
		
//		Tegn penger og liv
		g.setFont(GameData.normal);
		g.setColor(Color.WHITE);
		g.drawString("Money: "+money, 470, 602);
		g.drawString("Lives: "+lives, 470, 632);

		for(Block[] row: grid){
			for(Block block: row){
				if(block.contains(Screen.CURSOR)){
					if(block.getBlockID() == 1){
						GameData.modelTowers.get(activeTower).setBounds((int)block.getX(), (int)block.getY(), (int)block.getWidth(), (int)block.getHeight());
						GameData.modelTowers.get(activeTower).draw(g);
						GameData.modelTowers.get(activeTower).drawRange(g);
					} else if(block.getBlockID() == 0){
						g.setColor(Colors.popupBackground);
						g.drawRect((int)block.getX(), (int)block.getY(), (int)block.getWidth(), (int)block.getHeight());
					}
				}
			}
		}
		
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
	
	public boolean menuClicked() {
		return goToStore.contains(Screen.CURSOR);
	}

	public void mute(){
		if(mute.contains(Screen.CURSOR)){
			if(GameData.muted)GameData.muted = false;
			else GameData.muted = true;
		}
	}
	
	public void setStart(Block start) {
		this.start = start;
	}

	public void setGoal(Block goal) {
		this.goal = goal;
	}

	public boolean goToStore() {
		return goToStore.contains(Screen.CURSOR);
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
}
