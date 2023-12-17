package game;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

import javax.swing.JLabel;

public class SetText extends JLabel{

	private static final long serialVersionUID = 1L;
	
	public Font customFont;

	public SetText(String text, int x, int y, int width, int height, boolean isBoarder) {
		this.setText(text);
		this.setBounds(x, y, width, height);
		this.setForeground(Color.BLACK);
		
		if(isBoarder == true) {
			this.setOpaque(true);
			this.setBackground(Color.WHITE);
		}
        
        try {
			customFont = Font.createFont(Font.TRUETYPE_FONT, new File("resourses/MapleStory Bold.ttf")).deriveFont(14f);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        this.setFont(customFont);
	}
	
}
