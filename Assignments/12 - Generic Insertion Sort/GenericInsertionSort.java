// Erick Lopez
// Advanced Java 2023
// OCCC
// Generic Insertion Sort

public class GenericInsertionSort {
    public static void main(String[] args) {
        Integer[] intList = { 3, 2, 12, 34, 4, 28, 5, 72, 63, 9, 79, 3, 24, 2, 5, 234, 67, 23, 14, 38, 21 };
        Double[] doubleList = { 3.1, 1.4, 6.2, 3.2, 2.5, 2.3, 4.3, 1.2, 8.9, 6.7, 4.5, 5.4, 3.2, 1.1, 9.4, 9.5, 7.4,
                7.8,
                8.0, 1.0, 2.5 };
        String[] stringList = { "food", "shear", "monday", "sale", "essential", "outlet", "table", "organized",
                "draining", "coffee", "company", "smells", "like", "poop", "because", "of", "million", "did", "bread",
                "clouds", "effect", "orange" };
        // unsorted integer list
        // print unsorted
        System.out.println("Unsorted Integers");
        printList(intList);
        // sort
        insertionSort(intList);
        // print sorted
        System.out.println("Sorted Integers");
        printList(intList);
        // print unsorted
        System.out.println();
        System.out.println();
        System.out.println("Unsorted Doubles");
        printList(doubleList);
        // sort
        insertionSort(doubleList);
        // print sorted
        System.out.println("Sorted Doubles");
        printList(doubleList);
        // print unsorted
        System.out.println();
        System.out.println();
        System.out.println("Unsorted Strings");
        printList(stringList);
        // sort
        insertionSort(stringList);
        // print sorted
        System.out.println("Sorted Strings");
        printList(stringList);
    }

    public static <E extends Comparable<E>> void insertionSort(E[] list) {
        for (int i = 1; i < list.length; i++) {
            E current = list[i]; // save current item
            int index = i; // set an index to keep track of where the current item is at
            for (int j = i - 1; j >= 0; j--) {
                if (list[j].compareTo(current) > 0) {
                    // if item at index j is greater than the current item we move that item up by
                    // one
                    list[j + 1] = list[j]; // perform the move
                    index = j; // set index to the old spot of where the item was swapped
                }
            }
            list[index] = current; // replace item that was swapped with the original/current item
        }
    }

    public static void printList(Object[] list) {
        for (int i = 0; i < list.length; i++) {
            System.out.print(i + ": " + list[i] + "  ");
        }
        System.out.println();
    }
}
