package game;

import java.awt.Graphics;
import java.awt.Point;

public class Bullet extends GameObject {
	Point pos;
	int speed;
	int type;
	
	Bullet(int x, int y, int speed, int type)
	{
	    this.width = 20;
	    this.height = 45;
		this.type = type;
		this.speed = speed;
		pos = new Point(x,y);
		if(this.type == 1)
		{
			this.img = tk.getImage("resourses/sprites/EFX.png");
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


}
