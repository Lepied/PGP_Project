package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;


import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class BossUI extends JPanel {
	
	private JProgressBar healthBar;
	
	
    public BossUI(int x, int y, int width, int height, GameMain gameMain) {
        setBounds(x, y, width, height);
        setVisible(false);
        setOpaque(false);
        
      
        JPanel bgPanel = new JPanel()
        {
        	@Override
        	protected void paintComponent(Graphics g)
        	{
        		super.paintComponent(g);
        		ImageIcon backgroundIcon = new ImageIcon("resourses/sprites/Healthbar.png");
        		Image backgroundImg = backgroundIcon.getImage();

        		g.drawImage(backgroundImg,0,0, getWidth(), getHeight(),this);
        	}
        	
        };
        bgPanel.setPreferredSize(new Dimension(width,height-10));
        bgPanel.setOpaque(false);

        
        healthBar = new JProgressBar();

        ImageIcon fillIcon = new ImageIcon("resourses/sprites/Healthbar_HP.png");
        Image fillImg = fillIcon.getImage();
        healthBar.setUI(new ProgressBarUI(null,fillImg));
        healthBar.setPreferredSize(new Dimension(500, 40 ));
        healthBar.setBackground(new Color(0, 0, 0, 0));

        
        add(bgPanel);
        add(healthBar);

    }
    
    public void updateHealth(int currentHealth, int maxHealth)
    {
    	healthBar.setMaximum(maxHealth);
    	healthBar.setValue(currentHealth);
    	
    }

}