// Cigarette Smoker's Problem
// Erick Lopez
// OCCC Fall 2023
// Advanced Java

import java.util.concurrent.Semaphore;

public class CigSmoker {

    static int buffer = 3;
    static String[] items = { "Paper", "Matches", "Tobacco", "Empty" };
    static Semaphore tableSemaphore = new Semaphore(1);
    static Semaphore agentSemaphore = new Semaphore(0);

    public static void mySleep() {
        try {
            Thread.sleep((int) (Math.random() * 100));
        } catch (InterruptedException e) {
        }
    }

    public static void main(String[] args) {

        Agent agent = new Agent();
        Smoker paperNeeded = new Smoker(0);
        Smoker matchesNeeded = new Smoker(1);
        Smoker tobaccoNeeded = new Smoker(2);

        agent.start();
        paperNeeded.start();
        matchesNeeded.start();
        tobaccoNeeded.start();

    }

    private static class Smoker extends Thread {
        int i;

        public Smoker(int i) {
            super();
            this.i = i;
        }

        public void run() {
            while (true) {
                mySleep();
                System.out.println("Smoker (" + items[i] + " needed)" + ": attempting to check table");
                try {
                    tableSemaphore.acquire();
                    System.out.println("Smoker (" + items[i] + " needed)" + ": table acquired!");
                    mySleep();
                    if (buffer == 3) {
                        System.out.println("Smoker (" + items[i] + " needed)" + ": huh the table is empty!");
                        System.out.println("Smoker (" + items[i] + " needed)" + ": table released");
                        tableSemaphore.release();
                    } else {
                        System.out.println(
                                "Smoker (" + items[i] + " needed)" + ": current item on the table is "
                                        + items[buffer]);
                        System.out.println("Smoker (" + items[i] + " needed)" + ": my need is " + items[i]);
                        if (buffer == i) {
                            buffer = 3;
                            System.out.println("Smoker (" + items[i] + " needed)" + ": got what I needed!");
                            System.out.println("Smoker (" + items[i] + " needed)" + ": the table is now empty.");
                            agentSemaphore.release();
                        } else {
                            System.out.println("Smoker (" + items[i] + " needed)" + ": not what I need..");
                        }
                        System.out.println("Smoker (" + items[i] + " needed)" + ": table released");
                        tableSemaphore.release();
                    }
                } catch (InterruptedException e) {
                }
            }
        }
    }

    private static class Agent extends Thread {
        public Agent() {
            super();
        }

        public void run() {
            while (true) {
                mySleep();
                System.out.println("Agent: " + "attempting to restock the table");
                try {
                    tableSemaphore.acquire();
                    System.out.println("Agent: " + "table was acquired!");
                    mySleep();
                    System.out.println("Agent: " + "setting item on the table!");
                    buffer = (int) (Math.random() * 2);
                    System.out.println("Agent: " + "item set on table is " + items[buffer]);
                    System.out.println("Agent: " + "table released");
                    tableSemaphore.release();
                    agentSemaphore.acquire();
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
