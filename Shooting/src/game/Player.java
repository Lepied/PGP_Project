package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

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
    int maxPlayerHp;
    int playerDamage;
    int attackSpeed;
    int lazerDelay;
    int bombDelay;
    
    int bomb;
    int bombDamage;
    private long lastAttackTime; // 공격시간 저장 변수
    private long lastBombTime; // 폭탄 지연시간
    
    private Timer animationTimer;
    private Timer bombAnimationTimer;
	private Timer fireballExplosionTimer;
	private Timer enDeadAniTimer;
	
    private int currentPlayerFrame  = 0;
    private int totalPlayerFrames = 8;
    private int currentBombFrame = 0;
    private int totalBombFrames = 12;
	private int currentExplosionFrame = 0;
	private int totalExplosionFrames = 7;
	private int currentEnDeadFrame = 0;
	private int totalEnDeadFrames = 10;
	
    private ImageIcon bombEffect; 
    private ImageIcon[] playerSprites;
    private ImageIcon[] BombAnimation;
	private ImageIcon fireBallEffect;
	private ImageIcon[] fireballExplosion;
	private ImageIcon enDeadEffect;
	private ImageIcon[] enemyDeadAnimation;

	private int fireBallEffectPosX;
	private int fireBallEffectPosY;
    
    GameManager gameManager;
    SoundManager soundManager;
    
    private boolean drawBombEffect = false;
    private boolean drawFireBallEffect = false;

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

	private SwingWorker<Void, Void> soundWorker;

    public Player(int damage, int maxHP, int plusBombDamage) {

    	this.playerDamage = damage;
    	this.maxPlayerHp = 3+maxHP;
        this.hp = playerHP;
        this.speed = 10;
        this.posX = 300;
        this.posY = 600;
        this.width = 10;
        this.height = 10;
    	this.attackSpeed = 300;
    	this.lazerDelay = attackSpeed * 5;
    	this.lineShot = 1;
    	this.diaShot = 1;
    	this.attackType = 1;
    	this.bomb = 0;
    	this.bombDamage = 500 + plusBombDamage;
    	this.bombDelay = 100;
    	

    	soundManager = new SoundManager();
    
    	
    	bombEffect = new ImageIcon("resourses/sprites/BombEffect/BombEffect1.png");
    	fireBallEffect = new ImageIcon("resourses/sprites/FireballEffect/FBExplosion1.png");
    	enDeadEffect = new ImageIcon("resourses/sprites/enDeadEffect/EnDead1.png");
    	
    	playerSprites = new ImageIcon[totalPlayerFrames];
    	BombAnimation = new ImageIcon[totalBombFrames];
    	fireballExplosion = new ImageIcon[totalExplosionFrames];
    	enemyDeadAnimation = new ImageIcon[totalEnDeadFrames];
    	
       	for(int i = 0; i<totalEnDeadFrames; i++)
    	{
       		enemyDeadAnimation[i] = new ImageIcon("resourses/sprites/enDeadEffect/EnDead"+(i+1)+".png");
    	}
       
       	for(int i = 0; i<totalBombFrames; i++)
    	{
       		BombAnimation[i] = new ImageIcon("resourses/sprites/BombEffect/BombEffect"+(i+1)+".png");
    	}
    	for(int i = 0; i<totalPlayerFrames; i++)
    	{
    		playerSprites[i] = new ImageIcon("resourses/sprites/Player/PlayableCharacter-Sheet"+(i+1)+".png");
    	}
    	for(int i = 0; i<totalExplosionFrames; i++)
    	{
    		fireballExplosion[i] = new ImageIcon("resourses/sprites/FireballEffect/FBExplosion"+(i+1)+".png");
    	}
       
    	animationTimer = new Timer(100,e->updatePlayerAnimation());
    	animationTimer.start();
    	bombAnimationTimer = new Timer(50,e->updateBombAnimation());
    	fireballExplosionTimer = new Timer(50,e->updateFireballAnimation());
    	enDeadAniTimer = new Timer(50,e->updateEnDeadAnimation());
    	
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
    	 if (drawBombEffect) {
             g.drawImage(bombEffect.getImage(), 100, 0, bombEffect.getIconWidth() * 2,bombEffect.getIconHeight() * 2 , null);
         }
    	 if (drawFireBallEffect) {
             g.drawImage(fireBallEffect.getImage(), fireBallEffectPosX, fireBallEffectPosY, fireBallEffect.getIconWidth(),fireBallEffect.getIconHeight(), null);
         }
    	 if (GameManager.getInstance().drawEnDeadEffect) {
             g.drawImage(enDeadEffect.getImage(), GameManager.getInstance().enDeadEffectX,  GameManager.getInstance().enDeadEffectY, enDeadEffect.getIconWidth(),enDeadEffect.getIconHeight(), null);
         }
      	if(isKeySlow)
    	{
      		
    		g.drawRoundRect(posX+width/2+5, posY+height/2+5, 10, 10, 3, 3);
    		g.setColor(Color.red);
    	}
    }
    public void drawBullet(Graphics g)
    {
    	switch(attackType)
    	{
    		case 1:
    			 Graphics2D g2d = (Graphics2D) g.create();
    	    	for (int i=0; i<Bullet_List.size(); ++i)
    	    	{
    	    		bullet =(Bullet)(Bullet_List.get(i));
    	    		g.drawImage(bullet.img, bullet.pos.x,bullet.pos.y+35,null );
    	    		//g2d.rotate(Math.toRadians(bullet.direction), bullet.pos.x + width / 2, bullet.pos.y + height / 2);
    	    	    //g2d.drawImage(bullet.img, bullet.pos.x, bullet.pos.y+35, null);
    	    	  
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
    	    				bullet.img = tk.getImage("resourses/sprites/Lightning3.png"); 
    	    			}
    	    			if(lineShot >2)
    	    			{
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
    private void updatePlayerAnimation() {
        currentPlayerFrame = (currentPlayerFrame + 1) % totalPlayerFrames;
        this.img = playerSprites[currentPlayerFrame].getImage();

    }
    private void updateBombAnimation()
    {
    	currentBombFrame = (currentBombFrame + 1) % totalBombFrames;
    	bombEffect = BombAnimation[currentBombFrame];
    	if(currentBombFrame == 11)
    	{
    		 bombAnimationTimer.stop(); 
    		 drawBombEffect = false;
    	}
    }
    private void updateFireballAnimation()
    {
    	currentExplosionFrame = (currentExplosionFrame + 1) % totalExplosionFrames;
    	fireBallEffect = fireballExplosion[currentExplosionFrame];
    	if(currentExplosionFrame == 6)
    	{
    		fireballExplosionTimer.stop();
    		drawFireBallEffect = false;
    	}
    }
    private void updateEnDeadAnimation()
    {
    	currentEnDeadFrame = (currentEnDeadFrame + 1) % totalEnDeadFrames;
    	enDeadEffect = enemyDeadAnimation[currentEnDeadFrame];
    	if(currentEnDeadFrame == 6)
    	{
    		enDeadAniTimer.stop();
    		GameManager.getInstance().drawEnDeadEffect = false;
    	}
    }
	public void BulletProcess()
	{
		if (isAttack && attackType == 1 && System.currentTimeMillis() - lastAttackTime > attackSpeed) {
			soundManager.setEffectSound("resourses/Sound/SE/Fire_Magic.wav");
			// 직선 총알개수 * 대각 총알개수 (대각 총알은 직선 총알이 그냥 대각으로 더나가는거.)
			switch (lineShot) {
			case 1:
				bullet = new Bullet(posX, posY - 65, 5, 1, angle);
				Bullet_List.add(bullet);

				soundManager.setVFXVolume(0.85f);
				soundManager.play();
				break;
			case 2:
				
				for (int i = 0; i < lineShot; ++i) {
					bullet = new Bullet(posX - 10 + i * 20, posY - 65, 5, 1, angle);
					Bullet_List.add(bullet);
				}
				soundManager.setVFXVolume(0.85f);
				soundManager.play();
				break;
			case 3:
				
				for (int i = 0; i < lineShot; ++i) {
					bullet = new Bullet(posX - 30 + i * 20, posY - 65, 5, 1, angle);
					Bullet_List.add(bullet);
				}
				soundManager.setVFXVolume(0.85f);
				soundManager.play();
				break;
			default:
				
				for (int i = 0; i < lineShot; ++i) {
					bullet = new Bullet(posX - 30 + i * 20, posY - 65, 5, 1, angle);
					Bullet_List.add(bullet);
				}
				soundManager.setVFXVolume(0.85f);
				soundManager.play();
				break;
			}

			lastAttackTime = System.currentTimeMillis();
		}
		if (isAttack && attackType == 2 && System.currentTimeMillis() - lastAttackTime > lazerDelay) {
			soundManager.setEffectSound("resourses/Sound/SE/Lightning_Magic.wav");
			bullet = new Bullet(posX, 5, 5, 2, angle);
			Bullet_List.add(bullet);
			
			lastAttackTime = System.currentTimeMillis();
			soundManager.setVFXVolume(0.85f);
			soundManager.play();
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
				        fireBallEffectPosX = bullet.pos.x;
	                    fireBallEffectPosY = bullet.pos.y;
						drawFireBallEffect = true;
				        fireballExplosionTimer.start();
					}
					GameManager.getInstance().applyDamage(en, playerDamage);
					if(GameManager.getInstance().drawEnDeadEffect)
					{
						enDeadAniTimer.start();
					}
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
				
				en = (Enemy) GameManager.getInstance().getGameObjectList().get(i);
				GameManager.getInstance().applyDamage(en,bombDamage);
				i--;
			}
			if (!drawBombEffect) 
			{
	            drawBombEffect = true;
	            bombAnimationTimer.start(); 
	        }
			
			
			
			
		}
		if(tempBomb)
		{
			bomb--;
			tempBomb = false;
		}
		
	
	}



}