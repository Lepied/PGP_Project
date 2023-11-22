package game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Animator implements Runnable{
	
	private static Animator instance;
	
	
	private GameObject targetObject;
	
	private Graphics graphics;

	private Thread aniThread;
	private volatile boolean isRunning = false;
	
	private Animator()
	{//싱글톤 패턴
		
	} 
	public static Animator getInstance()
	{
		if(instance == null)
		{
			synchronized(GameManager.class)
			
			{
				instance = new Animator();
			}

		}
		return instance;
	}
	
	public void setTargetObject(GameObject target)
	{
		this.targetObject = target;
	}
	   // 추가: 그래픽스 객체를 설정하는 메서드
    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }
	
	public void startAnamation()
	{
		if(aniThread == null)
		{
			aniThread = new Thread(this);
			isRunning = true;
			aniThread.start();
		}
	}
	
	public void stopAnimation()
	{
		isRunning = false;
		aniThread = null;
	}
	
    synchronized private void drawAnimation(Graphics g, int frameIndex) {
        // 애니메이션 그리기 코드
        // ...
    	g.drawImage(targetObject.img, targetObject.posX,targetObject.posY,
    			targetObject.posX+32,targetObject.posY+32,frameIndex*0,0,frameIndex*32+32,32,null);
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (isRunning) {
            // 애니메이션 업데이트 및 그리기
            // 여기에서는 예시로 frameIndex를 0부터 3까지 변경하며 애니메이션을 그리는 코드를 작성
            for (int frameIndex = 0; frameIndex < 4; frameIndex++) {
                // 각 프레임을 그리는 메서드 호출
                drawAnimation(graphics, frameIndex);
                try {
                	System.out.println("애니메이션작동중");
                    Thread.sleep(1000); // 애니메이션 프레임 간의 간격
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
	}
}
