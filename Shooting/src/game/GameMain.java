package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GameMain extends JFrame implements Runnable{
	 private Image playerImage;
	 private Image playerBulletImage;
	 private Image enemyImage;
	 private Image enemyBulletImage;
	 
	 
	 private Player player;
	 GameManager gameManager;
	 
	 Enemy en;
	 
	 private int gameCnt;
	 
	 Thread th;
	 Image buffImage; // 더블버퍼링
	 Graphics buffg; // 더블버퍼링
	 
	 Toolkit tk = Toolkit.getDefaultToolkit();

	 int x,y;
	 public int frameWidth = 600;
	 public int frameHeight = 800;
	 
	 ImageIcon bgImg = new ImageIcon("resourses/sprites/background1.png");
	 Image backImg = bgImg.getImage();
	 int backY =0;
	 int back2Y = backImg.getHeight(null);


	 
	 public GameMain()
	 {
		 gameManager = GameManager.getInstance();
		 
		 player = new Player();
		 addKeyListener(player);
		 th = new Thread(this); 
		 th.start();

		 
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
				return;
			}
			buffg = buffImage.getGraphics();
		}
		
		buffg.clearRect(0,0,frameWidth,frameHeight);
		
		//게임오브젝트 그리는 부분
        buffg.drawImage(backImg, 0, backY, this);
        buffg.drawImage(backImg, 0, back2Y, this);
        player.draw(buffg);
        player.drawBullet(buffg);
        Draw_Enemy();
	}
	
	@Override
	public void run() {
		 
		try{ // 예외옵션 설정으로 에러 방지
			while(true){ // while 문으로 무한 루프 시키기
				player.KeyProcess(); // 키보드 입력처리를 하여 x,y 갱신
				player.BulletProcess();
				EnemyProcess();
				

				
				
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
				Thread.sleep(15); // 15 milli sec 로 스레드 돌리기 
				gameCnt++;
			
			}
		}catch (Exception e){}
		

		
	}
	
	public static void main(String[] args)
	{
		 SwingUtilities.invokeLater(() -> {
	         GameMain frame = new GameMain();
	         frame.setVisible(true);
	         

	        });
	}
	
	public void EnemyProcess()
	{
		for(int  i =0; i<gameManager.getGameObjectList().size(); ++i)
		{
			en = (Enemy)(gameManager.getGameObjectList().get(i));
			en.move();
			if(en.posY >800)
			{
				gameManager.getGameObjectList().remove(i);
			}
			
		}
		if(gameCnt%300 ==0) {
			en = new E_Wybern();
		}
		
	}
	public void Draw_Enemy()
	{
		for(int  i =0; i<gameManager.getGameObjectList().size(); ++i)
		{
			en = (Enemy)(gameManager.getGameObjectList().get(i));
			buffg.drawImage(en.img, en.posX,en.posY,this);
		}
	}


}
