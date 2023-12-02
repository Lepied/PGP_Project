package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

public class EnemyBullet extends GameObject {
	Point pos;
	int speed;
	int type;
	
	double direction;
	
	EnemyBullet(int posX, int posY, int speed, int type, int angle)
	{
		this.type = type;
		this.speed = speed;
		pos = new Point(posX,posY);
		//this.direction = direction;
		this.direction = Math.toRadians(angle);
		if(this.type == 1)
		{
			this.img = tk.getImage("resourses/sprites/Fireball.png");
		    this.width = 20;
		    this.height = 30;
		}
	}
	public void move()
	{
		
		pos.x += (double)(speed * Math.cos(direction));
		pos.y += (double)(speed * Math.sin(direction));

		/*
		if(type == 1)
		{
			pos.y+=3;
		}
		else if(type == 2)
		{
			pos.y+=3;
		}
		*/

	}
	public void draw(Graphics g)
	{
	    Graphics2D g2d = (Graphics2D) g.create();

	    // 이미지를 각도에 따라 회전
	    g2d.rotate(direction, pos.x + width / 2, pos.y + height / 2);

	    g2d.drawImage(img, pos.x, pos.y, width, height, null);

	    g2d.dispose();
	}


}
