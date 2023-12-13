package game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class DataManager {
	GameManager gm;
	
	private int coin;
	private int damage;
	private int luck;
	private int plusCoin;
	private int bombDamage;
	private int playerDamage;
	
	public DataManager() {
		gm = GameManager.getInstance();
	}
	
	public void saveData() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("saveData.txt"));
			coin = gm.getCoin();
			luck = gm.getPlayerLuck();
			plusCoin = gm.getPlusCoin();
			bombDamage = gm.getPlayerBombDamage();
			playerDamage = gm.getPlayerDamage();
			
			bw.write(""+ coin);
			bw.newLine();
			bw.write(""+ luck);
			bw.newLine();
			bw.write(""+ plusCoin);
			bw.newLine();
			bw.write(""+ bombDamage);
			bw.newLine();
			bw.write(""+ playerDamage);
			bw.newLine();


			
			System.out.print("--- SAVING ---\n"+
					"coin: " + coin + "\n"+ 
					"damage: " + damage + "\n"+ 
					"Success to save the data...\n");
			
			bw.close();
		}
		catch(Exception e){
			
		}
	}
	
	public void loadData() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("saveData.txt"));
			coin = Integer.parseInt(br.readLine());
			luck = Integer.parseInt(br.readLine());
			plusCoin = Integer.parseInt(br.readLine());
			bombDamage = Integer.parseInt(br.readLine());
			playerDamage = Integer.parseInt(br.readLine());

			
			gm.setCoin(coin);;
			gm.setPlayerLuck(luck);
			gm.setPlusCoin(plusCoin);
			gm.setPlayerBombDamage(bombDamage);
			gm.setPlayerDamage(playerDamage);
			
			System.out.print("--- LOADING ---\n"+
					"coin: " + gm.getCoin() + "\n"+ 
					"Success to load the data...\n");
			
			br.close();
		}
		catch(Exception e){
			
		}
	}
	
} 
