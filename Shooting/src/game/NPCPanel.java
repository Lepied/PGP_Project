package game;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;

public class NPCPanel extends JPanel {
	private Font customFont;
	private Image bgImage;
	 private String[] textToShow = 
		 	{"...", 
			 "아! 그래, 누구인가 했더니", 
			 "또 모험가구만, 질리지도 않는 것인가?",
			 "이런곳에서 만난것도 필시 인연일터, 나 좀 도와주지 않겠나?",
			 "이 앞에는 OOO들이 무리지어 있다네.",
			 "그런데 이 녀석들의 부산물이 아주 괜찮단 말이지.",
			 "처리해주면 내 사례는 섭섭지 않게 해주지. 어떤가?",
			 "고맙네! 지켜보고 있겠네!"};
    private String currentText = "...";
    private int currentCharIndex = 0;
    private int textNum=0;
    private Timer typingTimer;
    public boolean isNPCNow;
	
    public NPCPanel() {
    	setBounds(340,0,600,800);
    	setOpaque(false);
    	setVisible(false);
    	setLayout(null);
    	try {
			customFont = Font.createFont(Font.TRUETYPE_FONT, new File("resourses/MapleStory Bold.ttf")).deriveFont(20f);
		} catch (Exception e) {
			e.printStackTrace();

		}
        bgImage = new ImageIcon("resourses/sprites/UI-Conver.png").getImage();
        isNPCNow = true;
        Timer delayTimer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTypingEffect();
            }
        });
        delayTimer.setRepeats(false); // 한 번만 실행되도록 설정
        delayTimer.start();
        
        JLabel label = new JLabel("123123123123");
        label.setFont(customFont);
        label.setForeground(Color.WHITE);
        label.setBounds(0, 530, 600, 200); 
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               
                if (textNum < textToShow.length-1) {
                	textNum++;
                    startTypingEffect();
                }
                else 
                {
                	GameManager.getInstance().isNPCEnd=true;
                	
				}
                
    
            }
            

        });
        
        add(label);
        
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(bgImage, 0, 580, this);


        // 글씨를 그리는 위치 및 내용 설정
        int x = 50;
        int y = 700;

        g.setFont(customFont);
        g.setColor(Color.WHITE);
        g.drawString(currentText, x, y);
       
    
    }
    public void startTypingEffect() {
        currentText = "";
        currentCharIndex = 0;

        // 타이핑 효과를 위한 Timer 설정
        typingTimer = new Timer(300, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentCharIndex < textToShow[textNum].length()) {
                    currentText = textToShow[textNum].substring(0, currentCharIndex + 1);
                    currentCharIndex++;
                    repaint();
                } else {
                    // 타이핑이 끝났으면 타이머 중지
                    typingTimer.stop();
                }
            }
        });

        // 타이핑 효과 Timer 시작
        typingTimer.start();
    }

}