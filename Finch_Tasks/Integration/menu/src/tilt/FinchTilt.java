package tilt;

import java.awt.Color;
import java.util.ArrayList;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class FinchTilt {
	public static void main(final String[] args)
	   {
		  
	      Finch myFinch = new Finch();
	      int RecordingTime = 5000;
	         if (100 < RecordingTime ||  RecordingTime> 20000)
	          {
	        	 System.out.println("Error: Number is greater than 20 seconds or smaller than 1 second");
	          } 
	         
	      int RecordInterval = 500;
	      long SystemTimeStart =  System.currentTimeMillis();
	      ArrayList<Integer> CommandsList = new ArrayList<Integer>();
	      
	      while((System.currentTimeMillis()-SystemTimeStart)<RecordingTime) {
	    	myFinch.setLED(Color.RED);
	    	if(System.currentTimeMillis()-SystemTimeStart == RecordInterval) { 
	    		
	    	   if(myFinch.isFinchUpsideDown()) {
	    		  System.out.println("Upside Down");
	    		  CommandsList.add(0);
	    	   }
	    	   else if(myFinch.isLeftWingDown()) {
	    		  System.out.println("Left Wing Down");
	    		  CommandsList.add(1);
	    	   }
	    	   else if(myFinch.isRightWingDown()) {
	    		  System.out.println("Right Wing Down");
	    		  CommandsList.add(2);
	    	   }
	    	   else if(myFinch.isFinchLevel()) {
	    		  System.out.println("Finch Level");
	    		  CommandsList.add(3);
	    	   }
	    	   else if(myFinch.isBeakUp()) {
	    		  System.out.println("Beak Up");
	    		  CommandsList.add(4);
	    	   }
	    	   else if(myFinch.isBeakDown()) {
	    		  System.out.println("Beak Down");
	    		  CommandsList.add(5);
	    	   }
	    	}
	    	RecordInterval = (RecordInterval+500) ;
	      }
	      
	      myFinch.sleep(5000);
	      
	      while(CommandsList.isEmpty() != true) {
	    	  int CommandSet = CommandsList.get(0);
	    	  myFinch.setLED(Color.GREEN);
	    	   if (CommandSet == 4) {
	    		  myFinch.setWheelVelocities(255, 255, 500);
	    	   }
	    	   else if (CommandSet == 5) {
	    		  myFinch.setWheelVelocities(-255, -255, 500);
	    	   }
	    	   else if (CommandSet == 3) {
	    		  myFinch.sleep(500);
	    	   }
	    	   else if (CommandSet == 2) {
	    		  myFinch.setWheelVelocities(150, -150, 500);
	    	   }
	    	   else if (CommandSet ==1) {
	    		  myFinch.setWheelVelocities(-150, 150, 500);
	    	   }
	    	 CommandsList.remove(0);
	      }
	      myFinch.buzz(1000,500);
	      myFinch.sleep(1000);
	      myFinch.buzz(500,500);
   
	      myFinch.quit();
	      System.exit(0);
	    }
}
