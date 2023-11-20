package game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class MainFrame extends JFrame{
	private CardLayout cardLayout;
	private JPanel cardPanel;
	
	public int x = 960;
	public int y = 540;
	
	public MainFrame() {
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

	public static void main(String arg[]) {
		SwingUtilities.invokeLater(() -> new MainFrame());
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

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int i = e.getKeyChar();
				System.out.print(i);
				cardLayout.show(cardPanel, "main");
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	// 마우스 커서 좌표 추적
	public void mouseLocation() {
		PointerInfo pt = MouseInfo.getPointerInfo();
		while(true) {
			pt = MouseInfo.getPointerInfo();
			
			System.out.println(pt.getLocation());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
