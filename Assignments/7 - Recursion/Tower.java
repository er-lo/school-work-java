//Erick Lopez
//OCCC
//Advanced Java Fall 2023
//Tower of Hanoi Assignment

import java.util.Scanner;

public class Tower {
    public static void main(String[] args) {
        //variable creation
        int numberOfDisks=0, startSpindle=0, endSpindle=0;
        Scanner userInput = new Scanner(System.in);
        System.out.println("Welcome to Erick's Tower of Hanoi program.");
        // user input handling
        if (args.length == 1) {
            numberOfDisks = Integer.parseInt(args[0]);
            System.out.println("Please enter the start spindle");
            startSpindle = userInput.nextInt();
            System.out.println("Please enter the end spindle");
            endSpindle = userInput.nextInt();
        } else if (args.length == 2){
            numberOfDisks = Integer.parseInt(args[0]);
            startSpindle = Integer.parseInt(args[1]);
            System.out.println("Please enter the end spindle");
            endSpindle = userInput.nextInt();
        } else if (args.length == 3) {
            numberOfDisks = Integer.parseInt(args[0]);
            startSpindle = Integer.parseInt(args[1]);
            endSpindle = Integer.parseInt(args[2]);
        } else if (args.length > 3) {
            numberOfDisks = Integer.parseInt(args[0]);
            startSpindle = Integer.parseInt(args[1]);
            endSpindle = Integer.parseInt(args[2]);
            System.out.println("Too many arguments were entered. Only the first three will be accepted.");
        } else {
            System.out.println("Please enter the number of disks");
            numberOfDisks = userInput.nextInt();
            System.out.println("Please enter the start spindle");
            startSpindle = userInput.nextInt();
            System.out.println("Please enter the end spindle");
            endSpindle = userInput.nextInt();
        }
        while (numberOfDisks < 1) {
            System.out.println("Incorrect value for number of disks");
            System.out.println("Please enter the number of disks: ");
            numberOfDisks = userInput.nextInt();
        }
        while (startSpindle <= 0 || startSpindle > 3) {
            System.out.println("Incorrect value for the start spindle");
            System.out.println("Please enter the start spindle: ");
            startSpindle = userInput.nextInt();
        }
        while (endSpindle <= 0 || endSpindle > 3 || endSpindle == startSpindle) {
            System.out.println("Incorrect value for the end spindle");
            System.out.println("Please enter the end spindle: ");
            endSpindle = userInput.nextInt();
        }


        //algorithm execution
        TOH(numberOfDisks, startSpindle, endSpindle);


        userInput.close();
    }

    public static void TOH(int numDisks, int start, int end) {

        //setting the undesignated spindle to move the disk to
        int undesignatedSpindle = 0;
        if (start == 1) {
            if (end == 2) {
                undesignatedSpindle = 3;
            } else if (end == 3) {
                undesignatedSpindle = 2;
            }
        } else if (start == 2) {
            if (end == 1) {
                undesignatedSpindle = 3;
            } else if (end == 3) {
                undesignatedSpindle = 1;
            }
        } else if (start == 3) {
            if (end == 1) {
                undesignatedSpindle = 2;
            } else if (end == 2) {
                undesignatedSpindle = 1;
            }
        }

        //move the final disk to the end position and break out of loop
        if (numDisks == 1) {
            System.out.println("Move disk " + numDisks + " from tower " + start + " to tower " + end);
            return;
        }

        //move disks to undesignated / unused spindle 
        TOH(numDisks - 1, start, undesignatedSpindle);
        System.out.println("Move disk " + numDisks + " from tower " + start + " to tower " + end);
        //move from undesignated to end
        TOH(numDisks - 1, undesignatedSpindle, end);
    }
}
