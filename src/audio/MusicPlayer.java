/*
 *  Multiplayer game using threads
    Copyright (C) 2020  Luis Enrique Canales

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 * */
package audio;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class MusicPlayer implements Runnable{

	
	
	//play the sound
	
	private void playSound() {
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(MusicPlayer.class.getResource("music.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
			playSound();
		
	}

}
