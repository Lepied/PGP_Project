package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends GameObject implements KeyListener {
    
    boolean KeyUp = false; //키보드 입력 처리를 위한 변수
    boolean KeyDown = false;
    boolean KeyLeft = false;
    boolean KeyRight = false;

    int playerHP = 100;
   
    GameManager gameManager;
    
    public Player() {
        this.hp = playerHP;
        this.posX = 100;
        this.posY = 100;
        this.width = 35;
        this.height = 35;
        this.img = tk.getImage("resourses/sprites/f2.jpg");
      
        
        gameManager = GameManager.getInstance();
    }

    public void move(int dx, int dy) {
    	posX += dx;
    	posY += dy;
    }

    public void draw(Graphics g) {
        g.drawImage(img, posX, posY, null);
    }

    public void shoot() {
        // 총알 발사 로직
    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
			case KeyEvent.VK_UP :
				KeyUp = true;
				break;
			case KeyEvent.VK_DOWN :
				KeyDown = true;
				break;
			case KeyEvent.VK_LEFT :
				KeyLeft = true;
				break;
			case KeyEvent.VK_RIGHT :
				KeyRight = true;
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()){
			case KeyEvent.VK_UP :
				KeyUp = false;
				break;
			case KeyEvent.VK_DOWN :
				KeyDown = false;
				break;
			case KeyEvent.VK_LEFT :
				KeyLeft = false;
				break;
			case KeyEvent.VK_RIGHT :
				KeyRight = false;
				break;
		}
	}
	public void KeyProcess()
		{
		//실제로 캐릭터 움직임 실현을 위해
		//위에서 받아들인 키값을 바탕으로
		//키 입력시마다 5만큼의 이동을 시킨다.
		
			if(KeyUp == true) posY -= 5;
			if(KeyDown == true) posY += 5;
			if(KeyLeft == true) posX -= 5;
			if(KeyRight == true) posX += 5;
		
	
		}


 
}