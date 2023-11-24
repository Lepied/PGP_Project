package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

	 
	 
	 
	 private boolean isScrollPanelVisible = false; // 스크롤 패널이 표시되어야 하는지 여부
	 private ScrollPanel scrollPanel; // 스크롤 패널
	 private boolean isScrollGet =false; 
	 
	 private int[] ScrollIndex;
	 //ex(1,3,9)
	 

	 private int scrollType;
	 private boolean scroll_1_onMouse = false;
	 private boolean scroll_2_onMouse = false;
	 private boolean scroll_3_onMouse = false;
	 
	 

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

	 
	    
	 SelectPanel SelectPanel_1 = new SelectPanel(345,300,190,260,1);
	 SelectPanel SelectPanel_2 = new SelectPanel(540,300,190,260,2);
	 SelectPanel SelectPanel_3 = new SelectPanel(735,300,190,260,3);
	
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
	    gamePanel.addMouseMotionListener(new MouseAdapter()
	    {
	    	public void mouseMoved(MouseEvent e)
	    	{
	    		if(!isScrollPanelVisible)
	    		{
	    			System.out.println("GamePanel Mouse Moved : " + e.getX() + ", " + e.getY());
	    		}
	    	}
	    });
	    
	    
	    
	    scrollPanel = new ScrollPanel();
	    scrollPanel.setBounds(340,100,600,600);
	    scrollPanel.setVisible(false); // 초기에는 보이지 않도록 설정


	    
	    scrollPanel.addMouseMotionListener(new MouseAdapter() {
	    	public void mouseMoved(MouseEvent e)
	    	{
	    		/*
	    		if (isScrollPanelVisible) {
	                // Handle mouse movement in ScrollPanel
	                System.out.println("ScrollPanel Mouse Moved: " + e.getX() + ", " + e.getY());
	                System.out.println("스크롤 1 :" + scroll_1_onMouse);
	                System.out.println("스크롤 2 :" + scroll_2_onMouse);
	                System.out.println("스크롤 3 :" + scroll_3_onMouse);
	                
	            }
	            */
	    	}
	    	
	    });
	    
	    SelectPanel_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	scrollType = SelectPanel_1.type;
            }
        });
	    SelectPanel_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	scrollType = SelectPanel_1.type;
            }
        });
	    SelectPanel_3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	scrollType = SelectPanel_1.type;
            }
        });
	    
	   add(SelectPanel_1);
	   add(SelectPanel_2);
	   add(SelectPanel_3); 
	   add(scrollPanel);
	
	    

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
				
				// 게임 루프 안에서 각 패널의 상태 확인 // 더 수정해야함
		        if (scrollType == 1) {
		        	isScrollGet = false;
		        	isScrollPanelVisible = false;
		        	scrollPanel.setVisible(false);
		            SelectPanel_1.setVisible(false);
		            SelectPanel_2.setVisible(false);
		            SelectPanel_3.setVisible(false);
		            Ability(ScrollIndex[0]);
		            scrollType = 0;  //다시 초기화
		        }
		        else if (scrollType== 2) {
		        	isScrollGet = false;
		        	isScrollPanelVisible = false;
		        	scrollPanel.setVisible(false);
		            SelectPanel_1.setVisible(false);
		            SelectPanel_2.setVisible(false);
		            SelectPanel_3.setVisible(false);
		            Ability(ScrollIndex[1]);
		            scrollType = 0;  //다시 초기화
		        } 
		        else if (scrollType== 3) {
		        	isScrollGet = false;
		        	isScrollPanelVisible = false;
		        	scrollPanel.setVisible(false);
		            SelectPanel_1.setVisible(false);
		            SelectPanel_2.setVisible(false);
		            SelectPanel_3.setVisible(false);
		            Ability(ScrollIndex[2]);
		            scrollType = 0;  //다시 초기화
		        }

		        
				
		        // 게임 루프 안에서 각 패널의 상태 확인
		        if (SelectPanel_1.isMouseEntered()) {
		            // SelectPanel_1에 대한 처리
		        }
		        else if (SelectPanel_2.isMouseEntered()) {
		            // SelectPanel_2에 대한 처리
		        } 
		        else if (SelectPanel_3.isMouseEntered()) {
		            // SelectPanel_3에 대한 처리
		        }
		        
				
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
					System.out.println("게임 타이머 초기화");
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
	
	
	public void Ability(int ability) //능력리스트
	{
		switch(ability)
		{
			case 0: //플레이어데미지 강화
				player.playerDamage = player.playerDamage + 10;
				System.out.println("능력 0");
				break;
				
			case 1: //플레이어 공격 속도 강화
				if(player.attackSpeed > 30)
				{
					player.attackSpeed = player.attackSpeed - 10;
				}
				System.out.println("능력 1");
				break;
				
			case 2: 
				System.out.println("능력 2");
				break;
				
			case 3:
				System.out.println("능력 3");
				break;
				
			case 4:
				System.out.println("능력 4");
				break;
				
			case 5:
				System.out.println("능력 5");
				break;
				
			case 6:
				System.out.println("능력 6");
				break;
				
			case 7:
				System.out.println("능력 7");
				break;
				
			case 8:
				System.out.println("능력 8");
				break;
				
			case 9:
				System.out.println("능력 9");
				break;
				
		
		}
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
							isScrollGet = true;
							if(isScrollGet && !isScrollPanelVisible)
							{
								isScrollPanelVisible = true;
								scrollPanel.setVisible(true);
								SelectPanel_1.setVisible(true);
								SelectPanel_2.setVisible(true);
								SelectPanel_3.setVisible(true);
				                scrollPanel.generateRandomImages(); // 랜덤 이미지 생성
				                ScrollIndex = scrollPanel.returnScroll();
				                

				               
							}
							
			                repaint(); // 화면 갱신
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


class ScrollPanel extends JPanel {
    private List<Image> randomImages;
    private int[] selectedIndexes = new int[3]; // 3칸짜리 배열 추가
	public String[] scrollAbilities = { 
		    	"Ability 0",
		        "Ability 1",
		        "Ability 2",
		        "Ability 3",
		        "Ability 4",
		        "Ability 5",
		        "Ability 6",
		        "Ability 7",
		        "Ability 8",
		        "Ability 9",
		        "Ability 10"       
		    };

    
    public ScrollPanel() {
    	setOpaque(false);
        randomImages = new ArrayList<>();
        setPreferredSize(new Dimension(200, 200)); // 적절한 크기로 설정
        
  
    }

    public void generateRandomImages() {
    	SwingUtilities.invokeLater(() -> {
        // 랜덤으로 이미지 선택 및 리스트에 추가
    	
    	randomImages.clear();
        Random random = new Random();
        
        Set<Integer> usedIndexes = new HashSet<>(); // 중복을 방지하기 위한 Set
        for (int i = 0; i < 3; i++) {
            int randomIndex;
            
            do {
                randomIndex = random.nextInt(11); //능력의 갯수만큼 이미지도 만들기
            } while (usedIndexes.contains(randomIndex)); // 중복 체크

            usedIndexes.add(randomIndex); // 사용된 인덱스 저장

            selectedIndexes[i] = randomIndex; // 선택한 인덱스를 배열에 저장
           
            ImageIcon imageIcon = new ImageIcon("resourses/scrolls/CardShadow" + randomIndex + ".png");
            Image image = imageIcon.getImage();
            randomImages.add(image);
            
            String ability = scrollAbilities[randomIndex];
            System.out.println("Scroll " + (i + 1) + ": " + ability);

            
            //이미지를 그린 순서대로 해야함
            //세칸짜리 변수를 만들어두고 맨왼쪽 칸에 마우스가 위치하면 1번활성화, 가운데는 2번, 오른쪽은 3번활성화 해두고
            //1번활성화 되어있을때는 첫번째 변수만 

        }
        repaint();
        System.out.println("Selected Indexes: " + Arrays.toString(selectedIndexes));
    	});
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 리스트에 있는 이미지를 그림
        int x = 15;
        int y = 200;
        for (Image image : randomImages) {
            g.drawImage(image, x, y, this);
            x += 190; // 이미지 사이의 간격 조절
        }
    }
    public int[] returnScroll()
    {
    	return selectedIndexes;
    }

}
