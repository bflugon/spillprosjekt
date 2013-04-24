package backend;

import graphics.Board;
import graphics.Enemy;
import graphics.HitMarker;
import graphics.Screen;

import java.util.ArrayList;

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
			
			{"00:60:80","01:10:400","02:12:200","03:2:700"},
			{"00:10:100","01:15:450","02:10:400","03:5:300"}, //13
			{"00:33:130","01:10:450","00:33:120","02:23:400","00:33:130","03:4:450"},
			{"00:24:200","01:15:300","00:25:200","02:10:400","03:9:800"}, //15
			
			{"00:20:90","01:0:450","02:12:150","03:5:300","04:3:500"},
			{"00:0:90","01:0:450","02:20:150","03:8:500","04:4:500"},
			{"00:20:80","01:0:450","02:12:150","03:0:400","04:10:400"}, //18
			{"00:20:60","01:0:450","02:80:200","03:0:500","04:0:300"},
			{"00:0:150","01:10:450","02:4:400","03:5:400","04:10:300"}, // 20
			{"06:10:200"},
			{"00:0:150","01:10:450","02:0:400","03:0:800","04:0:800","05:7:800"},
			{"00:0:150","01:10:450","02:0:400","03:0:800","04:14:500","05:0:800"}, // 22
//			
//			//red				blue      		green			yellow			pink		black			white          zebra				lead
			{"00:0:150","01:10:200","02:0:400","03:0:800","04:0:800","05:0:800","06:8:300"},
			{"00:0:150","01:10:200","02:0:400","03:0:800","04:0:800","05:5:400","06:4:400"},
			{"00:0:150","01:10:200","02:0:400","03:0:800","04:0:800","05:7:300","06:8:400"}, // 25
			{"00:0:150","01:10:200","02:0:400","03:0:800","04:0:800","05:31:200","06:1:500"},
			
			{"00:0:150","01:10:450","02:0:400","03:0:800","04:0:800","05:0:800","06:23:800","07:1:800"},
			{"00:1:75","01:1:200","02:1:350","03:1:600","04:1:400","05:1:300","06:1:500","07:1:300"},
			
			//red		blue      	green	  yellow	pink		black		white          zebra		lead
			{"00:0:75","01:0:200","02:0:350","03:0:800","04:0:600","05:0:200","06:0:300","07:0:200","08:0:500","09:20:70"}, //29
			{"00:0:75","01:0:200","02:0:350","03:25:400","04:25:200","05:25:200","06:10:300","07:1:800","08:1:500","09:20:70"},
			{"00:0:75","01:0:200","02:0:350","03:0:800","04:0:600","05:100:200","06:10:300","07:8:200","08:1:500","09:20:70"},
			{"00:0:75","01:0:200","02:0:350","03:0:800","04:0:600","05:25:250","06:28:250","07:0:200","08:1:500","09:20:70"},
			{"08:1:500","09:25:70","00:7:1000","08:1:500","09:20:70",},
			{"08:1:500","09:20:70","08:1:2000","09:20:70",},
			{"08:1:500","09:20:70","08:1:2000","09:10:70","08:1:2000","09:10:70","08:1:2000","09:10:70","08:1:2000","09:10:70","08:1:2000","09:10:70"}
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

			screen.getBoard().addMoney(5);
			
			if(waveNumber == waveArray.length){
				waveNumber = -1;
				wavePart = -1;
				screen.goToMainMenu();
				return;
			}
			setProperties();
		}
	}
	
	private void nextPart(){
		if(waveNumber < 0 || wavePart >= waveArray[waveNumber].length-1) {
			canContinue =true;
			return;
		} else {
			Board.hitMarkers = new ArrayList<HitMarker>();
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
			enemyHealth = 150;
			enemySpeed = 4;
			break;
		case 4:
			enemyHealth = 3000;
			enemySpeed = 4;
			break;
		case 5:
			enemyHealth = 450;
			enemySpeed = 4;
			break;
		case 6:
			enemyHealth = 1000;
			enemySpeed = 4;
			break;
		case 7:
			enemyHealth = 2000;
			enemySpeed = 4;
			break;
		case 8:
			enemyHealth = 4000;
			enemySpeed = 5;
			break;
		case 9:
			enemyHealth = 500;
			enemySpeed = 5;
			break;
			
		}
		
		int end = s.indexOf(':',3);
		numOfEnemies = Integer.valueOf(s.substring(3, end));
		spawnRate = Integer.valueOf(s.substring(end+1));
		enemiesSpawned = 0;
		enemyIndex = index;
	}
}
