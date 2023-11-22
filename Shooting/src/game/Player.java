package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Player extends GameObject implements KeyListener {
    
    boolean KeyUp = false; //키보드 입력 처리를 위한 변수
    boolean KeyDown = false;
    boolean KeyLeft = false;
    boolean KeyRight = false;
    
    boolean isAttack = false;

    int playerHP = 100;
    int playerDamage;
    int attackSpeed;
    private long lastAttackTime; // 공격시간 저장 변수
    
    GameManager gameManager;
    
	 //임시 총알
	ArrayList Bullet_List = new ArrayList();
	 
	Bullet bullet;
	 //임시 총알
	Enemy en;
	//임시 적
	
    int x =100;
    int y =100;

	 
    public Player(int damage) {
    	this.playerDamage = damage;
        this.hp = playerHP;
        this.speed = 10;
        this.posX = 300;
        this.posY = 600;
        this.width = 35;
        this.height = 35;
    	this.attackSpeed = 100;
        this.img = tk.getImage("resourses/sprites/f2.jpg");
      
        this.lastAttackTime = System.currentTimeMillis();
        
    }

    public void move(int dx, int dy) {
    	posX += dx;
    	posY += dy;
    }

    public void draw(Graphics g) {
        g.drawImage(img, posX, posY, null);
    }
    public void drawBullet(Graphics g)
    {
    	for (int i=0; i<Bullet_List.size(); ++i)
    	{
    		bullet =(Bullet)(Bullet_List.get(i));
    		g.drawImage(bullet.img, bullet.pos.x,bullet.pos.y+35,
    				bullet.pos.x+35,bullet.pos.y+95,15,15,35,60,null );
   
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
		}
	}
	public void KeyProcess()
	{
		//실제로 캐릭터 움직임 실현을 위해
		//위에서 받아들인 키값을 바탕으로
		//키 입력시마다 5만큼의 이동을 시킨다.
		
		if(KeyUp == true) posY -= speed;
		if(KeyDown == true) posY += speed;
		if(KeyLeft == true) posX -= speed;
		if(KeyRight == true) posX += speed;
		if(isAttack == true)
		{
			this.img = tk.getImage("resourses/sprites/2222.jpg");
		}
		else if(isAttack == false)
		{
			this.img = tk.getImage("resourses/sprites/f2.jpg");
		}
			
	}
	public void BulletProcess()
	{
		if(isAttack && System.currentTimeMillis() - lastAttackTime > attackSpeed)
		{
			bullet = new Bullet(posX,posY-65,5,1);
			Bullet_List.add(bullet);
			lastAttackTime = System.currentTimeMillis();
		}
		for(int i=0; i<Bullet_List.size();++i) 
		{
			bullet =(Bullet)(Bullet_List.get(i));
			bullet.move();
			
			if(bullet.pos.y < 0)
			{
				System.out.println("총알삭제");
				Bullet_List.remove(i);
			}
			for(int j=0; j<GameManager.getInstance().getGameObjectList().size(); ++j)
			{ 	
				en = (Enemy) GameManager.getInstance().getGameObjectList().get(j);
				
				if(GameManager.getInstance().isBulletCollision(bullet,en))
				{
					System.out.println("충돌");
					Bullet_List.remove(i);
					GameManager.getInstance().applyDamage(en,playerDamage);
					//GameManager.getInstance().getGameObjectList().remove(j);
				}
			}
		}
			

		
		
		
	}
 
}