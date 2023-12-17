package game;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class PowerUpPanel extends JPanel{
	private int X = 960;
	private int Y = 540;
	
	private int margin = 30;
	
	private final int DAMAGE = 1;
	private final int HP = 2;
	private final int LUCK = 3;
	private final int BOMBDAMAGE = 4;
	private final int PLUSCOIN = 5;
	
	private int damageCount;
	private int hpCount;
	private double luckCount_double;
	private int luckCount;
	private int bombDamageCount;
	private int plusCoinCount;
	
	private int coins;
	JLabel coinDisplay;
	
	MainFrame main;
	GameManager gm;
	
	Image powerUpBg = new ImageIcon("resourses/sprites/powerUpBackground.png").getImage();
	
	Image scrollImg = new ImageIcon("resourses/sprites/title.png").getImage();
	Image commentImg = new ImageIcon("resourses/sprites/scrollBG.png").getImage();
	
	
	ImageIcon damageImg = new ImageIcon("resourses/sprites/damageUp.png");
	ImageIcon damageIcon = scaleImage(damageImg, 70, 90);
	ImageIcon hpImg = new ImageIcon("resourses/sprites/hpUp.png");
	ImageIcon hpIcon = scaleImage(hpImg, 70, 90);
	ImageIcon luckImg = new ImageIcon("resourses/sprites/luckUp.png");
	ImageIcon luckIcon = scaleImage(luckImg, 70, 90);
	ImageIcon bombDamageImg = new ImageIcon("resourses/sprites/bombDamageUp.png");
	ImageIcon bombDamageIcon = scaleImage(bombDamageImg, 70, 90);
	ImageIcon plusCoinImg = new ImageIcon("resourses/sprites/plusCoinUp.png");
	ImageIcon plusCoinIcon = scaleImage(plusCoinImg, 70, 90);
	
	ImageIcon coinIcon = new ImageIcon("resourses/sprites/coin(vivid).png");
	ImageIcon needCoinIcon = scaleImage(coinIcon, 20, 20);
	ImageIcon plusIcon = new ImageIcon("resourses/sprites/plus.png");
	
	ImageIcon backImg = new ImageIcon("resourses/sprites/back.png");
	ImageIcon backIcon = scaleImage(backImg, 70, 50);
	ImageIcon back2Img = new ImageIcon("resourses/sprites/back(2).png");
	ImageIcon back2Icon = scaleImage(back2Img, 70, 50);
	
	public PowerUpPanel(MainFrame main) {
		this.main = main;
		gm = GameManager.getInstance();
		Border border = new LineBorder(Color.BLACK, 5);
		Border thin = new LineBorder(Color.BLACK, 2);
		
		setLayout(null);
		this.setPreferredSize(new Dimension(X, Y));
		this.setBackground(Color.WHITE);
		this.setDoubleBuffered(true);
		
		//코인 디스플레이
		coins = gm.getCoin();
		coinDisplay = new SetText("coin: ", X-150, 8, 150, 50, false);
		
		Font coinFont = new Font("Elephant", Font.BOLD, 20);
		coinDisplay.setFont(coinFont);
		coinDisplay.setText(Integer.toString(coins));
		coinDisplay.setIcon(coinIcon);
		coinDisplay.setHorizontalAlignment(JLabel.CENTER);
		add(coinDisplay);
		
		//강화 관련 코멘트 디스플레이
		SetText comment = new SetText("코인을 이용하여 당신의 힘을 강화하십시오...", margin+45, Y-margin -130, 500, 130, false);
		try {
			comment.customFont = Font.createFont(Font.TRUETYPE_FONT, new File("resourses/MapleStory Bold.ttf")).deriveFont(20f);
			comment.setFont(comment.customFont);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		comment.setBorder(border);
		add(comment);
		
		//스탯 디스플레이
		JPanel statDisplay = new JPanel();
		statDisplay.setBounds(X - margin -280 , 100, 280, 380);
		statDisplay.setLayout(null);
		add(statDisplay);
		
		JLabel damage = new SetText("damage", margin , 40, 150, 50, false);
		statDisplay.add(damage);
		
		JLabel hp = new SetText("hp", margin, 100, 150, 50, false);
		statDisplay.add(hp);
		
		JLabel luck = new SetText("luck", margin, 160, 150, 50, false);
		statDisplay.add(luck);
		
		JLabel bombDamage = new SetText("bombDamage", margin, 220, 150, 50, false);
		statDisplay.add(bombDamage);
		
		JLabel plusCoin = new SetText("plusCoin", margin, 280, 150, 50, false);
		statDisplay.add(plusCoin);
		
		
		
		statDisplay.setBorder(border);
		
		//뒤로가기
		JLabel back = new CreateBtn(backIcon, 0, 0, 70, 50);
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				main.changePanel("main");
			}
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
				back.setIcon(back2Icon);
			}
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				back.setIcon(backIcon);
			}  
		 });
		add(back);
		
		//업그레이드
		
		//----------------데미지
		JLabel upgrade1 = new CreateBtn(damageIcon, margin, 100, 100, 130);
		upgrade1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
				comment.setText("마법의 기운을 강화하여 데미지를 상승시킵니다...");
				damageIcon = scaleImage(damageImg, 80, 100);
				upgrade1.setIcon(damageIcon);
			}
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				damageIcon = scaleImage(damageImg, 70, 90);
				upgrade1.setIcon(damageIcon);
			}
		 });
		add(upgrade1);
		
		JLabel damageCountDisplay = new SetText("", margin+10, 250, 60, 20, true);
		damageCountDisplay.setHorizontalAlignment(JLabel.CENTER);
		damageCountDisplay.setBorder(thin);
		add(damageCountDisplay);
		
		JLabel needCoinDisplay_damage = new SetText("", margin+15, 295, 60, 20, false);
		add(needCoinDisplay_damage);
		
		JLabel damageUp = new CreateBtn(plusIcon, margin+70, 250, 20, 20 );
		damageUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (damageCount < 10) {
					if(getNeedCoins(damageCount) <= coins) {
						useCoin(getNeedCoins(damageCount));	
						gm.setPlayerDamage(gm.getPlayerDamage()+5);
						refreshDisplay(damage, damageCountDisplay, needCoinDisplay_damage, DAMAGE);
						comment.setText("마법의 기운이 더욱 강해진 것 같습니다...");
						
					}
					else {
						comment.setText("강화를 위한 코인이 부족합니다...");
					}
				}
				

			}
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		 });
		add(damageUp);
		
		//---------------체력
		JLabel upgrade2 = new CreateBtn(hpIcon, margin+120, 100, 100, 130);
		upgrade2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
				comment.setText("의지를 더 굳건히 하여 적의 공격을 더욱 막아낼 수 있습니다...");
				hpIcon = scaleImage(hpImg, 80, 100);
				upgrade2.setIcon(hpIcon);
			}
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				hpIcon = scaleImage(hpImg, 70, 90);
				upgrade2.setIcon(hpIcon);
			}
		});
		add(upgrade2);
		
		JLabel hpCountDisplay = new SetText("", margin+130, 250, 60, 20, true);
		hpCountDisplay.setHorizontalAlignment(JLabel.CENTER);
		hpCountDisplay.setBorder(thin);
		add(hpCountDisplay);
		
		JLabel needCoinDisplay_hp = new SetText("", margin+135, 295, 60, 20, false);
		add(needCoinDisplay_hp);
		
		JLabel hpUp = new CreateBtn(plusIcon, margin+120+70, 250, 20, 20);
		hpUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (hpCount < 10) {
					if(getNeedCoins(hpCount) <= coins) {
						useCoin(getNeedCoins(hpCount));
						gm.setPlayerMaxHP(gm.getPlayerMaxHP()+1);
						refreshDisplay(hp, hpCountDisplay, needCoinDisplay_hp, HP);
						comment.setText("당신의 의지가 더 굳건해진 것 같습니다...");
						
					}
					else {
						comment.setText("강화를 위한 코인이 부족합니다...");
					}
				}
			}
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		 });
		add(hpUp);
		
		//-------------드랍률
		JLabel upgrade3 = new CreateBtn(luckIcon, margin+240, 100, 100, 130);
		upgrade3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
				comment.setText("측북의 힘을 받아 특별한 스크롤을 발견하기 쉬워집니다...");
				luckIcon = scaleImage(luckImg, 80, 100);
				upgrade3.setIcon(luckIcon);
			}
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				luckIcon = scaleImage(luckImg, 70, 90);
				upgrade3.setIcon(luckIcon);
			}
		});
		add(upgrade3);
		
		JLabel luckCountDisplay = new SetText("", margin+250, 250, 60, 20, true);
		luckCountDisplay.setHorizontalAlignment(JLabel.CENTER);
		luckCountDisplay.setBorder(thin);
		add(luckCountDisplay);
		
		JLabel needCoinDisplay_luck = new SetText("", margin+255, 295, 60, 20, false);
		add(needCoinDisplay_luck);
		
		JLabel luckUp = new CreateBtn(plusIcon, margin+240+70, 250, 20, 20);
		luckUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (luckCount < 10) {
					if(getNeedCoins(luckCount) <= coins) {
						useCoin(getNeedCoins(luckCount));
						gm.setPlayerLuck(gm.getPlayerLuck()+0.01);
						refreshDisplay(luck, luckCountDisplay, needCoinDisplay_luck, LUCK);
						comment.setText("당신의 여정에 축복이 깃든 것 같습니다...");
						
					}
					else {
						System.out.println(luckCount);
						comment.setText("강화를 위한 코인이 부족합니다...");
					}
				}
				
			}
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		 });
		add(luckUp);
		
		//--------------폭탄 데미지
		JLabel upgrade4 = new CreateBtn(bombDamageIcon, margin+360, 100, 100, 130);
		upgrade4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
				comment.setText("룬과의 친화력을 늘려 특별한 힘을 강화합니다...");
				bombDamageIcon = scaleImage(bombDamageImg, 80, 100);
				upgrade4.setIcon(bombDamageIcon);
			}
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				bombDamageIcon = scaleImage(bombDamageImg, 70, 90);
				upgrade4.setIcon(bombDamageIcon);
			}
		});
		add(upgrade4);
		
		JLabel bombDamageCountDisplay = new SetText("", margin+370, 250, 60, 20, true);
		bombDamageCountDisplay.setHorizontalAlignment(JLabel.CENTER);
		bombDamageCountDisplay.setBorder(thin);
		add(bombDamageCountDisplay);
		
		JLabel needCoinDisplay_bombDamage = new SetText("", margin+375, 295, 60, 20, false);
		add(needCoinDisplay_bombDamage);
		
		JLabel bombDamageUp = new CreateBtn(plusIcon, margin+360+70, 250, 20, 20);
		bombDamageUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (bombDamageCount < 10) {
					if(getNeedCoins(bombDamageCount) <= coins) {
						useCoin(getNeedCoins(bombDamageCount));
						gm.setPlayerBombDamage(gm.getPlayerBombDamage()+100);
						refreshDisplay(bombDamage, bombDamageCountDisplay, needCoinDisplay_bombDamage, BOMBDAMAGE);
						comment.setText("룬과의 친화력이 더욱 강해진 것 같습니다...");
						
					}
					else {
						comment.setText("강화를 위한 코인이 부족합니다...");
					}
				}
				
			}
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		 });
		add(bombDamageUp);
		
		//--------------추가코인
		JLabel upgrade5 = new CreateBtn(plusCoinIcon, margin+480, 100, 100, 130);
		upgrade5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
				comment.setText("재물운이 늘어나 적에게서 얻는 코인이 증가합니다...");
				plusCoinIcon = scaleImage(plusCoinImg, 80, 100);
				upgrade5.setIcon(plusCoinIcon);
			}
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				plusCoinIcon = scaleImage(plusCoinImg, 70, 90);
				upgrade5.setIcon(plusCoinIcon);
			}
		});
		add(upgrade5);
		
		JLabel plusCoinCountDisplay = new SetText("", margin+490, 250, 60, 20, true);
		plusCoinCountDisplay.setHorizontalAlignment(JLabel.CENTER);
		plusCoinCountDisplay.setBorder(thin);
		add(plusCoinCountDisplay);
		
		JLabel needCoinDisplay_plusCoin = new SetText("", margin+495, 295, 60, 20, false);
		add(needCoinDisplay_plusCoin);
		
		JLabel plusCoinUp = new CreateBtn(plusIcon, margin+480+70, 250, 20, 20);
		plusCoinUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (plusCoinCount < 10) {
					if(getNeedCoins(plusCoinCount) <= coins) {
						useCoin(getNeedCoins(plusCoinCount));
						gm.setPlusCoin(gm.getPlusCoin()+1);
						refreshDisplay(plusCoin, plusCoinCountDisplay, needCoinDisplay_plusCoin, PLUSCOIN);
						comment.setText("재물운이 더 좋아진 것 같습니다...");
						
					}
					else {
						comment.setText("강화를 위한 코인이 부족합니다...");
					}
				}
			}
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		 });
		add(plusCoinUp);
		
		//디스플레이 설정값 초기화
		refreshDisplay(damage, damageCountDisplay, needCoinDisplay_damage, DAMAGE);
		refreshDisplay(hp, hpCountDisplay, needCoinDisplay_hp, HP);
		refreshDisplay(luck, luckCountDisplay, needCoinDisplay_luck, LUCK);
		refreshDisplay(bombDamage, bombDamageCountDisplay, needCoinDisplay_bombDamage, BOMBDAMAGE);
		refreshDisplay(plusCoin, plusCoinCountDisplay, needCoinDisplay_plusCoin, PLUSCOIN);
		
		//test button
