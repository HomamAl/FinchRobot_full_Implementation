package Zigzag;

import java.awt.Color; //import the colours for the LED
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import edu.cmu.ri.createlab.terk.robot.finch.Finch; //The finch functions library

class ZigZag{ //Class to define the finch functions
	
	static int Length,Number;//so that the arguments on the movmnt object will be assigned to the integers
	private static Finch myFinch = new Finch(); //finch object
	static Color green = Color.green, red = Color.red, yellow = Color.yellow; //LED different colours
	static int tune1 = 4000, tune2=8000; //buzzer sound
	int time = 100*Length; //The motor speed times 0.11 velocity against motor speed
	
	

	//Constructor to receive the arguments from the User class 	
	public ZigZag(int x, int y) 
	{
		x = Length; //Length
		y = Number; //Number
	}

	
	
	//The method arguments are going to be called from the ZigZag constructor in the User class
	public static void line1(int time, int rightwheel, int leftwheel) {
		/*This method will contain:
		*LED colours
		*buzzer sound
		*Wheels velocity for movement
		*wheels velocity for rotation 
		*/
		myFinch.setLED(green); //Set the LED colour
		myFinch.buzz(tune1, time); 
		myFinch.setWheelVelocities(leftwheel, rightwheel, time);
		
	}
	
	//Method for the positive 90 rotation
	public static void pRotation()
	{
		myFinch.setWheelVelocities(-125, 125, 600);
	}

	
	
	//The method arguments are going to be called from the ZigZag constructor in the User class
	public static void line2(int time, int rightwheel, int leftwheel) {	
		/*This method will contain:
		*LED colours
		*buzzer sound
		*Wheels velocity for movement
		*wheels velocity for rotation 
		*/
		myFinch.setLED(red);
		myFinch.buzz(tune2, time);
		myFinch.setWheelVelocities(leftwheel, rightwheel, time);
		
	}
	
	//method for the negative 90 rotation 
	public static void nRotation()
	{
		myFinch.setWheelVelocities(125, -125, 600);
	}
	
	//method for the rotation before retracing the line
	public static void StartRotation()
	{
		myFinch.setWheelVelocities(-100, 100, 600);
	}
	
	//The finch wheels rotation based on the duration of the finch movement 
	public static void FullRotation()
	{
		myFinch.setWheelVelocities(90, -90, 750);
	}
	
	//Method for when the finch start say something 
	public static void Start()
	{
		myFinch.saySomething("Start ZigZag" + "Start ZigZag");
	}

	//Method for when retracing finish, finch say something 
	public static void Finish()
	{
		myFinch.saySomething("Retraced" + "Retraced");
	}
	
	//if the finch is leveled on the floor then it start else it doesnt 
	public static void levelonfloor()
	{
		while (myFinch.isFinchLevel() == false)
		{
			JOptionPane.showMessageDialog(null, "Please place finch level on floor");
			myFinch.setLED(red);
		}
		if(myFinch.isFinchLevel() == true)
		{
			JOptionPane.showMessageDialog(null, "Finch is on the floor");
			myFinch.setLED(green);
		}
	}
	
	
	//The finch play some music then start dancing 
	public static void dance()
	{
		//myFinch.playClip("H:\\VIVA Zigzag\\Zigzag\\music.wav");
		myFinch.saySomething("Dancing"+"Dancing");
		myFinch.setWheelVelocities(155, -155, 1100);
		myFinch.setWheelVelocities(-85, -85, 500);

	}
	
	
	
	
	//Halt the program
	public static void Stop()
    {
		myFinch.setLED(yellow);
    	myFinch.quit();
    }
}