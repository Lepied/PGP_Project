package game;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Unit.Ch1Boss;
import Unit.E_Wybern;
import Unit.E_Zaco;
import Unit.NPC;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GameMain extends JFrame implements Runnable {
	private Image playerImage;
	private Image playerBulletImage;
	private Image enemyImage;
	private Image enemyBulletImage;

	private boolean paused = false;
	private Player player;
	GameManager gameManager;
	Animator animator;

	boolean canCh1BossSpawn = true; // 게임타이머관리용 보스소환가능한상태?

	NPC npc;
	Enemy en;
	Item item;
	List<Item> itemsToRemove = new ArrayList<>(); // 아이템 일시 제거를 위한 리스트

	EnemyBullet enBullet;
	ArrayList Enemy_Bullet_List = new ArrayList(); // 적 총알 리스트

	private Font customFont;

	private boolean isScrollPanelVisible = false; // 스크롤 패널이 표시되어야 하는지 여부
	private ScrollPanel scrollPanel; // 스크롤 패널
	public BossUI Ch1BossUI;
	private Image scrollBgImage = new ImageIcon("resourses/sprites/ScrollBG.png").getImage();

	private boolean isScrollGet = false;

	private int scrollType;
	private boolean scroll_1_onMouse = false;
	private boolean scroll_2_onMouse = false;
	private boolean scroll_3_onMouse = false;

	private long immortalTime = 100; // 플레이어 무적

	private int gameCnt;

	Thread th;
	Image buffImage; // 더블버퍼링
	Graphics buffg; // 더블버퍼링

	Toolkit tk = Toolkit.getDefaultToolkit();

	int x, y;
	private int mouseX, mouseY; // 마우스의 좌표
	public int frameWidth = 1280;
	public int frameHeight = 800;

	int coinSel = 0; // 스프라이트 애니메이션

	ImageIcon bgImg = new ImageIcon("resourses/sprites/background.png");
	Image backImg = bgImg.getImage();
	int backY = 0;
	int back2Y = backImg.getHeight(null);

	SelectPanel SelectPanel_1 = new SelectPanel(385, 300, 160, 260, 1, this);
	SelectPanel SelectPanel_2 = new SelectPanel(555, 300, 160, 260, 2, this);
	SelectPanel SelectPanel_3 = new SelectPanel(735, 300, 160, 260, 3, this);

	NPCPanel npcPanel = new NPCPanel(this);

	private boolean canSpawnNPC = true;

	public GameMain() {
		gameManager = GameManager.getInstance();
		animator = Animator.getInstance();
		player = new Player(gameManager.getPlayerDamage());
		gameManager.setPlayer(player);

		addKeyListener(player);
		th = new Thread(this);
		th.start();

		try {
			customFont = Font.createFont(Font.TRUETYPE_FONT, new File("resourses/MapleStory Bold.ttf")).deriveFont(14f);
		} catch (Exception e) {
			e.printStackTrace();

		}

		setTitle("Shooting Game");
		setSize(frameWidth, frameHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		int panelWidth = 600;
		int panelHeight = 800;
		int x = (frameWidth - panelWidth) / 2;

		JPanel LeftUIPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image backgroundImage = new ImageIcon("resourses/sprites/UI_BG.png").getImage();
				setOpaque(false);

				g.drawImage(backgroundImage, 0, 0, x, panelHeight, this);

			}
		};
		JPanel RightUIPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image backgroundImage = new ImageIcon("resourses/sprites/UI_BG.png").getImage();
				setOpaque(false);
				g.drawImage(backgroundImage, 0, 0, 340, panelHeight, this);

				String hpText = "생명";
				g.setFont(customFont);
				g.setColor(Color.WHITE);
				g.drawString(hpText, 100, 280);
				int heartSize = 30;
				int heartsCount = player.hp;
				for (int i = 0; i < heartsCount; i++) {
					Image heartImage = new ImageIcon("resourses/sprites/Fireball.png").getImage();
					g.drawImage(heartImage, 100 + i * (heartSize + 5), 300, heartSize, heartSize, this);
				}
				String bombText = "폭탄";
				g.setFont(customFont);
				g.setColor(Color.WHITE);
				g.drawString(bombText, 100, 380);
				int bombSize = 30;
				int bombsCount = player.bomb;
				for (int i = 0; i < bombsCount; i++) {
					Image bombImage = new ImageIcon("resourses/sprites/Fireball.png").getImage();
					g.drawImage(bombImage, 100 + i * (bombSize + 5), 400, bombSize, bombSize, this);
				}

				String scoreText = "점수: " + gameManager.getScore();
				g.setFont(customFont);
				g.setColor(Color.WHITE);
				g.drawString(scoreText, 10, 100);
			}
		};

		JPanel gamePanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {

				super.paintComponent(g);
				drawDoubleBuffering();
				setOpaque(false);
				g.drawImage(buffImage, x, 0, this);

			}
		};
		gamePanel.addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				if (!isScrollPanelVisible) {
					mouseX = e.getX() - x;
					mouseY = e.getY();
					updateDirection();

				}
			}
		});

		LeftUIPanel.setBounds(0, 0, x, panelHeight);
		RightUIPanel.setBounds(x + panelWidth, 0, 340, panelHeight);
		LeftUIPanel.setVisible(true);
		RightUIPanel.setVisible(true);

		scrollPanel = new ScrollPanel();
		scrollPanel.setBounds(340, 100, 600, 600);
		scrollPanel.setVisible(false); // 초기에는 보이지 않도록 설정

		scrollPanel.addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				/*
				 * if (isScrollPanelVisible) { }
				 */
			}

		});

		SelectPanel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scrollType = SelectPanel_1.type;
				System.out.println("1번 선택됨");
			}
		});
		SelectPanel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scrollType = SelectPanel_2.type;
				System.out.println("2번 선택됨");
			}
		});
		SelectPanel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scrollType = SelectPanel_3.type;
				System.out.println("3번 선택됨");
			}
		});

		Ch1BossUI = new BossUI(340, -5, 600, 50, this);

		gamePanel.setBounds(x, 0, panelWidth, panelHeight);

		add(LeftUIPanel);
		add(RightUIPanel);
		add(SelectPanel_1);
		add(SelectPanel_2);
		add(SelectPanel_3);
		add(scrollPanel);
		add(Ch1BossUI);
		add(npcPanel);
		add(gamePanel);

	}

	public void start() {
		x = 100;
		y = 100;
	}

	private void drawDoubleBuffering() {
		if (buffImage == null) {
			buffImage = createImage(600, frameHeight);
			if (buffImage == null) {
				return;
			}
			buffg = buffImage.getGraphics();
		}

		buffg.clearRect(0, 0, 600, frameHeight);

		animator.setGraphics(buffg);
		// 게임오브젝트 그리는 부분
		buffg.drawImage(backImg, 0, backY, this);
		buffg.drawImage(backImg, 0, back2Y, this);

		player.draw(buffg);
		player.drawBullet(buffg);

		Draw_Enemy();
		Draw_Item();
		Draw_NPC();
	}

	@Override
	public void run() {

		try { // 예외옵션 설정으로 에러 방지
			while (true) { // while 문으로 무한 루프 시키기
				synchronized (this) {
					while (paused) {
						wait();
					}
				}
				repaint();
				
				// 플레이어 무적시간 부여하기
				if (player.isDamaged) {
					if (!player.isImmortal) {
						immortalTime = System.currentTimeMillis();
						player.isImmortal = true;
					}
					long currentTime = System.currentTimeMillis();
					System.out.println("무적 경과 시간: " + (currentTime - immortalTime));
					if (currentTime - immortalTime > 1000) {
						System.out.println("무적해제");
						player.isDamaged = false;
						player.isImmortal = false; // 추가된 부분: 무적 상태 해제
					}

				}
				
				player.KeyProcess(); // 키보드 입력처리를 하여 x,y 갱신
				player.BulletProcess(); // 플레이어 총알
				EnemyProcess(); // 적 매커니즘, 스폰 등
				ItemProcess(); // 아이템 매커니즘
				NPCProcess(); // NPC 매커니즘
				VisibleBossUI(); // 보스 UI 보이기
				
				
				if(gameManager.isNPCEnd)
				{
					npcPanel.setVisible(false);
					resumeGame();
					gameManager.isNPCEnd=false;
				}

				// 게임 루프 안에서 각 패널의 상태 확인 // 더 수정해야함
				if (scrollType == 1) {
					isScrollGet = false;
					isScrollPanelVisible = false;
					Ability(scrollPanel.returnScroll(scrollType-1));
					scrollPanel.setVisible(false);
					SelectPanel_1.setVisible(false);
					SelectPanel_2.setVisible(false);
					SelectPanel_3.setVisible(false);
					
					resumeGame();
					scrollType = 0; // 다시 초기화
				} else if (scrollType == 2) {
					isScrollGet = false;
					isScrollPanelVisible = false;
					Ability(scrollPanel.returnScroll(scrollType-1));
					scrollPanel.setVisible(false);
					SelectPanel_1.setVisible(false);
					SelectPanel_2.setVisible(false);
					SelectPanel_3.setVisible(false);
					
					resumeGame();
					scrollType = 0; // 다시 초기화
				} else if (scrollType == 3) {
					isScrollGet = false;
					isScrollPanelVisible = false;
					Ability(scrollPanel.returnScroll(scrollType-1));
					scrollPanel.setVisible(false);
					SelectPanel_1.setVisible(false);
					SelectPanel_2.setVisible(false);
					SelectPanel_3.setVisible(false);
				
					resumeGame();
					scrollType = 0; // 다시 초기화
				}
				
				// 게임 루프 안에서 각 패널의 상태 확인
				if (SelectPanel_1.isMouseEntered()) {
					// SelectPanel_1에 대한 처리
				} else if (SelectPanel_2.isMouseEntered()) {
					// SelectPanel_2에 대한 처리
				} else if (SelectPanel_3.isMouseEntered()) {
					// SelectPanel_3에 대한 처리
				}

				if (player.posX + player.width / 2 < 0) {
					player.posX = 0 - player.width / 2;
				} else if (player.posX + player.width > (frameWidth + x) / 2 - 20) {
					player.posX = (frameWidth + x) / 2 - player.width - 20;
				}

				if (player.posY + player.height / 2 < 0) {
					player.posY = 0 - player.height / 2;
				} else if (player.posY + player.height > frameHeight - 30) {
					player.posY = frameHeight - player.height - 30;
				}

				backY++;
				back2Y++;
				backY = backY + 2;
				back2Y = back2Y + 2;

				if (backY > (backImg.getHeight(null))) {
					backY = back2Y - backImg.getHeight(null);
				}
				if (back2Y > (backImg.getHeight(null))) {
					back2Y = backY - backImg.getHeight(null);
				}
				repaint(); // 갱신된 x,y값으로 이미지 새로 그리기
				Thread.sleep(15); // 15 milli sec 로 스레드 돌리기
				gameCnt++;
				// 게임 타이머 초기화 해서 게임 안터지게

				GameOver();
				if (gameCnt > 999999) {
					gameCnt = 0;
					System.out.println("게임 타이머 초기화");
				}

			}
		} catch (Exception e) {
		}

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			GameMain frame = new GameMain();
			frame.setVisible(true);

		});
	}

	
	public void GameOver()
	{
		if(player.hp == 0)
		{
			System.out.println("게임오버");
			player.bomb = 0;
		}
	}


	public void Ability(int ability) // 능력리스트
	{

		System.out.println("어빌리티    "+ability);
		switch (ability) {
		case 0: // 플레이어데미지 강화
			player.playerDamage = player.playerDamage + 10;
			System.out.println("플레이어 데미지 증가");
			break;

		case 1: // 플레이어 공격 속도 강화
			if (player.attackSpeed > 30) {
				player.attackSpeed = player.attackSpeed - 10;
			}
			System.out.println("플레이어 공격속도 업");
			break;

		case 2:
			if (player.lineShot < 4) {
				player.lineShot = player.lineShot + 1;
			}
			System.out.println(player.lineShot);
			System.out.println("플레이어 직선 공격 추가");
			break;

		case 3:
			gameManager.setCoin(gameManager.getCoin() + 10);
			System.out.println("코인 더미");
			break;

		case 4:
			System.out.println("능력 4");
			if (player.lineShot < 4) {
				player.lineShot = player.lineShot + 1;
			}
			System.out.println(player.lineShot);
			System.out.println("플레이어 직선 공격 추가");
			break;


		case 5:
			System.out.println("능력 5");
			if (player.lineShot <4) {
				player.lineShot = player.lineShot + 1;
			}
			if(player.attackType ==2)
			{
				player.playerDamage+=50;
			}
			System.out.println(player.lineShot);
			System.out.println("플레이어 직선 공격 추가");
			break;
	
		case 6:
			System.out.println("능력 6");
			if (player.lineShot < 4) {
				player.lineShot = player.lineShot + 1;
			}
			System.out.println(player.lineShot);
			System.out.println("플레이어 직선 공격 추가");
			break;

		case 7:
			System.out.println("능력 7");
			if (player.lineShot < 4) {
				player.lineShot = player.lineShot + 1;
			}
			System.out.println(player.lineShot);
			System.out.println("플레이어 직선 공격 추가");
			break;

		case 8:
			System.out.println("능력 8");
			if (player.lineShot <4) {
				player.lineShot = player.lineShot + 1;
			}
			System.out.println(player.lineShot);
			System.out.println("플레이어 직선 공격 추가");
			break;

		case 9:
			System.out.println("능력 9");
			if (player.lineShot < 4) {
				player.lineShot = player.lineShot + 1;
			}
			System.out.println(player.lineShot);
			System.out.println("플레이어 직선 공격 추가");
			break;

		}

	}

	private void updateDirection() {

		double angle = Math.atan2(player.posY - mouseY, mouseX - player.posX);

		player.setAngle(angle);
	}

	public void EnemyProcess() {
		for (int i = 0; i < gameManager.getGameObjectList().size(); ++i) {
			en = (Enemy) (gameManager.getGameObjectList().get(i));
			if (en.type == 3) {
				gameManager.isBossNow = true;
				Ch1BossUI.updateHealth(en.hp, en.maxHealth);
			}

			if(player.isDamaged==false &&gameManager.isCollision(player,en))
			{
				player.isDamaged = true;
				player.hp--;
			}
			en.move();
			en.BulletProcess();

			if (en.posY > 800) {

				gameManager.getGameObjectList().remove(i);
			}

		}
		//보스전 이전 스테이지 구성
		if (canCh1BossSpawn == true && gameCnt < 4000) {
			if(gameCnt % 100 == 0 && gameCnt < 500)
			{
				en = new E_Zaco(3);
				en = new E_Zaco(4);
			}
			else if(gameCnt % 100 == 0 && gameCnt<800)
			{
				en = new E_Zaco(1);
				en = new E_Zaco(2);
			}

		}
		if (gameCnt > 800 && canSpawnNPC) {//엔피씨 소환
			npc = new NPC();
			canSpawnNPC = false;
		}
		if(gameCnt >1000) //퀘스트 받을때 안받을떄 로직
		{
			if(gameManager.isQuest)
			{
				if(gameCnt%200==0)
				{
					en = new E_Wybern(1);

				}
			}
			else {}
		}
		/*
		 * if(gameCnt>200 && canCh1BossSpawn) {
		 * 
		 * canCh1BossSpawn = false; en = new Ch1Boss(2); gameCnt = 0; 
		 * // 게임카운트 초기화해서
		 * 보스전돌입 }
		 */

	}

	public void VisibleBossUI() {
		SwingUtilities.invokeLater(() -> {
			if (!GameManager.getInstance().isBossNow) {
				Ch1BossUI.setVisible(false);
			}

			if (GameManager.getInstance().isBossNow) {
				Ch1BossUI.setVisible(true);
			}
		});
	}

	public void ItemProcess() {

		for (int i = 0; i < gameManager.getItemList().size(); ++i) {
			item = (Item) (gameManager.getItemList().get(i));
			item.move();
			if (item.posY > 800) {
				itemsToRemove.add(item);
			}
			
			else if (gameManager.isCollision(player, item)) {
				itemsToRemove.add(item);

				switch (item.type) {
				case 1:
					item.getCoin();
					break;
				case 2:
					isScrollGet = true;
					if (isScrollGet && !isScrollPanelVisible) {
						pauseGame();
						isScrollPanelVisible = true;
						scrollPanel.setVisible(true);
						SelectPanel_1.setVisible(true);
						SelectPanel_2.setVisible(true);
						SelectPanel_3.setVisible(true);
						scrollPanel.generateRandomImages(); // 랜덤 이미지 생성
						//System.out.println(ScrollIndex.length);
					}

					repaint(); // 화면 갱신
					break;
				case 3:
					if (player.bomb < 3) {
						
						player.bomb = player.bomb + 1;
						

					}
					break;
				}

			}
		}
		gameManager.getItemList().removeAll(itemsToRemove);
		itemsToRemove.clear();

	}

	public void NPCProcess() {
		boolean NPCGO = false;

		for (int i = 0; i < gameManager.getNPCList().size(); ++i) {
			npc = (NPC) (gameManager.getNPCList().get(i));
			npc.move();
			if (npc.posY > 800) {
				gameManager.getNPCList().remove(i);
			}
			if (gameManager.isNPCCollision(npc, player)) {
				gameManager.getNPCList().remove(i);
				NPCGO = true;
			}
		}
		if(NPCGO)
		{
			pauseGame();
			npcPanel.setVisible(true);
		} 

	}

	public void Draw_Enemy() {
		for (int i = 0; i < gameManager.getGameObjectList().size(); ++i) {
			en = (Enemy) (gameManager.getGameObjectList().get(i));
			buffg.drawImage(en.img, en.posX, en.posY, this);
			en.Draw_EnemyBullet(buffg);
		}
	}

	public void Draw_Item() {
		for (int i = 0; i < gameManager.getItemList().size(); ++i) {

			item = (Item) (gameManager.getItemList().get(i));
			if (item.type == 1) {
				buffg.drawImage(item.img, item.posX, item.posY, item.posX + 32, item.posY + 32, coinSel * 0, 0,
						coinSel * 32 + 32, 32, this);
			} else {
				buffg.drawImage(item.img, item.posX, item.posY, this);
			}

		}
	}

	public void Draw_NPC() {
		for (int i = 0; i < gameManager.getNPCList().size(); ++i) {

			npc = (NPC) (gameManager.getNPCList().get(i));
			buffg.drawImage(npc.img, npc.posX, npc.posY, this);
		}

	}

	public synchronized void pauseGame() {
		paused = true;
	}

	public synchronized void resumeGame() {
		paused = false;
		notify();
	}
}

