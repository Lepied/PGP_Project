package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main extends JFrame implements Runnable{
	 private Image playerImage;
	 private Image playerBulletImage;
	 private Image enemyImage;
	 private Image enemyBulletImage;
	 
	 
	 private Player player;
	 GameManager gameManager;
	 
	 Thread th;
	 Image buffImage; // 더블버퍼링
	 Graphics buffg; // 더블버퍼링
	 
	 Toolkit tk = Toolkit.getDefaultToolkit();
	 //임시 총알
	 Image BulletImage;
	 ArrayList Bullet_List = new ArrayList();
	 
	 Bullet bullet;
	 //임시 총알
	
	 int x,y;
	 public int frameWidth = 600;
	 public int frameHeight = 800;
	 
	 ImageIcon bgImg = new ImageIcon("resourses/sprites/background1.png");
	 Image backImg = bgImg.getImage();
	 int backY =0;
	 int back2Y = backImg.getHeight(null);

	 
	 public Main()
	 {
		 gameManager = GameManager.getInstance();
		 
		 player =  new Player();
		 addKeyListener(player);
		 th = new Thread(this); 
		 th.start();
		 //총알 임시 이미지
		 BulletImage = tk.getImage("");
		 //총알 임시 이미지
		 
		 setTitle("Shooting Game");
	     setSize(frameWidth, frameHeight);
	     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     setLocationRelativeTo(null);

	     // 직접 JPanel을 생성하고 JFrame에 추가
	     JPanel gamePanel = new JPanel() {
	     @Override
	         protected void paintComponent(Graphics g) {

	    	 	super.paintComponent(g);
	    	 	drawDoubleBuffering();
	    	 	g.drawImage(buffImage,0,0,this);

	            // 그 외 게임 오브젝트를 그리는 로직을 추가하세요.
	        }
	    };
	    add(gamePanel);       
	}
	public void start()
	{
		x= 100;
		y= 100;
		frameWidth = 600;
		frameHeight = 800;
		
	}
	 
	private void drawDoubleBuffering()
	{
		if(buffImage == null)
		{
			buffImage =createImage(frameWidth, frameHeight);
			if(buffImage == null)
			{
				System.out.println("더블버퍼실패");
				return;
			}
			buffg = buffImage.getGraphics();
		}
		
		buffg.clearRect(0,0,frameWidth,frameHeight);
		
		
		//게임오브젝트 그리는 부분
        buffg.drawImage(backImg, 0, backY, this);
        buffg.drawImage(backImg, 0, back2Y, this);
        player.draw(buffg);
        
	}
	
	@Override
	public void run() {
		 
		try{ // 예외옵션 설정으로 에러 방지
			while(true){ // while 문으로 무한 루프 시키기
				player.KeyProcess(); // 키보드 입력처리를 하여 x,y 갱신
				if(player.posX + player.width /2<0)
				{
					player.posX = 0 - player.width/2 ;
				}
				else if(player.posX + player.width > frameWidth )
				{
					player.posX =  frameWidth - player.width;
				}
				
				if(player.posY + player.height / 2 < 0)
				{
					player.posY = 0 - player.height / 2;
				}
				else if(player.posY + player.height >  frameHeight - 30)
				{
					System.out.println(player.posY +" 1111" + player.height);
					player.posY = frameHeight - player.height-30;
				}
				
				backY--;
				back2Y--;
				backY= backY-2;
				back2Y=back2Y-2;
				
				if(backY <-(backImg.getHeight(null)))
				{
					backY = backImg.getHeight(null);
				}
				if(back2Y <-(backImg.getHeight(null)))
				{
					back2Y = backImg.getHeight(null);
				}
				drawDoubleBuffering();
				repaint(); // 갱신된 x,y값으로 이미지 새로 그리기
				Thread.sleep(15); // 20 milli sec 로 스레드 돌리기 
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
	
	
	public boolean Crash(GameObject obj1, GameObject obj2)
	{
		boolean isCrushed = false;
		if(Math.abs((obj1.posX + obj1.width/2) - (obj2.posX + obj1.width/2)) < (obj2.width/2 + obj1.width/2) 
				&& Math.abs((obj1.posY + obj1.height / 2) - (obj2.posY + obj2.height/2)) < (obj2.height/2 + obj1.height/2))
		{
			isCrushed = true;
		}
		else isCrushed = false;
		
		return isCrushed;
	}
	
}
