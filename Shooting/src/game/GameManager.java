package game;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import Unit.NPC;

import java.util.ArrayList;

public class GameManager {
	
	private static GameManager instance;
	
	Player player;
	private int Score;     //스코어 또한 따로 저장용도, 게임이 끝나면 초기화 되야 함
	private int playerCoin;
	private int playerBomb;  // 폭탄 갯수자체는 게임이 끝나면 초기화 시켜야 함.
	private int playerBombDamage;
	private int playerUpgradeMaxHp;
	private double playerUpgradeLuck;
	private int plusCoin;
	
	
	private List<GameObject> gameObjectList;
	
	private List<Item> itemList;
	
	private List<NPC> npcList;
	

	private int scrollNum;
	
	private int itemPosX;
	private int itemPosY;
	
	private int playerDamage;
	
	public boolean isBossNow = false;
	public boolean isNPCNow = false;
	public boolean isNPCEnd = false;
	public boolean isQuest = false;
	public boolean isQuestFinished = false;
	

	public boolean drawEnDeadEffect = false;
	public int enDeadEffectX;
	public int enDeadEffectY;
	 
	private GameManager()
	{//싱글톤 패턴
		gameObjectList = new ArrayList<>();
		itemList = new ArrayList<>();
		npcList = new ArrayList<>();
    
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
	
	public void setPlayer(Player player)
	{
		this.player = player;
	}
	public Player getPlayer()
	{
		return player;
	}
	public void addEnemy(Enemy enemy)
	{
		gameObjectList.add(enemy);
	}
	public void removeEnemy(Enemy enemy)
	{
		gameObjectList.remove(enemy);
	}
	
	public List<GameObject> getGameObjectList()
	{
		return gameObjectList;
	}
	
	
	public void addNPC(NPC npc)
	{
		npcList.add(npc);
	}
	public void removeNPC(NPC npc)
	{
		npcList.remove(npc);
	}
	
	public List<NPC> getNPCList()
	{
		return npcList;
	}


	
	public void addItem(Item item)
	{
		itemList.add(item);
	}
	public void removeItem(Item item)
	{
		itemList.remove(item);
	}
	
	public List<Item> getItemList()
	{
		return itemList;
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
	
	public boolean isBulletCollision(Bullet obj1, GameObject obj2)
	{
		boolean isBulletCrushed = false;
		if(Math.abs((obj1.pos.x + obj1.width/2) - (obj2.posX + obj1.width/2)) < (obj2.width/2 + obj1.width/2) 
				&& Math.abs((obj1.pos.y + obj1.height / 2) - (obj2.posY + obj2.height/2)) < (obj2.height/2 + obj1.height/2))
		{
			isBulletCrushed = true;
		}
		else isBulletCrushed = false;
		
		return isBulletCrushed;
	}
	
	public boolean isEnBulletCollision(EnemyBullet obj1, GameObject obj2)
	{
		boolean isEnBulletCrushed = false;
		if(Math.abs((obj1.pos.x + obj1.width/2) - (obj2.posX + obj1.width/2)) < (obj2.width/2 + obj1.width/2) 
				&& Math.abs((obj1.pos.y + obj1.height / 2) - (obj2.posY + obj2.height/2)) < (obj2.height/2 + obj1.height/2))
		{
			isEnBulletCrushed = true;
		}
		else isEnBulletCrushed = false;
		
		return isEnBulletCrushed;
	}
	
	public boolean isNPCCollision(NPC npc, GameObject obj2)
	{
		boolean isNPCMeet = false;
		if(Math.abs((npc.posX + npc.width/2) - (obj2.posX + npc.width/2)) < (obj2.width/2 + npc.width/2) 
				&& Math.abs((npc.posY + npc.height / 2) - (obj2.posY + obj2.height/2)) < (obj2.height/2 + npc.height/2))
		{
			isNPCMeet = true;
			if(isQuestFinished == true)
			{
				player.attackType = 2;
			}
		}
		else isNPCMeet = false;
		
		return isNPCMeet;
	}
	
	public void applyDamage(Enemy target, int damage)
	{
		target.hp = target.hp - damage;
		if(target.hp <=0)
		{
			if(target.type == 3)
			{
				isBossNow = false;

			}
			if(target.type ==2)
			{
				this.isQuestFinished = true;
			}
			removeEnemy(target);
			drawEnDeadEffect = true;
			if(target.type==1)
			{
				enDeadEffectX = target.posX;
				enDeadEffectY = target.posY;
			}
			else if(target.type==2)
			{
				enDeadEffectX = target.posX+90;
				enDeadEffectY = target.posY+75;
			}
			else if(target.type ==3)
			{
				enDeadEffectX = target.posX+225;
				enDeadEffectY = target.posY+175;
			}
		
			
			
			double randomValue = Math.random(); // 0~1.0
			if (randomValue < 0.1+playerUpgradeLuck) { // 10% + 강화된 럭 수치
	            addItem(new Scroll(target.posX, target.posY));
	        } else if (randomValue < 0.2) { // 5%
	            addItem(new Bomb(target.posX, target.posY));
	        } else { // 60%
	            addItem(new Coin(target.posX, target.posY));
	        }
		}
	}
	

	
	
	public int getScore()
	{
		return Score;
	}
	
	public void setScore(int Score)
	{
		this.Score = Score;
	}

	
	public int getCoin()
	{
		return playerCoin;
	}
	
	public void setCoin(int playerCoin)
	{
		this.playerCoin = playerCoin;
	}

	public int getBomb()
	{
		return playerBomb;
	}
	
	public void setBomb(int playerBomb)
	{
		this.playerBomb = playerBomb;
	}
	
	
	public int getPlayerDamage()
	{
		return playerDamage;
	}
	
	public void setPlayerDamage(int damage)
	{
		this.playerDamage = damage;
	}
	
	
	public int getScrollNum()
	{
		return scrollNum;
	}
	
	public void setScrollNum(int scrollNum)
	{
		this.scrollNum = scrollNum;
	}
	
	public int getPlayerBombDamage()
	{
		return playerBombDamage;
	}
	
	public void setPlayerBombDamage(int bombDamage)
	{
		this.playerBombDamage = bombDamage;
	}
	
	
	public int getPlayerMaxHP()
	{
		return playerUpgradeMaxHp;
	}
	
	public void setPlayerMaxHP(int playerUpgradeMaxHp)
	{
		this.playerUpgradeMaxHp = playerUpgradeMaxHp;
	}
	
	public double getPlayerLuck()
	{
		return playerUpgradeLuck;
	}
	
	public void setPlayerLuck(double playerUpgradeLuck)
	{
		this.playerUpgradeLuck = playerUpgradeLuck;
	}
	
	public int getPlusCoin()
	{
		return plusCoin;
	}
	
	public void setPlusCoin(int plusCoin)
	{
		this.plusCoin = plusCoin;
	}
	
	
	
	

	

	

}
