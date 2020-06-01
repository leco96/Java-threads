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

public class Player2 extends JPanel implements KeyListener{
	private int x, y;
	private boolean player2right = false;
	private boolean player2left = false;
	private boolean player2down = false;
	private boolean player2up = true;
	private ImageIcon player2;
	
	//private PlayerBullet player2Bullet = null;
	private boolean player2Shoot = false;
	//private String bulletShootDir2 = "";
	private SoundPlayer sound;
	private int vidas;
	private boolean bandera =true;
	
	
	// Set of currently pressed keys
	private final Set<Integer> pressed = new HashSet<Integer>();
	
	//disparos
	ArrayList<PlayerBullet> Bullet = new ArrayList<PlayerBullet>();
	
	public Player2() {
		x = 100;
		y = 50;
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
		// draw player 2
		if(player2up)
			player2=new ImageIcon("player2_tank_up.png");	
		else if(player2down)
			player2=new ImageIcon("player2_tank_down.png");
		else if(player2right)
			player2=new ImageIcon("player2_tank_right.png");
		else if(player2left)
			player2=new ImageIcon("player2_tank_left.png");
					
		player2.paintIcon(this, g, getPosX(), getPosY());
		
		//BULLETS
		if(Bullet.size() > 0)
		{
			PlayerBullet anterior = Bullet.get(Bullet.size() -1 );
			if(anterior.get_bulletShotDir1().equals(""))
			{
				if(player2up)
				{					
					anterior.set_bulletShotDir1("up");
				}
				else if(player2down)
				{					
					anterior.set_bulletShotDir1("down");
				}
				else if(player2right)
				{				
					anterior.set_bulletShotDir1("right");
				}
				else if(player2left)
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
		if(pressed.contains(KeyEvent.VK_M)  && bandera == true){
			
				if(player2up)
				{					
					PlayerBullet player1Bullet = new PlayerBullet(getPosX() + 20, getPosY());
					Bullet.add(player1Bullet);
				}
				else if(player2down)
				{					
					PlayerBullet player1Bullet = new PlayerBullet(getPosX() + 20, getPosY() + 40);
					Bullet.add(player1Bullet);
				}
				else if(player2right)
				{				
					PlayerBullet player1Bullet = new PlayerBullet(getPosX() + 40, getPosY() + 20);
					Bullet.add(player1Bullet);
				}
				else if(player2left)
				{			
					PlayerBullet player1Bullet = new PlayerBullet(getPosX(), getPosY() + 20);
					Bullet.add(player1Bullet);
				}
				bandera =false;
				sound.playSound();
			
		}
		//movimiento player 2
		if(pressed.contains(KeyEvent.VK_UP)){
			if(!(pressed.contains(KeyEvent.VK_W)|| pressed.contains(KeyEvent.VK_A) || pressed.contains(KeyEvent.VK_S)|| pressed.contains(KeyEvent.VK_D))){
				player2right = false;
				player2left = false;
				player2down = false;
				player2up = true;	
				if(!(y < 10))
					setPosY_Izquiera();
			}
			if(pressed.contains(KeyEvent.VK_W)|| pressed.contains(KeyEvent.VK_A) || pressed.contains(KeyEvent.VK_S)|| pressed.contains(KeyEvent.VK_D)){
					
				player2right = false;
				player2left = false;
				player2down = false;
				player2up = true;	
				if(!(y < 10))
					setPosY_Izquiera();
			}
		}
		//MOVIMIENTO
		if(pressed.contains(KeyEvent.VK_LEFT)){
			if(!(pressed.contains(KeyEvent.VK_W)|| pressed.contains(KeyEvent.VK_A) || pressed.contains(KeyEvent.VK_S)|| pressed.contains(KeyEvent.VK_D))){
				player2right = false;
				player2left = true;
				player2down = false;
				player2up = false;
				if(!(x < 10))
					setPosX_Izquierda();
			}
			if(pressed.contains(KeyEvent.VK_W)|| pressed.contains(KeyEvent.VK_A) || pressed.contains(KeyEvent.VK_S)|| pressed.contains(KeyEvent.VK_D)){
				player2right = false;
				player2left = true;
				player2down = false;
				player2up = false;
				if(!(x < 10))
					setPosX_Izquierda();
			}
			
			
		}
		if(pressed.contains(KeyEvent.VK_DOWN)){
			if(!(pressed.contains(KeyEvent.VK_W)|| pressed.contains(KeyEvent.VK_A) || pressed.contains(KeyEvent.VK_S)|| pressed.contains(KeyEvent.VK_D))){
				player2right = false;
				player2left = false;
				player2down = true;
				player2up = false;
				if(!(y > 540))
					setPosY_Derecha();
			}
			if(pressed.contains(KeyEvent.VK_W)|| pressed.contains(KeyEvent.VK_A) || pressed.contains(KeyEvent.VK_S)|| pressed.contains(KeyEvent.VK_D)){
				player2right = false;
				player2left = false;
				player2down = true;
				player2up = false;
				if(!(y > 540))
					setPosY_Derecha();
			}
			
			
			
		}
		if(pressed.contains(KeyEvent.VK_RIGHT)){
			if(!(pressed.contains(KeyEvent.VK_W)|| pressed.contains(KeyEvent.VK_A) || pressed.contains(KeyEvent.VK_S)|| pressed.contains(KeyEvent.VK_D))){
				player2right = true;
				player2left = false;
				player2down = false;
				player2up = false;
				if(!(x > 590))
					setPosX_Derecha();
			}
			if(pressed.contains(KeyEvent.VK_W)|| pressed.contains(KeyEvent.VK_A) || pressed.contains(KeyEvent.VK_S)|| pressed.contains(KeyEvent.VK_D)){
				player2right = true;
				player2left = false;
				player2down = false;
				player2up = false;
				if(!(x > 590))
					setPosX_Derecha();
			}
			
			
		}

		
	}
	
	
	public synchronized void keyReleased(KeyEvent e) {
		pressed.remove(e.getKeyCode());
		bandera = true;
	}
	
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
