// Erick Lopez
// Advanced Java 2023
// OCCC
// Iterators and Linked Lists

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class IteratorVLinkedList {
    public static void main(String[] args) {
        long val;
        if (args.length == 1) {
            val = Long.parseLong(args[0]);
        } else {
            Scanner userInput = new Scanner(System.in);
            System.out.println("Please enter a value: ");
            val = userInput.nextLong();
            userInput.close();
        }
        LinkedList<Object> list = new LinkedList<>(); // create linkedlist

        // create random ints of size defined by user.
        for (int i = 0; i < val; i++) {
            int random = (int) (Math.random() * (1000 + 1));
            list.add(i, random);
        }

        // traverse through the linked list using iterator and capture the amount of
        // time it takes.
        ListIterator<Object> listIterator = list.listIterator();
        long iteratorStartTime = System.currentTimeMillis();
        while (listIterator.hasNext()) {
            // do nothing just traverse
            Object j = listIterator.next();
        }
        long iteratorEndTime = System.currentTimeMillis();
        System.out.println("It took " + (iteratorEndTime - iteratorStartTime) / 1000. + " seconds to traverse through "
                + list.size()
                + " items using iterator");

        // traverse through the linked list using the get index method and capture the
        // amount of time it takes.
        long getMethodStartTime = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            // do nothing just traverse
            Object j = list.get(i);
        }
        long getMethodEndTime = System.currentTimeMillis();
        System.out.println("It took " + (getMethodEndTime - getMethodStartTime) / 1000.
                + " seconds to traverse through " + list.size() + " items using get index method");
    }
}
