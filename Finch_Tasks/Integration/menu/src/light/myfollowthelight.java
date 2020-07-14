package light;


import java.util.Collections;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap; //allows the use of null and null keys
import java.util.Map;  //
import java.util.Scanner;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class myfollowthelight {
	static long now;
public static void main (String args [])throws Exception {
	ArrayList<Integer> r_light_values_throughout = new ArrayList<Integer>();   //creates an array list to sore all the light sensor values
	ArrayList<Integer> l_light_values_throughout = new ArrayList<Integer>();
    


	Finch myf = new Finch();

       int optimum_threshold = 160; //chosen optimum light intensity (user is able to change this in regards to the place of testing)
       int prev = -1;
       long r_light_value = 0;
       long l_light_value = 0;
       ArrayList<String> actions = new ArrayList<String>(); //this produces an array that has the actions the finch  will take.
       String s = "";
       System.out.println("\n"+"Running: "+s+"\n");
   		while(myf.isBeakUp() == true)   //will not start code until condition is met
   		{
   			System.out.println("Please place the finch nose down to start the program");	//constantly prompts user to complete the action in order to continue 
   		}
   		{
   			System.out.println("Please tap the finch's nose to continue");   //prompts user to tap finch before beginning
   		}
   		while (myf.isTapped() == false);
   		{
   			
   		}
   		
   		long start= System.currentTimeMillis(); //start time of program
   		while(myf.isBeakUp()==false){ 
   			myf.setLED(Color.green);
   			r_light_value = (myf.getRightLightSensor());
   			l_light_value = (myf.getLeftLightSensor());
   			if (myf.getRightLightSensor() < optimum_threshold && myf.getLeftLightSensor() < optimum_threshold);        //if no light source is detected finch moves forward at steady pace
   			{
   				myf.setLED(Color.green);
   			myf.setWheelVelocities(50, 50, 500);
   			r_light_values_throughout.add(myf.getRightLightSensor());    //these are placed throughout the code to constantly store the light sensitivity values throughout the execution
   			l_light_values_throughout.add(myf.getRightLightSensor());
		 	
   			}
   			
   			 while ( myf.getRightLightSensor() > optimum_threshold )  {
   				 myf.setLED(Color.red); 
   				 myf.setWheelVelocities(150,20,500);  
   				 System.out.println("right");//diverts program if the condition of both the light sensors are picking up light values higher than the chosen optimum light and there are no obstacles in the way is met
   				if (prev != 2) {
          		   actions.add("right");    //these actions are also storred in the array which keeps track of all the movements the finch undertakes
    			 }
          	   prev = 2;    //numbers are assigned to each type of movement
          	 r_light_values_throughout.add(myf.getRightLightSensor());
    			l_light_values_throughout.add(myf.getRightLightSensor());
   			 }
   			while ( myf.getLeftLightSensor() > optimum_threshold ){
   				 myf.setLED(Color.red); 
   				 myf.setWheelVelocities(20,150,500);  
   				 System.out.println("left");
   				if (prev != 3) {
          		   actions.add("left");
    			 }
          	   prev = 3;
          	 r_light_values_throughout.add(myf.getRightLightSensor());
    			l_light_values_throughout.add(myf.getRightLightSensor());
   				
   			 }
   			while( myf.getRightLightSensor()>optimum_threshold&&myf.getRightLightSensor()> optimum_threshold){
   				myf.setLED(Color.red);
   				myf.setWheelVelocities(70, 70, 500);
   				System.out.println("forward");
   				if (prev != 1) {
         		   actions.add("forward");
   			 }
         	   prev = 1;
         	  r_light_values_throughout.add(myf.getRightLightSensor());
     			l_light_values_throughout.add(myf.getRightLightSensor());
   			}
   			if (myf.isBeakUp()==true){				//this statement ensures that the user is able to end the program at any moment by placing the finch nose up
   				myf.quit();
                break;
   			}
   		
   		}
   		
   		Map<String, Integer> FrequencyOfActions = new HashMap<String, Integer>();


   		{
        for (String str : actions) {
 	        if (FrequencyOfActions.containsKey(str)) 
 	        {
 	        	FrequencyOfActions.put(str, FrequencyOfActions.get(str) + 1);  //keeps a track of how many times an action has been taken place, adding it to the total of each action
 	        } else {
 	        	FrequencyOfActions.put(str, 1);
 	        }
        }
        
        {
   			Scanner scan = new Scanner(System.in);    	//the scanner function is used in the interaction of the user in order to find out whether the log should be executed or not
   			System.out.print("Would you like to see the log of execution? Type 'yes' is you do. Press any key if not"); //user prompted on what to do to facilitate use
   			String input = scan.nextLine();
   			if (input.equalsIgnoreCase("yes"))   //equalsIgnoreCase function used to reduce errors as user may input data with caps
   			{
   				long end=System.currentTimeMillis();
   		   		long time_elapsed = end - start;			//The calculation for the overall run time of the program
   		   		long time_elapsed_s = time_elapsed / 1000;   // this calculation ensures that the time is given in seconds. Seconds are easier to read than miliseconds 
   		   		{
   		   		System.out.println("Time elapsed for excercise:" + " " + time_elapsed_s +"s");     //all the contents of the log given with text indicating what is being shown
   		   		System.out.println("Initial right light sensor value:" + " " + r_light_value); 
   		   		System.out.println("Initial left light sensor value:" + " " + l_light_value);
   		   		System.out.println( " Min Value for right light sensor:" + Collections.min( r_light_values_throughout) );
   		   		System.out.println( " Min Value for left light sensor:" + Collections.min( l_light_values_throughout) );
   		   		System.out.println( " Max Value for right light sensor:" + Collections.max( r_light_values_throughout) );
   		   		System.out.println( " Max Value for left light sensor:" + Collections.max( l_light_values_throughout) );
   		   	for (Map.Entry<String, Integer> entry : FrequencyOfActions.entrySet()) 
   	        {
   	        	System.out.println(entry.getKey() + ": " + entry.getValue());  			//produces how many times a certain movement has been made (left:_, right:_, forward:_)
   	        }
   		   		}
   		   	if (input != "yes");      //if answer is not yes, the log is not given and the program ends
   		   			System.out.println("End of program");
   		   		}
   		}

   		}
   		
}
}

         