class ScrollPanel extends JPanel {
	private List<Image> randomImages;
	private int[] selectedIndexes = new int[3]; // 3칸짜리 배열 추가
	public String[] scrollAbilities = { "Ability 0", "Ability 1", "Ability 2", "Ability 3", "Ability 4", "Ability 5",
			"Ability 6", "Ability 7", "Ability 8", "Ability 9", "Ability 10" };
	private Image bgImage;

	public ScrollPanel() {
		setOpaque(false);
		randomImages = new ArrayList<>();
		bgImage = new ImageIcon("resourses/sprites/ScrollBG.png").getImage();
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
					randomIndex = random.nextInt(11); // 능력의 갯수만큼 이미지도 만들기
				} while (usedIndexes.contains(randomIndex)); // 중복 체크

				usedIndexes.add(randomIndex); // 사용된 인덱스 저장

				selectedIndexes[i] = randomIndex; // 선택한 인덱스를 배열에 저장
				

				ImageIcon imageIcon = new ImageIcon("resourses/scrolls/CardShadow" + randomIndex + ".png");
				Image image = imageIcon.getImage();
				randomImages.add(image);

				String ability = scrollAbilities[randomIndex];
				System.out.println("Scroll " + (i + 1) + ": " + ability);

				// 이미지를 그린 순서대로 해야함
				// 세칸짜리 변수를 만들어두고 맨왼쪽 칸에 마우스가 위치하면 1번활성화, 가운데는 2번, 오른쪽은 3번활성화 해두고
				// 1번활성화 되어있을때는 첫번째 변수만

			}
			repaint();
			System.out.println("Selected Indexes: " + Arrays.toString(selectedIndexes));
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(bgImage, 0, 100, getWidth(), 450, this);
		// 리스트에 있는 이미지를 그림
		int x = 45;
		int y = 200;
		for (Image image : randomImages) {
			g.drawImage(image, x, y, this);
			x += 170; // 이미지 사이의 간격 조절
		}
	}

	public int returnScroll(int i) {
		System.out.println(selectedIndexes[i]);
		return selectedIndexes[i];
		
	}
	
	
	//일시정지
	private void escToPause(GameMain gameMain) {
		gameMain.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					gameMain.pauseGame();
				}
				
			}

			public void keyReleased(KeyEvent e) {}
			
		});
		
	}
}
