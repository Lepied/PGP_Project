package Unit;

import java.awt.Graphics;

import game.Enemy;

public class E_Zaco extends Enemy{

	public E_Zaco(int spawnPoint)
	{
		super(spawnPoint);
		this.type = 1; // 일반 타입
        this.hp = 30;
        this.width = 20; //충돌관리 위한 이미지 너비 높이등
        this.height = 20;
        this.speed = 1;
        this.img = tk.getImage("resourses/sprites/MonsterBird.jpg");
        this.attackSpeed = 10000;
        
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
