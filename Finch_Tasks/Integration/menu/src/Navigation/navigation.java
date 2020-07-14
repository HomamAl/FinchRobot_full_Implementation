package Navigation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Stack;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class navigation {

  public static void main(String[] args) throws IOException {

    // Declaration of variables in entire program's scope
    Stack<String> retraceStack = new Stack<String>(); //stack containing the retrace
    Stack<String> commandStack = new Stack<String>(); //the stack containing the command string
    Stack<Integer> durationStack = new Stack<Integer>(); //stack containing the time int
    Stack<Integer> speedStack = new Stack<Integer>(); //stack containing Finch motor int
    Stack<Integer> retraceStepsStack = new Stack<Integer>(); //stack containing the amount of steps
    Scanner commandInput = new Scanner(System.in);
    Scanner durationInput = new Scanner(System.in);
    Scanner speedInput = new Scanner(System.in);
    Scanner stepsInput = new Scanner(System.in);
    Finch myfinch = new Finch();


    // Main Code

    while (true) { //infinite true loop to make program loop back until "Quit"
      System.out.println("Enter a command [Forward, Backward, Left, Right, Retrace, Log, Exit]");
      String command = "";

      if (commandInput.hasNextLine()) {
        command = commandInput.nextLine();
      }

      // Movement validation
      if (command.equals("Forward") || command.equals("Backward") || command.equals("Left") || command.equals("Right")) {
        System.out.println("Enter how many seconds you would like the Finch to move for:");

        int x = durationInput.nextInt();
        durationInput.nextLine();

        //duration validation
        while (x > 6 || x < 1) {
          System.out.println("You have to enter a duration between 1 and 6 seconds, please try again");
          x = durationInput.nextInt();

        }

        x = x * 1000; // Converts the user's input in s to ms, Finch can understand ms
        System.out.println("Enter the speed the Finch will move at:");
        speedInput = new Scanner(System.in);

        int y = 0;
        if (speedInput.hasNextLine()) {
          y = speedInput.nextInt();
        }

        //speed validation
        while (y > 200 || y < 50) {
          System.out.println("You have to enter a speed between 50 and 200, please try again");
          y = speedInput.nextInt();
        }

        //input push into retrace section's stacks
        retraceStack.push(command);
        commandStack.push(command);
        durationStack.push(x / 1000);
        speedStack.push(y);

        // Switch case for seeing which movement command was entered (Forward, Backward, Left, Right)

        switch (command) {

          case "Forward":

            myfinch.setWheelVelocities(y, y, x); 
            myfinch.buzz(440, 1000); //additional feature
            System.out.println("Finch: I am done moving!");
            break;


          case "Backward":

            myfinch.setWheelVelocities(-y, -y, x); 
            myfinch.buzz(440, 1000); //additional feature
            System.out.println("Finch: I am done moving!");
            break;


          case "Left":

            myfinch.setWheelVelocities(0, 90, 2000);
            myfinch.stopWheels();
            myfinch.sleep(1000); //pause
            myfinch.setWheelVelocities(y, y, x);
            myfinch.buzz(440, 1000); //additional feature
            System.out.println("Finch: I am done moving!");
            break;


          case "Right":

            myfinch.setWheelVelocities(90, 0, 2000);
            myfinch.stopWheels();
            myfinch.sleep(1000); //pause
            myfinch.setWheelVelocities(y, y, x);
            myfinch.buzz(440, 1000); //additional feature
            System.out.println("Finch: I am done moving!");
            break;
        }

      // Retrace Code
      //Retrace Validation/Error handling
      } else if (command.equals("Retrace")) {
        retraceStack.push(command);
        if (commandStack.size() == 0) { //validation for input "0"
          System.out.println("There are no steps to retrace, try moving the Finch first!");
        } else {

          System.out.println("Enter the amount of steps you would like to retrace");
          stepsInput = new Scanner(System.in);
          int retrace = 0;

          if (stepsInput.hasNextLine()) {
            retrace = stepsInput.nextInt();
          }

          //retrace validation
          while (retrace > commandStack.size()) {
            System.out.println("Please Enter an Amount of Steps Less Than the Finch's History of " + commandStack.size() + " steps");
            retrace = stepsInput.nextInt();

          }

          retraceStepsStack.push(retrace);

          for (int i = 1; i <= retrace; i++) {
            // Retrace switch case

            int value = commandStack.size() - i;
            switch (commandStack.get(value)) {

              case "Forward":
                System.out.println("Retracing Command: '" + commandStack.get(value) + "', With Speed: '" + speedStack.get(value) + "', With Duration: '" + durationStack.get(value) + "' second(s)");
                myfinch.setWheelVelocities(speedStack.get(value), speedStack.get(value), (durationStack.get(value) * 1000));
                myfinch.buzz(440, 1000); //additional feature
                myfinch.stopWheels();
                break;

              case "Backward":
                System.out.println("Retracing Command: '" + commandStack.get((commandStack.size() - i)) + "', With Speed: '" + speedStack.get((commandStack.size() - i)) + "', With Duration: '" + durationStack.get((commandStack.size() - i)) + "' second(s)");
                myfinch.setWheelVelocities(-speedStack.get((commandStack.size() - i)), -speedStack.get((commandStack.size() - i)), (durationStack.get((commandStack.size() - i)) * 1000));
                myfinch.buzz(440, 1000); //additional feature
                myfinch.stopWheels();
                break;

              case "Left":
                System.out.println("Retracing Command: '" + commandStack.get((commandStack.size() - i)) + "', With Speed: '" + speedStack.get((commandStack.size() - i)) + "', With Duration: '" + durationStack.get((commandStack.size() - i)) + "' second(s)");
                myfinch.setWheelVelocities(0, 90, 2000);
                myfinch.stopWheels();
                myfinch.sleep(1000);
                myfinch.setWheelVelocities(speedStack.get((commandStack.size() - i)), speedStack.get((commandStack.size() - i)), (durationStack.get((commandStack.size() - i)) * 1000));
                myfinch.buzz(440, 1000); //additional feature
                myfinch.stopWheels();
                break;

              case "Right":
                System.out.println("Retracing Command: '" + commandStack.get((commandStack.size() - i)) + "', With Speed: '" + speedStack.get((commandStack.size() - i)) + "', With Duration: '" + durationStack.get((commandStack.size() - i)) + "' second(s)");
                myfinch.setWheelVelocities(90, 0, 2000);
                myfinch.stopWheels();
                myfinch.sleep(1000);
                myfinch.setWheelVelocities(speedStack.get((commandStack.size() - i)), speedStack.get((commandStack.size() - i)), (durationStack.get((commandStack.size() - i)) * 1000));
                myfinch.buzz(440, 1000); //additional feature
                myfinch.stopWheels();
                break;
            }
          }
        }

      // Logging to Text File Code
      } else if (command.equals("Log")) {

        if (commandStack.size() == 0) {
          System.out.println("There are no steps to log, try moving the Finch first!"); //"0" input
        } else {
          System.out.println("Logging to text file...");
          BufferedWriter buffWrite = new BufferedWriter(new FileWriter("log.txt"));


          for (int i = 0; i < retraceStack.size(); i++) {
            //BufferedWriter code which writes the contents of the stack to a text file

            //creating a timestamp
            Date date = new Date();
            String strDateFormat = "hh:mm:ss a";
            DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
            String completeDate = dateFormat.format(date);

            if (retraceStack.get(i).equals("Retrace")) {
              buffWrite.write(retraceStack.get(i) + ", " + retraceStepsStack.peek() + ":\n");
              retraceStack.pop();

              int steps = retraceStepsStack.peek();
              for (int j = 1; j <= steps; j++) {
                int value = commandStack.size() - j;
                buffWrite.write(completeDate + "- " + commandStack.get(value) + ", " + durationStack.get(value) + ", " + speedStack.get(value) + ".\n");
              }
              retraceStepsStack.pop();
            } else {
              buffWrite.write(completeDate + commandStack.get(i) + ", " + durationStack.get(i) + ", " + speedStack.get(i) + ".\n");
            }
          }
          buffWrite.close();
        }

      // Exit Program Code
      } else if (command.equals("Exit")) {
        System.out.println("Finch: Bye Bye!"); //additional feature
        System.exit(0);
      }
    }
  }
}
