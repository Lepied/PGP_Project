package game;

import java.awt.Graphics;

public class NPC extends GameObject{
	
	public NPC()
	{
		super();
		this.width = 60;
		this.height = 60;
		this.img = tk.getImage("resourses/sprites/NPC_lowQ.png");
		GameManager.getInstance().addNPC(this);
	}
	

	public void move()
	{
		posY += 1;
	}
	public void draw(Graphics g)
	{
		g.drawImage(img, posX, posY, null);
	}
}
