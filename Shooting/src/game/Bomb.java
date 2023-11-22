package game;

import java.awt.Graphics;

public class Bomb extends Item{
	public Bomb(int x, int y)
	{
		 this.posX = x;
		 this.posY = y;
		 this.type = 3;
		 this.img = tk.getImage("resourses/sprites/2222.jpg");
	}
	public void draw(Graphics g) 
	{
	     super.draw(g);
	}
	public void move()
	{
	    super.move();
	}
}
