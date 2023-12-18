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
	private Image npcImage;
	private String[] textToShow = 
		 	{"...", 
			 "아! 그래, 누구인가 했더니", 
			 "또 모험가구만, 질리지도 않는 것인가?",
			 "이런곳에서 만난것도 필시 인연일터, 나 좀 도와주지 않겠나?",
			 "이 앞에는 아룡이 있다네.",
			 "그런데 이 녀석의 부산물이 아주 괜찮단 말이지.",
			 "처리해주면 내 사례는 섭섭지 않게 해주지. 어떤가?",
			 "고맙네! 지켜보고 있겠네!"};
	private String[] textToShow2 = 
	 	{"...", 
		 "또 만났군, 자네!", 
		 "벌써 아룡을 무찔렀나?",
		 "내 눈이 틀리지 않은 모양이로군.",
		 "자네의 마법이 뭔가 달라지지 않았나?",
		 "그 마법은 괴물들의 몸을 관통하여 지나가는",
		 "강력한 전격을 뿜어내지.",
		 "그 힘을 이용해 더 많은 녀석들을 무찔러 주게.",
		 "덤으로 거기서 나온 것들좀 가져다 주면 더 좋고 하하!"};
    private String currentText = "...";
    private int currentCharIndex = 0;
    private int textNum=0;
    private Timer typingTimer;
    private Timer typingTimer2;
  
    private GameMain gameMain;
	
    public NPCPanel(GameMain gameMain) {
    	setBounds(340,0,600,800);
    	setOpaque(false);
    	setVisible(false);
    	setLayout(null);
    	this.gameMain = gameMain;
    	try {
			customFont = Font.createFont(Font.TRUETYPE_FONT, new File("resourses/MapleStory Bold.ttf")).deriveFont(20f);
		} catch (Exception e) {
			e.printStackTrace();

		}
        bgImage = new ImageIcon("resourses/sprites/UI-Conver.png").getImage();
        npcImage = new ImageIcon("resourses/sprites/NPC_highQ.png").getImage();
        GameManager.getInstance().isNPCEnd = false;
        GameManager.getInstance().isQuest = false;
        Timer delayTimer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTypingEffect();
            }
        });
        delayTimer.setRepeats(false); // 한 번만 실행되도록 설정
        delayTimer.start();
        
        JLabel label = new JLabel("???");
        label.setFont(customFont);
        label.setForeground(Color.WHITE);
        label.setBounds(0, 530, 600, 200); 
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               if(GameManager.getInstance().isQuestFinished==false)
               {
            	   System.out.println("1111");
					if (textNum < textToShow.length - 1) {
						textNum++;
						startTypingEffect();
					} else {
						GameManager.getInstance().isQuest = true;
						GameManager.getInstance().isNPCEnd = true;
						typingTimer.stop();
						gameMain.resumeGame();
						currentCharIndex = 0;
		            	textNum=0;
					}
               }
               else if(GameManager.getInstance().isQuestFinished)
               {
            	   System.out.println("2222");
					if (textNum < textToShow2.length - 1) {
						textNum++;
						startTypingEffect2();
					} else {
						GameManager.getInstance().isQuest = true;
						GameManager.getInstance().isNPCEnd = true;
						typingTimer2.stop();
						gameMain.resumeGame();
					}
               }
    
                
    
            }
            

        });
        
        add(label);
        
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(npcImage, -40, 200,this);
        g.drawImage(bgImage, 0, 580, this);
        


        // 글씨를 그리는 위치 및 내용 설정
        int x = 50;
        int y = 700;

        g.setFont(customFont);
        g.setColor(Color.WHITE);
        g.drawString(currentText, x, y);
        
       
    
    }

	public void startTypingEffect() {
	    if (typingTimer != null && typingTimer.isRunning()) {
	        typingTimer.stop();
	    }
		// 타이핑 효과를 위한 Timer 설정
		typingTimer = new Timer(200, new ActionListener() {
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
		currentCharIndex = 0;
		typingTimer.start();

	}

	public void startTypingEffect2() {
		if (typingTimer2 != null && typingTimer2.isRunning()) {
			typingTimer2.stop();
		}
		typingTimer2 = new Timer(200, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentCharIndex < textToShow2[textNum].length()) {
					currentText = textToShow2[textNum].substring(0, currentCharIndex + 1);
					currentCharIndex++;
					repaint();
				} else {
					// 타이핑이 끝났으면 타이머 중지
					typingTimer2.stop();
				}
			}
		});
		currentCharIndex = 0;
		typingTimer2.start();

	}

}