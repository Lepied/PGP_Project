package game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class SelectPanel extends JPanel {
    private boolean isMouseEntered = false;
    public int type;
    public SelectPanel(int x, int y, int width, int height, int num) {
        setBounds(x, y, width, height);
        setVisible(false);
        setOpaque(false);
        
        this.type = num;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isMouseEntered = true;
                System.out.println("마우스가 "+ type +" 영역에 진입했습니다");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isMouseEntered = false;
                System.out.println("마우스가 "+ type +" 영역을 벗어났습니다");
            }
            
            public void mouseClicked(MouseEvent e) {
                handleMouseClick();
            }
        });
    }

    public boolean isMouseEntered() {
        return isMouseEntered;
    }
    
    public void handleMouseClick() {
        System.out.println("패널이 클릭되었습니다");
        // 패널을 클릭했을 때 수행할 동작을 여기에 추가
    }
}