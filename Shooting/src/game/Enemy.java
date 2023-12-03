package game;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.awt.Graphics2D;

public class Enemy extends GameObject {
	
	public int maxHealth; // 변하지 않는 최대체력
	public int type;  // 적의 타입, 엘리트인지 일반몹인지
	public int bulletType; // 적의 공격타입, 산탄형, 단발형 등
	public int spawnPoint;
	public int attackSpeed; //공격딜레이
	private long lastAttackTime; // 공격시간 저장 변수
	
	private int bulletCount = 5; //발사할 총알의 개수
	private int bulletAngle = 10; //발사할 총알의 각도
	
	public int Pattern;
	
	private boolean isPatternNow = false;
	private int patternCnt = 0;



	
	EnemyBullet enBullet;
	Player player; // 임시 플레이어
	private ArrayList<EnemyBullet> Enemy_Bullet_List = new ArrayList();
	
	public Enemy(int spawnPoint)
	{
		super();
		this.spawnPoint = spawnPoint;
		GameManager.getInstance().addEnemy(this);

	}
	
	
    public void move() {
    	if(this.type == 1)
    	{
        	posY += speed;
    	}
    	if(this.type == 3)
    	{
    		if(posY <100)
    		{
    			posY +=speed;
    		}
    	}
    }
    
    public void draw(Graphics g) {
        g.drawImage(img, posX, posY, null);
    }
    
    public boolean Crash(GameObject obj1, GameObject obj2)
    {
    	return GameManager.getInstance().isCollision(obj1, obj2);
    }
    
    public void Draw_EnemyBullet(Graphics g)
    {
    	
    	for (int i=0; i<Enemy_Bullet_List.size(); ++i)
    	{
    		enBullet =Enemy_Bullet_List.get(i);
    		double angle = enBullet.direction+4.8;
    	

            Graphics2D g2d = (Graphics2D) g.create();
            g2d.rotate(angle, enBullet.pos.x + enBullet.width / 2, enBullet.pos.y + enBullet.height / 2); // 이미지를 각도만큼 회전
            g2d.drawImage(enBullet.img, enBullet.pos.x, enBullet.pos.y,
                    enBullet.width, enBullet.height, null);


            g2d.dispose();
    		
   
    	}
    }
    
