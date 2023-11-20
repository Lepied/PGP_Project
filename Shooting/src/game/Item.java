package game;

import java.awt.Graphics;

public class Item extends GameObject{
	public Item()
	{
		super();
	}
	
	public void applyEffect()
	{
		
	}
	
	public void move()
	{
		posY += 3;
	}
	public void draw(Graphics g)
	{
		g.drawImage(img, posX, posY, null);
	}
}
