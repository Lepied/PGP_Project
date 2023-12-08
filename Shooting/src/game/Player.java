package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Player extends GameObject implements KeyListener {
    
    boolean KeyUp = false; //키보드 입력 처리를 위한 변수
    boolean KeyDown = false;
    boolean KeyLeft = false;
    boolean KeyRight = false;
    
    boolean isAttack = false;
    boolean isBomb = false;
    boolean isKeySlow =false;
    
    boolean isDamaged = false; //맞았을때

    int playerHP = 3;
    int playerDamage;
    int attackSpeed;
    int lazerDelay;
    int bombDelay;
    
    int bomb;
    int bombDamage;
    private long lastAttackTime; // 공격시간 저장 변수
    private long lastBombTime; // 폭탄 지연시간
    
    private Timer animationTimer;
    private int currentFrame  = 0;
    private int totalFrames = 8;
    private ImageIcon[] playerSprites ;
    GameManager gameManager;
    

    
	 //임시 총알
	ArrayList Bullet_List = new ArrayList();
	 
	Bullet bullet;
	 //임시 총알
	Enemy en;
	//임시 적
	
    int x =100;
    int y =100;

    
    private int mouseX, mouseY;
	private double angle = 0;
	
	public int lineShot;
	public int diaShot;
	public boolean isImmortal;
	public int attackType; // 1 = 불(일반탄) , 2= 전기(레이저)

    
    public Player(int damage) {

    	this.playerDamage = damage;
        this.hp = playerHP;
        this.speed = 10;
        this.posX = 300;
        this.posY = 600;
        this.width = 35;
        this.height = 35;
    	this.attackSpeed = 300;
    	this.lazerDelay = attackSpeed * 5;
    	this.lineShot = 1;
    	this.diaShot = 1;
    	this.attackType = 2;
    	this.bomb = 0;
    	this.bombDamage = 500;
    	this.bombDelay = 100;
    	
    	playerSprites = new ImageIcon[totalFrames];
    	for(int i = 0; i<totalFrames; i++)
    	{
    		playerSprites[i] = new ImageIcon("resourses/sprites/Player/PlayableCharacter-Sheet"+(i+1)+".png");
    	}
       
    	animationTimer = new Timer(100,e->updateAnimation());
    	animationTimer.start();
      
        this.lastAttackTime = System.currentTimeMillis();
        this.lastBombTime = System.currentTimeMillis();
      
 
       
    }
    
    public void setAngle(double angle)
    {
    	this.angle =angle;
    }
    public double getAngle()
    {
    	return angle;
    }
    

    public void move(int dx, int dy) {
    	posX += dx;
    	posY += dy;
    }

    public void draw(Graphics g) {
  
    	 if (isDamaged) 
    	 {
    	     Graphics2D g2d = (Graphics2D) g;
    	     float alpha = 0.5f; // 원하는 투명도 값 (0.0f: 완전 투명, 1.0f: 완전 불투명)
    	     g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    	     g2d.drawImage(img, posX, posY, null);
    	     g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f)); // 투명도를 원래 상태로 복원
    	 }
    	 else
    	 {
    		 g.drawImage(img, posX, posY, null);    	
    	 }
    	        	
      	if(isKeySlow)
    	{
      		
    		g.drawRoundRect(posX+width/2-5, posY+height/2-5, 10, 10, 3, 3);
    		g.setColor(Color.red);
    	}
    }
    public void drawBullet(Graphics g)
    {
    	switch(attackType)
    	{
    		case 1:
    	    	for (int i=0; i<Bullet_List.size(); ++i)
    	    	{
    	    		bullet =(Bullet)(Bullet_List.get(i));
    	    		g.drawImage(bullet.img, bullet.pos.x,bullet.pos.y+35,
    	    				bullet.pos.x+35,bullet.pos.y+95,15,15,35,60,null );
    	   
    	    	}
    			break;
    		case 2:
    	    	for (int i=0; i<Bullet_List.size(); ++i)
    	    	{
    	    		bullet =(Bullet)(Bullet_List.get(i));
    	    		if(playerDamage < 30)
    	    		{
        	    		g.drawImage(bullet.img, this.posX-30,this.posY-900,null );
    	    		}
    	    		if(playerDamage >=30)
    	    		{
    	    			if(lineShot==2)
    	    			{
    	    				System.out.println("오오옹");
    	    				bullet.img = tk.getImage("resourses/sprites/Lightning3.png"); 
    	    			}
    	    			if(lineShot >2)
    	    			{
    	    				System.out.println("오오옹");
    	    				bullet.img = tk.getImage("resourses/sprites/Lightning4.png"); 
    	    			}
    	    			g.drawImage(bullet.img,this.posX-30, this.posY-900,null );
    	    		}
    
    	   
    	    	}
    			break;
    	}

    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
			case KeyEvent.VK_W :
				KeyUp = true;
				break;
			case KeyEvent.VK_S :
				KeyDown = true;
				break;
			case KeyEvent.VK_A :
				KeyLeft = true;
				break;
			case KeyEvent.VK_D :
				KeyRight = true;
				break;
			case KeyEvent.VK_SPACE :
				isAttack = true;
				break;
			case KeyEvent.VK_F :
				isBomb = true;
				break;
			case KeyEvent.VK_SHIFT:
				isKeySlow = true;
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()){
			case KeyEvent.VK_W :
				KeyUp = false;
				break;
			case KeyEvent.VK_S :
				KeyDown = false;
				break;
			case KeyEvent.VK_A :
				KeyLeft = false;
				break;
			case KeyEvent.VK_D :
				KeyRight = false;
				break;
			case KeyEvent.VK_SPACE :
				isAttack = false;
				break;
			case KeyEvent.VK_SHIFT:
				isKeySlow = false;
				break;

		}
	}
	public void KeyProcess()
	{
		//실제로 캐릭터 움직임 실현을 위해
		//위에서 받아들인 키값을 바탕으로
		//키 입력시마다 5만큼의 이동을 시킨다.
		
		if(isKeySlow)
		{
			if(KeyUp == true) posY -= speed/2;
			if(KeyDown == true) posY += speed/2;
			if(KeyLeft == true) posX -= speed/2;
			if(KeyRight == true) posX += speed/2;
		}
		else 
		{
			if(KeyUp == true) posY -= speed;
			if(KeyDown == true) posY += speed;
			if(KeyLeft == true) posX -= speed;
			if(KeyRight == true) posX += speed;
		}

		
		if(isBomb == true)
		{
			UseBomb();
			isBomb = false;
		}
			
	}
    private void updateAnimation() {
        currentFrame = (currentFrame + 1) % totalFrames;
        this.img = playerSprites[currentFrame].getImage();

    }
	
	public void BulletProcess()
	{
		if (isAttack && attackType == 1 && System.currentTimeMillis() - lastAttackTime > attackSpeed) {
			// 직선 총알개수 * 대각 총알개수 (대각 총알은 직선 총알이 그냥 대각으로 더나가는거.)
			switch (lineShot) {
			case 1:
				bullet = new Bullet(posX, posY - 65, 5, 1, angle);
				Bullet_List.add(bullet);

				break;
			case 2:

				for (int i = 0; i < lineShot; ++i) {
					bullet = new Bullet(posX - 10 + i * 20, posY - 65, 5, 1, angle);
					Bullet_List.add(bullet);
				}

				break;
			case 3:

				for (int i = 0; i < lineShot; ++i) {
					bullet = new Bullet(posX - 30 + i * 20, posY - 65, 5, 1, angle);
					Bullet_List.add(bullet);
				}

				break;
			default:
				for (int i = 0; i < lineShot; ++i) {
					bullet = new Bullet(posX - 30 + i * 20, posY - 65, 5, 1, angle);
					Bullet_List.add(bullet);
				}

				break;
			}

			lastAttackTime = System.currentTimeMillis();
		}
		if (isAttack && attackType == 2 && System.currentTimeMillis() - lastAttackTime > lazerDelay) {

			bullet = new Bullet(posX, 5, 5, 2, angle);
			Bullet_List.add(bullet);
			
			lastAttackTime = System.currentTimeMillis();
		}

		for (int i = 0; i < Bullet_List.size(); ++i) {
			bullet = (Bullet) (Bullet_List.get(i));
			bullet.move();

			// 화면 밖으로 나가면 제거
			if (bullet.pos.y < 0) {
				Bullet_List.remove(i);
			}
			if (bullet.type == 2) { // 각 총알객체의 타이머가 0.2초지나면 제거
				if (System.currentTimeMillis() - bullet.creationTime >= 200) {
					Bullet_List.remove(i);
				}
			}

			for (int j = 0; j < GameManager.getInstance().getGameObjectList().size(); ++j) {
				en = (Enemy) GameManager.getInstance().getGameObjectList().get(j);

				if (GameManager.getInstance().isBulletCollision(bullet, en)) {
					if (bullet.type == 1) {
						Bullet_List.remove(i);
					}
					GameManager.getInstance().applyDamage(en, playerDamage);
					// GameManager.getInstance().getGameObjectList().remove(j);
				}
			}
		}

	}
	
	public void UseBomb()
	{
		boolean tempBomb = false;

		if(bomb>0 && System.currentTimeMillis() - lastBombTime > bombDelay)
		{
			tempBomb = true;
			System.out.println("폭탄ㅍㅍㅍㅍㅍㅍ");

			for(int i=0; i<GameManager.getInstance().getGameObjectList().size(); ++i)
			{ 	
				System.out.println(GameManager.getInstance().getGameObjectList().size());
				en = (Enemy) GameManager.getInstance().getGameObjectList().get(i);
				GameManager.getInstance().applyDamage(en,bombDamage);
				i--;
			}
			
		}
		if(tempBomb)
		{
			bomb--;
			tempBomb = false;
		}
		
	
	}




}