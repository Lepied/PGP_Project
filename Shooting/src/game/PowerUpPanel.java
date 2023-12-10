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
	
	MainFrame main;
	GameManager gm;
	
//	ImageIcon start = new ImageIcon("resourses/sprites/start.png");
//	ImageIcon startBtn = scaleImage(start, 300, 100);
//	ImageIcon powerUp = new ImageIcon("resourses/sprites/powerUp.png");
//	ImageIcon powerUpBtn = scaleImage(powerUp, 300, 100);
//	Image bg = new ImageIcon("resourses/sprites/background.jpg").getImage();

	public PowerUpPanel(MainFrame main) {
		this.main = main;
		gm = GameManager.getInstance();
		
		setLayout(null);
		this.setPreferredSize(new Dimension(X, Y));
		this.setBackground(Color.WHITE);
		this.setDoubleBuffered(true);
		
		//코인
		JLabel coinDisplay = new SetText("coin: ", X-150, 0, 150, 50, "Elephant");
		Font customFont = new Font("Elephant", Font.BOLD, 20);
		coinDisplay.setFont(customFont);
		coinDisplay.setText(Integer.toString(gm.getCoin()));
		add(coinDisplay);
		
		Border border = new LineBorder(Color.BLACK, 2);
		coinDisplay.setBorder(border);
		
		//뒤로가기
		JLabel back = new CreateBtn("Back", 0, 0, 50, 50);
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
            	main.changePanel("main");
            }
		 });
		add(back);
		
		JLabel upgrade1 = new CreateBtn("damage", 100, 100, 100, 100);
		upgrade1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
            	
            }
		 });
		add(upgrade1);
		
		JLabel upgrade2 = new CreateBtn("armor", 220, 100, 100, 100);
		upgrade2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
            	
            }
		 });
		add(upgrade2);
		
		JLabel upgrade3 = new CreateBtn("cri_per", 340, 100, 100, 100);
		upgrade3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
            	
            }
		 });
		add(upgrade3);
		
		JLabel upgrade4 = new CreateBtn("cri_dam", 460, 100, 100, 100);
		upgrade4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
            	
            }
		 });
		add(upgrade4);
		
		JLabel upgrade5 = new CreateBtn("health", 580, 100, 100, 100);
		upgrade5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
            	
            }
		 });
		add(upgrade5);
		
		//test btn
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

