package game;

import java.awt.*;

import javax.swing.*;

public class MainPanel extends JPanel implements Runnable{
	
	public MainPanel() {
		setLayout(null);
		this.setPreferredSize(new Dimension(960, 540));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		createText();
		
//		SelectPanel start_Btn = new SelectPanel()
	}
	
	public void createText() {
		JLabel label = new JLabel("BLUE ARCHIVE!"); 
		add(label);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	//기습 브루~~~~~~~~~~~ 아카이브~!
} 
