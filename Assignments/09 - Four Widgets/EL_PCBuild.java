import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EL_PCBuild extends JPanel implements ActionListener {

    JTextArea buildText;
    JButton buildButton;

    JRadioButton i3, i5, i7, i9; //cpu options
    JRadioButton fanCooled, waterCooled; //cpu cooler options
    JRadioButton eightGB, sixteenGB, thirtytwoGB, sixtyfourGB; //ram options
    JRadioButton fortyFifty, fortySixty, fortySeventy, fortyEighty, fortyNinety; //gpu options

    ButtonGroup cpuGroup, cpuCoolerGroup, ramGroup, gpuGroup;

    public EL_PCBuild(){

        this.setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        JPanel cpuPanel = new JPanel();
        JPanel cpuCoolerPanel = new JPanel();
        JPanel ramPanel = new JPanel();
        JPanel gpuPanel = new JPanel();
        JPanel buildPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        Font appFontLarge = new Font("Arial", Font.PLAIN, 32);
        Font appFontSmall = new Font("Arial", Font.PLAIN, 14);


        //title
        JLabel titleLabel = new JLabel("Build A Custom PC");
        titleLabel.setFont(appFontLarge);
        titlePanel.add(titleLabel);

        //cpu
        JLabel cpuLabel = new JLabel("CPU:");
        cpuLabel.setFont(appFontSmall);

        i3 = new JRadioButton("i3");
        i5 = new JRadioButton("i5");
        i7 = new JRadioButton("i7");
        i9 = new JRadioButton("i9");

        i3.setFont(appFontSmall);
        i5.setFont(appFontSmall);
        i7.setFont(appFontSmall);
        i9.setFont(appFontSmall);

        cpuGroup = new ButtonGroup();

        cpuGroup.add(i3);
        cpuGroup.add(i5);
        cpuGroup.add(i7);
        cpuGroup.add(i9);

        cpuPanel.add(cpuLabel);
        cpuPanel.add(i3);
        cpuPanel.add(i5);
        cpuPanel.add(i7);
        cpuPanel.add(i9);

        //cpu cooler
        JLabel cpuCoolerLabel = new JLabel("CPU Cooler:");
        cpuCoolerLabel.setFont(appFontSmall);

        fanCooled = new JRadioButton("Fan Cooled");
        waterCooled = new JRadioButton("Water Cooled");

        fanCooled.setFont(appFontSmall);
        waterCooled.setFont(appFontSmall);

        cpuCoolerGroup = new ButtonGroup();

        cpuCoolerGroup.add(fanCooled);
        cpuCoolerGroup.add(waterCooled);

        cpuCoolerPanel.add(cpuCoolerLabel);
        cpuCoolerPanel.add(fanCooled);
        cpuCoolerPanel.add(waterCooled);

        //ram
        JLabel ramLabel = new JLabel("RAM:");
        ramLabel.setFont(appFontSmall);

        eightGB = new JRadioButton("8 GB");
        sixteenGB = new JRadioButton("16 GB");
        thirtytwoGB = new JRadioButton("32 GB");
        sixtyfourGB = new JRadioButton("64 GB");

        eightGB.setFont(appFontSmall);
        sixteenGB.setFont(appFontSmall);
        thirtytwoGB.setFont(appFontSmall);
        sixtyfourGB.setFont(appFontSmall);

        ramGroup = new ButtonGroup();

        ramGroup.add(eightGB);
        ramGroup.add(sixteenGB);
        ramGroup.add(thirtytwoGB);
        ramGroup.add(sixtyfourGB);

        ramPanel.add(ramLabel);
        ramPanel.add(eightGB);
        ramPanel.add(sixteenGB);
        ramPanel.add(thirtytwoGB);
        ramPanel.add(sixtyfourGB);
        
        //gpu
        JLabel gpuLabel = new JLabel("GPU:");
        gpuLabel.setFont(appFontSmall);

        fortyFifty = new JRadioButton("4050");
        fortySixty = new JRadioButton("4060");
        fortySeventy = new JRadioButton("4070");
        fortyEighty = new JRadioButton("4080");
        fortyNinety = new JRadioButton("4090");

        fortyFifty.setFont(appFontSmall);
        fortySixty.setFont(appFontSmall);
        fortySeventy.setFont(appFontSmall);
        fortyEighty.setFont(appFontSmall);
        fortyNinety.setFont(appFontSmall);

        gpuGroup = new ButtonGroup();

        gpuGroup.add(fortyFifty);
        gpuGroup.add(fortySixty);
        gpuGroup.add(fortySeventy);
        gpuGroup.add(fortyEighty);
        gpuGroup.add(fortyNinety);

        gpuPanel.add(gpuLabel);
        gpuPanel.add(fortyFifty);
        gpuPanel.add(fortySixty);
        gpuPanel.add(fortySeventy);
        gpuPanel.add(fortyEighty);
        gpuPanel.add(fortyNinety);

        centerPanel.add(cpuPanel);
        centerPanel.add(cpuCoolerPanel);
        centerPanel.add(ramPanel);
        centerPanel.add(gpuPanel);


        // build the PC
        buildText = new JTextArea(7, 60);
        buildText.setFont(appFontSmall);
        buildText.setText("Click the button below to build your new PC!");
        buildButton = new JButton("Build my PC!");
        buildButton.setFont(appFontSmall);

        buildPanel.setLayout(new BorderLayout());
        JPanel upperBuild = new JPanel(new GridLayout(1,1));
        JPanel lowerBuild = new JPanel(new FlowLayout(FlowLayout.CENTER));

        upperBuild.add(buildText);
        lowerBuild.add(buildButton);
        buildPanel.add(upperBuild, BorderLayout.NORTH);
        buildPanel.add(lowerBuild, BorderLayout.SOUTH);

        centerPanel.setLayout(new GridLayout(7,1));

        this.add(titlePanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(buildPanel, BorderLayout.SOUTH);

        
        i3.addActionListener(this);
        i5.addActionListener(this);
        i7.addActionListener(this);
        i9.addActionListener(this);
        fanCooled.addActionListener(this);
        waterCooled.addActionListener(this);
        eightGB.addActionListener(this);
        sixteenGB.addActionListener(this);
        thirtytwoGB.addActionListener(this);
        sixtyfourGB.addActionListener(this);
        fortyFifty.addActionListener(this);
        fortySixty.addActionListener(this);
        fortySeventy.addActionListener(this);
        fortyEighty.addActionListener(this);
        fortyNinety.addActionListener(this);
        buildButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        buildText.setText("Click the button below to build your new PC! \n");


        if(e.getSource() == buildButton) {
            if(i3.isSelected() || i5.isSelected() || i7.isSelected() || i9.isSelected()) {
                buildText.append("CPU: ");
                if(i3.isSelected()) {
                    buildText.append("i3 \n");
                }
                if(i5.isSelected()) {
                    buildText.append("i5 \n");
                }
                if(i7.isSelected()) {
                    buildText.append("i7 \n");
                }
                if(i9.isSelected()) {
                    buildText.append("i9 \n");
                }
            }

            if(fanCooled.isSelected() || waterCooled.isSelected()) {
                buildText.append("CPU Cooler: ");
                if(fanCooled.isSelected()) {
                    buildText.append("Fan Cooled \n");
                }
                if(waterCooled.isSelected()) {
                    buildText.append("Water Cooled \n");
                }
            }

            if(eightGB.isSelected() || sixteenGB.isSelected() || thirtytwoGB.isSelected() || sixtyfourGB.isSelected()) {
                buildText.append("RAM: ");
                if(eightGB.isSelected()) {
                    buildText.append("8 GB \n");
                }
                if(sixteenGB.isSelected()) {
                    buildText.append("16 GB \n");
                }
                if(thirtytwoGB.isSelected()) {
                    buildText.append("32 GB \n");
                }
                if(sixtyfourGB.isSelected()) {
                    buildText.append("64 GB \n");
                }
            }

            if(fortyFifty.isSelected() || fortySixty.isSelected() || fortySeventy.isSelected() || fortyEighty.isSelected() || fortyNinety.isSelected()) {
                buildText.append("GPU: ");
                if(fortyFifty.isSelected()) {
                    buildText.append("4050 \n");
                }
                if(fortySixty.isSelected()) {
                    buildText.append("4060 \n");
                }
                if(fortySeventy.isSelected()) {
                    buildText.append("4070 \n");
                }
                if(fortyEighty.isSelected()) {
                    buildText.append("4080 \n");
                }
                if(fortyNinety.isSelected()) {
                    buildText.append("4090 \n");
                }
            }
            
        }

    }
}