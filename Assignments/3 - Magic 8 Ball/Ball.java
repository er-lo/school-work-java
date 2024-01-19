// Erick Lopez
// Advanced Java
// OCCC Fall 2023
// Magic 8 Ball Assignment
import java.util.Scanner;

public class Ball {
    public static void main(String[] args) {
        // local variables used for program
        int questionCounter = 0; //keeps track of questions
        boolean programStillRunning = true; //boolean to keep the loop alive
        String userInputString; //user input string. just used to check if empty
        Scanner input = new Scanner(System.in); //input scanner

        //intro message
        System.out.println("\n\nWelcome to the Magic 8 Ball."); 
        System.out.println("You may ask the Magic 8 Ball as many questions as you desire. If you choose to exit, simply press enter when prompted for a question.\n");

        //main loop
        while (programStillRunning){
            System.out.println("Ask a question and the Magic 8 Ball shall answer:");
            userInputString = input.nextLine();

            //checks to see if there is an empty string
            //if true will give number of questions answered and close scanner and kill while loop
            // if false spits out a ball response and increases the counter for questions asked.
            if (userInputString.length() <= 0) {
                System.out.println("\nThe number of questions answered: " + questionCounter);
                input.close();
                programStillRunning = false;
            } else {
                System.out.println("\n" + ballResponse() + "\n\n");
                questionCounter++;
            }
        }
    }

    //separate function to handle the response
    public static String ballResponse() {
        //using math.random to get a random integer to use with the switch case
        int randomNumber = (int) (Math.random() * 10);
        //string to hold the string to be returned by the function
        String selectedString = "";
        //switch case, returns a string based on random integer
        switch (randomNumber) {
            case 0: selectedString = "Don't count on it"; break;
            case 1: selectedString = "Ask again later."; break;
            case 2: selectedString = "Without a doubt"; break;
            case 3: selectedString = "My reply is no."; break;
            case 4: selectedString = "Better not to tell you."; break;
            case 5: selectedString = "You may rely on it."; break;
            case 6: selectedString = "Outlook not good."; break;
            case 7: selectedString = "Cannot predict now."; break;
            case 8: selectedString = "As I see it, yes."; break;
            case 9: selectedString = "It is certain."; break;
        }
        return selectedString;
    }
}
