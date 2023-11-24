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
	 Animator animator;
	 
	 Enemy en;
	 Item item;
	 List<Item> itemsToRemove = new ArrayList<>(); // 아이템 일시 제거를 위한 리스트
	 
	 private int gameCnt;
	 
	 Thread th;
	 Image buffImage; // 더블버퍼링
	 Graphics buffg; // 더블버퍼링
	 
	 Toolkit tk = Toolkit.getDefaultToolkit();

	 int x,y;
	 public int frameWidth = 1280;
	 public int frameHeight = 800;
	 
	 int coinSel = 0; //스프라이트 애니메이션
	 
	 ImageIcon bgImg = new ImageIcon("resourses/sprites/background1.png");
	 Image backImg = bgImg.getImage();
	 int backY =0;
	 int back2Y = backImg.getHeight(null);

	
	 public GameMain()
	 {
		 gameManager = GameManager.getInstance();
		 animator = Animator.getInstance();
		 
		 player = new Player(gameManager.getPlayerDamage());
		 addKeyListener(player);
		 th = new Thread(this); 
		 th.start();

		
		 
		 setTitle("Shooting Game");
	     setSize(frameWidth, frameHeight);
	     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     setLocationRelativeTo(null);
	     
		 int panelWidth = 600;
		 int panelHeight = 800;
		 int x = (frameWidth - panelWidth)/2;

	     // 직접 JPanel을 생성하고 JFrame에 추가
	     JPanel gamePanel = new JPanel() {
	     @Override
	         protected void paintComponent(Graphics g) {

	    	 	super.paintComponent(g);
	    	 	drawDoubleBuffering();
	    	 	g.drawImage(buffImage,x,0,this);

	        }
	    };

	    gamePanel.setBounds(x, 0, panelWidth, panelHeight);
	    add(gamePanel);       
	}
	public void start()
	{
		x= 100;
		y= 100;
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
		
		animator.setGraphics(buffg);
		//게임오브젝트 그리는 부분
        buffg.drawImage(backImg, 0, backY, this);
        buffg.drawImage(backImg, 0, back2Y, this);
        
        player.draw(buffg);
        player.drawBullet(buffg);
        
        Draw_Enemy();
        Draw_Item();
	}
	
	@Override
	public void run() {
		 
		try{ // 예외옵션 설정으로 에러 방지
			while(true){ // while 문으로 무한 루프 시키기
				repaint();

				player.KeyProcess(); // 키보드 입력처리를 하여 x,y 갱신
				player.BulletProcess();
				EnemyProcess();
				ItemProcess();
				
				
				if(player.posX + player.width /2<0)
				{
					player.posX = 0 - player.width/2 ;
				}
				else if(player.posX + player.width > (frameWidth+x)/2-20 )
				{
					player.posX = (frameWidth+x)/2 - player.width-20;
				}
				
				if(player.posY + player.height / 2 < 0)
				{
					player.posY = 0 - player.height / 2;
				}
				else if(player.posY + player.height >  frameHeight - 30)
				{
					player.posY = frameHeight - player.height-30;
				}
				//System.out.println("플레이어 X좌요 : "+player.posX+" "
				//	+ " 플레이어 Y좌표 : "+player.posY);
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
				repaint(); // 갱신된 x,y값으로 이미지 새로 그리기
				Thread.sleep(15); // 15 milli sec 로 스레드 돌리기 
				gameCnt++;
				//게임 타이머 초기화 해서 게임 안터지게
				if(gameCnt > 999999)
				{
					gameCnt = 0; 
					System.out.println("초기화");
				}
				
			
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
			en = new E_Wybern(1);
			en = new E_Wybern(2);
			en = new E_Wybern(3);

		}
		
	}
	
	public void ItemProcess()
	{
	
		for(int  i =0; i<gameManager.getItemList().size(); ++i)
		{
			item = (Item)(gameManager.getItemList().get(i));
			item.move();
			if(item.posY >800)
			{
				itemsToRemove.add(item);

			}

			for(int j=0; j<gameManager.getItemList().size(); ++j)
			{ 	
				if(gameManager.isCollision(player, item)) 
				{
					itemsToRemove.add(item);
					

					switch(item.type)
					{
						case 1:
							item.getCoin();
							break;
						case 2:
							item.getScroll();
							break;
						case 3:
							if(player.bomb<3)
							{
								player.bomb++;
							}
							break;
					}
					
				}
			}
		}
		gameManager.getItemList().removeAll(itemsToRemove);
		itemsToRemove.clear();
	
	}
	
	
	
	public void Draw_Enemy()
	{
		for(int  i =0; i<gameManager.getGameObjectList().size(); ++i)
		{
			en = (Enemy)(gameManager.getGameObjectList().get(i));
			buffg.drawImage(en.img, en.posX,en.posY,this);
		}
	}
	public void Draw_Item()
	{
		for(int i =0; i<gameManager.getItemList().size(); ++i)
		{
			
			item = (Item)(gameManager.getItemList().get(i));
			if(item.type == 1)
			{
				 buffg.drawImage(item.img, item.posX,item.posY,
						 item.posX+32,item.posY+32,coinSel*0,0,coinSel*32+32,32,this);
			}
			else
			{
				buffg.drawImage(item.img, item.posX,item.posY,this);
			}
			
		}
	}


}
