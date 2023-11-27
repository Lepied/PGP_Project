package game;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class Enemy extends GameObject {
	
	public int type;  // 적의 타입, 엘리트인지 일반몹인지
	public int bulletType; // 적의 공격타입, 산탄형, 단발형 등
	public int spawnPoint;
	public int attackSpeed; //공격딜레이
	private long lastAttackTime; // 공격시간 저장 변수
	
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
    		g.drawImage(enBullet.img, enBullet.pos.x, enBullet.pos.y+25,
    				enBullet.pos.x+25,enBullet.pos.y+55,903,113,919,142,null);
   
    	}
    }
    
    public void BulletProcess()
	{
		if(System.currentTimeMillis() - lastAttackTime > attackSpeed)
		{
			enBullet = new EnemyBullet(this.posX,this.posY+35,5,1);
			Enemy_Bullet_List.add(enBullet);
			lastAttackTime = System.currentTimeMillis();
			System.out.println("적 총알 발사");

		}
		for(int i=0; i<Enemy_Bullet_List.size();++i) 
		{
			enBullet =(EnemyBullet)(Enemy_Bullet_List.get(i));
			enBullet.move();
			
			
			//화면 밖으로 나가면 제거
			if(enBullet.pos.y < 0 || enBullet.pos.y >800)
			{
				Enemy_Bullet_List.remove(i);
				System.out.println("총알제거");
			}
			
			player = (Player) GameManager.getInstance().getPlayer();
			
			if(GameManager.getInstance().isEnBulletCollision(enBullet,player))
			{
				System.out.println("플레이어 피격됨");
				Enemy_Bullet_List.remove(i);
			}
		}
		
	}
    
}
