package game;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
	
	 ImageIcon gameOverImage;

	public String text = new String("          You Died      ");
    public InfoPanel() {
    	setBounds(340,200,600,200);
    	setOpaque(false);
    	setVisible(true);
    	setLayout(null);
       
    	gameOverImage = new ImageIcon("resources/sprites/InfoBG.png");

        JLabel youDiedLabel = new JLabel(text);
        youDiedLabel.setFont(new Font("Arial", Font.BOLD, 60));
        youDiedLabel.setForeground(Color.RED);
        youDiedLabel.setBounds(0, 0, 600, 200); 
       
        add(youDiedLabel);
        repaint();
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(gameOverImage.getImage(), 0, 0, getWidth(), getHeight(), this);

        
    }
}