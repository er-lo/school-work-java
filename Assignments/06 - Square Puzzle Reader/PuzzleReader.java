// Erick Lopez
// Advanced Java Fall 2023
// OCCC
// Puzzle Reader Assignment

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PuzzleReader {

    public static void main(String[] args){
        String fileName = "";
        Scanner userInput = new Scanner(System.in);

        //program greeting
        System.out.println("Hello! Welcome to Erick's Puzzle Reader program.");

        //check for command line inputs
        if (args.length == 0) {
            System.out.println("Please enter the first file name: ");
            fileName = userInput.nextLine();
        } 
	    if (args.length == 1) {
            fileName = args[0];
        }

	    if(args.length >= 2) {
	    	System.out.println("Seems like too many arguments were entered. Only the first will be used.");
	    }

        //try catch to read the file and perform the code
	    try {
            //file scanner object
		    Scanner file = new Scanner(new File(fileName));
            //variables
            int row = 0;
            int column = 0;
            //while loop to read through file and capture how many columns and rows so I can create array
            while(file.hasNextLine()){
                String[] line = file.nextLine().trim().split(" ");
                column = line.length;
                row++;
            }

            // creating the 2d array with the right sizes
            String[][] array = new String[row][column];

            // so decided to create a second scanner so that I could read through the file again
            Scanner fileSecondScan = new Scanner(new File(fileName));

            //putting the file info into a 2d array
            for (int i=0; i<array.length; i++) {
                String[] line = fileSecondScan.nextLine().trim().split(" ");
                for (int j=0; j<array[i].length; j++) {
                    array[i][j] = line[j];
                }
            }
            //output original input
            System.out.println("");
            System.out.println("Input: ");
            for (int i=0; i<array.length; i++) {
                for (int j=0; j<array[i].length; j++) {
                    System.out.print(array[i][j] + " ");;
                }
                System.out.println("");
            }

            //output converted input
            System.out.println("");
            System.out.println("Output: ");
            for (int i=0; i<array.length; i++) {
                for (int j=0; j<array[i].length; j++) {
                    if (baseConversion(array[i][j], 36) == -1) {
                        System.out.print("   ");
                    } else {
                        System.out.format("%2d", baseConversion(array[i][j], 36));
                        System.out.print(" ");
                    }
                }
                System.out.println("");
            }

            file.close();
            fileSecondScan.close();
	    } catch(FileNotFoundException e) {}
        userInput.close();
    }

    public static int baseConversion(String inputString, int initialBase) {
        int power = 1;
        int finalBaseTenSum = 0;
        //base 10 calculation
        for (int i = inputString.length() - 1; i >= 0; i--) {
            if(inputString.charAt(i) == '-' || inputString.charAt(i) == '*') {
                return -1;
            } else if(inputString.charAt(i) >= '0' && inputString.charAt(i) <= '9') {
                int currentValue = inputString.charAt(i) - '0';
                finalBaseTenSum += currentValue * power;
                power = power * initialBase;
            } else {
                int otherValue = inputString.toUpperCase().charAt(i) - 'A' + 10;
                finalBaseTenSum += otherValue * power;
                power = power * initialBase;
            }
        }
        return finalBaseTenSum;
    }
    
}
