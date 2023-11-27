package Unit;

import java.awt.Graphics;

import game.Enemy;

public class E_Wybern extends Enemy{

	public E_Wybern(int spawnPoint)
	{
		super(spawnPoint);
		this.type = 1; // 일반 타입
        this.hp = 40;
        this.width = 35; //충돌관리 위한 이미지 너비 높이등
        this.height = 35;
        this.speed = 1;
        this.img = tk.getImage("resourses/sprites/f2.jpg");
        this.attackSpeed = 5000;
        
        if (spawnPoint == 1) {
            this.posX = 300;
            this.posY = 50;
        } else if (spawnPoint == 2) {
            this.posX = 500;
            this.posY = 50;
        } else if (spawnPoint == 3) {
            this.posX = 100;
            this.posY = 50;
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