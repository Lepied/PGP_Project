package game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class DataManager {
	GameManager gm;
	
	private int coin;
	private int damage;
	
	public DataManager() {
		gm = GameManager.getInstance();
	}
	
	public void saveData() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("saveData.txt"));
			coin = gm.getCoin();
			damage = gm.getUpDamage();
			
			bw.write(""+ coin);
			bw.newLine();
			bw.write(""+ damage);
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
			damage = Integer.parseInt(br.readLine());
			
			gm.setCoin(coin);
			gm.setUpDamage(damage);
			
			System.out.print("--- LOADING ---\n"+
					"coin: " + gm.getCoin() + "\n"+ 
					"damage: " + gm.getUpDamage() + "\n"+ 
					"Success to load the data...\n");
			
			br.close();
		}
		catch(Exception e){
			
		}
	}
}
