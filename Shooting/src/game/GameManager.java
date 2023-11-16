package game;

import java.util.List;
import java.util.ArrayList;

public class GameManager {
	
	private static GameManager instance;
	
	private int playerCoin;
	private int playerUpgradeDamage;
	private int playerUpgradeMaxHp;
	
	private List<GameObject> gameObjectList;
	

	
	
	private GameManager()
	{//싱글톤 패턴
		gameObjectList = new ArrayList<>();
		playerCoin =0;
		
	} 
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
	
	public void addGameObject(GameObject gameObject)
	{
		gameObjectList.add(gameObject);
	}
	public void removeGameObject(GameObject gameObject)
	{
		gameObjectList.remove(gameObject);
	}
	
	public List<GameObject> getGameObjectList()
	{
		return gameObjectList;
	}


	public boolean isCollision(GameObject obj1, GameObject obj2)
	{
		boolean isCrushed = false;
		if(Math.abs((obj1.posX + obj1.width/2) - (obj2.posX + obj1.width/2)) < (obj2.width/2 + obj1.width/2) 
				&& Math.abs((obj1.posY + obj1.height / 2) - (obj2.posY + obj2.height/2)) < (obj2.height/2 + obj1.height/2))
		{
			isCrushed = true;
		}
		else isCrushed = false;
		
		return isCrushed;
	}
	public void applyDamage(GameObject target, int damage)
	{
		target.hp = target.hp - damage;
		if(target.hp <=0)
		{
			removeGameObject(target);
		}
	}

	
	public int getCoin()
	{
		return playerCoin;
	}
	
	public void setCoin(int playerCoin)
	{
		this.playerCoin = playerCoin;
	}
	
	public int getUpDamage()
	{
		return playerUpgradeDamage;
	}
	
	public void setUpDamage(int damage)
	{
		this.playerUpgradeDamage = damage;
	}
	

	

}
