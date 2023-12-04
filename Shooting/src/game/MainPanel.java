package game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class MainPanel extends JPanel implements Runnable{
	private int X = 960;
	private int Y = 540;
	
	MainFrame main;
	
	ImageIcon start = new ImageIcon("resourses/sprites/start.png");
	ImageIcon startBtn = scaleImage(start, 300, 100);
	ImageIcon powerUp = new ImageIcon("resourses/sprites/powerUp.png");
	ImageIcon powerUpBtn = scaleImage(powerUp, 300, 100);
	Image bg = new ImageIcon("resourses/sprites/background.jpg").getImage();
	
	
	public MainPanel(MainFrame main) {
		this.main = main;
		
		setLayout(null);
		this.setPreferredSize(new Dimension(X, Y));
		this.setBackground(Color.WHITE);
		this.setDoubleBuffered(true);
		createText();
		
		JLabel start = new CreateBtn(startBtn, 20, Y/2 , 300, 100);
		start.addMouseListener(new MouseAdapter() {
			@Override
	        public void mouseClicked(MouseEvent e) {
	        	main.gameStart();
	        }
		 }); 
		add(start);
		
		JLabel powerUp = new CreateBtn(powerUpBtn, 20, Y/2 + 120 , 300, 100);
		powerUp.addMouseListener(new MouseAdapter() {
			@Override
	        public void mouseClicked(MouseEvent e) {
				main.changePanel("powerUp");
	        }
		 }); 
		add(powerUp);
		
	}
	
	//타이틀 생성
	public void createText() {
		JLabel label = new JLabel("REHIRE");
		label.setForeground(Color.BLACK);
		label.setBounds(X/2 - 500/2, 50, 500, 100);
		Font customFont = new Font("Elephant", Font.BOLD, 50);
		label.setFont(customFont);
        label.setHorizontalAlignment(JLabel.CENTER);
		add(label);
	}
	
	//이미지 크기 조정
	public ImageIcon scaleImage(ImageIcon img, int width, int height) {
		Image originImg = img.getImage();
		Image changeImg =  originImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon scaledImg =  new ImageIcon(changeImg);
		return scaledImg;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

//	public void paintComponent(Graphics g){
//		g.drawImage(bg, 0, 0, 960, 540, null);
//	}

} 
