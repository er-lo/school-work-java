//Erick Lopez
//OCCC
//Advanced Java Fall 2023
//Palindromes Assignment

import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args) {
        String userInputString;
        //program greeting
        System.out.println("Welcome to Erick's Palindrome program.");
        //argument handling
        if (args.length == 1) {
            userInputString = args[0];
        } else {
            Scanner userInput = new Scanner(System.in);
            System.out.println("Please enter a phrase: ");
            userInputString = userInput.nextLine();
            userInput.close();
        }
        //clean the string to remove any special characters
        String cleanString = stringCleaning(userInputString);
        //if statement to handle the print statement if it is a palindrome or not.
        if (recursivePalindromeCheck(cleanString)) {
            System.out.println("The phrase was a palindrome.");
        } else {
            System.out.println("The phrase was not a palindrome.");
        }
    }

    public static String stringCleaning(String dirtyString) {
        //function to clean the string.
        //I thought the name was funny
        String cleanString;
        cleanString = dirtyString.replaceAll("[^a-zA-Z0-9]", "");
        return cleanString.toLowerCase();
    }

    public static boolean recursivePalindromeCheck(String phrase) {
        //function to do the actual check
        if (phrase.length() <= 1) {
            return true;
        }
        if (phrase.charAt(0) == phrase.charAt(phrase.length() - 1)) {
            return recursivePalindromeCheck(phrase.substring(1, phrase.length() - 1));
        }
        return false;
    }
}
