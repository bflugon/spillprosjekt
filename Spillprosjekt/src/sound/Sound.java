package sound;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import backend.GameData;

public class Sound {
	public static void playSound(final String sound){
		if(GameData.muted) return;
		System.out.println(GameData.muted);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					AudioInputStream inputStream;
					Clip clip = AudioSystem.getClip();
					inputStream = AudioSystem.getAudioInputStream(new File("resources/sounds/"+sound));
					clip.open(inputStream);
					clip.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