//		JLabel test1 = new CreateBtn("money+", X-50, Y-50, 50, 50);
//		test1.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//            	gm.setCoin(gm.getCoin()+10);
//            	coins = gm.getCoin();
//            	coinDisplay.setText(Integer.toString(gm.getCoin()));
//            }
//		 });
//		add(test1);
//		
//		JLabel test2 = new CreateBtn("money-", X-100, Y-50, 50, 50);
//		test2.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//            	gm.setCoin(gm.getCoin()-10);
//            	coins = gm.getCoin();
//            	coinDisplay.setText(Integer.toString(gm.getCoin()));
//            }
//		 });
//		add(test2);
//		
//		JLabel test3 = new CreateBtn("display to Console", X-250, Y-50, 150, 50);
//		test3.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//            	System.out.print("========"+
//            			"\nGMcoins: "+gm.getCoin()+
//            			"\ncoin: "+coins+
//            			"\ndamage: "+ gm.getPlayerDamage()+
//            			"\nmaxHP: "+gm.getPlayerMaxHP()+
//            			"\nluck: "+gm.getPlayerLuck()+
//            			"\nbombDamage: "+gm.getPlayerBombDamage()+
//            			"\nplusCoin: "+gm.getPlusCoin()+"\n");
//            }
//		 });
//		add(test3);
//		
//		JLabel test4 = new CreateBtn("reset", X-300, Y-50, 50, 50);
//		test4.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				gm.setPlayerDamage(20);
//				gm.setPlayerMaxHP(0);
//				gm.setPlayerLuck(0.0);
//				gm.setPlusCoin(0);
//				gm.setPlayerBombDamage(0);
//				refreshDisplay(damage, damageCountDisplay, needCoinDisplay_damage, DAMAGE);
//				refreshDisplay(hp, hpCountDisplay, needCoinDisplay_hp, HP);
//				refreshDisplay(luck, luckCountDisplay, needCoinDisplay_luck, LUCK);
//				refreshDisplay(bombDamage, bombDamageCountDisplay, needCoinDisplay_bombDamage, BOMBDAMAGE);
//				refreshDisplay(plusCoin, plusCoinCountDisplay, needCoinDisplay_plusCoin, PLUSCOIN);
//				System.out.print("+reset+\n");
//            }
//		 });
//		add(test4);
		
	}
	
	private String doubleToPer(double luck) {
		DecimalFormat format = new DecimalFormat("#0");
		String per = format.format(luck * 100) + "%";
		
		return per;
	}
	
	public ImageIcon scaleImage(ImageIcon img, int width, int height) {
		Image originImg = img.getImage();
		Image changeImg =  originImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon scaledImg =  new ImageIcon(changeImg);
		return scaledImg;
	}
	
	private void refreshDisplay(JLabel state, JLabel count, JLabel needCoin, int type) {
		needCoin.setIcon(needCoinIcon);
		
		switch (type) {
		case 1:
			damageCount = (gm.getPlayerDamage() - 20) / 5;
			
			count.setText(damageCount+" / 10");
			state.setText("데미지: "+Integer.toString(gm.getPlayerDamage()));
			if (damageCount == 10) {
				needCoin.setText("MAX");
			}
			else {
				needCoin.setText(Integer.toString(getNeedCoins(damageCount)));
			}
			
			break;
			
		case 2:
			hpCount = gm.getPlayerMaxHP();
			
			count.setText(hpCount+" / 10");
			state.setText("체력: "+Integer.toString(gm.getPlayerMaxHP()));
			
			if (hpCount == 10) {
				needCoin.setText("MAX");
			}
			else {
				needCoin.setText(Integer.toString(getNeedCoins(hpCount)));
			}
			
			break;
			
		case 3:
			luckCount_double = gm.getPlayerLuck() * 100 ;
			luckCount = (int) Math.floor(luckCount_double);
			
			count.setText(luckCount+" / 10");
			state.setText("운: "+doubleToPer(gm.getPlayerLuck()));
			
			if (luckCount == 10) {
				needCoin.setText("MAX");
			}
			else {
				needCoin.setText(Integer.toString(getNeedCoins(luckCount)));
			}
			
			break;
			
		case 4:
			bombDamageCount = gm.getPlayerBombDamage() / 100;
			
			count.setText(bombDamageCount+" / 10");
			state.setText("룬데미지: "+Integer.toString(gm.getPlayerBombDamage()));	
			
			if (bombDamageCount == 10) {
				needCoin.setText("MAX");
			}
			else {
				needCoin.setText(Integer.toString(getNeedCoins(bombDamageCount)));
			}
			
			break;
			
		case 5:
			plusCoinCount = gm.getPlusCoin();
			
			count.setText(plusCoinCount+" / 10");
			state.setText("추가코인: "+Integer.toString(gm.getPlusCoin()));
			
			if (plusCoinCount == 10) {
				needCoin.setText("MAX");
			}
			
			else {
				needCoin.setText(Integer.toString(getNeedCoins(plusCoinCount)));
			}
			
			break;
		}
		
		coins = gm.getCoin();
		coinDisplay.setText(Integer.toString(coins));
	}
	
	private void useCoin(int value) {
		if (coins >= value) {
			gm.setCoin(gm.getCoin() - value);
			System.out.print("before:"+coins+" after:"+gm.getCoin()+"\n");
		}
		
	}
	
	private int getNeedCoins(int count) {
		int needCoin = 0;
		
		switch (count) {
		case 0:
			needCoin = 10;
			break;
		case 1:
			needCoin = 10;
			break;
		case 2:
			needCoin = 20;
			break;
		case 3:
			needCoin = 30;
			break;
		case 4:
			needCoin = 35;
			break;
		case 5:
			needCoin = 40;
			break;
		case 6:
			needCoin = 45;
			break;
		case 7:
			needCoin = 50;
			break;
		case 8:
			needCoin = 55;
			break;
		case 9:
			needCoin = 60;
			break;
		case 10:
			needCoin = 99999;
			break;	
		}
		return needCoin;
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(powerUpBg, 0, 0, 960, 540, null);
		g.drawImage(scrollImg, X-150, 0, 150, 80, null);
		g.drawImage(commentImg, margin, Y-margin -170, 560, 200, null);
		
		g.drawImage(commentImg, margin, 290, 70, 30, null);
		g.drawImage(commentImg, margin+130, 290, 70, 30, null);
		g.drawImage(commentImg, margin+250, 290, 70, 30, null);
		g.drawImage(commentImg, margin+370, 290, 70, 30, null);
		g.drawImage(commentImg, margin+490, 290, 70, 30, null);
	}

} 

