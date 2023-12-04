package game;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MainFrame extends JFrame implements Runnable{
	private JFrame frame;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private GameMain gameMain;

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
		add(cardPanel);
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
	
	// 카드레이아웃 덱 생성(각 패널 저장)
	private void createDeck() {
		StartPanel startPanel = new StartPanel();
		MainPanel mainPanel = new MainPanel(this);		
		PowerUpPanel powerUpPanel = new PowerUpPanel(this);
		
		cardPanel.add(startPanel, "start");
		cardPanel.add(mainPanel, "main");
		cardPanel.add(powerUpPanel,"powerUp");
		
		//아무 버튼이나 누르시오
		startPanel.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}

			public void keyPressed(KeyEvent e) {
				int i = e.getKeyChar();
				System.out.print(i);
				changePanel("main");
			}

			public void keyReleased(KeyEvent e) {}
			
		});
	}
	
	// 패널 변경
	public void changePanel(String panel) {
		cardLayout.show(cardPanel, panel);
		
	}
	
	public void gameStart() {
		this.setVisible(false);
		gameMain = new GameMain();
		gameMain.setVisible(true);
	}
	
	//메인
	public static void main(String arg[]) {
		SwingUtilities.invokeLater(() -> new MainFrame());
	}
}
