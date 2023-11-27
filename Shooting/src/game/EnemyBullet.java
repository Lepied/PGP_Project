package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

public class EnemyBullet extends GameObject {
	Point pos;
	int speed;
	int type;
	
	private double direction;
	
	EnemyBullet(int posX, int posY, int speed, int type)
	{
		this.type = type;
		this.speed = speed;
		pos = new Point(posX,posY);
		//this.direction = direction;
		if(this.type == 1)
		{
			this.img = tk.getImage("resourses/sprites/EFX.png");
		    this.width = 20;
		    this.height = 30;
		}
	}
	public void move()
	{
		
		//pos.x += (double)(speed * Math.cos(direction));
		//pos.y += (double)(speed * Math.sin(direction));

		if(type == 1)
		{
			pos.y+=3;
		}
		else if(type == 2)
		{
			pos.y+=3;
		}

	}
	public void draw(Graphics g)
	{
		g.drawImage(img, posX, posY, null);
	}


}