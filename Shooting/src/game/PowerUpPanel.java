package game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
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
		
		//코인
		JLabel coinDisplay = new SetText("coin: ", X-150, 0, 150, 50);
		Font coinFont = new Font("Elephant", Font.BOLD, 20);
		coinDisplay.setFont(coinFont);
		coinDisplay.setText(Integer.toString(gm.getCoin()));
		add(coinDisplay);

		coinDisplay.setBorder(border);
		
		//스탯
		JLabel statDisplay = new SetText("state", X - margin -280 , 100, 280, 380);
		add(statDisplay);
		
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

			}
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		 });
		add(upgrade1);
		
		JLabel upgrade2 = new CreateBtn("armor", margin+120, 100, 100, 100);
		upgrade2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		 });
		add(upgrade2);
		
		JLabel upgrade3 = new CreateBtn("cri_per", margin+240, 100, 100, 100);
		upgrade3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		 });
		add(upgrade3);
		
		JLabel upgrade4 = new CreateBtn("cri_dam", margin+360, 100, 100, 100);
		upgrade4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		 });
		add(upgrade4);
		
		JLabel upgrade5 = new CreateBtn("health", margin+480, 100, 100, 100);
		upgrade5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		 });
		add(upgrade5);
		
		JLabel upgrade6 = new CreateBtn("damage", margin, 250, 100, 100 );
		upgrade6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		 });
		add(upgrade6);
		
		JLabel upgrade7 = new CreateBtn("damage", margin+120, 250, 100, 100 );
		upgrade7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		 });
		add(upgrade7);
		
		
		JLabel upgrade8 = new CreateBtn("damage", margin+240, 250, 100, 100 );
		upgrade8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		 });
		add(upgrade8);
		
		JLabel upgrade9 = new CreateBtn("damage", margin+360, 250, 100, 100 );
		upgrade9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		 });
		add(upgrade9);
		
		JLabel upgrade10 = new CreateBtn("damage", margin+480, 250, 100, 100 );
		upgrade10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		 });
		add(upgrade10);
		
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


	}
	
//	public String test() {
//		String x = Integer.toString(gm.getCoin());
//		System.out.print(x);
//		return x;
//	}
	
	public void createText() {
		JLabel label = new JLabel("power");
		label.setForeground(Color.BLACK);
		label.setBounds(X/2 - 500/2, 50, 500, 100);
		Font customFont = new Font("Elephant", Font.BOLD, 50);
		label.setFont(customFont);
        label.setHorizontalAlignment(JLabel.CENTER);
		add(label);
	}
	
	public void coinDisplay() {
		JLabel label = new JLabel();
		label.setForeground(Color.BLACK);
		label.setBounds(X/2 - 500/2, 50, 500, 100);
		Font customFont = new Font("Elephant", Font.BOLD, 50);
		label.setFont(customFont);
        label.setHorizontalAlignment(JLabel.CENTER);
		add(label);
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

