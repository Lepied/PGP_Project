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
        // 패널이 그려질 내용 추가 (예: 배경 이미지, 텍스트 등)
    }

    // 다른 필요한 메서드나 설정을 추가할 수 있습니다.
}