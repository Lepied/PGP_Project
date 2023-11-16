package game;

import java.awt.Graphics;

public class E_Wybern extends Enemy{

	public E_Wybern()
	{
		
		this.type = 1; // 일반 타입
        this.hp = 50;
        this.posX = 300;
        this.posY = 200;
        this.width = 35;
        this.height = 35;
        this.img = tk.getImage("resourses/sprites/f2.jpg");
        
	}
    public void draw(Graphics g) {
       super.draw(g);
    }
    public void move()
    {
    	super.move();
    }
}
