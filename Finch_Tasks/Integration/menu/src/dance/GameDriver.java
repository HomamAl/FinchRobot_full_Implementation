package dance;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class GameDriver {
	static String hex;
	static Finch fin = new Finch();
	static String fileName="data.txt";
	static PrintWriter writer=null;

	public void setHex(String str){
		this.hex=str;
	}
	
	public String getHex(){
		return hex;
	}
	
	
	public static void main(String[] args) {
		try {
			PrintWriter writer=new PrintWriter("data.txt");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Gui game = new Gui();
		game.start();
		
	}
	
}
		
	


