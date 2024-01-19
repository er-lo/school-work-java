// Erick Lopez
// Advanced Java
// OCCC Fall 2023
// Base Conversion Assignment

import java.math.BigInteger;
import java.util.Scanner;

public class BaseConversion {
    public static void main(String[] args) {
        //variables for the program.
        String inputString = "";
        int initialBase, desiredBase;
        Scanner userInput = new Scanner(System.in);
        boolean validInitialBaseValue, validDesiredBaseValue;

        //program greeting
        System.out.println("Hello! Welcome to Erick's Base Conversion program.");

        //check for command line inputs
        if (args.length == 0) {
            System.out.println("Please enter in a value to convert base: ");
            inputString = userInput.nextLine();
            System.out.println("Please enter in your initial base: ");
            initialBase = userInput.nextInt();
            System.out.println("Please enter your desired base: ");
            desiredBase = userInput.nextInt();
        } else if (args.length == 1) {
            inputString = args[0];
            System.out.println("Please enter in your initial base: ");
            initialBase = userInput.nextInt();
            System.out.println("Please enter your desired base: ");
            desiredBase = userInput.nextInt();
        } else if (args.length == 2) {
            inputString = args[0];
            initialBase = Integer.parseInt(args[1]);
            System.out.println("Please enter your desired base: ");
            desiredBase = userInput.nextInt();
        } else if (args.length == 3) {
            inputString = args[0];
            initialBase = Integer.parseInt(args[1]);
            desiredBase = Integer.parseInt(args[2]);
        } else {
            inputString = args[0];
            initialBase = Integer.parseInt(args[1]);
            desiredBase = Integer.parseInt(args[2]);
            System.out.println("Seems like you entered one too many arguments. The first three will be assigned and any remaining arguments will be ignored.");
        }

        // check to see if the bases entered are in the range of 2 - 36
        validInitialBaseValue = isBaseInRange(initialBase);
        validDesiredBaseValue = isBaseInRange(desiredBase);

        while(!validInitialBaseValue) {
            System.out.println("Seems like you entered an invalid initial base value. Base values may only range from 2 to 36.");
            System.out.println("Please re-enter in your initial base: ");
            initialBase = userInput.nextInt();
            validInitialBaseValue = isBaseInRange(initialBase);
        }

        while(!validDesiredBaseValue) {
            System.out.println("Seems like you entered an invalid desired base value. Base values may only range from 2 to 36.");
            System.out.println("Please re-enter in your desired base: ");
            desiredBase = userInput.nextInt();
            validDesiredBaseValue = isBaseInRange(desiredBase);
        }

        //if statement to display base conversion if the integer is in the valid base.
        if (isValidInteger(inputString, initialBase)) {
            System.out.println("Your converted value is " + baseConversion(inputString, initialBase, desiredBase));
            userInput.close();
        } else {
            System.out.println("The value you entered to be converted was not in the correct base. Program exiting.");
            userInput.close();
        }
    }


    //function to check if the bases entered are in the range allowed.
    public static boolean isBaseInRange(int baseValue) {
        if (baseValue >= 2 && baseValue <=36) {
            return true;
        } else {
            return false;
        }
    }

    //function to determine if the integer is in the valid base
    public static boolean isValidInteger(String inputString, int initialBase) {
        if (initialBase <= 10) {
            for(int i = 0; i < inputString.length(); i++){
                if(inputString.charAt(i) >= '0' && inputString.charAt(i) < ('0' + initialBase)) {
                } else {
                    return false;
                }
            }
        } else if (initialBase > 10) {
            for (int i=0; i < inputString.length(); i++) {
                if(inputString.charAt(i) >= '0' && inputString.charAt(i) <= '9') {
                    if(inputString.charAt(i) >= '0' && inputString.charAt(i) < ('0' + initialBase)) {
                    } else {
                        return false;
                    }
                } else {
                    if(inputString.toUpperCase().charAt(i) - 'A' + 10 >= 0 && inputString.toUpperCase().charAt(i) - 'A' + 10 < (0 + initialBase)) {
                    } else {
                        return false;
                    }
                }
                
            }
        }
        return true;
    }

    //function to handle the base conversion. converts to base 10 first then to desired base
    public static String baseConversion(String inputString, int initialBase, int desiredBase) {
        BigInteger power = BigInteger.valueOf(1);
        BigInteger bigInitialBase = BigInteger.valueOf(Long.valueOf(initialBase));
        BigInteger finalBaseTenSum = BigInteger.valueOf(0);
        String tempString = "";
        String finalString = "";
        //base 10 calculation
        for (int i = inputString.length() - 1; i >= 0; i--) {
            if(inputString.charAt(i) >= '0' && inputString.charAt(i) <= '9') {
                int currentValue = inputString.charAt(i) - '0';
                BigInteger bigCurrentValue = BigInteger.valueOf(Long.valueOf(currentValue));
                finalBaseTenSum = finalBaseTenSum.add(bigCurrentValue.multiply(power));
                power = power.multiply(bigInitialBase);
            } else {
                int otherValue = inputString.toUpperCase().charAt(i) - 'A' + 10;
                BigInteger bigOtherValue = BigInteger.valueOf(Long.valueOf(otherValue));
                finalBaseTenSum = finalBaseTenSum.add(bigOtherValue.multiply(power));
                power = power.multiply(bigInitialBase);
            }
        }
        //desired base calculation
        while (finalBaseTenSum.compareTo(BigInteger.valueOf(0)) == 1) {
            BigInteger remainder = finalBaseTenSum.mod(BigInteger.valueOf(Long.valueOf(desiredBase)));
            if (remainder.compareTo(BigInteger.valueOf(9)) == 1) {
                tempString += (char) (remainder.intValue() - 10 + 65);
            } else {
                tempString += finalBaseTenSum.mod(BigInteger.valueOf(Long.valueOf(desiredBase))).toString();
            }
            finalBaseTenSum = finalBaseTenSum.divide(BigInteger.valueOf(Long.valueOf(desiredBase)));
        }

        for (int i = tempString.length() - 1; i >= 0; i--) {
            finalString += tempString.charAt(i);
        }

        return finalString;
    }
}
