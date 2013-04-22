package backend;

import graphics.Board;
import graphics.Enemy;
import graphics.Screen;

public class WaveControl {
	
	private int waveNumber = 0,
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
			{"0:5:10:6:830"},
			{"0:20:10:4:400"},
			{"0:30:10:3:350"},
			{"0:5:10:3:400","1:3:25:4:400","0:5:10:3:400","0:10:10:3:300"},
			{"0:20:10:3:400","1:15:25:4:400", "0:10:10:4:400"},//5
			{"0:5:10:3:400","1:25:25:3:400"},
			{"0:15:10:3:250","1:10:25:4:470","2:4:40:4:900","1:5:25:4:470"},
			{"0:20:10:3:250","1:25:25:4:300","2:5:40:4:350"}, //8
			{"0:10:10:3:300","1:10:25:4:550","2:14:40:3:450","1:10:25:4:550"},
			{"2:30:40:3:600"}, // 10
			{"1:102:25:3:200"},
			
			{"0:10:10:3:200","1:10:25:4:400","2:12:40:4:500","3:2:60:4:700"},
			{"0:0:10:3:100","1:15:25:4:450","2:10:40:4:500","3:5:60:4:600"}, //13
			{"0:33:10:3:130","1:0:25:4:450","0:33:10:3:120","2:23:45:4:400","0:33:10:3:130","3:4:60:4:450"},
			{"0:24:10:3:200","1:15:25:4:450","0:25:10:3:200","2:10:45:4:400","3:9:60:4:800"}, //15
			
			{"0:20:10:3:150","1:0:25:4:450","2:12:45:4:400","3:5:60:4:800","4:3:60:4:800"},
			{"0:0:10:3:150","1:0:25:4:450","2:20:45:4:400","3:8:60:4:800","4:4:60:4:800"},
			{"0:20:10:3:150","1:0:25:4:450","2:12:45:4:400","3:0:60:4:800","4:10:60:4:800"}, //18
			{"0:20:10:3:150","1:0:25:4:450","2:80:45:4:500","3:0:60:4:800","4:0:60:4:800"},
			{"0:0:10:3:150","1:10:25:4:450","2:4:45:4:400","3:5:60:4:800","4:7:60:4:800"}, // 20
			
			{"0:0:10:3:150","1:10:25:4:450","2:0:35:4:400","3:0:60:4:800","4:0:60:4:800","5:7:150:4:800"},
			{"0:0:10:3:150","1:10:25:4:450","2:0:35:4:400","3:0:60:4:800","4:14:60:4:500","5:0:150:4:800"}, // 22
			
			//red				blue      		green			yellow			pink		black			white          zebra				lead
			{"0:0:10:3:150","1:10:25:4:450","2:0:35:4:400","3:0:60:4:800","4:0:60:4:800","5:0:150:4:800","6:8:300:4:800"},
			{"0:0:10:3:150","1:10:25:4:450","2:0:35:4:400","3:0:60:4:800","4:0:60:4:800","5:5:150:4:800","6:4:300:4:800"},
			{"0:0:10:3:150","1:10:25:4:450","2:0:35:4:400","3:0:60:4:800","4:0:60:4:800","5:7:150:4:800","6:8:300:4:800"}, // 25
			{"0:0:10:3:150","1:10:25:4:450","2:0:35:4:400","3:0:60:4:800","4:0:60:4:800","5:31:150:4:600","6:1:300:4:800"},
			
			{"0:0:10:3:150","1:10:25:4:450","2:0:35:4:400","3:0:60:4:800","4:0:60:4:800","5:0:150:4:800","6:23:300:4:800","7:4:500:4:800"},
			{"0:120:10:3:75","1:55:25:4:200","2:45:35:4:350","3:45:60:4:800","4:0:60:4:800","5:0:150:4:800","6:0:300:4:800","7:0:500:4:800"},
			
			//red				blue      		green			yellow			pink		black			white          zebra				lead
			{"0:0:10:3:75","1:0:25:4:200","2:0:35:4:350","3:0:60:4:800","4:0:60:4:800","5:0:150:4:800","6:0:300:4:800","7:0:500:4:800","8:4:1000:4:800"}, //29

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
			System.out.println("Runde: " + waveNumber);
			
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

	public int getWave() {
		return waveNumber;
	}
}
