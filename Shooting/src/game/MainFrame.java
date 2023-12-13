package game;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MainFrame extends JFrame{
	private JFrame frame;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private GameMain gameMain;
	private SoundManager soundManager = new SoundManager();
	
	public int x = 960;
	public int y = 540;
	
	DataManager dm;
	
	public MainFrame() {
		dm = new DataManager();
		dm.loadData();
		
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
		
		soundManager.setMusic("resourses/Sound/Hollow-Knight-OST-Title-Theme.wav");
		soundManager.play();
		soundManager.setVolume(0.9f);
		soundManager.setVFXVolume(1.0f);
		
		//데이터 저장
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dm.saveData();
				System.out.println("system off");
				System.exit(0);
			}
	    });
		
		pack();
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
		soundManager.stop();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainFrame.this.dispose();
				gameMain = new GameMain();
				gameMain.setVisible(true);
			}
		});
	}

	
	//메인
	public static void main(String arg[]) {
		SwingUtilities.invokeLater(() -> new MainFrame());
	}
}
