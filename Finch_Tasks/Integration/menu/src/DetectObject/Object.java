package DetectObject;

import java.util.Scanner;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class Object{

	public static void main(String[] args)
	{
		try {
			System.out.print("please choose 1 for Scaredy and 2 for Curious");

			Scanner input = new Scanner(System.in);
			int mode = input.nextInt();
			int time = (int) System.currentTimeMillis();
			int count_obstacle=0;
			Finch myf = new Finch();
			if(mode!=1&&mode!=2)


			{
				myf.quit();
				Object.main(args);

			}

			else if (mode == 1) {

				while (myf.isBeakUp()==false){


					while (myf.isObstacle()==false) {
						myf.setWheelVelocities(100, 100);
						myf.setLED(0,255,0);
					}
					if (myf.isObstacleRightSide()==true){
						myf.setWheelVelocities(-185,185,500);
						myf.setLED(255, 0, 0,600);
						myf.buzz(2000, 1000);
						count_obstacle++;

					}
					if (myf.isObstacleLeftSide()==true){
						myf.setWheelVelocities(185, -185,500);
						myf.setLED(255, 0, 0,600);
						myf.buzz(2000, 1000);
						count_obstacle++;
					}}}
			else if (mode == 2)
				while (myf.isBeakUp()==false){


					while (myf.isObstacle()==false) {
						myf.setWheelVelocities(100, 100);
						myf.setLED(0,255,0);}


					if (myf.isObstacleRightSide() == true && myf.isObstacleLeftSide() == true)
					{
						myf.setLED(255, 0, 0);	
						myf.stopWheels();




					} 
					else if (myf.isObstacleRightSide()==true) {
						myf.setWheelVelocities(150, -150,200);
						myf.setWheelVelocities(100, 100, 300);
						myf.setLED(0,255,0);
						myf.buzz(2000, 1000);
						count_obstacle++;
					}


					else if (myf.isObstacleLeftSide()==true) {
						myf.setWheelVelocities(-150, 150,200);
						myf.setWheelVelocities(100, 100, 300);
						myf.setLED(0,255,0);
						myf.buzz(2000, 1000);
						count_obstacle++;

					}


				}


			
			if (mode ==1 ) {System.out.println("Mode chosen = Scaredy");
			}
			else 
			{System.out.println("Mode chosen = Curuios");
			}
            



			myf.quit();
			time = (int) System.currentTimeMillis() - time;
			System.out.println("the number of obstacles encountered was : " + count_obstacle);
			System.out.println("the duration of exectution : " + time/1000 + " seconds" );




		} catch (java.util.InputMismatchException e) {

			System.out.println("wrong input, please start the program again !");
		}
	}
}


