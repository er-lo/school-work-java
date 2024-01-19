import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.sound.sampled.*;
import javax.swing.*;

public class EL_MusicPlayer extends JPanel implements ActionListener {
    
    
    JButton playButton;
    JLabel songLabel;
    String songPath = "./EL_Media/2.aiff";
    File soundFile = new File(songPath);
    AudioInputStream audio;
    Clip clip;
        
    
    public EL_MusicPlayer(){
    
        this.setLayout(new BorderLayout());
    
        JPanel titlePanel = new JPanel();
        JPanel musicPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        Font appFontLarge = new Font("Arial", Font.PLAIN, 32);
        Font appFontSmall = new Font("Arial", Font.PLAIN, 14);

        try {
            audio = AudioSystem.getAudioInputStream(soundFile);     

            clip = AudioSystem.getClip();

            clip.open(audio);

        } catch (Exception e) {
            System.out.println(e);
        }
    
        
        //title
        JLabel titleLabel = new JLabel("Music Player"); // change this
        titleLabel.setFont(appFontLarge);
        titlePanel.add(titleLabel);
    
        //music panel 
        musicPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        songLabel = new JLabel("Music was grabbed from pixabay. It is copyright free.");
        songLabel.setFont(appFontSmall);

        musicPanel.add(songLabel);
    
        //bottom panel buttons
        playButton = new JButton("Play/Stop");
        playButton.setFont(appFontSmall);
    
        bottomPanel.add(playButton);
         
    
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(musicPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
    
        playButton.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == playButton) {
            //play/stop song
            if(clip.isRunning()) {
                clip.stop();
            } else {
                clip.start();
            }
        }
    }
}
     
