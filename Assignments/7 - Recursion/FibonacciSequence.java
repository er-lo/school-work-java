// Erick Lopez
// Advanced Java 2023
// OCCC
// Fibonacci sequence

import java.math.BigInteger;
import java.util.Scanner;

class FibonacciSequence {
    //Big Integer array memo
    public static long[] longMemo;
    public static BigInteger[] bigIntMemo;
    public static void main(String[] args) {
        //grab user arguments
        int val = 0;
        if (args.length == 1) {
            val = Integer.parseInt(args[0]);
        } else {
            Scanner userInput = new Scanner(System.in);
            System.out.println("Please enter a value: ");
            val = userInput.nextInt();
            userInput.close();
        }

        //just uncomment the one you want to use.
        //first one is the loop second is recursion.

        // //long loop
        // long startTimeLongLoop = System.currentTimeMillis();
        //long fibSeqLongLoop = fibSeqLoopLong(val);
        // long endTimeLongLoop = System.currentTimeMillis();
        // System.out.println("value " + fibSeqLongLoop + " ");
        // System.out.println("The elapsed time is " + ( endTimeLongLoop - startTimeLongLoop ) / 1000. + " seconds.");

        // //long recursion
        // long startTimeLongRecursion = System.currentTimeMillis();
        // long fibSeqLongRecursion = fibSeqRecursiveLong(val);
        // long endTimeLongRecursion = System.currentTimeMillis();
        // System.out.println("value " + fibSeqLongRecursion + " ");
        // System.out.println("The elapsed time is " + ( endTimeLongRecursion - startTimeLongRecursion ) / 1000. + " seconds.");

        //long recursion with memoization
        //create the memoization array
        // longMemo = new long[val + 1];
        // long startTimeLongRecursionMemo = System.currentTimeMillis();
        // long fibSeqLongRecursionMemo = fibSeqRecursiveLongMemoization(val);
        // long endTimeLongRecursionMemo = System.currentTimeMillis();
        // System.out.println("value " + fibSeqLongRecursionMemo + " ");
        // System.out.println("The elapsed time is " + ( endTimeLongRecursionMemo - startTimeLongRecursionMemo ) / 1000. + " seconds.");


        // //BigInteger loop
        // long startTimeBigIntegerLoop = System.currentTimeMillis();
        // BigInteger fibSeqBigIntegerLoop = fibSeqLoopBigInteger(val);
        // long endTimeBigIntegerLoop = System.currentTimeMillis();
        // System.out.println("value " + fibSeqBigIntegerLoop + " ");
        // System.out.println("The elapsed time is " + ( endTimeBigIntegerLoop - startTimeBigIntegerLoop ) / 1000. + " seconds.");

        // // BigInteger recursion
        // long startTimeBigIntegerRecursion = System.currentTimeMillis();
        // BigInteger fibSeqBigIntegerRecursion = fibSeqRecursiveBigInteger(val);
        // long endTimeBigIntegerRecursion = System.currentTimeMillis();
        // System.out.println("value " + fibSeqBigIntegerRecursion + " ");
        // System.out.println("The elapsed time is " + ( endTimeBigIntegerRecursion - startTimeBigIntegerRecursion ) / 1000. + " seconds.");
 
        //BigInteger recursion with memoization
        //create the memoization array
        bigIntMemo = new BigInteger[val + 1];
        long startTimeBigIntegerRecursionMemo = System.currentTimeMillis();
        BigInteger fibSeqBigIntegerRecursionMemo = fibSeqRecursiveBigIntegerMemoization(val);
        long endTimeBigIntegerRecursionMemo = System.currentTimeMillis();
        System.out.println("value " + fibSeqBigIntegerRecursionMemo + " ");
        System.out.println("The elapsed time is " + ( endTimeBigIntegerRecursionMemo - startTimeBigIntegerRecursionMemo ) / 1000. + " seconds.");

    }

    public static long fibSeqLoopLong(int n) {
        //function for the loop fib seq
        long result = 1;
        if (n > 2) {
            long a = 1;
            long b = 1;
            for (int count = 3; count <= n; count++) {
                result = a + b;
                a = b;
                b = result;
            }
        }
        return result;
    }

    public static long fibSeqRecursiveLong(int n) {
        //function for the recursive fib seq
        long result = 1;
        if (n > 2) {
            result = fibSeqRecursiveLong(n-1) + fibSeqRecursiveLong(n-2);
        }
        //return the result which is what gets printed
        return result;
    }

    public static long fibSeqRecursiveLongMemoization(int n) {
        //function for the recursive fib seq
        long result = 1;
        if (n > 2) {
            //had to check for null since the array initializes null compared to 0 for int and long array
            if (longMemo[n] != 0) {
                result = longMemo[n];
            } else {
                //recursion occurs here
                result = fibSeqRecursiveLongMemoization(n-1) + fibSeqRecursiveLongMemoization(n-2);
                longMemo[n] = result;
            }
        }
        //return the result which is what gets printed
        return result;
    }

    public static BigInteger fibSeqLoopBigInteger(int n) {
        //function for the loop fib seq
        BigInteger result = BigInteger.valueOf(1);
        if (n > 2) {
            BigInteger a = BigInteger.valueOf(1);
            BigInteger b = BigInteger.valueOf(1);
            for (int count = 3; count <= n; count++) {
                result = a.add(b);
                a = b;
                b = result;
            }
        }
        return result;
    }

    public static BigInteger fibSeqRecursiveBigInteger(int n) {
        //function for the recursive fib seq
        BigInteger result = BigInteger.valueOf(1);
        if (n > 2) {
            result = fibSeqRecursiveBigInteger(n-1).add(fibSeqRecursiveBigInteger(n-2));
        }
        //return the result which is what gets printed
        return result;
    }

    public static BigInteger fibSeqRecursiveBigIntegerMemoization(int n) {
        //function for the recursive fib seq
        BigInteger result = BigInteger.valueOf(1);
        if (n > 2) {
            //had to check for null since the array initializes null compared to 0 for int and long array
            if (bigIntMemo[n] != null) {
                result = bigIntMemo[n];
            } else {
                //recursion occurs here
                result = fibSeqRecursiveBigIntegerMemoization(n-1).add(fibSeqRecursiveBigIntegerMemoization(n-2));
                bigIntMemo[n] = result;
            }
        }
        //return the result which is what gets printed
        return result;
    }
}