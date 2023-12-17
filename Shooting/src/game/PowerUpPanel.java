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
		JLabel coinDisplay = new SetText("coin: ", X-150, 0, 150, 50);
		Font coinFont = new Font("Elephant", Font.BOLD, 20);
		coinDisplay.setFont(coinFont);
		coinDisplay.setText(Integer.toString(gm.getCoin()));
		add(coinDisplay);

		coinDisplay.setBorder(border);
		
		//스탯 디스플레이
		JPanel statDisplay = new JPanel();
		statDisplay.setBounds(X - margin -280 , 100, 280, 380);
		statDisplay.setLayout(null);
		add(statDisplay);
		JLabel damage = new SetText("damage", 0 , 40, 150, 50);
		damage.setText("데미지: "+Integer.toString(gm.getPlayerDamage()));
		statDisplay.add(damage);
		JLabel hp = new SetText("hp", 0, 100, 150, 50);
		hp.setText("체력: "+Integer.toString(gm.getPlayerMaxHP()));
		statDisplay.add(hp);
		JLabel luck = new SetText("luck", 0, 160, 150, 50);
		luck.setText("운: "+doubleToPer(gm.getPlayerLuck()));
		statDisplay.add(luck);
		JLabel plusCoin = new SetText("plusCoin", 0, 220, 150, 50);
		plusCoin.setText("추가코인: "+Integer.toString(gm.getPlusCoin()));
		statDisplay.add(plusCoin);
		JLabel bombDamage = new SetText("bombDamage", 0, 280, 150, 50);
		bombDamage.setText("폭탄데미지: "+Integer.toString(gm.getPlayerBombDamage()));
		statDisplay.add(bombDamage);
		
		
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
		JLabel upgrade1 = new CreateBtn("damage", margin, 100, 100, 100 );
		upgrade1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				gm.setPlayerDamage(gm.getPlayerDamage()+5);
				damage.setText("데미지: "+Integer.toString(gm.getPlayerDamage()));
			}
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		 });
		add(upgrade1);
		
		JLabel upgrade2 = new CreateBtn("hp", margin+120, 100, 100, 100);
		upgrade2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				gm.setPlayerMaxHP(gm.getPlayerMaxHP()+1);
				hp.setText("체력: "+Integer.toString(gm.getPlayerMaxHP()));
			}
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		 });
		add(upgrade2);
		
		JLabel upgrade3 = new CreateBtn("luck", margin+240, 100, 100, 100);
		upgrade3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				gm.setPlayerLuck(gm.getPlayerLuck()+0.01);
				luck.setText("운: "+doubleToPer(gm.getPlayerLuck()));
			}
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		 });
		add(upgrade3);
		
		JLabel upgrade4 = new CreateBtn("bomb_damage", margin+360, 100, 100, 100);
		upgrade4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				gm.setPlayerBombDamage(gm.getPlayerBombDamage()+100);
				bombDamage.setText("폭탄데미지: "+Integer.toString(gm.getPlayerBombDamage()));
			}
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		 });
		add(upgrade4);
		
		JLabel upgrade5 = new CreateBtn("plusCoin", margin+480, 100, 100, 100);
		upgrade5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				gm.setPlusCoin(gm.getPlusCoin()+1);
				plusCoin.setText("추가코인: "+Integer.toString(gm.getPlusCoin()));
			}
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		 });
		add(upgrade5);
		
		//test button
		JLabel test1 = new CreateBtn("money+", X-50, Y-50, 50, 50);
		test1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
            	gm.setCoin(gm.getCoin()+10);
            	coinDisplay.setText(Integer.toString(gm.getCoin()));
            }
		 });
		add(test1);
		
		JLabel test2 = new CreateBtn("money-", X-100, Y-50, 50, 50);
		test2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
            	gm.setCoin(gm.getCoin()-10);
            	coinDisplay.setText(Integer.toString(gm.getCoin()));
            }
		 });
		add(test2);
		
		JLabel test3 = new CreateBtn("display to Console", X-250, Y-50, 150, 50);
		test3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
            	System.out.print("========"+
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
				damage.setText("데미지: "+Integer.toString(gm.getPlayerDamage()));
				hp.setText("체력: "+Integer.toString(gm.getPlayerMaxHP()));
				luck.setText("운: "+doubleToPer(gm.getPlayerLuck()));
				plusCoin.setText("추가코인: "+Integer.toString(gm.getPlusCoin()));
				bombDamage.setText("폭탄데미지: "+Integer.toString(gm.getPlayerBombDamage()));
				System.out.print("+reset+\n");
            }
		 });
		add(test4);
		
	}
	
	public void createText() {
		JLabel label = new JLabel("power");
		label.setForeground(Color.BLACK);
		label.setBounds(X/2 - 500/2, 50, 500, 100);
		Font customFont = new Font("Elephant", Font.BOLD, 50);
		label.setFont(customFont);
        label.setHorizontalAlignment(JLabel.CENTER);
		add(label);
	}
	
	public String doubleToPer(double luck) {
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
	
//	public void paintComponent(Graphics g){
//		g.drawImage(bg, 0, 0, 960, 540, null);
//	}

} 

