package Unit;

import java.awt.Graphics;

import game.Enemy;

public class Ch1Boss extends Enemy{

	public Ch1Boss(int spawnPoint) {
		super(spawnPoint);
		this.type = 3; // 보스 타입
        this.hp = 500;
        this.width = 1000; //충돌관리 위한 이미지 너비 높이등
        this.height = 100;
        this.speed = 1;
        this.img = tk.getImage("resourses/sprites/DragonUpscale.png");
        this.attackSpeed = 5000;
        
        this.posX = 300;
        this.posY = 10;
	}

    public void draw(Graphics g) {
        super.draw(g);
     }
    
    public void move()
    {
    	super.move();
    }
}
