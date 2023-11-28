package game;

import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;
import java.awt.image.ImageObserver;

public class ProgressBarUI extends BasicProgressBarUI {
    private Image backgroundImage;
    private Image fillImage;

    public ProgressBarUI(Image backgroundImage, Image fillImage) {
        this.backgroundImage = backgroundImage;
        this.fillImage = fillImage;
    }

    @Override
    protected void paintDeterminate(Graphics g, JComponent c) {
    	super.paintDeterminate(g, c);
    	Insets b =progressBar.getInsets();
    	
        int barRectWidth = progressBar.getWidth() - (b.right + b.left);
        int barRectHeight = progressBar.getHeight() - (b.top + b.bottom);
        int amountFull = getAmountFull(b, barRectWidth, barRectHeight);

       
        if (amountFull > 0) {
            g.drawImage(fillImage, b.left, b.top, amountFull, barRectHeight, (ImageObserver) c);
        }
        g.drawImage(backgroundImage, b.left, b.top, barRectWidth, barRectHeight, (ImageObserver) c);
       ;
    }
}
