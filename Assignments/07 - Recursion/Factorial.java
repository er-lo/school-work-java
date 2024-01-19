// Erick Lopez
// Advanced Java 2023
// OCCC
// Factorial Program

import java.math.BigInteger;
import java.util.Scanner;

class Factorial {
    public static void main(String[] args) {
        int val = 0;
        if (args.length == 1) {
            val = Integer.parseInt(args[0]);
        } else {
            Scanner userInput = new Scanner(System.in);
            System.out.println("Please enter a value: ");
            val = userInput.nextInt();
            userInput.close();
        }


        //these all run factorials at different data types.
        //just uncomment the one you want to use.
        //they are all labeled.

        // //integer loop
        // long startTimeIntegerLoop = System.currentTimeMillis();
        // int factorialIntegerLoop = factorialLoopInteger(val);
        // long endTimeIntegerLoop = System.currentTimeMillis();
        // System.out.println("value " + factorialIntegerLoop + " ");
        // System.out.println("The elapsed time is " + ( endTimeIntegerLoop - startTimeIntegerLoop ) / 1000. + " seconds.");

        // //integer recursion
        // long startTimeIntegerRecursion = System.currentTimeMillis();
        // int factorialIntegerRecursion = factorialRecursionInteger(val);
        // long endTimeIntegerRecursion = System.currentTimeMillis();
        // System.out.println("value " + factorialIntegerRecursion + " ");
        // System.out.println("The elapsed time is " + ( endTimeIntegerRecursion - startTimeIntegerRecursion ) / 1000. + " seconds.");

        // //long loop
        // long startTimeLongLoop = System.currentTimeMillis();
        // long factorialLongLoop = factorialLoopLong(val);
        // long endTimeLongLoop = System.currentTimeMillis();
        // System.out.println("value " + factorialLongLoop + " ");
        // System.out.println("The elapsed time is " + ( endTimeLongLoop - startTimeLongLoop ) / 1000. + " seconds.");

        // //long recursion
        // long startTimeLongRecursion = System.currentTimeMillis();
        // long factorialLongRecursion = factorialRecursionLong(val);
        // long endTimeLongRecursion = System.currentTimeMillis();
        // System.out.println("value " + factorialLongRecursion + " ");
        // System.out.println("The elapsed time is " + ( endTimeLongRecursion - startTimeLongRecursion ) / 1000. + " seconds.");

        // //BigInteger loop
        // long startTimeBigIntegerLoop = System.currentTimeMillis();
        // BigInteger factorialBigIntegerLoop = factorialLoopBigInteger(val);
        // long endTimeBigIntegerLoop = System.currentTimeMillis();
        // System.out.println("value " + factorialBigIntegerLoop + " ");
        // System.out.println("The elapsed time is " + ( endTimeBigIntegerLoop - startTimeBigIntegerLoop ) / 1000. + " seconds.");

        //BigInteger recursion
        long startTimeBigIntegerRecursion = System.currentTimeMillis();
        BigInteger factorialBigIntegerRecursion = factorialRecursionBigInteger(val);
        long endTimeBigIntegerRecursion = System.currentTimeMillis();
        System.out.println("value " + factorialBigIntegerRecursion + " ");
        System.out.println("The elapsed time is " + ( endTimeBigIntegerRecursion - startTimeBigIntegerRecursion ) / 1000. + " seconds.");
    }

    public static int factorialLoopInteger(int n) {
        //integer results using loop
        int result = 1;
        if (n > 1) {
            for (int i = 1; i <= n; i++) {
                result *= i;
            }
        }
        return result;
    }

    public static int factorialRecursionInteger(int n) {
        //integer results using recursion
        int result = 1;
        if (n > 1) {
            result = n * factorialRecursionInteger(n-1);
        }
        return result;
    }

    public static long factorialLoopLong(int n) {
        //long results using loop
        long result = 1;
        if (n > 1) {
            for (int i = 1; i <= n; i++) {
                result *= i;
            }
        }
        return result;
    }

    public static long factorialRecursionLong(int n) {
        //long results using recursion
        long result = 1;
        if (n > 1) {
            result = n * factorialRecursionLong(n-1);
        }
        return result;
    }

    public static BigInteger factorialLoopBigInteger(int n) {
        //big integer using loop
        BigInteger result = BigInteger.valueOf(1);
        if (n > 1) {
            for (int i = 1; i <= n; i++) {
                result = result.multiply(BigInteger.valueOf(Long.valueOf(i)));
            }
        }
        return result;
    }

    public static BigInteger factorialRecursionBigInteger(int n) {
        //big integer using recursion
        BigInteger result = BigInteger.valueOf(1);
        if (n > 1) {
            result = BigInteger.valueOf(Long.valueOf(n)).multiply(factorialRecursionBigInteger(n-1));
        }
        return result;
    }
}