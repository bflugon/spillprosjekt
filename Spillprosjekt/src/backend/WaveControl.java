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
									{"0:10:10:3:300","1:1:40:5:100","0:1:10:850","0:10:10:4:350","1:2:40:5:700"},
									{"0:10:10:3:300","1:1:40:5:100","0:1:10:850","0:10:10:4:350","1:2:40:5:700"},
									{"0:10:10:3:300","1:1:40:5:100","0:1:10:850","0:10:10:3:250","1:2:50:4:700"},
									{"0:10:10:4:400","1:1:40:4:800"},
									{"0:30:20:4:100","1:20:20:3:120"},
									{"0:30:20:4:100","1:20:20:3:120"},
									{"0:30:20:4:100","1:20:20:3:120"},
									{"0:30:20:4:100","1:20:20:3:120"},
									{"0:30:20:4:100","1:20:20:3:120"},
									{"0:30:20:4:100","1:20:20:3:120"}
									};	
	
	private Screen screen;
	
	@SuppressWarnings("unused")
	private void spawnWave(int wave) {
		int a = wave*5;
		int b = wave / 3;		
	}
	
	public WaveControl(Screen screen){
		this.screen = screen;
	}
	
	public void spawnTimer(Board board) {
		boolean done = true;
		for(Enemy enemy:board.getEnemies()){
			if(enemy.inGame()) done = false;
		}

		
		if(numOfEnemies == enemiesSpawned) {
			updateWave();
			if(waveNumber == waveArray.length-1 && done){
				GameData.money += 300;
				waveNumber = 0;
				wavePart = 0;
				screen.goToMainMenu();
				return;
			}
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
	
	private void updateWave(){
		wavePart++;
		if(wavePart >= waveArray[waveNumber].length) {
			if(waveNumber != waveArray.length-1) canContinue =true;
			return;
		}
		enemiesSpawned = 0;
		
		System.out.println(waveNumber + " " + wavePart);
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
