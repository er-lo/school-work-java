import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class EL_Person extends JPanel implements ActionListener {

    JRadioButton person, registeredPerson, OCCCPerson;
    ButtonGroup personGroup;
    JTextField firstName, lastName, dateOfBirth, governmentID, studentID;

    JButton createButton, saveButton, loadButton;
    
    JPanel personFirstNamePanel, personLastNamePanel, personDateOfBirthPanel, personGovernmentIDPanel, personStudentIDPanel;

    String storedFirstName, storedLastName, storedDateOfBirth, storedGovernmentID, storedStudentID;

    public EL_Person(){

        this.setLayout(new BorderLayout());

        //panels
        JPanel titlePanel = new JPanel();
        JPanel infoPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        //fonts
        Font appFontLarge = new Font("Arial", Font.PLAIN, 32);
        Font appFontSmall = new Font("Arial", Font.PLAIN, 14);

        //title
        JLabel titleLabel = new JLabel("Person UI"); 
        titleLabel.setFont(appFontLarge);
        titlePanel.add(titleLabel);

        //info panel 
        //person subtype
        infoPanel.setBorder(new EmptyBorder(10,10,10,10));
        JPanel personSubClassPanel = new JPanel();
        JPanel personSubClassLabelPanel = new JPanel();
        JLabel personSubClassLabel = new JLabel("Person subclass:");
        personSubClassLabel.setFont(appFontSmall);
        personSubClassLabelPanel.add(personSubClassLabel);

        person = new JRadioButton("Person");
        person.setFont(appFontSmall);
        registeredPerson = new JRadioButton("Registered Person");
        registeredPerson.setFont(appFontSmall);
        OCCCPerson = new JRadioButton("OCCC Person");
        OCCCPerson.setFont(appFontSmall);

        JPanel personSubClassGroupPanel = new JPanel();
        personGroup = new ButtonGroup();
        personGroup.add(person);
        personGroup.add(registeredPerson);
        personGroup.add(OCCCPerson);

        personSubClassGroupPanel.add(person);
        personSubClassGroupPanel.add(registeredPerson);
        personSubClassGroupPanel.add(OCCCPerson);

        personSubClassPanel.add(personSubClassLabelPanel, BorderLayout.WEST);
        personSubClassPanel.add(personSubClassGroupPanel, BorderLayout.WEST);

        // person info
        //first name
        personFirstNamePanel = new JPanel();
        JLabel personFirstNameLabel = new JLabel("First Name:");
        personFirstNameLabel.setFont(appFontSmall);
        firstName = new JTextField(20);
        firstName.setFont(appFontSmall);

        personFirstNamePanel.add(personFirstNameLabel);
        personFirstNamePanel.add(firstName);
        personFirstNamePanel.setVisible(false);

        //last name
        personLastNamePanel = new JPanel();
        JLabel personLastNameLabel = new JLabel("Last Name:");
        personLastNameLabel.setFont(appFontSmall);
        lastName = new JTextField(20);
        lastName.setFont(appFontSmall);

        personLastNamePanel.add(personLastNameLabel);
        personLastNamePanel.add(lastName);
        personLastNamePanel.setVisible(false);

        //date of birth
        personDateOfBirthPanel = new JPanel();
        JLabel personDateOfBirthLabel = new JLabel("Date Of Birth:");
        personDateOfBirthLabel.setFont(appFontSmall);
        dateOfBirth = new JTextField(20);
        dateOfBirth.setFont(appFontSmall);

        personDateOfBirthPanel.add(personDateOfBirthLabel);
        personDateOfBirthPanel.add(dateOfBirth);
        personDateOfBirthPanel.setVisible(false);
        
        //government ID
        personGovernmentIDPanel = new JPanel();
        JLabel personGovernmentIDLabel = new JLabel("Government ID:");
        personGovernmentIDLabel.setFont(appFontSmall);
        governmentID = new JTextField(20);
        governmentID.setFont(appFontSmall);

        personGovernmentIDPanel.add(personGovernmentIDLabel);
        personGovernmentIDPanel.add(governmentID);
        personGovernmentIDPanel.setVisible(false);

        //Student ID
        personStudentIDPanel = new JPanel();
        JLabel personStudentIDLabel = new JLabel("Student ID:");
        personStudentIDLabel.setFont(appFontSmall);
        studentID = new JTextField(20);
        studentID.setFont(appFontSmall);

        personStudentIDPanel.add(personStudentIDLabel);
        personStudentIDPanel.add(studentID);
        personStudentIDPanel.setVisible(false);

        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(personSubClassPanel);
        infoPanel.add(personFirstNamePanel);
        infoPanel.add(personLastNamePanel);
        infoPanel.add(personDateOfBirthPanel);
        infoPanel.add(personGovernmentIDPanel);
        infoPanel.add(personStudentIDPanel);

        //bottom panel buttons
        createButton = new JButton("Create New");
        createButton.setFont(appFontSmall);
        saveButton = new JButton("Save Person");
        saveButton.setFont(appFontSmall);
        loadButton = new JButton("Load Person");
        loadButton.setFont(appFontSmall);

        bottomPanel.add(createButton);
        bottomPanel.add(saveButton);
        bottomPanel.add(loadButton);
     

        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(infoPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.PAGE_END);

        person.addActionListener(this);
        registeredPerson.addActionListener(this);
        OCCCPerson.addActionListener(this);
        firstName.addActionListener(this);
        lastName.addActionListener(this);
        dateOfBirth.addActionListener(this);
        governmentID.addActionListener(this);
        studentID.addActionListener(this);
        createButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(person.isSelected() || registeredPerson.isSelected() || OCCCPerson.isSelected()) {
            if(person.isSelected()) {
                personFirstNamePanel.setVisible(true);
                personLastNamePanel.setVisible(true);
                personDateOfBirthPanel.setVisible(true);
                personGovernmentIDPanel.setVisible(false);
                personStudentIDPanel.setVisible(false);
            }
            if(registeredPerson.isSelected()) {
                personFirstNamePanel.setVisible(true);
                personLastNamePanel.setVisible(true);
                personDateOfBirthPanel.setVisible(true);
                personGovernmentIDPanel.setVisible(true);
                personStudentIDPanel.setVisible(false);
            }
            if(OCCCPerson.isSelected()) {
                personFirstNamePanel.setVisible(true);
                personLastNamePanel.setVisible(true);
                personDateOfBirthPanel.setVisible(true);
                personGovernmentIDPanel.setVisible(true);
                personStudentIDPanel.setVisible(true);
            }
        }


        if(e.getSource() == createButton) {
            // create button / wipe all info
            firstName.setText("");
            lastName.setText("");
            dateOfBirth.setText("");
            governmentID.setText("");
            studentID.setText("");
        }

        if(e.getSource() == saveButton) {
            // save current person then wipe all info
            // check all field have been entered
            storedFirstName = firstName.getText();
            storedLastName = lastName.getText();
            storedDateOfBirth = dateOfBirth.getText();
            storedGovernmentID = governmentID.getText();
            storedStudentID = studentID.getText();

            firstName.setText("");
            lastName.setText("");
            dateOfBirth.setText("");
            governmentID.setText("");
            studentID.setText("");
        }

        if(e.getSource() == loadButton) {
            // load info that was saved, throw message box if nothing saved
            firstName.setText(storedFirstName);
            lastName.setText(storedLastName);
            dateOfBirth.setText(storedDateOfBirth);
            governmentID.setText(storedGovernmentID);
            studentID.setText(storedStudentID);
        }
    }
}
