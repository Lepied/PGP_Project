package game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class MainPanel extends JPanel implements Runnable{
	private int X = 960;
	private int Y = 540;
	ImageIcon start = new ImageIcon("resourses/sprites/start.png");
	ImageIcon startBtn = scaleImage(start, 300, 100);
	ImageIcon powerUp = new ImageIcon("resourses/sprites/powerUp.png");
	ImageIcon powerUpBtn = scaleImage(powerUp, 300, 100);
	Image bg = new ImageIcon("resourses/sprites/background.jpg").getImage();

	public MainPanel() {
		setLayout(null);
		this.setPreferredSize(new Dimension(X, Y));
		this.setBackground(Color.WHITE);
		this.setDoubleBuffered(true);
		createText();
		
		btn("start", startBtn, 20, Y/2 , 300, 100);
		btn("powerUp", powerUpBtn, 20, Y/2 + 120 , 300, 100);

	}
	
	public void createText() {
		JLabel label = new JLabel("REHIRE");
		label.setForeground(Color.BLACK);
		label.setBounds(X/2 - 500/2, 50, 500, 100);
		Font customFont = new Font("Elephant", Font.BOLD, 50);
		label.setFont(customFont);
        label.setHorizontalAlignment(JLabel.CENTER);
		add(label);
	}
	
	public void btn(String id, ImageIcon image,int x, int y, int width, int height) {
		JLabel label = new JLabel(image, JLabel.CENTER);
		String btnId = id;
		label.setBounds(x, y, width, height);
		label.setOpaque(false);

//		Border border = new LineBorder(Color.BLACK, 2);
//        label.setBorder(border);
		Border border = new EmptyBorder(0, 0, 0, 0); // EmptyBorder를 사용하여 투명한 테두리 생성
	    label.setBorder(border);
			
		label.addMouseListener(new MouseAdapter() {
			@Override
        	public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
            
            public void mouseClicked(MouseEvent e) {
            	if(btnId.equals("start")) {
            		System.out.print("start");
            		
            	}
            	else if(btnId.equals("powerUp")) {
            		System.out.print("powerUp");
            		
            	}	
            }
		 });
		 
		 add(label);
	}
	
	public ImageIcon scaleImage(ImageIcon img, int width, int height) {
		Image originImg = img.getImage();
		Image changeImg =  originImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon scaledImg =  new ImageIcon(changeImg);
		return scaledImg;
	}
	
	public static ImageIcon transformColorToTransparency(ImageIcon imageIcon, Color c1) {
        BufferedImage image = toBufferedImage(imageIcon.getImage());

        final int r1 = c1.getRed();
        final int g1 = c1.getGreen();
        final int b1 = c1.getBlue();

        ImageFilter filter = new RGBImageFilter() {
            public int filterRGB(int x, int y, int rgb) {
                int r = (rgb & 0xFF0000) >> 16;
                int g = (rgb & 0xFF00) >> 8;
                int b = (rgb & 0xFF);
                if (r == r1 && g == g1 && b == b1) {
                    return rgb & 0xFFFFFF;
                }
                return rgb;
            }
        };

        ImageProducer ip = new FilteredImageSource(image.getSource(), filter);
        Image transformedImage = Toolkit.getDefaultToolkit().createImage(ip);
        ImageIcon transformedIcon = new ImageIcon(transformedImage);
        return transformedIcon;
    }
	
	private static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics g = bufferedImage.getGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        return bufferedImage;
    }
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, 960, 540, null);
	}

} 
