package game;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class SetText extends JLabel{
	public SetText(String text, int x, int y, int width, int height) {
		this.setText(text);
		this.setBounds(x, y, width, height);
		this.setForeground(Color.BLACK);
        this.setHorizontalAlignment(JLabel.CENTER);

	}
}
