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
			{"00:5:400","1:3:400","0:5:400","0:10:10:3:300"},
			{"00:20:10:3:400","1:15:25:4:400", "0:10:10:4:400"},//5
			{"00:5:10:3:400","1:25:25:3:400"},
			{"00:15:10:3:250","1:10:25:4:470","2:4:40:4:1400","1:5:25:4:470"},
			{"00:20:10:3:250","1:25:25:4:300","2:5:40:4:350"}, //8
			{"00:10:10:3:300","1:10:25:4:550","2:14:40:3:450","1:10:25:4:550"},
			{"02:30:40:3:600"}, // 10
			{"01:102:25:3:200"},
			
			{"0:10:10:3:200","1:10:25:4:400","2:12:40:4:500","3:2:70:4:700"},
			{"0:0:10:3:100","1:15:25:4:450","2:10:40:4:500","3:5:70:4:600"}, //13
			{"0:33:10:3:130","1:0:25:4:450","0:33:10:3:120","2:23:45:4:400","0:33:10:3:130","3:4:70:4:450"},
			{"0:24:10:3:200","1:15:25:4:450","0:25:10:3:200","2:10:45:4:400","3:9:70:4:800"}, //15
			
			{"0:20:10:3:150","1:0:25:4:450","2:12:45:4:400","3:5:70:4:800","4:3:95:4:800"},
			{"0:0:10:3:150","1:0:25:4:450","2:20:45:4:400","3:8:70:4:800","4:4:95:4:800"},
			{"0:20:10:3:150","1:0:25:4:450","2:12:45:4:400","3:0:70:4:800","4:10:95:4:800"}, //18
			{"0:20:10:3:150","1:0:25:4:450","2:80:45:4:500","3:0:70:4:800","4:0:95:4:800"},
			{"0:0:10:3:150","1:10:25:4:450","2:4:45:4:400","3:5:70:4:800","4:7:95:4:800"}, // 20
			
			{"0:0:10:3:150","1:10:25:4:450","2:0:45:4:400","3:0:70:4:800","4:0:95:4:800","5:7:150:4:800"},
			{"0:0:10:3:150","1:10:25:4:450","2:0:45:4:400","3:0:70:4:800","4:14:95:4:500","5:0:150:4:800"}, // 22
			
			//red				blue      		green			yellow			pink		black			white          zebra				lead
			{"0:0:10:3:150","1:10:25:4:450","2:0:45:4:400","3:0:70:4:800","4:0:95:4:800","5:0:150:4:800","6:8:300:4:800"},
			{"0:0:10:3:150","1:10:25:4:450","2:0:45:4:400","3:0:70:4:800","4:0:95:4:800","5:5:150:4:800","6:4:300:4:800"},
			{"0:0:10:3:150","1:10:25:4:450","2:0:45:4:400","3:0:70:4:800","4:0:95:4:800","5:7:150:4:800","6:8:300:4:800"}, // 25
			{"0:0:10:3:150","1:10:25:4:450","2:0:45:4:400","3:0:70:4:800","4:0:95:4:800","5:31:150:4:600","6:1:300:4:800"},
			
			{"0:0:10:3:150","1:10:25:4:450","2:0:45:4:400","3:0:70:4:800","4:0:95:4:800","5:0:150:4:800","6:23:300:4:800","7:4:500:4:800"},
			{"0:1:10:3:75","1:1:25:4:200","2:1:45:4:350","3:1:70:4:600","4:1:95:4:700","5:1:150:4:800","6:1:300:4:800","7:1:500:4:800"},
			
			//red				blue      		green			yellow			pink		black			white          zebra				lead
			{"0:0:10:3:75","1:0:25:4:200","2:0:35:4:350","3:0:60:4:800","4:0:60:4:800","5:0:150:4:800","6:0:300:4:800","7:0:500:4:800","8:4:2000:4:800"}, //29

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
					System.out.println(wavePart);
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
			screen.getBoard().addMoney(15 + 3*waveNumber);
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
			System.out.println(waveNumber);
			setProperties();
//			updateProperties(waveArray[waveNumber][wavePart]);
		}
	}
	
	private void nextPart(){
		if(wavePart >= waveArray[waveNumber].length-1) {
			canContinue =true;
			return;
		} else {
			wavePart++;
		}
		
		setProperties();
	}
	
//	private void updateProperties(String info){
//		int start = 0,
//			end = info.indexOf(':');;
//			
//		enemyIndex = Integer.parseInt(info.substring(start, end));
//		
//		start = end+1;
//		end = info.indexOf(':', start);
//		numOfEnemies = Integer.parseInt(info.substring(start, end));
//		
//		start = end+1;
//		end = info.indexOf(':', start);
//		enemyHealth = Integer.parseInt(info.substring(start, end));
//		
//		start = end+1;
//		end = info.indexOf(':', start);
//		enemySpeed = Integer.parseInt(info.substring(start, end));
//		
//		start = end+1;
//		spawnRate = Integer.parseInt(info.substring(start));
//		
//		enemiesSpawned = 0;
//	}

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
			enemyIndex = 0;
			break;
		case 1:
			enemyHealth = 20;
			enemySpeed = 4;
			enemyIndex = 1;
			break;
		}
		
		int end = s.indexOf(':',3);
		numOfEnemies = Integer.valueOf(s.substring(3, end));
		spawnRate = Integer.valueOf(s.substring(end+1));
	}
}
