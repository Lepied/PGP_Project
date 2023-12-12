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
	private int X = 960;
	private int Y = 540;
	
	private Image bg;
	
	public StartPanel() {
		this.setLayout(null);
		this.setPreferredSize(new Dimension(X, Y));
		this.setBackground(Color.WHITE);
		this.setDoubleBuffered(true);
		this.bg = new ImageIcon("resourses/sprites/background.jpg").getImage();
		createText();
		createTitle();

		this.setFocusable(true);
	}
	
	private void createText() {
		int lX = 400;
		int lY = 50;
		
		JLabel label = new JLabel("- To start press any key... -");
		
		// 폰트 설정
		Font customFont = new Font("Harlow Solid Italic", Font.BOLD, 30);
	    label.setFont(customFont);

        // 색상 설정
	    label.setOpaque(true); 
        label.setForeground(Color.BLACK);
        label.setBackground(Color.WHITE);
        

        // 위치 및 크기 설정
        label.setBounds(960/2 - lX/2, 400, lX, lY);
        label.setHorizontalAlignment(JLabel.CENTER);
        
        // 테두리 설정
       // Border border = new LineBorder(Color.BLACK, 2);
        //label.setBorder(border);
		add(label);
	}
	
	public void createTitle() {
		JLabel label = new JLabel("REHIRE");
		label.setForeground(Color.BLACK);
		label.setBounds(X/2 - 500/2, 50, 500, 100);
		Font customFont = new Font("Elephant", Font.BOLD, 50);
		label.setFont(customFont);
        label.setHorizontalAlignment(JLabel.CENTER);
		add(label);
	}
	
//	public void paintComponent(Graphics g){
//		g.drawImage(bg, 0, 0, 960, 540, null);
//	}
	
}
