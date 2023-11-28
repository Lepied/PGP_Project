package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import javax.swing.*;

public class StartPanel extends JPanel{
	MainPanel main = new MainPanel();
	private Image bg;
	
//	private x = 
	
	public StartPanel() {
		this.setLayout(null);
		this.setPreferredSize(new Dimension(960, 540));
		this.setBackground(Color.WHITE);
		this.setDoubleBuffered(true);
//		this.bg = new ImageIcon("resourses/sprites/startBackground.png").getImage();
		createText();

		this.setFocusable(true);
	}
	
	private void createText() {
		int lX = 400;
		int lY = 50;
		
		JLabel label = new JLabel("To start press any key...");
		Font customFont = new Font("Harlow Solid Italic", Font.BOLD, 30);
	    label.setFont(customFont);

        // 텍스트 색상 설정
        label.setForeground(Color.BLACK);
        

        // 위치와 크기 설정
        label.setBounds(960/2 - lX/2, 400, lX, lY);
        label.setHorizontalAlignment(JLabel.CENTER);
        
     // 라벨 주위에 테두리 추가
        Border border = new LineBorder(Color.BLACK, 2);
        label.setBorder(border);
		add(label);
	}
	
//	public void paintComponent(Graphics g){
//		g.drawImage(bg, 0, 0, 960, 540, null);
//	}
	
}
