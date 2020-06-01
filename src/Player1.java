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

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import audio.SoundPlayer;

public class Player1 extends JPanel implements  KeyListener {
	private int x, y;
	private boolean player1right = false;
	private boolean player1left = false;
	private boolean player1down = false;
	private boolean player1up = true;
	private ImageIcon player1;
	private boolean player1Shoot = false;
	private SoundPlayer sound;
	private boolean bandera=true;
	private int vidas;
	
	
	// Set of currently pressed keys
	private final Set<Integer> pressed = new HashSet<Integer>();
	
	//disparos
	ArrayList<PlayerBullet> Bullet = new ArrayList<PlayerBullet>();
	
	public Player1() {
		x = 0;
		y = 100;
		sound = new SoundPlayer();
		vidas=3;
	}
	
	public int get_vidas() {
		return vidas;
	}
	
	void set_vidas() {
		this.vidas -= 1;
	}
	
	public void paint(Graphics g) {
		if(player1up)
			player1=new ImageIcon("player1_tank_up.png");	
		else if(player1down)
			player1=new ImageIcon("player1_tank_down.png");
		else if(player1right)
			player1=new ImageIcon("player1_tank_right.png");
		else if(player1left)
			player1=new ImageIcon("player1_tank_left.png");
			
		player1.paintIcon(this, g, getPosX(), getPosY());
		
		//bullets
		if(Bullet.size() > 0){
			PlayerBullet anterior = Bullet.get(Bullet.size() -1 );
			if(anterior.get_bulletShotDir1().equals(""))
			{
				if(player1up)
				{					
					anterior.set_bulletShotDir1("up");
				}
				else if(player1down)
				{			
					anterior.set_bulletShotDir1("down");
				}
				else if(player1right)
				{				
					anterior.set_bulletShotDir1("right");
				}
				else if(player1left)
				{			
					anterior.set_bulletShotDir1("left");
				}
			}
			else
			{
				for(int i =0 ; i<Bullet.size();i++) {
					Bullet.get(i).move(Bullet.get(i).get_bulletShotDir1());
					Bullet.get(i).draw(g);
				}
			
			}
			
			//reset bullet
			if(anterior.getY() < 1 || anterior.getY() > 580 || anterior.getX() < 1 || anterior.getX() > 630)
			{
				Bullet.remove(anterior);
			
			}
		}
		
	}

	void setPosX_Izquierda() {
		this.x-=10;
	}
	
	void setPosX_Derecha() {
		this.x+=10;
	}
	
	void setPosY_Izquiera() {
		this.y = y -10;;
	}
	
	void setPosY_Derecha(){
		this.y+=10;
	}
	
	public int getPosX() {
		return x;
	}
	
	public int getPosY() {
		return y;
	}
	