    public void BulletProcess() //총알 패턴들 모임
	{
		player = (Player) GameManager.getInstance().getPlayer();
		double deltaX = player.posX - this.posX;
	    double deltaY = player.posY - (this.posY + 35); // 총알 시작 위치에 대한 조정
	    double startAngle = Math.atan2(deltaY, deltaX);
    	if(this.bulletType ==3)  //플레이어 방향으로 산탄
    	{
    		switch(this.Pattern)
    		{
    		case 0:// 패턴: 부채꼴 모양으로 총알 발사
        		if (System.currentTimeMillis() - lastAttackTime > attackSpeed) {
                    
                    for (int i = 0; i < bulletCount; ++i) {
                        int angle = i * bulletAngle - (bulletCount - 1) * bulletAngle / 2;
                        int finalAngle = (int) Math.toDegrees(startAngle) + angle;
                        
                        enBullet = new EnemyBullet(this.posX, this.posY + 35, 5, 1, finalAngle);
                        Enemy_Bullet_List.add(enBullet);
                    }
                    patternCnt++;
                    lastAttackTime = System.currentTimeMillis();
 			        if(patternCnt > 2)
			        {
 			        
			        	patternCnt = 0;
			        	this.Pattern = 1;
			        }
                }
    			break;
    		case 1: // 원형 모양으로 발사
    				
    			   if (System.currentTimeMillis() - lastAttackTime > 100) {
    				    int tempAngle=0;
    			        // 패턴: 원형 모양으로 총알 발사
    			        for (int i = 0; i < 60; ++i) { //100판
    			            int finalAngle = (int) Math.toDegrees(startAngle) + i * (360 / 60);

    			            enBullet = new EnemyBullet(this.posX, this.posY + 35, 5, 1, finalAngle+tempAngle);
    			            Enemy_Bullet_List.add(enBullet);
    			            tempAngle = tempAngle +45;
    			        }
    			        patternCnt++;
    			        lastAttackTime = System.currentTimeMillis();
    			        if(patternCnt > 20)
    			        {
    			        	patternCnt =0;
    			        	this.Pattern =2;
    			        }
    			    }
    			break;
    		case 2: //다시 부채꼴
				if (System.currentTimeMillis() - lastAttackTime > attackSpeed) {

					for (int i = 0; i < bulletCount; ++i) {
						int angle = i * bulletAngle - (bulletCount - 1) * bulletAngle / 2;
						int finalAngle = (int) Math.toDegrees(startAngle) + angle;

						enBullet = new EnemyBullet(this.posX+100, this.posY + 35, 5, 1, finalAngle);
						Enemy_Bullet_List.add(enBullet);
					}

					for (int i = 0; i < bulletCount; ++i) {
						int angle = i * bulletAngle - (bulletCount - 1) * bulletAngle / 2;
						int finalAngle = (int) Math.toDegrees(startAngle) + angle;

						enBullet = new EnemyBullet(this.posX-10, this.posY + 35, 5, 1, finalAngle);
						Enemy_Bullet_List.add(enBullet);
					}
					patternCnt++;
					lastAttackTime = System.currentTimeMillis();
			        if(patternCnt > 3)
			        {
			        	patternCnt =0;
			        	this.Pattern =0;
			        }
                }
    			break;
    		case 3: //원형탄막에 플레이어 유도탄
    			
 			   if (System.currentTimeMillis() - lastAttackTime > 100) {
 				    int tempAngle=0;
 			        // 패턴: 원형 모양으로 총알 발사
 			        for (int i = 0; i < 60; ++i) { //100판
 			            int finalAngle = (int) Math.toDegrees(startAngle) + i * (360 / 60);

 			            enBullet = new EnemyBullet(this.posX, this.posY + 35, 5, 1, finalAngle+tempAngle);
 			            Enemy_Bullet_List.add(enBullet);
 			            tempAngle = tempAngle +45;
 			        }
 			        patternCnt++;
 			        lastAttackTime = System.currentTimeMillis();
 			        if(patternCnt > 20)
 			        {
 			        	patternCnt =0;
 			        	this.Pattern =2;
 			        }
 			    }
    		}

    	}
    	
    	else //아래방향으로 직진
    	{
    		if(System.currentTimeMillis() - lastAttackTime > attackSpeed)
    		{
    			enBullet = new EnemyBullet(this.posX,this.posY+35,5,1,90); //각도는 90도
    			Enemy_Bullet_List.add(enBullet);
    			lastAttackTime = System.currentTimeMillis();


    		}
    	}
		
    	//모든 총알 움직이게하기, 화면밖으로 나가면 제거시키기, 플레이어 피격 판정 두기
		for(int i=0; i<Enemy_Bullet_List.size();++i) 
		{
			enBullet =(EnemyBullet)(Enemy_Bullet_List.get(i));
			enBullet.move();
			
			
			//화면 밖으로 나가면 제거
			if(enBullet.pos.y < 0 || enBullet.pos.y >800 ||enBullet.pos.x <0 || enBullet.pos.x>600)
			{
				Enemy_Bullet_List.remove(i);
				System.out.println("총알제거");
			}
			
			//플레이어 피격
			player = (Player) GameManager.getInstance().getPlayer();
			
			if(player.isDamaged==false && GameManager.getInstance().isEnBulletCollision(enBullet,player))
			{
				System.out.println("플레이어 피격됨");
				player.isDamaged = true;
				Enemy_Bullet_List.remove(i);
				player.hp--;
			}
		}
		
	}
    
}
