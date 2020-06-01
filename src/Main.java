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

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import javax.swing.JFrame;
import javax.swing.JPanel;

import audio.MusicPlayer;

public class Main extends JPanel {

	   
    public static void main(String[] args) {
    	
    	ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

    	Gameplay gm = new Gameplay();
    	MusicPlayer music = new MusicPlayer();
    	
    	
    	JFrame jf = new JFrame();
        jf.setSize(555,555);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(gm);
      
        jf.setVisible(true);
       
        executor.execute(gm);
        executor.execute(music);
        new Gameplay();
    }

}
