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
        this.speed = 5;
        this.img = tk.getImage("resourses/sprites/MonsterFish.png");
        this.attackSpeed = 999999999;//공격안함
        
        if (spawnPoint == 1) {
            this.posX = 0;
            this.posY = 50;
        } else if (spawnPoint == 2) {
            this.posX = 600;
            this.posY = 50;
        } else if (spawnPoint == 3) {
            this.posX = 200;
            this.posY = 0;
        } else if (spawnPoint == 4) {
            this.posX = 400;
            this.posY = 0;
        } else if (spawnPoint == 5){
        	this.posX = 50;
        	this.posY = 0;
        } else if (spawnPoint == 6){
        	this.posX = 550;
        	this.posY = 0;
        }
        		
	}
    public void draw(Graphics g) {
       super.draw(g);
    }
    public void move()
    {
    	switch(spawnPoint)
    	{
    	case 1:
    		posY += speed;
    		posX += (speed-2);
    		break;
    	
    	case 2:
    		posY += speed;
    		posX -= (speed-2);
    		break;
    	case 3:
    		if(posY<200)
    		{
    			posY += speed;
    		}
    		else
    		{
    			posY += speed;
    			posX += speed;
    		}
    		break;
    		
    	case 4:
    		if(posY<200)
    		{
    			posY += speed;
    		}
    		else
    		{
        		posY += speed;
    			posX -= speed;
    		}

    		break;
    	case 5:
			posY += speed;
			if(posY >400)
			{
				posY-=speed/2;
			}

			break;
		case 6:
			posY += speed;
			if(posY >400)
			{
				posY-=speed/2;
			}

    	}
    }
    	
}
