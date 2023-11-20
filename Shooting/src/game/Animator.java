package game;

import java.util.ArrayList;
import java.util.List;

public class Animator {
	
	private static Animator instance;
	
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

}
