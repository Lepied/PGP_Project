package game;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainPanel extends JPanel{
	
	public MainPanel() {
		this.setPreferredSize(new Dimension(960, 540));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		createText();
	}
	
	public void createText() {
		JLabel label = new JLabel("Here is main");
		add(label);
	}
}
