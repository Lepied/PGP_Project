package Unit;

import java.awt.Graphics;

import game.GameManager;
import game.GameObject;

public class NPC extends GameObject{
	
	public NPC()
	{
		super();
		this.posX = 200;
		this.width = 60;
		this.height = 100;
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
