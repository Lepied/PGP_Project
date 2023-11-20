package game;

import java.awt.Graphics;
import java.awt.Image;

public class Enemy extends GameObject {
	
	public int type;  // 적의 타입, 엘리트인지 일반몹인지
	public int bulletType; // 적의 공격타입, 산탄형, 단발형 등
	public int spawnPoint;
	
	
	public Enemy(int spawnPoint)
	{
		super();
		this.spawnPoint = spawnPoint;
		GameManager.getInstance().addGameObject(this);
	}
	
	
    public void move() {
    	posY += speed;
    }
    
    public void draw(Graphics g) {
        g.drawImage(img, posX, posY, null);
    }
    
    public boolean Crash(GameObject obj1, GameObject obj2)
    {
    	return GameManager.getInstance().isCollision(obj1, obj2);
    }
}
