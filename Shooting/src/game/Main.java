package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main extends JFrame implements Runnable{
	 private Image playerImage;
	 
	 private Player player;
	 
	
	 
	 public Main()
	 {
		 playerImage = Toolkit.getDefaultToolkit().getImage("C:\\Users\\gang0\\git\\PGP_Project\\Shooting\\resourses\\sprites\\f2.jpg");
		 System.out.println(playerImage);
		 if(playerImage == null)
		 {
			 System.out.println("왜 안되 씨발");
		 }
		 player =  new Player(30,30,playerImage,100);
		 addKeyListener(player);
		 
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
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args)
	{
		 SwingUtilities.invokeLater(() -> {
	         Main frame = new Main();
	         frame.setVisible(true);
	         

	        });
	}
	
}
