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
	
	ImageIcon startImg = new ImageIcon("resourses/sprites/start.png");
	ImageIcon startBtn = scaleImage(startImg, 300, 100);
	ImageIcon powerUpImg = new ImageIcon("resourses/sprites/powerUp.png");
	ImageIcon powerUpBtn = scaleImage(powerUpImg, 300, 100);
	Image bg = new ImageIcon("resourses/sprites/background.jpg").getImage();
	Image title = new ImageIcon("resourses/sprites/title.png").getImage();;
	
	public MainPanel(MainFrame main) {
		this.main = main;
		
		setLayout(null);
		this.setPreferredSize(new Dimension(X, Y));
		this.setBackground(Color.WHITE);
		this.setDoubleBuffered(true);
		createTitle();
		
		JLabel start = new CreateBtn(startBtn, 20, Y/2 , 300, 100);
		start.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				main.gameStart();
			}
			public void mouseEntered(MouseEvent e) {
				startBtn = scaleImage(startImg, 330, 120);
				start.setBounds(20, Y/2-10, 330, 120);
				start.setIcon(startBtn);
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				startBtn = scaleImage(startImg, 300, 100);
				start.setBounds(20, Y/2, 300, 100);
				start.setIcon(startBtn);
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		start.setBorder(null);
		add(start);
		
		JLabel powerUp = new CreateBtn(powerUpBtn, 20, Y/2 + 120 , 300, 100);;
			powerUp.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					main.changePanel("powerUp");
				}
				public void mouseEntered(MouseEvent e) {
					powerUpBtn = scaleImage(powerUpImg, 330, 120);
					powerUp.setBounds(20, Y/2+120 -10, 330, 120);
					powerUp.setIcon(powerUpBtn);
					setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
				public void mouseExited(MouseEvent e) {
					powerUpBtn = scaleImage(powerUpImg, 300, 100);
					powerUp.setBounds(20, Y/2+120, 300, 100);
					powerUp.setIcon(powerUpBtn);
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
			});
		powerUp.setBorder(null);
		add(powerUp);
		
	}
	
	//타이틀 생성
	public void createTitle() {
		JLabel label = new JLabel("REHIRE");
		label.setForeground(Color.BLACK);
		label.setBounds(X/2 - 500/2, 60, 500, 100);
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

	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, 960, 540, null);
		g.drawImage(title, X/2 - 500/2, 0, 500, 250, null);
	}

} 
