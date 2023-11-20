package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

public class Bullet extends GameObject {
	Point pos;
	int speed;
	int type;
	
	Bullet(int posX, int posY, int speed, int type)
	{
		this.type = type;
		this.speed = speed;
		pos = new Point(posX,posY);
		if(this.type == 1)
		{
			this.img = tk.getImage("resourses/sprites/EFX.png");
		    this.width = 20;
		    this.height = 30;
		}
	}
	public void move()
	{
		if(type == 1)
		{
			pos.y-=10;
		}
		else if(type == 2)
		{
			pos.y-=10;
		}
	}
	public void draw(Graphics g)
	{
		g.drawImage(img, posX, posY, null);
	}


}
