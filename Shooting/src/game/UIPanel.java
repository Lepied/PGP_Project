package game;

import javax.swing.*;
import java.awt.*;

public class UIPanel extends JPanel {

    public UIPanel(int width, int height) {
        setLayout(null); 
        setOpaque(false); 

        setBounds(0, 0, width, height); 
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}