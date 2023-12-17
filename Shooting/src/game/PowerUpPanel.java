package game;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

	public PowerUpPanel(MainFrame main) {
		this.main = main;
		gm = GameManager.getInstance();
		Border border = new LineBorder(Color.BLACK, 2);
		
		setLayout(null);
		this.setPreferredSize(new Dimension(X, Y));
		this.setBackground(Color.WHITE);
		this.setDoubleBuffered(true);
		
		//코인 디스플레이
		coins = gm.getCoin();
		coinDisplay = new SetText("coin: ", X-150, 0, 150, 50);
		
		Font coinFont = new Font("Elephant", Font.BOLD, 20);
		coinDisplay.setFont(coinFont);
		coinDisplay.setText(Integer.toString(coins));
		add(coinDisplay);

		coinDisplay.setBorder(border);
		
		//강화 관련 코멘트 디스플레이
		JLabel comment = new SetText("comment", margin+40, Y-margin -130, 500, 130);
//		Font commentFont = new Font("courier", Font.BOLD, 20);
//		comment.setFont(commentFont);
		comment.setBorder(border);
		add(comment);
		
		//스탯 디스플레이
		JPanel statDisplay = new JPanel();
		statDisplay.setBounds(X - margin -280 , 100, 280, 380);
		statDisplay.setLayout(null);
		add(statDisplay);
		
		JLabel damage = new SetText("damage", 0 , 40, 150, 50);
		statDisplay.add(damage);
		
		JLabel hp = new SetText("hp", 0, 100, 150, 50);
		statDisplay.add(hp);
		
		JLabel luck = new SetText("luck", 0, 160, 150, 50);
		statDisplay.add(luck);
		
		JLabel bombDamage = new SetText("bombDamage", 0, 220, 150, 50);
		statDisplay.add(bombDamage);
		
		JLabel plusCoin = new SetText("plusCoin", 0, 280, 150, 50);
		statDisplay.add(plusCoin);
		
		
		
		statDisplay.setBorder(border);
		
		//뒤로가기
		JLabel back = new CreateBtn("Back", 0, 0, 50, 50);
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				main.changePanel("main");
			}
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}  
		 });
		add(back);
		
		//업그레이드
		
		//데미지
		JLabel upgrade1 = new CreateBtn("damage", margin, 100, 100, 100);
		add(upgrade1);
		
		JLabel damageCountDisplay = new CreateBtn("", margin, 220, 60, 20);
		add(damageCountDisplay);
		
		JLabel damageUp = new CreateBtn("", margin+70, 220, 20, 20 );
		damageUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (damageCount < 10) {
					if(getNeedCoins(damageCount) <= coins) {
						useCoin(getNeedCoins(damageCount));	
						gm.setPlayerDamage(gm.getPlayerDamage()+5);
						refreshDisplay(damage, damageCountDisplay, DAMAGE);
						comment.setText("마법의 기운이 더욱 강해진 것 같다.");
						
					}
					else {
						comment.setText("강화를 위한 돈이 부족합니다...");
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
		
		//체력
		JLabel upgrade2 = new CreateBtn("hp", margin+120, 100, 100, 100);
		add(upgrade2);
		
		JLabel hpCountDisplay = new CreateBtn("", margin+120, 220, 60, 20);
		add(hpCountDisplay);
		
		JLabel hpUp = new CreateBtn("", margin+120+70, 220, 20, 20);
		hpUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (hpCount < 10) {
					if(getNeedCoins(hpCount) < coins) {
						useCoin(getNeedCoins(hpCount));
						gm.setPlayerMaxHP(gm.getPlayerMaxHP()+1);
						refreshDisplay(hp, hpCountDisplay, HP);
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
		
		//드랍률
		JLabel upgrade3 = new CreateBtn("luck", margin+240, 100, 100, 100);
		add(upgrade3);
		
		JLabel luckCountDisplay = new CreateBtn("", margin+240, 220, 60, 20);
		add(luckCountDisplay);
		
		JLabel luckUp = new CreateBtn("", margin+240+70, 220, 20, 20);
		luckUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (luckCount < 10) {
					if(getNeedCoins(luckCount) < coins) {
						useCoin(getNeedCoins(luckCount));
						gm.setPlayerLuck(gm.getPlayerLuck()+0.01);
						refreshDisplay(luck, luckCountDisplay, LUCK);
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
		
		//폭탄 데미지
		JLabel upgrade4 = new CreateBtn("bombDamage", margin+360, 100, 100, 100);
		add(upgrade4);
		
		JLabel bombDamageCountDisplay = new CreateBtn("", margin+360, 220, 60, 20);
		add(bombDamageCountDisplay);
		
		JLabel bombDamageUp = new CreateBtn("", margin+360+70, 220, 20, 20);
		bombDamageUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (bombDamageCount < 10) {
					if(getNeedCoins(bombDamageCount) < coins) {
						useCoin(getNeedCoins(bombDamageCount));
						gm.setPlayerBombDamage(gm.getPlayerBombDamage()+100);
						refreshDisplay(bombDamage, bombDamageCountDisplay, BOMBDAMAGE);
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
		
		//추가코인
		JLabel upgrade5 = new CreateBtn("plusCoin", margin+480, 100, 100, 100);
		add(upgrade5);
		
		JLabel plusCoinCountDisplay = new CreateBtn("", margin+480, 220, 60, 20);
		add(plusCoinCountDisplay);
		
		JLabel plusCoinUp = new CreateBtn("", margin+480+70, 220, 20, 20);
		plusCoinUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (plusCoinCount < 10) {
					if(getNeedCoins(plusCoinCount) < coins) {
						useCoin(getNeedCoins(plusCoinCount));
						gm.setPlusCoin(gm.getPlusCoin()+1);
						refreshDisplay(plusCoin, plusCoinCountDisplay, PLUSCOIN);
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
		refreshDisplay(damage, damageCountDisplay, DAMAGE);
		refreshDisplay(hp, hpCountDisplay, HP);
		refreshDisplay(luck, luckCountDisplay, LUCK);
		refreshDisplay(bombDamage, bombDamageCountDisplay, BOMBDAMAGE);
		refreshDisplay(plusCoin, plusCoinCountDisplay, PLUSCOIN);
		
		//test button
		JLabel test1 = new CreateBtn("money+", X-50, Y-50, 50, 50);
		test1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
            	gm.setCoin(gm.getCoin()+10);
            	coins = gm.getCoin();
            	coinDisplay.setText(Integer.toString(gm.getCoin()));
            }
		 });
		add(test1);
		
		JLabel test2 = new CreateBtn("money-", X-100, Y-50, 50, 50);
		test2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
            	gm.setCoin(gm.getCoin()-10);
            	coins = gm.getCoin();
            	coinDisplay.setText(Integer.toString(gm.getCoin()));
            }
		 });
		add(test2);
		
		JLabel test3 = new CreateBtn("display to Console", X-250, Y-50, 150, 50);
		test3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
            	System.out.print("========"+
            			"\nGMcoins: "+gm.getCoin()+
            			"\ncoin: "+coins+
            			"\ndamage: "+ gm.getPlayerDamage()+
            			"\nmaxHP: "+gm.getPlayerMaxHP()+
            			"\nluck: "+gm.getPlayerLuck()+
            			"\nbombDamage: "+gm.getPlayerBombDamage()+
            			"\nplusCoin: "+gm.getPlusCoin()+"\n");
            }
		 });
		add(test3);
		
		JLabel test4 = new CreateBtn("reset", X-300, Y-50, 50, 50);
		test4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				gm.setPlayerDamage(20);
				gm.setPlayerMaxHP(0);
				gm.setPlayerLuck(0.0);
				gm.setPlusCoin(0);
				gm.setPlayerBombDamage(0);
				refreshDisplay(damage, damageCountDisplay, DAMAGE);
				refreshDisplay(hp, hpCountDisplay, HP);
				refreshDisplay(luck, luckCountDisplay, LUCK);
				refreshDisplay(bombDamage, bombDamageCountDisplay, BOMBDAMAGE);
				refreshDisplay(plusCoin, plusCoinCountDisplay, PLUSCOIN);
				System.out.print("+reset+\n");
            }
		 });
		add(test4);
		
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
	
	private void refreshDisplay(JLabel state, JLabel count, int type) {
		switch (type) {
		case 1:
			damageCount = (gm.getPlayerDamage() - 20) / 5;
			count.setText(damageCount+" / 10");
			state.setText("데미지: "+Integer.toString(gm.getPlayerDamage()));
			break;
			
		case 2:
			hpCount = gm.getPlayerMaxHP();
			count.setText(hpCount+" / 10");
			state.setText("체력: "+Integer.toString(gm.getPlayerMaxHP()));
			break;
			
		case 3:
			luckCount_double = gm.getPlayerLuck() * 100 ;
			luckCount = (int) Math.floor(luckCount_double);
			count.setText(luckCount+" / 10");
			state.setText("운: "+doubleToPer(gm.getPlayerLuck()));
			break;
			
		case 4:
			bombDamageCount = gm.getPlayerBombDamage() / 100;
			count.setText(bombDamageCount+" / 10");
			state.setText("폭탄데미지: "+Integer.toString(gm.getPlayerBombDamage()));
			break;
			
		case 5:
			plusCoinCount = gm.getPlusCoin();
			count.setText(plusCoinCount+" / 10");
			state.setText("추가코인: "+Integer.toString(gm.getPlusCoin()));
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
			needCoin = 65;
			break;	
		}
		return needCoin;
	}
//	public void paintComponent(Graphics g){
//		g.drawImage(bg, 0, 0, 960, 540, null);
//	}

	
	//스탯 강화 카운트 
	
//	damageCount = (gm.getPlayerDamage() - 20) / 5;
//	hpCount = gm.getPlayerMaxHP();
//	luckCount_double = gm.getPlayerLuck() * 100 ;
//	luckCount = (int) Math.floor(luckCount_double);
//	bomb_damageCount = gm.getPlayerBombDamage() / 100;
//	plusCoinCount = gm.getPlusCoin();
} 

