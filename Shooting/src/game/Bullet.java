package game;

import java.awt.Point;

public class Bullet {
	Point pos;
	int speed;
	int type;
	
	Bullet(int x, int y, int speed, int type)
	{
		this.type = type;
		this.speed = speed;
		pos = new Point(x,y);
	}
	public void move()
	{
		if(type == 1)
		{
			pos.y+=10;
		}
		else if(type == 2)
		{
			pos.y-=10;
		}
	}

}
