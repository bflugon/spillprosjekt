package sound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import backend.GameData;

public class BigSound implements Runnable { 
	File file;
	AudioInputStream in;
	SourceDataLine line;
	int frameSize;
	byte[] buffer = new byte [32 * 1024]; // 32k is arbitrary
	Thread playThread;
	boolean playing;
	boolean notYetEOF;

	public BigSound (File f)throws Exception {
		
		file = f;
		in = AudioSystem.getAudioInputStream (f);
		AudioFormat format = in.getFormat();
		AudioFormat.Encoding formatEncoding = format.getEncoding();
		if (! (formatEncoding.equals (AudioFormat.Encoding.PCM_SIGNED) ||
			   formatEncoding.equals (AudioFormat.Encoding.PCM_UNSIGNED))) 
		   throw new UnsupportedAudioFileException (
                              file.getName() + " is not PCM audio");
	   frameSize = format.getFrameSize(); 
	   DataLine.Info info = new DataLine.Info (SourceDataLine.class, format); 
	   
	   line = (SourceDataLine) AudioSystem.getLine (info); 
	   line.open(); 
	   
	   playThread = new Thread (this); 
	   playing = true; 
	   
	   notYetEOF = true;        
	   playThread.start();
	}
	public void run() {
		int readPoint = 0;
		int bytesRead = 0;
		try {
			while (notYetEOF) {
				if(GameData.musicMuted)stop();
				else start();
				if (playing) {
					bytesRead = in.read (buffer, 
								readPoint, 
								buffer.length - readPoint);
                   if (bytesRead == -1) { 
                	   	notYetEOF = false; 
                	   	break;
                   }
					// how many frames did we get,
					// and how many are left over?
					int frames = bytesRead / frameSize;
					int leftover = bytesRead % frameSize;
					// send to line
					line.write (buffer, readPoint, bytesRead-leftover);
					// save the leftover bytes
					System.arraycopy (buffer, bytesRead,
							  buffer, 0, 
							  leftover); 
	                    readPoint = leftover;
				} else { 
					// if not playing                   
					// Thread.yield(); 
					try { Thread.sleep (10);} 
					catch (InterruptedException ie) {}
				}
			} // while notYetEOF
			line.drain();
			line.stop();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			
			GameData.songNum++;
//			HUFF HUFF HUFF
//			TODO Auto-generated catch block
			try {
				new BigSound(new File("resources/sounds/"+GameData.songs[GameData.songNum%2])).start();
			} catch (Exception e) {
				e.printStackTrace();
			}
			line.close();
		}
	} // run

	public void start() {
		playing = true;
		if (! playThread.isAlive())
			playThread.start();
		line.start();
	}

	public void stop() {
		playing = false;
		line.stop();
	}
   
	public SourceDataLine getLine() {
		return line;
	}

	public File getFile() {		
		return file; 
	} 
}
