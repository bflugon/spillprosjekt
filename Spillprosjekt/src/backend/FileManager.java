package backend;

import graphics.Block;
import graphics.Board;
import graphics.Tower;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import components.Ammo;
import components.Barrel;
import components.Base;

public class FileManager {
	public static void loadBoard(File loadPath, Board board) {
		try {
			Scanner loadScanner = new Scanner(loadPath);
			Block[][] grid = board.getGrid();
			while (loadScanner.hasNext()) {
				for (int y = 0; y < grid.length; y++) {
					for (int x = 0; x < grid[0].length; x++) {
						int id = loadScanner.nextInt();
						if (id > 99) {
							String towerID = String.valueOf(id);
							Tower newTower = new Tower(grid[y][x], board);
							newTower.setBarrel(GameData.barrels.get(Character.getNumericValue(towerID.charAt(0))-1));
							newTower.setAmmo(GameData.ammo.get(Character.getNumericValue(towerID.charAt(1))-1));
							newTower.setBase(GameData.bases.get(Character.getNumericValue(towerID.charAt(2))-1));
							grid[y][x].setBlockID(GameData.foundation);
						}
						else {
							grid[y][x].setBlockID(id);
						}
						if (id == GameData.start) {
							board.setStart(grid[y][x]);
						}
						else if (id == GameData.goal) {
							board.setGoal(grid[y][x]);
						}
					}
				}
				int money = loadScanner.nextInt();
				board.setMoney(money);
			
				loadScanner.close();
			}
		} catch(Exception e) {}
	}
	
	public static void saveBoard(Board board) {
		int mapNum = new File("./resources/maps").list().length;
		File file = new File("resources/maps/"+mapNum+".map");
		Block[][] grid = board.getGrid();
		file.delete();
	    try {
	        BufferedWriter out = new BufferedWriter(new FileWriter(file));
	        for (int i = 0; i < grid.length; i++) {
	        	if (i != 0) {
	        		out.write("\n");
	        	}
	        	for (int j = 0; j < grid[i].length; j++) {	
	        		if (grid[i][j].getBlockID() < 10) {
	        		out.write(" " + grid[i][j].getBlockID() + "  ");
	        		}
	        		else {
	        			out.write(grid[i][j].getBlockID() + " ");
	        		}
	        	}
	        }
	        out.write("\n");
	        out.write(board.getMoney());
	        out.close();
	    } catch (IOException e) {
	    }
	}
	
	public static void saveGameData(){
		File file = new File("resources/saves/save.txt");
		
	    try {
	        BufferedWriter out = new BufferedWriter(new FileWriter(file));

	        out.write(""+GameData.rank);
	        out.write("\n"+GameData.money);
	        out.write("\n"+GameData.totEnemiesKilled);
	        out.write("\n"+GameData.score);
	        out.write("\n"+GameData.rankUpLimit);
	        
	        out.write("\n\nRank: "+GameData.rank);
	        out.write("\nMoney: "+GameData.money);
	        out.write("\nEnemies killed: "+GameData.totEnemiesKilled);
	        out.write("\nScore: "+GameData.score);
	        out.write("\nRank-up limit: "+GameData.rankUpLimit);
	        
	        out.close();
	    } catch (IOException e) {}
	    
	    saveModelTowers();
	}
	
	public static void loadGameData(){
		File file = new File("resources/saves/save.txt");
		GameData.score = 0;
		GameData.rankUpLimit = 800;
		GameData.rank = 0;
		
	    try {
	    	Scanner loadScanner = new Scanner(file);

	    	GameData.rank = loadScanner.nextInt();
	    	GameData.money = loadScanner.nextInt();
	    	GameData.totEnemiesKilled = loadScanner.nextInt();
	    	GameData.score = loadScanner.nextInt();
	    	GameData.rankUpLimit = loadScanner.nextInt();

	    	System.out.println("Game Loaded!");
	    	loadScanner.close();
	    } catch (IOException e) {}
	    
	    loadModelTowers();
	}

	public static void newGame(){
		File file = new File("resources/saves/towersSave.txt");
		file.delete();
		
		file = new File("resources/saves/save.txt");
		file.delete();
		
		loadGameData();
	}
	
	public static void saveModelTowers(){
		ArrayList<Tower> towers = GameData.modelTowers;
		
		File file = new File("resources/saves/towersSave.txt");
		try{
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
		
			for(Tower tower: towers){
				if(towers.get(0) != tower){
					out.write("\n");
				}
				out.write(tower.getBarrel().getName()+"\n");
				out.write(tower.getAmmo().getName()+"\n");
				out.write(tower.getBase().getName());
			}
			out.close();
		}catch (Exception e) {}
	}
	
	public static void loadModelTowers(){
		File file = new File("resources/saves/towersSave.txt");
		GameData.modelTowers = new ArrayList<Tower>();
		try{
			Scanner loadScanner = new Scanner(file);
			
			
			while(loadScanner.hasNext() && GameData.modelTowers.size() < 7){
				Tower tower = new Tower();
				String barrelName = loadScanner.nextLine();
				String ammoName = loadScanner.nextLine();
				String baseName = loadScanner.nextLine();
				
				for(Barrel barrel : GameData.barrels){
					if(barrel.getName().equals(barrelName)){
						tower.setBarrel(barrel);
					}
				}
				
				for(Ammo ammo : GameData.ammo){
					if(ammo.getName().equals(ammoName)){
						tower.setAmmo(ammo);
					}
				}
				
				for(Base base : GameData.bases){
					if(base.getName().equals(baseName)){
						tower.setBase(base);
					}
				}
				tower.updateProperties();
				GameData.modelTowers.add(tower);
				loadScanner.close();
			}
		}catch (Exception e) {}
	}
}
