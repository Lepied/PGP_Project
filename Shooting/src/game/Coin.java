package game;

import java.awt.Graphics;

public class Coin extends Item {

	
	public Coin(int x, int y)
	{
		super();
		this.posX = x;
		this.posY = y;
		this.type = 1;
		this.img = tk.getImage("resourses/sprites/Coin.png");
		
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
