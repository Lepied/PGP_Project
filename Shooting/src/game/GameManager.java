package game;

public class GameManager {
	
	private static GameManager instance;
	
	private int score;

	
	
	private GameManager() {} //싱글톤 패턴
	
	public static GameManager getInstance()
	{
		if(instance == null)
		{
			synchronized(GameManager.class)
			
			{
				instance = new GameManager();
			}

		}
		return instance;
	}

	
	public int getScore()
	{
		return score;
	}
	
	public void setScore(int score)
	{
		this.score = score;
	}
	

	

}
