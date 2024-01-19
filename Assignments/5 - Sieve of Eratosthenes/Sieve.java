// Erick Lopez
// Advanced Java 2023
// OCCC
// Sieve of Eratosthenes

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.Math;

public class Sieve {
    public static void main(String[] args) {
        int startValue=1, stopValue=1;
        Scanner userInput = new Scanner(System.in);

        //program greeting
        System.out.println("Hello! Welcome to Erick's Sieve of Eratosthenes program.");

        //check for command line inputs
        if (args.length == 0) {
            System.out.println("Please enter a start value: ");
            startValue = userInput.nextInt();
            System.out.println("Please enter a stop value: ");
            stopValue = userInput.nextInt();
        } 
	    if (args.length == 1) {
            startValue = Integer.parseInt(args[0]);
            System.out.println("Please enter a stop value: ");
            stopValue = userInput.nextInt();
        }
	    if (args.length == 2) {
            startValue = Integer.parseInt(args[0]);
            stopValue = Integer.parseInt(args[1]);
        } 
	    if(args.length >= 3) {
	    	System.out.println("Seems like too many arguments were entered. Only the first two will be used.");
	    }

        while (startValue <= 1) {
            System.out.println("Start value cannot be less than 1.");
            System.out.println("Please enter a valid start value: ");
            startValue = userInput.nextInt();
        }

        // variables to get the array size and the square root value of the entered stop value
        int arraySize = stopValue - 1;
        int srOfStopValue = (int) Math.sqrt( (double) stopValue);

        //creating of the array of booleans with the correct array size.
        boolean[] array = new boolean[arraySize];

        //for loop to set all values in the array to true starting at 2
        for (int i=2; i < array.length; i++) {
            array[i] = true;
        }

        //starttime for the sieve
        long startTime = System.nanoTime();
        //for loop that begins by looping from the start value to the square root of the stop value
        for (int j=0; j <= srOfStopValue; j++) {
            //check to see if the value hasn't already been eliminated (first pass through it won't)
            if (array[j] == true) {
                //this was a little tricky to find out
                //for loop where we set the value of k to equal j^2
                //so example: if the start value is 2, we set k to 2^2 or 2*2 which is 4 we then set the boolean at position 4 in the array to false
                //then we increment by j which is still 2 since we haven't broke out of the loop, so k is now 6, again set the boolean at position 6 to false
                //and again increment by j (still 2), so k = 8, set boolean at position 8 to false and so on
                //until we finally break out of loop by reaching the end then j gets incremented by 1 and process starts again until j reaches the square root value
                for (int k = j*j; k < array.length; k+=j) {
                    array[k] = false;
                }
            }
        }
        //end time of the sieve
        long endTime = System.nanoTime();

        long duration = endTime - startTime;

        double timeInSeconds = (double) duration / 1_000_000_000;

        //variable to count the amount of primes
        int count = 0;
        //for loop to increase the count when a true value is found in the array
        for (int l=startValue; l < array.length; l++) {
            if(array[l] == true) {
                count++;
            }
        }

        //display count
        System.out.println("Number of primes in the range: " + count);
        System.out.println("Amount of time it took (in seconds): " + timeInSeconds);

        userInput.close();
    }
    
}
