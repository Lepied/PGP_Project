package game;

import java.awt.Graphics;
import java.awt.Image;

public class Item extends GameObject{
	public int type;
	
	private int tempCoin;
	private int tempScroll;
	private int tempBomb;
	
	private int frameIndex;
	private int maxFrames;
	private int animationSpeed;
	
	public Item()
	{
		super();
		this.width = 32;
		this.height = 32;
		

	}
	public void updateAnimation()
	{
		frameIndex = (frameIndex +1) % maxFrames;
	}
	
	public void getCoin()
	{
		tempCoin = GameManager.getInstance().getCoin();
		GameManager.getInstance().setCoin(tempCoin+GameManager.getInstance().getPlusCoin()+1);
		System.out.println(GameManager.getInstance().getCoin());
	}
	
	

	
	public void move()
	{
		posY += 1;
	}
	public void draw(Graphics g)
	{
		g.drawImage(img, posX, posY, null);
	}
	public void drawCoinAnimation(Graphics g)
	{
		g.drawImage(img,
				posX,
				posY, 
				posX+32, 
				posY+32, 
				frameIndex*0, 
				0, 
				frameIndex*32+32, 
				32, 
				null);
		 
	}
}
