package game;

import java.awt.Graphics;

public class NPC extends GameObject{
	
	public NPC()
	{
		super();
		this.width = 60;
		this.height = 60;	
	}
	
	public void getNPC()
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
}
