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
									{"0:5:10:6:800"},
									{"0:5:10:4:600"},

									{"0:10:10:4:500"},
									{"0:20:10:4:400"},
									{"0:30:10:4:400"},
									{"0:20:10:4:400","1:3:20:4:800"},
									{"0:30:10:4:400","1:15:20:4:800"},
									{"0:5:10:4:400","1:25:20:4:800"},
									
									
									
									
									
									
									
									{"0:15:10:4:300","1:2:20:5:500","0:1:10:4:1500","0:10:10:4:350","1:2:40:5:900"},
									{"1:10:10:4:500"},
									{"2:10:10:4:400","3:1:40:4:800"},
				
									
									{"0:5:10:4:300","1:1:40:5:500","0:1:10:4:1550","0:10:10:4:350","1:2:40:5:900"},
									{"0:7:10:4:300","1:2:40:5:400","0:1:10:4:1000","0:12:10:4:350","1:3:40:6:900"},
									{"0:12:10:4:300","1:2:40:5:400","0:1:10:4:1000","0:15:10:4:350","1:3:40:5:400"},
									
									
									
									
									{"1:3:60:5:1500"},
									{"0:12:10:4:300","1:4:40:5:400","0:1:10:1500","0:15:10:4:350","1:4:40:5:300","1:3:60:5:1400"},
									};	
	
	private Screen screen;
	
	public WaveControl(Screen screen){
		this.screen = screen;
	}
	
	public void spawnTimer(Board board) {
		if(canContinue)return;
		if(numOfEnemies == enemiesSpawned) {
			
			if(waveNumber == waveArray.length){
				GameData.money += 300;
				waveNumber = 0;
				wavePart = 0;
				screen.goToMainMenu();
				return;
			}
			nextPart();
			if(numOfEnemies == enemiesSpawned) return;
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
			wavePart = 0;
			enemiesSpawned = 0;
			waveNumber++;
			
			updateProperties(waveArray[waveNumber][wavePart]);
		}
	}
	
	private void nextPart(){
		if(wavePart >= waveArray[waveNumber].length-1) {
			canContinue =true;
			return;
		} else {
			wavePart++;
		}
		updateProperties(waveArray[waveNumber][wavePart]);
	}
	
	private void updateProperties(String info){
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
		
		enemiesSpawned = 0;
	}
}
