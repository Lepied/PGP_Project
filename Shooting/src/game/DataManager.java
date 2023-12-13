package game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class DataManager {
	GameManager gm;
	
	private int coin;
	private int damage;
	private int hp;
	private double luck;
	private int plusCoin;
	private int bombDamage;
	
	public DataManager() {
		gm = GameManager.getInstance();
	}
	
	public void saveData() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("saveData.txt"));
			coin = gm.getCoin();
			damage = gm.getPlayerDamage();
			hp = gm.getPlayerMaxHP();
			luck = gm.getPlayerLuck();
			plusCoin = gm.getPlusCoin();
			bombDamage = gm.getPlayerBombDamage();
			
			bw.write(""+ coin);
			bw.newLine();
			bw.write(""+ damage);
			bw.newLine();
			bw.write(""+ hp);
			bw.newLine();
			bw.write(""+ luck);
			bw.newLine();
			bw.write(""+ plusCoin);
			bw.newLine();
			bw.write(""+ bombDamage);
			bw.newLine();
			
			System.out.print("--- SAVING ---\n"+
					"coin: " + coin + "\n"+ 
					"damage: " + damage + "\n"+
					"HP: " + hp + "\n"+
					"luck: " + luck + "\n"+
					"plusCoin: " + plusCoin + "\n"+
					"bombDamge: " + bombDamage + "\n"+
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
			damage = Integer.parseInt(br.readLine());
			hp = Integer.parseInt(br.readLine());
			luck = Double.parseDouble(br.readLine());
			plusCoin = Integer.parseInt(br.readLine());
			bombDamage = Integer.parseInt(br.readLine());
			

			
			gm.setCoin(coin);
			gm.setPlayerDamage(damage);
			gm.setPlayerMaxHP(hp);
			gm.setPlayerLuck(luck);
			gm.setPlusCoin(plusCoin);
			gm.setPlayerBombDamage(bombDamage);
			
			
			System.out.print("--- LOADING ---\n"+
					"coin: " + gm.getCoin() + "\n"+ 
					"damage: " + gm.getUpDamage() + "\n"+
					"HP: " + gm.getPlayerMaxHP() + "\n"+
					"luck: " + gm.getPlayerLuck() + "\n"+
					"plusCoin: " + gm.getPlayerLuck() + "\n"+
					"bombDamge: " + gm.getPlayerBombDamage() + "\n"+
					"Success to load the data...\n");
			
			br.close();
		}
		catch(Exception e){
			
		}
	}
	
} 
