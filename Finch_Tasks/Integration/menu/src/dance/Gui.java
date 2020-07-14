package dance;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class Gui extends GameDriver{
	
	int tryNum=1;
	NumberSystem num = new NumberSystem();
	
	public void start(){
			String digits="0123456789ABCDEF";
			String input = JOptionPane.showInputDialog("Enter 2-length hexadecimal digit");
			String hexa=input.toUpperCase();
			if(hexa.length()>2){
				JOptionPane.showMessageDialog(null, "Inputs should be less than 2 charachter" , "Invalid input", JOptionPane.ERROR_MESSAGE);
				tryagain();
			}else if(hexa.length()==1){
				if(digits.contains(hexa)){
					setHex(hexa);
					writeFile();
				}else{
					JOptionPane.showMessageDialog(null, "Invalid charachter input:  "+hexa.charAt(0) , "Invalid input", JOptionPane.ERROR_MESSAGE);
					tryagain();
				}
			}else if(hexa.length()==2){
					boolean invalidFirstchar=true;
					outer:
					for(int i=0;i<digits.length();i++){
						if(digits.charAt(i)== hexa.charAt(0)){
							for(int j=0;j<digits.length();j++){
								if(digits.charAt(j)== hexa.charAt(1)){
									setHex(hexa);
									writeFile();
									invalidFirstchar=false;
									break outer;
								}
							}
								JOptionPane.showMessageDialog(null, "Invalid second charachter input:  "+hexa.charAt(1) , "Invalid input", JOptionPane.ERROR_MESSAGE);
								tryagain();
								invalidFirstchar=false;
								break outer;
						}
					}
					if(invalidFirstchar==true){
						JOptionPane.showMessageDialog(null, "Invalid first charachter input:  "+hexa.charAt(0) , "Invalid input", JOptionPane.ERROR_MESSAGE);
						tryagain();
					}
			}
				
				
	}
	
	public void writeFile(){
				try{
					FileWriter fw = new FileWriter(fileName, true);
					writer=new PrintWriter(fw);
				}catch(FileNotFoundException e){
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				writer.println("\nTrial number "+tryNum+" values are: ");
				writer.println("------------------------");
				writer.println("Hexadecimal number entered: "+getHex());
				writer.println("Equivalent Decimal: "+num.getDecimal(getHex()));
				writer.println("Equivalent Octal: "+num.getOctal(num.getDecimal(getHex())));
				writer.println("Equivalent Binary: "+num.getBinary(num.getDecimal(getHex())));
				
				moveFinch();
			}
	
	public void moveFinch(){
				
				GameSetUp up = new GameSetUp();
				up.setColour(num.getDecimal(getHex()));
				writer.println("Speed of the finch: "+up.setSpeed(num.getOctal(num.getDecimal(getHex()))));
				
				up.Dance(num.getBinary(num.getDecimal(getHex())));
				writer.close();
				tryagain();
	}
	
	public void tryagain(){			
				boolean tryagain=true;
				while(tryagain){
					int response = JOptionPane.showConfirmDialog(null, "Do you want to try again?", "Try again", JOptionPane.YES_NO_OPTION);
					if(response==0){
						tryNum++;
						start();
						break;
					}
					else if(response==1){
						JOptionPane.showMessageDialog(null, "Please go to 'data.txt' file to see the final result" , "Thanks for playing the game !!!", JOptionPane.PLAIN_MESSAGE);
						tryagain=false;
						fin.quit();
						break;
					}
				}
				
			
		
	}

}

