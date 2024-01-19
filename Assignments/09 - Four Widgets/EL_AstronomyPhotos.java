import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EL_AstronomyPhotos extends JPanel implements ActionListener {

    JButton nextButton, lastButton;
    JLabel picLabel;
    String [] photos = {"./EL_Media/1.jpg", "./EL_Media/2.jpg", "./EL_Media/3.jpg", "./EL_Media/4.jpg"};
    int photoCount = 0;
    

    public EL_AstronomyPhotos(){

        this.setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        JPanel photoPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        Font appFontLarge = new Font("Arial", Font.PLAIN, 32);
        Font appFontSmall = new Font("Arial", Font.PLAIN, 14);

        //title
        JLabel titleLabel = new JLabel("Astronomy Photos"); // change this
        titleLabel.setFont(appFontLarge);
        titlePanel.add(titleLabel);

        //photo panel 
        photoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        picLabel = new JLabel(new ImageIcon(photos[photoCount]));
        picLabel.setPreferredSize(new Dimension(600,400));
        photoPanel.add(picLabel);

        //bottom panel buttons
        lastButton = new JButton("Last");
        lastButton.setFont(appFontSmall);
        nextButton = new JButton("Next");
        nextButton.setFont(appFontSmall);

        bottomPanel.add(lastButton);
        bottomPanel.add(nextButton);
     

        this.add(titlePanel, BorderLayout.NORTH);
        this.add(photoPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

        lastButton.addActionListener(this);
        nextButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == lastButton) {
            // change to last photo
            if (photoCount == 0) {
                photoCount = 3;
                picLabel.setIcon(new ImageIcon(photos[photoCount]));
                updateUI();
            } else {
                photoCount--;
                picLabel.setIcon(new ImageIcon(photos[photoCount]));
                updateUI();
            }
        }

        if(e.getSource() == nextButton) {
            //change to next
            if (photoCount == 3) {
                photoCount = 0;
                picLabel.setIcon(new ImageIcon(photos[photoCount]));
                updateUI();
            } else {
                photoCount++;
                picLabel.setIcon(new ImageIcon(photos[photoCount]));
                updateUI();
            }
        }

    }
}
