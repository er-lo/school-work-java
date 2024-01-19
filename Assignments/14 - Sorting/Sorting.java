// Erick Lopez
// Advanced Java 2023
// OCCC
// Sorting

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Sorting {
    public static void main(String[] args) {
        String text = "";
        String inputFileName;
        String outputFileName = "output.txt";
        if (args.length == 1) {
            inputFileName = args[0];
            String[] tempFileName = inputFileName.split("[^a-zA-Z']+");
            outputFileName = tempFileName[0] + ".out";

        } else if (args.length == 2) {
            inputFileName = args[0];
            outputFileName = args[1];

        } else {
            Scanner userInput = new Scanner(System.in);
            System.out.println("Please enter your input File Name: ");
            inputFileName = userInput.nextLine();
            String[] tempFileName = inputFileName.split("[^a-zA-Z']+");
            outputFileName = tempFileName[0] + ".out";
            userInput.close();
        }

        try {
            // file scanner object
            Scanner file = new Scanner(new File(inputFileName));

            while (file.hasNextLine()) {
                text += file.nextLine();
            }

            file.close();

        } catch (FileNotFoundException e) {
        }

        Map<String, Integer> map = new LinkedHashMap<>();

        // splitting the words
        String[] words = text.split("[^a-zA-Z']+");
        String[] sortedWords = words;

        // for loop to sort the words so I could easily insert them in lexicographic
        // order in the linkedhashmap
        for (int i = 0; i < sortedWords.length - 1; i++) {
            for (int j = i + 1; j < sortedWords.length; j++) {
                if (sortedWords[i].toLowerCase().compareTo(sortedWords[j].toLowerCase()) > 0) {
                    String temp = sortedWords[i];
                    sortedWords[i] = sortedWords[j];
                    sortedWords[j] = temp;
                }
            }
        }

        for (int i = 0; i < sortedWords.length; i++) {
            String key = sortedWords[i].toLowerCase(); // set all words to lowercase and set it to the key

            // for loop to iterate through all the words to check for exact duplicates
            for (int j = 0; j < sortedWords.length; j++) {
                // if statement to check i'm not comparing the same words had non stop issues
                // before I implemented this
                if (i != j) {
                    // if statement to check if there was another word that was the same
                    if (sortedWords[i].toLowerCase().compareTo(sortedWords[j].toLowerCase()) == 0) {
                        // if statement to check if the word was the exact same (capitalized)
                        if (sortedWords[i].compareTo(sortedWords[j]) == 0) {
                            // set key equal to that word
                            key = sortedWords[i];
                        }
                    }

                }

            }

            // if statement to add the values to the map
            if (key.length() > 0) {
                if (map.containsKey(key)) {
                    int value = map.get(key);
                    value++;
                    map.put(key, value);
                } else {
                    map.put(key, 1);
                }
            }
        }

        try {
            PrintWriter output = new PrintWriter(outputFileName);
            // for each loop to go through each item of the map
            map.forEach((key, value) -> {
                if (value > 1) {
                    output.println(key + "\t(" + value + ")");
                    System.out.println(key + "\t(" + value + ")");
                } else {
                    output.println(key);
                    System.out.println(key);
                }
            });
            output.close();
        } catch (Exception e) {
        }
    }
}
