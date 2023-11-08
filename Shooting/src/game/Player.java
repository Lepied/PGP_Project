package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements KeyListener {
    private int x;
    private int y;
    private Image image;
    private int health;
    
    private int speed = 5;

    public Player(int x, int y, Image image, int initialHealth) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.health = initialHealth;
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
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
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT) {
            x -= speed;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            x += speed;
        } else if (keyCode == KeyEvent.VK_UP) {
            y -= speed;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            y += speed;
        }

       // 화면 다시 그리기
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

    // 플레이어 체력 및 관련 메서드
}