package game;

import java.awt.Graphics;
import java.awt.Image;

public class Scroll extends Item{
	public Scroll(int x, int y) {

	 this.posX = x;
	 this.posY = y;
	 this.type = 2;
	 this.img = tk.getImage("resourses/sprites/Scroll-export.png");

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
