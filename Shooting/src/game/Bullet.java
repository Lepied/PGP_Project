package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

public class Bullet extends GameObject {
	Point pos;
	int speed;
	int type;
	
	private double direction;
	
	Bullet(int posX, int posY, int speed, int type, double direction)
	{
		this.type = type;
		this.speed = speed;
		pos = new Point(posX,posY);
		this.direction = direction;
		if(this.type == 1)
		{
			this.img = tk.getImage("resourses/sprites/EFX.png");
		    this.width = 20;
		    this.height = 30;
		}
		if(this.type == 2)
		{
			this.img = tk.getImage("resourses/sprites/Lightning1.png");
		    this.width = 70;
		    this.height = 600;
		}
	}
	public void move()
	{
		
		switch(this.type)
		{
		case 1:
			pos.x += (double)(speed * Math.cos(direction));
			pos.y -= (double)(speed * Math.sin(direction));
			break;
		case 2:

			break;
		}
		


	}
	public void draw(Graphics g)
	{
		g.drawImage(img, posX, posY, null);
	}


}
