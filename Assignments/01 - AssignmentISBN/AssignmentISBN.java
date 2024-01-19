/**
 * Name: Erick Lopez
 * File: AssignmentISBN.java
 * Description: Assignment 2. Program that does book inventory by verifying ISBN is valid. 
 */

import java.util.Scanner;

public class AssignmentISBN {
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in); //scanner for input
		String closeProgramString; //String that gets entered at the end when user is asked if to continue or quit.
		String returnOrCheckOutString; //String that gets entered for either return or check out.
		String firstTwelveDigits; //String that gets entered when first 12 digits are asked for. 
		int parenthesisSum = 0; //sum of operations inside parenthesis.
		int finalDigit = 0; //checksum / final digit that gets concatenated to first twelve digits string.
		int booksReturned = 0; //int to track books returned.
		int booksCheckedOut = 0; //int to track books checked out.
		int booksProcessed = 0; //int to track books processed.
		boolean programStillRunning = true; //boolean to keep track if program is still running. 
		
		while (programStillRunning) {
			
			parenthesisSum = 0;
			//First section of the printout. All we ask for here is the first 12 digits.
			System.out.println("-=========================================-");
			System.out.println("Book Inventory Program");
			System.out.println("-=========================================-");
			System.out.print("Enter the first 12 digits of an ISBN-13 as a String: "); 
			//this is where the first twelve digits gets read in.
			firstTwelveDigits = input.nextLine();
			//after we get the 12 digits we check the length 
			while(firstTwelveDigits.length() != 12) {
				System.out.println("\n\nInvalid ISBN-13 number! Try again.");
				System.out.print("Enter the first 12 digits of an ISBN-13 as a String: "); 
				firstTwelveDigits = input.nextLine();
			}
			//I broke the checksum calculation apart. I decided the do the work inside the parenthesis first.
			//This for loop handles the the parenthesis calculation by looping through each character of the string and finding out if it's even.
			//if it's even it will do the 3 * the int value at that position in the string.
			//The int value is grabbed by subtracting the ascii decimal value of '0' to give the value of the charater
			for (int i = 0; i < firstTwelveDigits.length(); i++) {
				if (i % 2 == 0) {
					parenthesisSum = 3 * (firstTwelveDigits.charAt(i) - 48);
				} else {
					parenthesisSum += firstTwelveDigits.charAt(i) - 48;
				}
			}
			
			//after the parenthesis sum is calculated I threw it back into the checksum calculation formula. 
			//I named this finalDigit since it's the last digit in the ISBN-13 number.
			finalDigit = 10 - parenthesisSum % 10;
			
			//per the problem that if the checksum is 10 then set it to 0
			if (finalDigit == 10) {
				finalDigit = 0;
			}
			
			//Here we print out the full ISBN-13 number
			System.out.println("ISBN-13 # " + firstTwelveDigits + finalDigit);
			
			//Here we do the user input for a return or check out. 
			System.out.print("\nEnter 'R' for a return or 'C' to check out: ");
			returnOrCheckOutString = input.nextLine();
			
			//while loop to check that the correct input was entered. I took the string then lower-cased it and checked the value at position 0.
			//My reasoning was to cut down on what to check for. It also by checking for only the first char then if a user
			//enter "Return" or "Check Out" then the program would still run as intended.
			//As long as input is good then loop doesn't loop.
			while(returnOrCheckOutString.toLowerCase().charAt(0) != 'r' && returnOrCheckOutString.toLowerCase().charAt(0) != 'c') {
				System.out.println("\n\nInvalid Entry...");
				System.out.print("Enter 'R' for a return or 'C' to check out: ");
				returnOrCheckOutString = input.nextLine();
			}
			
			if (returnOrCheckOutString.toLowerCase().charAt(0) == 'r') {
				//check for if a return command was entered.
				booksReturned++;
				booksProcessed++;
				
			} else if (returnOrCheckOutString.toLowerCase().charAt(0) == 'c') {
				//check for if a check out command was entered.
				booksCheckedOut++;
				booksProcessed++;
			}
			
			
			//output for the books processed.
			System.out.println("\n\n-=========================================-");
			System.out.println("Book Inventory Program");
			System.out.println("-=========================================-");
			System.out.println("# of books returned: " + booksReturned);
			System.out.println("# of books checked out: " + booksCheckedOut);
			System.out.println("# of books processed: " + booksProcessed);
			System.out.println("-=========================================-");
			
			//User input for a repeat of the program and to quit out of it.
			System.out.print("Enter 'Y' to continue, 'N' to quit: ");
			closeProgramString = input.nextLine();
			
			//Did similar to the while loop above. Lower-cased then checked value at position 0.
			//If option that wasn't listed was typed in then it would loop again displaying an error message.
			while(closeProgramString.toLowerCase().charAt(0) != 'y' && closeProgramString.toLowerCase().charAt(0) != 'n') {
				System.out.println("\n\nInvalid Entry...");
				System.out.print("Enter 'Y' to continue, 'N' to quit: ");
				closeProgramString = input.nextLine();
			}
			
			//Check for if the user wants to quit.
			if(closeProgramString.toLowerCase().charAt(0) == 'n') {
				programStillRunning = false;
			}
		}
	}

}
