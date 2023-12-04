package game;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class SetText extends JLabel{
	public SetText(String text, int x, int y, int width, int height, String font) {
		this.setText(text);
		this.setBounds(x, y, width, height);
		this.setForeground(Color.BLACK);
		Font customFont = new Font(font, Font.BOLD, 50);
		this.setFont(customFont);
        this.setHorizontalAlignment(JLabel.CENTER);

	}
}
