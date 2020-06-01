package audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundPlayer  {
	
	//play the sound
	
		public void playSound() {
			try {
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(MusicPlayer.class.getResource("shot.wav"));
				Clip clip = AudioSystem.getClip();
				clip.open(audioIn);
				clip.start();
		    } catch(Exception ex) {
		        System.out.println("Error with playing sound.");
		        ex.printStackTrace();
		    }
		}
	

}
