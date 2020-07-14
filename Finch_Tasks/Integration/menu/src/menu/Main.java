package menu;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
	
	static Scanner sc1=new Scanner(System.in);

	
	
	private static void showMenu() {
		System.out.println("1. Follow light");
		System.out.println("2. Draw shape");
		System.out.println("3. Navigate");
		System.out.println("4. ZigZag");
		System.out.println("5. Follow object");
		System.out.println("6. Dance");
		System.out.println("7. Exit");
	}
	
	private static boolean repeat(int exit) {
		boolean valid;
		do {
			try {
		    	System.out.println("Press 1 to repeat, or 2 to exit");
				exit=sc1.nextInt();
				valid=true;
				switch(exit) {
				case 1:	
					return true;
				case 2:	
					return false;
				default:
		        	valid = false;
				}
			}
	        catch(InputMismatchException E)
			{
	        	valid=false;
			}
		}while (!valid || exit ==1 && exit ==2);
		return false;
}
	
	public static void main(String[] args) {
		boolean valid = true;
		int exit = 0, option = 0;
		showMenu();
		do {
			try {
			option=sc1.nextInt();
			sc1.nextLine();
			valid = true;
		    switch (option)
		    {
		        case 1:
		        	do {
			        	try {
							light.myfollowthelight.main(args);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		        	}while (repeat(exit)==true);
		    		showMenu();
		        	break;
		        case 2: 
		        	do {
		        		Draw.gui.main(args);
		        	}while (repeat(exit)==true);
		    		showMenu();
		        	break;
		        case 3: 
		        	do {
		        		try {
							Navigation.navigation.main(args);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		        	}while (repeat(exit)==true);
		    		showMenu();
		        	break;
		        case 4:
		        	do {
			        	Zigzag.User.main(args);
		        	}while (repeat(exit)==true);
		    		showMenu();
		        	break;
		        case 5:
		        	do {
		        		DetectObject.Object.main(args);
		        	}while (repeat(exit)==true);
		    		showMenu();
		        	break;
		        case 6:
			        	dance.GameDriver.main(args);
		    		showMenu();
		        	break;
		        	
		        case 7:
		        	//do {
			        	tilt.FinchTilt.main(args);
		        	//}while (repeat(exit)==true);
		    		showMenu();
		        	break;
		        	
		        case 8:
		        	System.out.println("Bye!");
		        	break;
		        default:
		        	showMenu();
		            System.out.println("Number "+option +" doesnt look like an option, choose a new number");
		    }
			}
		        catch(InputMismatchException E)
		        {
		    		showMenu();
		            System.out.println("This doesnt look like an option, choose a new NUMBER");
					valid = false;
		        }catch (NoSuchElementException E){
					sc1.nextLine();
		        }
		} while (!valid || option != 7);
	    sc1.close();
		}
	}