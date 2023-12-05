package game;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class NPCPanel extends JPanel {

	private Image bgImage;
    public NPCPanel() {
    	setBounds(340,0,600,800);
    	setOpaque(false);
    	setVisible(false);
        bgImage = new ImageIcon("resourses/sprites/UI-Conver.png").getImage();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(bgImage, 0, 580, this);


    }

}