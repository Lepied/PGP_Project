package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main extends JFrame implements Runnable{
	 private Image playerImage;
	 private Image playerBulletImage;
	 private Image enemyImage;
	 private Image enemyBulletImage;

	 
	 private Player player;
	 
	
	 Thread th;
	
	 
	 public Main()
	 {
		 playerImage = Toolkit.getDefaultToolkit().getImage("C:\\Users\\gang0\\git\\PGP_Project\\Shooting\\resourses\\sprites\\f2.jpg");

		 player =  new Player(30,30,playerImage,100);
		 addKeyListener(player);
		 th = new Thread(this); 
		 th.start();
		 
		 setTitle("Shooting Game");
	     setSize(800, 600);
	     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     setLocationRelativeTo(null);

	     // 직접 JPanel을 생성하고 JFrame에 추가
	     JPanel gamePanel = new JPanel() {
	     @Override
	         protected void paintComponent(Graphics g) {
	    	 	super.paintComponent(g);
	    	 	// 플레이어를 그립니다.
	            player.draw(g);
	            
	            repaint();

	            // 그 외 게임 오브젝트를 그리는 로직을 추가하세요.
	        }
	    };
	    add(gamePanel);
	}


	@Override
	public void run() {
		 
		try{ // 예외옵션 설정으로 에러 방지
			while(true){ // while 문으로 무한 루프 시키기
				player.KeyProcess(); // 키보드 입력처리를 하여 x,y 갱신
				repaint(); // 갱신된 x,y값으로 이미지 새로 그리기
				Thread.sleep(20); // 20 milli sec 로 스레드 돌리기 
			}
		}catch (Exception e){}
		

		
	}
	
	public static void main(String[] args)
	{
		 SwingUtilities.invokeLater(() -> {
	         Main frame = new Main();
	         frame.setVisible(true);
	         

	        });
	}
	
}