	@Override
	 public synchronized void keyPressed(KeyEvent e) {
		pressed.add(e.getKeyCode());
		if(pressed.contains(KeyEvent.VK_U) && bandera == true){
			
			
				if(player1up)
				{					
					PlayerBullet player1Bullet = new PlayerBullet(getPosX() + 20, getPosY());
					Bullet.add(player1Bullet);
				}
				else if(player1down)
				{					
					PlayerBullet player1Bullet = new PlayerBullet(getPosX() + 20, getPosY() + 40);
					Bullet.add(player1Bullet);
				}
				else if(player1right)
				{				
					PlayerBullet player1Bullet = new PlayerBullet(getPosX() + 40, getPosY() + 20);
					Bullet.add(player1Bullet);
				}
				else if(player1left)
				{			
					PlayerBullet player1Bullet = new PlayerBullet(getPosX(), getPosY() + 20);
					Bullet.add(player1Bullet);
				}
				
				bandera =false;
				
				sound.playSound();
			
			
		}
		//movimiento Player1
		if(pressed.contains(KeyEvent.VK_W)){
			if(!(pressed.contains(KeyEvent.VK_UP)|| pressed.contains(KeyEvent.VK_LEFT) || pressed.contains(KeyEvent.VK_DOWN)|| pressed.contains(KeyEvent.VK_RIGHT))){
				player1right = false;
				player1left = false;
				player1down = false; 
				player1up = true;
				if(!(y < 10)) {
					setPosY_Izquiera();
					
					}
				}
			if(pressed.contains(KeyEvent.VK_UP) || pressed.contains(KeyEvent.VK_LEFT) || pressed.contains(KeyEvent.VK_DOWN)|| pressed.contains(KeyEvent.VK_RIGHT)){
				player1right = false;
				player1left = false;
				player1down = false; 
				player1up = true;
				if(!(y < 10)) {
					setPosY_Izquiera();
					
				}
			}
		}
		if(pressed.contains(KeyEvent.VK_A)){
			if(!(pressed.contains(KeyEvent.VK_UP)|| pressed.contains(KeyEvent.VK_LEFT) || pressed.contains(KeyEvent.VK_DOWN)|| pressed.contains(KeyEvent.VK_RIGHT))){
				player1right = false;
				player1left = true;
				player1down = false;
				player1up = false;
				if(!(x < 10))
					setPosX_Izquierda();
			}
			if(pressed.contains(KeyEvent.VK_UP) || pressed.contains(KeyEvent.VK_LEFT) || pressed.contains(KeyEvent.VK_DOWN)|| pressed.contains(KeyEvent.VK_RIGHT)){
				player1right = false;
				player1left = true;
				player1down = false;
				player1up = false;
				if(!(x < 10))
					setPosX_Izquierda();
			}
							
		}
		if(pressed.contains(KeyEvent.VK_S)){
			if(!(pressed.contains(KeyEvent.VK_UP)|| pressed.contains(KeyEvent.VK_LEFT) || pressed.contains(KeyEvent.VK_DOWN)|| pressed.contains(KeyEvent.VK_RIGHT))){
				player1right = false;
				player1left = false;
				player1down = true;
				player1up = false;
				if(!(y > 540))
					setPosY_Derecha();
			}
			if(pressed.contains(KeyEvent.VK_UP) || pressed.contains(KeyEvent.VK_LEFT) || pressed.contains(KeyEvent.VK_DOWN)|| pressed.contains(KeyEvent.VK_RIGHT)){
				player1right = false;
				player1left = false;
				player1down = true;
				player1up = false;
				if(!(y > 540))
					setPosY_Derecha();
			}

				
		}
		if(pressed.contains(KeyEvent.VK_D)){
			if(!(pressed.contains(KeyEvent.VK_UP)|| pressed.contains(KeyEvent.VK_LEFT) || pressed.contains(KeyEvent.VK_DOWN)|| pressed.contains(KeyEvent.VK_RIGHT))){
				player1right = true;
				player1left = false;
				player1down = false;
				player1up = false;
				if(!(x > 590))
					setPosX_Derecha();
			}
			if(pressed.contains(KeyEvent.VK_UP) || pressed.contains(KeyEvent.VK_LEFT) || pressed.contains(KeyEvent.VK_DOWN)|| pressed.contains(KeyEvent.VK_RIGHT)){
				player1right = true;
				player1left = false;
				player1down = false;
				player1up = false;
				if(!(x > 590))
					setPosX_Derecha();
			}

		}
		
	}
	
	@Override
	public synchronized void keyReleased(KeyEvent e) {
	    pressed.remove(e.getKeyCode());
	    bandera = true;
	    
	  }
	
	@Override
	public void keyTyped(KeyEvent e) {}

	//METODOS PARA SABER SI UNA BULLET COLISIONO
		public boolean get_posBullet_Y(int poYPlayer1,int poxPlayer1) {
			for(int i =0 ; i<Bullet.size();i++) {
				if(Bullet.get(i).getY() > poYPlayer1-30 && Bullet.get(i).getY() < poYPlayer1+40 && Bullet.get(i).getX() > poxPlayer1 && Bullet.get(i).getX() < poxPlayer1+40) {
					Bullet.remove(i);
					return true;
				}
			}
			
			return false;
			
		}
	

}