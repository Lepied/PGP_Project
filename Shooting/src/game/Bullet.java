package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Bullet extends GameObject {
	Point pos;
	int speed;
	int type;
	
	private double direction;
	
	public long creationTime;
	
    private Timer animationTimer;
    private int currentFrame  = 0;
    private int totalFrames = 2;
    private ImageIcon[] lazerSprites ;
	
	Bullet(int posX, int posY, int speed, int type, double direction)
	{
		this.type = type;
		this.speed = speed;
		pos = new Point(posX,posY);
		this.direction = direction;
		 lazerSprites = new ImageIcon[totalFrames];
		 for(int i = 0; i<totalFrames; i++)
		 {
			 lazerSprites[i] = new ImageIcon("resourses/sprites/Lightning"+(i+1)+".png");
		 }
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
		    this.height = 1000;
			animationTimer = new Timer(50,e->updateAnimation());
		    animationTimer.start();
		}
		 this.creationTime = System.currentTimeMillis(); 

		 

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
	
	private void updateAnimation() {
	  currentFrame = (currentFrame + 1) % totalFrames;
	  this.img = lazerSprites[currentFrame].getImage();

	}


}
