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
		GameManager.getInstance().setCoin(tempCoin+1);
		System.out.println("획득한 코인 수 : " +GameManager.getInstance().getCoin());
	}
	/*
	public void getBomb()
	{
		if(GameManager.getInstance().getBomb()<3)
		{
			tempBomb = GameManager.getInstance().getBomb();
			GameManager.getInstance().setBomb(tempBomb+1);
			System.out.println(GameManager.getInstance().getBomb());
		}
	}
	*/
	public void getScroll()
	{
		//스크롤 먹으면 잠깐 게임 스레드 자체를 멈추고 UI를 띄운다음에 선택지를 부여, 해당 선택지에 해당되는 스탯 업그레이드 구현하기
		System.out.println("스크롤 획득");
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
