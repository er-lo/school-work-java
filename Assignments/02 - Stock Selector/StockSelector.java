import java.util.Scanner;

/**
 * Author: Erick Lopez
 * File: StockSelector.java
 * 
 * Description: Program to help new investors figure out how many shares can be bought.
 */

public class StockSelector {
	public static void main(String[] args) {
		//Variable declarations.
		Scanner input = new Scanner(System.in); //handles user input.
		double moneyAvailable, stockPrice, priceToEarningsRatio; //double type variables to be used
		int sharesAbleToBuy; //int type variable to be used.
		String stockTicker; //string type variable to be used.
		
		//Initial Message to be displayed on program start.
		System.out.println("Welcome to Erick Lopez's Stock Selector App \n");

		//Handle the initial money input.
		System.out.print("Enter the total money you are willing to invest: $");
		moneyAvailable = input.nextDouble();
		input.nextLine();
		
		//Handle the stock ticker input. Can't be longer than 5 characters. 
		//If statement used to perform the check.
		System.out.print("Enter the stock ticker: ");
		stockTicker = input.nextLine();
		if (stockTicker.length() > 5) {
			System.out.println("Invalid input. Try again.");
			System.out.print("Enter the stock ticker: ");
			stockTicker = input.nextLine();
		}
		//Handle the stock price input. Can't be less that $5. If statement used to perform the check.
		System.out.print("Enter the price of the stock: $");
		stockPrice = input.nextDouble();
		input.nextLine();
		if (stockPrice < 5) {
			System.out.println("Invalid price entered for stock. Try again.");
			System.out.print("Enter the price of the stock: $");
			stockPrice = input.nextDouble();
			input.nextLine();
		}
		//Handle the PE ratio. Display a warning if PE ratio is greater than 75. 
		//Warning is checked for in program output.
		System.out.print("Enter the P/E ratio: ");
		priceToEarningsRatio = input.nextDouble();
		input.nextLine();
	
		//Calculation for the amount of shares able to buy.
		//Cast to int helps to capture any negative amounts and set to 0.
		sharesAbleToBuy = (int) (moneyAvailable / stockPrice);
		
		
		//Program output. 
		System.out.println("\n\nBelow are your results: \n");
		System.out.println("Stock: " + stockTicker);
		System.out.printf("Price: $%.2f", stockPrice);
		System.out.println("\nP/E Ratio: " + priceToEarningsRatio);
		//Handle the  PE ratio check.
		if (priceToEarningsRatio > 75) {
			System.out.println("This stock may be overvalued. Proceed with caution!");
		}
		System.out.println("# of shares able to be purchased: " + sharesAbleToBuy);
		
	}
}
