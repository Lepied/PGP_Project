package Unit;

import java.awt.Graphics;

import game.Enemy;

public class E_Wybern extends Enemy{

	public E_Wybern(int spawnPoint)
	{
		super(spawnPoint);
		this.type = 2; // 정예 타입
        this.hp = 2000;
        this.width = 200; //충돌관리 위한 이미지 너비 높이등
        this.height = 180;
        this.speed = 1;
        this.img = tk.getImage("resourses/sprites/Dragonfolly.png");
        this.attackSpeed = 5000;
        this.Pattern=0;
        
        if (spawnPoint == 1) {
            this.posX = 200;
            this.posY = 0;
        } else if (spawnPoint == 2) {
            this.posX = 400;
            this.posY = 0;
        } else if (spawnPoint == 3) {
            this.posX = 0;
            this.posY = 0;
        }
	}
    public void draw(Graphics g) {
       super.draw(g);
    }
    public void move()
    {
    	super.move();
    }

}
