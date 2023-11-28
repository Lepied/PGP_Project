package game;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MainFrame extends JFrame implements Runnable{
	private JFrame frame;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	
	Thread thread;
	
	public int x = 960;
	public int y = 540;
	
	public MainFrame() {
		thread = new Thread(this);
		thread.start();
		
		setResizable(false);
		setTitle("REHIRE");
		setSize(x, y);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout); 
		createDeck(); 
		
		pack();
	}

	@Override
	public void run() {
		try {
			while(true) {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//카드레이아웃 덱 생성(각 패널 저장)
	private void createDeck() {
		MainPanel mainPanel = new MainPanel();
		StartPanel startPanel = new StartPanel();
		
		cardPanel.add(startPanel, "start");
		cardPanel.add(mainPanel, "main");
		
		add(cardPanel);
		
		//아무 버튼이나 누르시오
		startPanel.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}

			public void keyPressed(KeyEvent e) {
				int i = e.getKeyChar();
				System.out.print(i);
				cardLayout.show(cardPanel, "main");
			}

			public void keyReleased(KeyEvent e) {}
			
		});
	}
	
	//메인
	public static void main(String arg[]) {
		SwingUtilities.invokeLater(() -> new MainFrame());
	}
}
