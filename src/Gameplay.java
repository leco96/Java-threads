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

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Gameplay  extends JPanel implements Runnable {

	int x2=100, y2=0,x,y;
	private final int DELAY = 50;
	private Thread animator;
	private Player1 p1;
	private Player2 p2;
	Image background = Toolkit.getDefaultToolkit().createImage("Background.jpg");
	
	public Gameplay() {
		p1 = new Player1();
		addKeyListener(p1);
		p2 = new Player2();
		addKeyListener(p2);
		setFocusable(true);
	}
	
	
	
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0,null);
        
        int fontSize = 25;
        g.setFont(new Font("TimesRoman", Font.BOLD, fontSize));
        g.setColor(Color.red);
        
        g.drawString("Player1: " + p1.get_vidas(), 10, 20);
        g.drawString("Player2: " + p2.get_vidas(), 350, 20);
        
        p2.paint(g);
        p1.paint(g);
        
        if(p1.get_vidas() <= 0) {
        	g.drawString("Player2 WIN", 175, 250);
        }else if(p2.get_vidas() <= 0) {
        	g.drawString("Player1 WIN " , 175, 250);
        }
       
        
    }
    
    @Override
    public void addNotify() {
        super.addNotify();
        animator = new Thread(this);
        animator.start();
    }

  

    public void run() {
        long beforeTime, timeDiff, sleep;
        boolean isPlaying = true;
        
        beforeTime = System.currentTimeMillis();
        while (isPlaying) {
            repaint();
            if(p2.get_posBullet_Y(p1.getPosY(),p1.getPosX())) {
            	 if(p1.get_vidas() > 0) {
            		 p1.set_vidas();
            	 }
            }
            if(p1.get_posBullet_Y(p2.getPosY(),p2.getPosX())) {
            	if(p2.get_vidas() > 0) {
            		p2.set_vidas();
            	}
            	
            }
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0)
                sleep = 2;
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }

            beforeTime = System.currentTimeMillis();
            if(p1.get_vidas() == 0 || p2.get_vidas() == 0) {
            	isPlaying = false;
            }
        }

    }
	    
	  
}
