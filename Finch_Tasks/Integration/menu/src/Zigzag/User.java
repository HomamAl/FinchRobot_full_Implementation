package Zigzag;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import static java.time.temporal.ChronoUnit.MINUTES;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class User {


	public static void main(String[] args) {
		
		ZigZag.levelonfloor();
		
		//Graphical user interface for the user input 
		//obtain user input from JOption dialog for the length 
		String length = 
				JOptionPane.showInputDialog("Length of the zigzag?(cm) between 20cm and 80cm");
		
		if (length == null) {
			JOptionPane.showMessageDialog(null, "You can always come back and try the project again :)", null, JOptionPane.ERROR_MESSAGE);;
			  System.exit(0);
			} 
		else if (length == "") {
			JOptionPane.showMessageDialog(null, "You can always come back and try the project again :)", null, JOptionPane.ERROR_MESSAGE);;
			  System.exit(0);
			} 
		
		/*else if (length.equalsIgnoreCase("")) {
			  System.out.println("This is OK button without input");
			}*/
		
		//convert String inputs to int values for the use of calculation
		int intLen = Integer.parseInt(length);
		//Validation rules for the input 
		

		
		while(intLen<20 || intLen>80)
			try {
				intLen = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the length of line 'must be between(20cm to 80 cm)"));
				}
			catch (NumberFormatException e) 
			{
			System.out.println("The exception is: " + e);
			JOptionPane.showMessageDialog(null, "Only numeric values accepted", null, JOptionPane.ERROR_MESSAGE);
			} 
			
		
		//obtain user input from JOption dialog for the number 
		String number = 
				JOptionPane.showInputDialog("How many zigzags?(even number from 2-10)");
		
		if (number == null) {
			JOptionPane.showMessageDialog(null, "You can always come back and try the project again :)", null, JOptionPane.ERROR_MESSAGE);;
			  System.exit(0);
			}

		// convert String inputs to int values for the use of calculation
		int intNum = Integer.parseInt(number);
		//Validation rules for the input
		while((intNum<2 || intNum>10) || (intNum % 2 != 0)) {
			try {
				intNum = Integer.parseInt(JOptionPane.showInputDialog(null, "How many zigzags?(even number from 2-10)"));
			}
			catch (NumberFormatException e)
			{
				System.out.println("The exception is: " + e);
				JOptionPane.showMessageDialog(null, "Only numeric values accepted", null, JOptionPane.ERROR_MESSAGE);
				}
		}
		
		//display results in JOptionPane message dialog
		JOptionPane.showMessageDialog(null, "The finch will move for " + intLen + "cm " + 
		"and for " + intNum + " section", "Movment", JOptionPane.PLAIN_MESSAGE);

		//System.out.print("The length is: " + length + "cm");
			
		//Constructor to pass the arguments to the ZigZag class
			ZigZag movement = new ZigZag(intLen,intNum); 
		
		//This will be the length * by the motor speed 
		//The motor speed times 0.11 velocity against motor speed
			int time = (intLen * 85);
			//int time = (intLen * 100); 
			
		//Create a random integer between (100-255) for the speed	
		int RandNum = ThreadLocalRandom.current().nextInt(80,150);
		int rightwheel = (int) (RandNum*1.08); //Just to make the straight line movement straight excatly 
		int leftwheel = RandNum*1; 
		
		//for loop for the finch to draw the zigzag line based on the input 
		//when i is less than the user input, increament i
		//if i is an even number call line1 method from the ZigZag class
		//if i is an odd number call line2 method from the ZigZag class
		//when the loop is done rotate the finch

		int i;
		ZigZag.dance(); //make the finch dance
			LocalTime StartTime = java.time.LocalTime.now();
			ZigZag.Start();  //
			for(i=0; i < intNum; i++) 
			{
				if(i%2 == 0) //if "i" an even number
				{
					
					movement.line1(time, rightwheel, leftwheel);
					if(i != intNum)
					{
						movement.pRotation();
					}
				}
				else{
					movement.line2(time, rightwheel, leftwheel);
					if(i != intNum)
					{
						movement.nRotation();
						}
    			}
				
				
			}
			movement.FullRotation();
		//for loop for the finch to retrace its movement
	    // it repeats the same process as the previous loop 
		//Just the methods are swapped 
			for(i=0; i < intNum; i++)
			{
				if(i%2 == 0) //if "i" an even number
				{
					movement.line2(time, rightwheel, leftwheel);
					if(i != intNum)
					{
						movement.nRotation();
					}
				}else{
					movement.line1(time, rightwheel, leftwheel);
					if(i != intNum)
					{
						movement.pRotation();
					}
    			}
			}
			movement.StartRotation(); //Rotate bnack to its original place
			ZigZag.Finish(); //Text to speech that it finish
			
			
			LocalTime EndTime = java.time.LocalTime.now(); 
			
			
    //Calculations for the user log
			 
			int LineDistance = (intLen * intNum);	//calculate the line distance			
			double travDistance = (Math.sqrt(Math.pow(intLen, 2) + Math.pow(intLen, 2))) * intNum/2;	//calculate distance from start to END of the zigzag line


			
			
	//Print the calculations into a text file		
			String filename ="Output.txt"; //Name of the text file 
			try {
				PrintWriter outputStream = new PrintWriter(filename);
				outputStream.println("The line distance is: " + Math.round(LineDistance) + "cm"); //Stores in the RAM first //calculate the line distance
				outputStream.println("distance from start/End of zigzag line: " + Math.round(travDistance) + "cm "); //calculate the distance from start to end of zigzag line");
				outputStream.println("Speed: " + RandNum);
				outputStream.println("The time to start is: " + StartTime); 
				outputStream.println("The End time is: " + EndTime);
				outputStream.close();//Push the data to the file
				System.out.println("Done");
			}catch(FileNotFoundException e) {
				e.printStackTrace();
				}
			JOptionPane.showMessageDialog(null, "The line distance is: " + Math.round(LineDistance) + "cm" + 
				
				"\n" + "distance from start/END of zigzag line: " + Math.round(travDistance) + "cm " +
				
				"\n" + "Speed: " + RandNum + "\n" + "The time to start is: " + StartTime + 
				
				"\n" + "The End time is: " + EndTime , "Output", JOptionPane.PLAIN_MESSAGE);
			}  
	
	}


