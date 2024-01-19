// Producer Consumer
// John Goulden
// Advanced Java OCCC Fall 2015

// Producer Consumer (Modified for GUI)
// Erick Lopez
// OCCC Fall 2023
// Advanced Java

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.concurrent.Semaphore;

public class ProducerConsumer extends JFrame {

   private static final int WIDTH = 600;
   private static final int HEIGHT = 600;
   static int theBuffer;
   static Semaphore s = new Semaphore(1);
   public static JTextArea displayConsole, currentState;

   public static void mySleep() {
      // this function puts the thread "to sleep" for a while,
      // to simulate time spent processing

      try {
         Thread.sleep((int) (Math.random() * 1000));
      } catch (InterruptedException e) {
         // do nothing
      }
   } // close sleep method

   public static void main(String[] args) {
      ProducerConsumer pc = new ProducerConsumer();

   }

   private static void runProducerConsumer(int numProducers, int numConsumers) {
      Consumer[] c = new Consumer[numConsumers];
      Producer[] p = new Producer[numProducers];

      for (int i = 0; i < numProducers; i++) {
         p[i] = new Producer(i);
         p[i].start();
      }

      for (int i = 0; i < numConsumers; i++) {
         c[i] = new Consumer(i);
         c[i].start();
      }
   }

   public ProducerConsumer() {
      super("Producer Consumer GUI");
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setLayout(new CardLayout());
      JPanel jp = new ProducerConsumerGUI();
      add(jp);
      setSize(WIDTH, HEIGHT);
      setResizable(false);
      setVisible(true);
   }

   private static class Producer extends Thread {
      int i;

      public Producer(int i) {
         super();
         this.i = i;
      }

      public void run() {
         while (true) {
            mySleep();
            System.out.println("Producer " + i + ": attempting to acquire");
            displayConsole.setText("Producer " + i + ": attempting to acquire");
            try {
               s.acquire();
               System.out.println("Producer " + i + ": resource acquired!");
               displayConsole.setText("Producer " + i + ": resource acquired!");
               mySleep();
               System.out.println("Producer " + i + ": theBuffer (pre)  is " + theBuffer);
               displayConsole.setText("Producer " + i + ": theBuffer (pre)  is " + theBuffer);
               currentState.setText(String.valueOf(theBuffer));
               theBuffer += (int) (Math.random() * 6);
               System.out.println("Producer " + i + ": theBuffer (post) is " + theBuffer);
               displayConsole.setText("Producer " + i + ": theBuffer (post) is " + theBuffer);
               currentState.setText(String.valueOf(theBuffer));
               System.out.println("Producer " + i + ": resource released");
               displayConsole.setText("Producer " + i + ": resource released");
               s.release();
            } catch (InterruptedException e) {
            }
         }
      }
   }

   private static class Consumer extends Thread {
      int i;

      public Consumer(int i) {
         super();
         this.i = i;
      }

      public void run() {
         while (true) {
            mySleep();
            System.out.println("Consumer " + i + ": attempting to acquire");
            displayConsole.setText("Consumer " + i + ": attempting to acquire");
            try {
               s.acquire();
               System.out.println("Consumer " + i + ": resource acquired!");
               displayConsole.setText("Consumer " + i + ": resource acquired!");
               mySleep();
               System.out.println("Consumer " + i + ": theBuffer is " + theBuffer);
               displayConsole.setText("Consumer " + i + ": theBuffer is " + theBuffer);
               currentState.setText(String.valueOf(theBuffer));
               int need = (int) (1 + Math.random() * 6);
               System.out.println("Consumer " + i + ": my need is " + need);
               displayConsole.setText("Consumer " + i + ": my need is " + need);
               if (theBuffer >= need) {
                  theBuffer -= need;
                  System.out.println("Consumer " + i + ": got what I needed!");
                  displayConsole.setText("Consumer " + i + ": got what I needed!");
                  System.out.println("Consumer " + i + ": theBuffer is now " + theBuffer);
                  displayConsole.setText("Consumer " + i + ": theBuffer is now " + theBuffer);
                  currentState.setText(String.valueOf(theBuffer));
               } else {
                  System.out.println("Consumer " + i + ": resource unavailable");
                  displayConsole.setText("Consumer " + i + ": resource unavailable");
               }
               System.out.println("Consumer " + i + ": resource released");
               displayConsole.setText("Consumer " + i + ": resource released");
               s.release();
            } catch (InterruptedException e) {
            }
         }
      }
   }

   private class ProducerConsumerGUI extends JPanel implements ActionListener {

      JTextField numProducers, numConsumers;

      JButton startButton;

      JPanel numProducersPanel, numConsumersPanel, currentStatePanel, displayConsolePanel;

      public ProducerConsumerGUI() {

         this.setLayout(new BorderLayout());
         // panels
         JPanel infoPanel = new JPanel();
         JPanel bottomPanel = new JPanel();

         // fonts
         Font appFontSmall = new Font("Arial", Font.PLAIN, 14);

         // num producers
         numProducersPanel = new JPanel();
         JLabel numProducersLabel = new JLabel("Number of Producers:");
         numProducersLabel.setFont(appFontSmall);
         numProducers = new JTextField(20);
         numProducers.setFont(appFontSmall);

         numProducersPanel.add(numProducersLabel);
         numProducersPanel.add(numProducers);
         numProducersPanel.setVisible(true);

         // num consumers
         numConsumersPanel = new JPanel();
         JLabel numConsumersLabel = new JLabel("Number of Consumers:");
         numConsumersLabel.setFont(appFontSmall);
         numConsumers = new JTextField(20);
         numConsumers.setFont(appFontSmall);

         numConsumersPanel.add(numConsumersLabel);
         numConsumersPanel.add(numConsumers);
         numConsumersPanel.setVisible(true);

         // current state
         currentStatePanel = new JPanel();
         JLabel currentStateLabel = new JLabel("Current State:");
         currentStateLabel.setFont(appFontSmall);
         currentState = new JTextArea();
         currentState.setFont(appFontSmall);

         currentStatePanel.add(currentStateLabel);
         currentStatePanel.add(currentState);
         currentStatePanel.setVisible(true);

         // display console
         displayConsolePanel = new JPanel();
         JLabel displayConsoleLabel = new JLabel("Console:");
         displayConsoleLabel.setFont(appFontSmall);
         displayConsole = new JTextArea();
         displayConsole.setFont(appFontSmall);

         displayConsolePanel.add(displayConsoleLabel);
         displayConsolePanel.add(displayConsole);
         displayConsolePanel.setVisible(true);

         // info panel
         infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
         infoPanel.add(numProducersPanel);
         infoPanel.add(numConsumersPanel);
         infoPanel.add(currentStatePanel);
         infoPanel.add(displayConsolePanel);

         // bottom panel buttons
         startButton = new JButton("Start");
         startButton.setFont(appFontSmall);

         bottomPanel.add(startButton);

         this.add(infoPanel, BorderLayout.PAGE_START);
         this.add(bottomPanel, BorderLayout.PAGE_END);

         numProducers.addActionListener(this);
         numConsumers.addActionListener(this);
         startButton.addActionListener(this);

      }

      @Override
      public void actionPerformed(ActionEvent e) {
         if (e.getSource() == startButton) {
            int numOfProducers = Integer.parseInt(numProducers.getText());
            int numOfConsumers = Integer.parseInt(numConsumers.getText());
            runProducerConsumer(numOfProducers, numOfConsumers);
         }

      }
   }

}
