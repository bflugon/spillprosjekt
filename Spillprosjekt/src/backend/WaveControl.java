package backend;

import graphics.Board;
import graphics.Enemy;
import graphics.Screen;

public class WaveControl {
	
	private int waveNumber = -1,
				wavePart = -1;
	
	private int enemyHealth,
				spawnRate,
				enemiesSpawned = 0,
				spawnFrame = 0,
				enemyIndex,
				enemySpeed,
				numOfEnemies = 0;
	
	public static boolean canContinue = true;
	
//									Index:Antall:Lives:Speed:Spawnrate
	private String[][] waveArray =	{
									{"0:5:10:4:600"},
									{"0:10:10:4:500"},
									{"0:10:10:4:400","1:1:40:4:800"},
									{"0:5:10:4:300","1:1:40:5:500","0:1:10:4:1550","0:10:10:4:350","1:2:40:5:900"},
									{"0:7:10:4:300","1:2:40:5:400","0:1:10:1000","0:12:10:4:350","1:3:40:6:900"},
									{"0:12:10:4:300","1:2:40:5:400","0:1:10:1000","0:15:10:4:350","1:3:40:5:400"},
									{"1:3:60:5:1500"},
									{"0:12:10:4:300","1:4:40:5:400","0:1:10:1500","0:15:10:4:350","1:4:40:5:300","1:3:60:5:1400"},
									};	
	
	private Screen screen;
	
	public WaveControl(Screen screen){
		this.screen = screen;
	}
	
	public void spawnTimer(Board board) {
		
		if(numOfEnemies == enemiesSpawned) {
			if(waveNumber == waveArray.length){
				GameData.money += 300;
				waveNumber = -1;
				wavePart = 0;
				screen.goToMainMenu();
				return;
			}
			updateWave(board);
			if(numOfEnemies == enemiesSpawned)return;
		}
		
		Enemy[] enemies =  board.getEnemies();
		
		if(spawnFrame >= spawnRate) {
			for(int i = 0; i < enemies.length;i++) {
				if(!enemies[i].inGame()) {
					enemies[i].spawnEnemy(enemyHealth, enemySpeed, enemyIndex, board.getStart());
					enemiesSpawned++;
					break;
				}
			}
			spawnFrame = 0;
		} else {
			spawnFrame ++;
		}
	}
	
	public void nextWave(){
		if(canContinue){
			canContinue = false;
			wavePart = -1;
			waveNumber++;
		}
	}
	
	private void updateWave(Board board){
		
		boolean done = true;
		for(Enemy enemy:board.getEnemies()){
			if(enemy.inGame()) done = false;
		}

		if(!done)return;
		
		wavePart++;
		if(wavePart >= waveArray[waveNumber].length) {
			if(waveNumber < waveArray.length) canContinue =true;
			return;
		}
		enemiesSpawned = 0;
		
		String info = waveArray[waveNumber][wavePart];
		int start = 0,
			end = info.indexOf(':');;
		
		enemyIndex = Integer.parseInt(info.substring(start, end));
		
		start = end+1;
		end = info.indexOf(':', start);
		numOfEnemies = Integer.parseInt(info.substring(start, end));
		
		start = end+1;
		end = info.indexOf(':', start);
		enemyHealth = Integer.parseInt(info.substring(start, end));
		
		start = end+1;
		end = info.indexOf(':', start);
		enemySpeed = Integer.parseInt(info.substring(start, end));
		
		start = end+1;
		spawnRate = Integer.parseInt(info.substring(start));

	}
}
