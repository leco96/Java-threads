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
import java.awt.Graphics;
import audio.SoundPlayer;

public class PlayerBullet {
	private double x, y;
	private String bulletShootDir1;
	
	
	public PlayerBullet(double x, double y)
	{
		this.x = x;
		this.y = y;
		bulletShootDir1 = "";
		
	}
	

	
	public String get_bulletShotDir1() {
		return bulletShootDir1;
	}
	
	
	void  set_bulletShotDir1(String s) {
		 bulletShootDir1 = s;
	}
	
	
	//metodos
	public void move(String face)
	{
		if(face.equals("right"))
			x += 10;
		else if(face.equals("left"))
			x -= 10;
		else if(face.equals("up"))
			y -= 10;
		else
			y +=10;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.yellow);
		g.fillOval((int) x, (int) y, 10, 10);
	}
	
	public int getX()
	{
		return (int)x;
	}
	public int getY()
	{
		return (int)y;
	}

}
