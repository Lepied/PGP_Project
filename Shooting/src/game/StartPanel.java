package game;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;

public class StartPanel extends JPanel{
	MainPanel main = new MainPanel();
	
	public StartPanel() {
		this.setPreferredSize(new Dimension(960, 540));
		this.setBackground(Color.WHITE);
		this.setDoubleBuffered(true);
		createText();

		this.setFocusable(true);
	}
	
	public void createText() {
		JLabel label = new JLabel("Press any button...");
		add(label);
	}
}
