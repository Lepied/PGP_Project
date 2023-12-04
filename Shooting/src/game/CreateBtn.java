package game;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class CreateBtn extends JLabel{
	// 텍스트
	public CreateBtn(String text, int x, int y, int width, int height) {
		this.setText(text);
		this.setBounds(x, y, width, height);
		this.setOpaque(false);

		Border border = new LineBorder(Color.BLACK, 2);
		this.setBorder(border);
 
	}
	
	// 이미지
	public CreateBtn(ImageIcon img, int x, int y, int width, int height) {
		this.setIcon(img);
		this.setBounds(x, y, width, height);
		this.setOpaque(false);

		Border border = new LineBorder(Color.BLACK, 2);
		this.setBorder(border);
 
	}
	
	/* 버튼 마우스 리스너 설정
	
	label.addMouseListener(new MouseAdapter() {
		@Override
    	public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
        
        public void mouseClicked(MouseEvent e) {
        	main.changePanel("main");
        }
	 }); 
	 
	*/
}
