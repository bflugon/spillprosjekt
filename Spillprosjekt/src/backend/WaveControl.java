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
			{"00:5:830"},
			{"00:20:400"},
			{"00:30:350"},
			{"00:5:400","01:3:400","00:5:400","00:10:300"},
			{"00:20:400","01:15:300", "00:10:400"},//5
			{"00:5:400","01:25:400"},
			{"00:15:250","01:10:470","02:4:1400","01:5:470"},
			{"00:20:250","01:25:300","02:10:400"}, //8
			{"00:10:300","01:10:550","02:14:400","01:10:550"},
			{"02:30:400"}, // 10
			{"01:102:180"},
			
			{"00:60:30","01:10:400","02:12:200","03:2:700"},
			{"00:10:100","01:15:450","02:10:500","03:5:300"}, //13
			{"00:33:130","01:10:450","00:33:120","02:23:400","00:33:130","03:4:450"},
			{"00:24:200","01:15:450","00:25:200","02:10:400","03:9:800"}, //15
			
			{"00:20:150","01:0:450","02:12:400","03:5:800","04:3:800"},
			{"00:0:150","01:0:450","02:20:400","03:8:800","04:4:800"},
			{"00:20:150","01:0:450","02:12:400","03:0:800","04:10:800"}, //18
			{"00:20:150","01:0:450","02:80:500","03:0:800","04:0:800"},
			{"00:0:150","01:10:450","02:4:400","03:5:800","04:7:800"}, // 20
			
			{"00:0:150","01:10:450","02:0:400","03:0:800","04:0:800","05:7:800"},
			{"00:0:150","01:10:450","02:0:400","03:0:800","04:14:500","05:0:800"}, // 22
			
			//red				blue      		green			yellow			pink		black			white          zebra				lead
			{"00:0:150","01:10:450","02:0:400","03:0:800","04:0:800","05:0:800","06:8:800"},
			{"00:0:150","01:10:450","02:0:400","03:0:800","04:0:800","05:5:800","06:4:800"},
			{"00:0:150","01:10:450","02:0:400","03:0:800","04:0:800","05:7:800","06:8:800"}, // 25
			{"00:0:150","01:10:450","02:0:400","03:0:800","04:0:800","05:31:600","06:1:800"},
			
			{"00:0:150","01:10:450","02:0:400","03:0:800","04:0:800","05:0:800","06:23:800","07:4:800"},
			{"00:1:75","01:1:200","02:1:350","03:1:600","04:1:700","05:1:800","06:1:800","07:1:800"},
			
			//red				blue      		green			yellow			pink		black			white          zebra				lead
			{"00:0:75","01:0:200","02:0:350","03:0:800","04:0:600","05:0:800","06:0:800","07:0:800","08:4:800"}, //29

			};	
	
	private Screen screen;
	
	public WaveControl(Screen screen){
		this.screen = screen;
	}
	
	public void spawnTimer(Board board) {
		if(canContinue)return;
		if(numOfEnemies == enemiesSpawned) {
			nextPart();
			if(numOfEnemies == enemiesSpawned) return;;
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

			if(waveNumber == waveArray.length){
				GameData.money += 300;
				waveNumber = -1;
				wavePart = -1;
				screen.goToMainMenu();
				return;
			}
			setProperties();
		}
	}
	
	private void nextPart(){
		if(wavePart >= waveArray[waveNumber].length-1 || waveNumber < 0) {
			canContinue =true;
			return;
		} else {
			wavePart++;
		}
		
		setProperties();
	}

	public int getWave() {
		return waveNumber;
	}

	private void setProperties(){
		String s = waveArray[waveNumber][wavePart];
		final int index = Integer.valueOf(s.substring(0, 2));
		
		switch(index){
		case 0:
			enemyHealth = 10;
			enemySpeed = 3;
			break;
		case 1:
			enemyHealth = 20;
			enemySpeed = 4;
			break;
		case 2:
			enemyHealth = 45;
			enemySpeed = 3;
			break;
		case 3:
			enemyHealth = 70;
			enemySpeed = 4;
			break;
		case 4:
			enemyHealth = 95;
			enemySpeed = 4;
			break;
		case 5:
			enemyHealth = 150;
			enemySpeed = 4;
			break;
		case 6:
			enemyHealth = 300;
			enemySpeed = 4;
			break;
		case 7:
			enemyHealth = 500;
			enemySpeed = 4;
			break;
		case 8:
			enemyHealth = 2000;
			enemySpeed = 4;
			break;
		}
		
		int end = s.indexOf(':',3);
		numOfEnemies = Integer.valueOf(s.substring(3, end));
		spawnRate = Integer.valueOf(s.substring(end+1));
		enemiesSpawned = 0;
		enemyIndex = index;
	}
}
