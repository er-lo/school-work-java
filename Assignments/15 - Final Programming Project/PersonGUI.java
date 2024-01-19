//Erick Lopez
//Advanced Java
//OCCC Fall 2023
//PersonGUI

import java.awt.*;
import java.awt.event.*;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PersonGUI extends JFrame implements ActionListener {

    // file menu items
    JMenuItem fileMenu_new;
    JMenuItem fileMenu_open;
    JMenuItem fileMenu_save;
    JMenuItem fileMenu_saveAs;
    JMenuItem fileMenu_exit;

    // dialog option pane
    JOptionPane dialog;

    // list model
    File fileToSave;
    DefaultListModel<String> listModel = new DefaultListModel<>();
    JList<String> list;
    JScrollPane scrollPane;
    ArrayList<Person> filePersons = new ArrayList<Person>();
    Person[] filePersonsArray = new Person[30];
    int arrayPosition = 0;

    // file chooser
    JFileChooser fileChooser;

    // panels
    JPanel mainPanel;
    JPanel leftSidePanel;
    JPanel rightSidePanel;

    // JStuff for displaying persons panel (left side)
    JButton addPerson, delete;
    JLabel firstNameLabel, lastNameLabel, dateOfBirthLabel, governmentIDLabel, studentIDLabel;
    String currentFirstName = "", currentLastName = "", currentDOB = "", currentGovernmentID = "",
            currentStudentID = "";

    // JStuff for Adding Persons Panel (right side)
    JRadioButton person, registeredPerson, OCCCPerson;
    ButtonGroup personGroup;
    JComboBox monthComboBox, dayComboBox, yearComboBox;
    JButton savePerson;
    JTextField firstName, lastName, dateOfBirth, governmentID, studentID;

    JPanel personFirstNamePanel, personLastNamePanel, personDateOfBirthPanel, personGovernmentIDPanel,
            personStudentIDPanel;

    String storedFirstName, storedLastName, storedDateOfBirth, storedGovernmentID, storedStudentID;

    public static void main(String[] args) {
        PersonGUI personGUI = new PersonGUI();
    }

    public PersonGUI() {
        super("Person GUI");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);// change this depending on code function

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (listModel.getSize() == filePersonsArray.length) {
                    exitPersonGUI();
                } else {
                    Object[] options = { "Yes", "No", "Cancel" };
                    int selection = JOptionPane.showOptionDialog(mainPanel, "Save First?", "WAIT",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                    if (selection == 0) {
                    } else if (selection == 1) {
                        exitPersonGUI();
                    } else {
                    }
                }
            }
        });

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        leftSidePanel = new JPanel();
        rightSidePanel = new JPanel();
        Font appFontSmall = new Font("Arial", Font.PLAIN, 14);

        // *****************************************
        // file menu
        // *****************************************
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        fileMenu_new = new JMenuItem("New");
        fileMenu_new.addActionListener(this);
        fileMenu_new.setMnemonic(KeyEvent.VK_N);

        fileMenu_open = new JMenuItem("Open...");
        fileMenu_open.addActionListener(this);
        fileMenu_open.setMnemonic(KeyEvent.VK_O);

        fileMenu_save = new JMenuItem("Save");
        fileMenu_save.addActionListener(this);
        fileMenu_save.setMnemonic(KeyEvent.VK_V);
        fileMenu_save.setEnabled(false);

        fileMenu_saveAs = new JMenuItem("Save As...");
        fileMenu_saveAs.addActionListener(this);
        fileMenu_saveAs.setMnemonic(KeyEvent.VK_S);
        fileMenu_saveAs.setEnabled(false);

        fileMenu_exit = new JMenuItem("Exit");
        fileMenu_exit.addActionListener(this);
        fileMenu_exit.setMnemonic(KeyEvent.VK_X);

        fileMenu.add(fileMenu_new);
        fileMenu.add(fileMenu_open);
        fileMenu.add(fileMenu_save);
        fileMenu.add(fileMenu_saveAs);
        fileMenu.addSeparator();
        fileMenu.add(fileMenu_exit);

        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);

        menuBar.add(fileMenu);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);

        // *****************************************
        // left side panel: displays list of people
        // *****************************************
        leftSidePanel.setLayout(new BorderLayout(10, 10));
        list = new JList<String>(listModel);
        scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(300, 250));

        JPanel displaySelectedPersonPanel = new JPanel();
        displaySelectedPersonPanel.setLayout(new GridLayout(0, 1, 5, 5));
        JPanel firstNamePanel = new JPanel();
        firstNamePanel.setLayout(new GridLayout(0, 2));
        JLabel firstNameDisplayLabel = new JLabel("First Name: ");
        firstNameLabel = new JLabel(currentFirstName);
        firstNameDisplayLabel.setFont(appFontSmall);
        firstNameLabel.setFont(appFontSmall);
        firstNamePanel.add(firstNameDisplayLabel);
        firstNamePanel.add(firstNameLabel);

        JPanel lastNamePanel = new JPanel();
        lastNamePanel.setLayout(new GridLayout(0, 2));
        JLabel lastNameDisplayLabel = new JLabel("Last Name: ");
        lastNameLabel = new JLabel(currentLastName);
        lastNameDisplayLabel.setFont(appFontSmall);
        lastNameLabel.setFont(appFontSmall);
        lastNamePanel.add(lastNameDisplayLabel);
        lastNamePanel.add(lastNameLabel);

        JPanel dobPanel = new JPanel();
        dobPanel.setLayout(new GridLayout(0, 2));
        JLabel dobDisplayLabel = new JLabel("Date of Birth: ");
        dateOfBirthLabel = new JLabel(currentDOB);
        dobDisplayLabel.setFont(appFontSmall);
        dateOfBirthLabel.setFont(appFontSmall);
        dobPanel.add(dobDisplayLabel);
        dobPanel.add(dateOfBirthLabel);

        JPanel govermentIDPanel = new JPanel();
        govermentIDPanel.setLayout(new GridLayout(0, 2));
        JLabel governmentIDDisplayLabel = new JLabel("Government ID: ");
        governmentIDLabel = new JLabel(currentGovernmentID);
        governmentIDDisplayLabel.setFont(appFontSmall);
        governmentIDLabel.setFont(appFontSmall);
        govermentIDPanel.add(governmentIDDisplayLabel);
        govermentIDPanel.add(governmentIDLabel);

        JPanel studentIDPanel = new JPanel();
        studentIDPanel.setLayout(new GridLayout(0, 2));
        JLabel studentIDDisplayLabel = new JLabel("Student ID: ");
        studentIDLabel = new JLabel(currentStudentID);
        studentIDDisplayLabel.setFont(appFontSmall);
        studentIDLabel.setFont(appFontSmall);
        studentIDPanel.add(studentIDDisplayLabel);
        studentIDPanel.add(studentIDLabel);

        displaySelectedPersonPanel.add(firstNamePanel);
        displaySelectedPersonPanel.add(lastNamePanel);
        displaySelectedPersonPanel.add(dobPanel);
        displaySelectedPersonPanel.add(govermentIDPanel);
        displaySelectedPersonPanel.add(studentIDPanel);

        JPanel listButtonPanel = new JPanel();
        addPerson = new JButton("Add New Person");
        addPerson.setFont(appFontSmall);
        delete = new JButton("Delete");
        delete.setFont(appFontSmall);
        delete.setEnabled(false);

        listButtonPanel.add(addPerson);
        listButtonPanel.add(delete);
        listButtonPanel.setLayout(new GridLayout(0, 1, 5, 5));

        leftSidePanel.add(scrollPane, BorderLayout.PAGE_START);
        leftSidePanel.add(displaySelectedPersonPanel, BorderLayout.CENTER);
        leftSidePanel.add(listButtonPanel, BorderLayout.PAGE_END);

        // *****************************************
        // right side panel: area in which a new person is created
        // *****************************************
        rightSidePanel.setLayout(new BorderLayout(10, 10));
        rightSidePanel.setVisible(false);

        // panels
        JPanel infoPanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        // info panel
        // person subtype
        infoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JPanel personSubClassPanel = new JPanel();
        personSubClassPanel.setLayout(new BoxLayout(personSubClassPanel, BoxLayout.Y_AXIS));
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

        personSubClassPanel.add(personSubClassLabelPanel, BorderLayout.PAGE_START);
        personSubClassPanel.add(personSubClassGroupPanel, BorderLayout.CENTER);

        // person info
        // first name
        personFirstNamePanel = new JPanel();
        JLabel personFirstNameLabel = new JLabel("First Name:");
        personFirstNameLabel.setFont(appFontSmall);
        firstName = new JTextField(20);
        firstName.setFont(appFontSmall);

        personFirstNamePanel.add(personFirstNameLabel);
        personFirstNamePanel.add(firstName);
        personFirstNamePanel.setVisible(false);

        // last name
        personLastNamePanel = new JPanel();
        JLabel personLastNameLabel = new JLabel("Last Name:");
        personLastNameLabel.setFont(appFontSmall);
        lastName = new JTextField(20);
        lastName.setFont(appFontSmall);

        personLastNamePanel.add(personLastNameLabel);
        personLastNamePanel.add(lastName);
        personLastNamePanel.setVisible(false);

        // date of birth
        String[] months = new String[12];
        String[] days = new String[31];
        String[] years = new String[124];

        createArrays(months);
        createArrays(days);
        createYearArrays(years, 2023);
        personDateOfBirthPanel = new JPanel();
        JLabel personDateOfBirthLabel = new JLabel("Date Of Birth:");
        personDateOfBirthLabel.setFont(appFontSmall);
        monthComboBox = new JComboBox<>(months);
        dayComboBox = new JComboBox<>(days);
        yearComboBox = new JComboBox<>(years);
        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new GridLayout(0, 3, 5, 5));
        comboBoxPanel.add(monthComboBox);
        comboBoxPanel.add(dayComboBox);
        comboBoxPanel.add(yearComboBox);

        personDateOfBirthPanel.add(personDateOfBirthLabel);
        personDateOfBirthPanel.add(comboBoxPanel);
        personDateOfBirthPanel.setVisible(false);

        // government ID
        personGovernmentIDPanel = new JPanel();
        JLabel personGovernmentIDLabel = new JLabel("Government ID:");
        personGovernmentIDLabel.setFont(appFontSmall);
        governmentID = new JTextField(20);
        governmentID.setFont(appFontSmall);

        personGovernmentIDPanel.add(personGovernmentIDLabel);
        personGovernmentIDPanel.add(governmentID);
        personGovernmentIDPanel.setVisible(false);

        // Student ID
        personStudentIDPanel = new JPanel();
        JLabel personStudentIDLabel = new JLabel("Student ID:");
        personStudentIDLabel.setFont(appFontSmall);
        studentID = new JTextField(20);
        studentID.setFont(appFontSmall);

        personStudentIDPanel.add(personStudentIDLabel);
        personStudentIDPanel.add(studentID);
        personStudentIDPanel.setVisible(false);

        infoPanel.setLayout(new GridLayout(0, 1));
        infoPanel.add(personSubClassPanel);
        infoPanel.add(personFirstNamePanel);
        infoPanel.add(personLastNamePanel);
        infoPanel.add(personDateOfBirthPanel);
        infoPanel.add(personGovernmentIDPanel);
        infoPanel.add(personStudentIDPanel);

        savePerson = new JButton("Finish Adding Person");
        savePerson.setFont(appFontSmall);

        buttonPanel.add(savePerson);

        rightSidePanel.add(infoPanel, BorderLayout.CENTER);
        rightSidePanel.add(buttonPanel, BorderLayout.PAGE_END);
        rightSidePanel.setAlignmentX(CENTER_ALIGNMENT);

        list.addListSelectionListener(lsl);
        savePerson.addActionListener(this);
        addPerson.addActionListener(this);
        delete.addActionListener(this);
        person.addActionListener(this);
        registeredPerson.addActionListener(this);
        OCCCPerson.addActionListener(this);
        firstName.addActionListener(this);
        lastName.addActionListener(this);
        governmentID.addActionListener(this);
        studentID.addActionListener(this);

        mainPanel.add(leftSidePanel, BorderLayout.WEST);
        mainPanel.add(new JSeparator(JSeparator.VERTICAL));
        mainPanel.add(rightSidePanel, BorderLayout.EAST);
        // mainPanel.setVisible(false);

        this.add(mainPanel);

        setVisible(true);
    }

    ListSelectionListener lsl = new ListSelectionListener() {
        public void valueChanged(ListSelectionEvent e) {
            fileMenu_save.setEnabled(true);
            fileMenu_saveAs.setEnabled(true);
            if (e.getValueIsAdjusting() == false) {
                if (list.getSelectedIndex() == -1) {
                    delete.setEnabled(false);
                } else {
                    fileMenu_save.setEnabled(true);
                    fileMenu_saveAs.setEnabled(true);
                    delete.setEnabled(true);
                    Object personObject = filePersonsArray[list.getSelectedIndex()];
                    if (personObject != null) {
                        if (personObject.getClass().equals(OCCCPerson.class)) {
                            OCCCPerson occcPerson = (OCCCPerson) personObject;
                            firstNameLabel.setText(occcPerson.getFirstName());
                            lastNameLabel.setText(occcPerson.getLastName());
                            dateOfBirthLabel.setText("1/1/2023");
                            governmentIDLabel.setText(occcPerson.getGovernmentID());
                            studentIDLabel.setText(occcPerson.getStudentID());
                        } else if (personObject.getClass().equals(RegisteredPerson.class)) {
                            RegisteredPerson registeredPerson = (RegisteredPerson) personObject;
                            firstNameLabel.setText(registeredPerson.getFirstName());
                            lastNameLabel.setText(registeredPerson.getLastName());
                            dateOfBirthLabel.setText("1/1/2023");
                            governmentIDLabel.setText(registeredPerson.getGovernmentID());
                            studentIDLabel.setText("N/A");
                        } else if (personObject.getClass().equals(Person.class)) {
                            Person person = (Person) personObject;
                            firstNameLabel.setText(person.getFirstName());
                            lastNameLabel.setText(person.getLastName());
                            dateOfBirthLabel.setText("1/1/2023");
                            governmentIDLabel.setText("N/A");
                            studentIDLabel.setText("N/A");
                        }
                    }

                }
            }
        }
    };

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fileMenu_new) {
            Object[] options = { "Yes", "No", "Cancel" };
            int selection = JOptionPane.showOptionDialog(mainPanel, "Save First?", "WAIT",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (selection == 0) {
            } else if (selection == 1) {
            } else {
            }

        }
        if (e.getSource() == fileMenu_open) {
            // open dialog
            fileChooser = new JFileChooser();
            int returnVal = fileChooser.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                fileToSave = fileChooser.getSelectedFile();
                try {
                    listModel.removeAllElements();
                    filePersonsArray = new Person[30];
                    FileInputStream intialFileInput = new FileInputStream(fileToSave);
                    ObjectInputStream initialObjectInput = new ObjectInputStream(intialFileInput);
                    Object personObject;

                    boolean fileEnd = true;

                    while (fileEnd) {

                        try {
                            personObject = initialObjectInput.readObject();
                            if (personObject.getClass().equals(OCCCPerson.class)) {
                                filePersons.add((OCCCPerson) personObject);
                                filePersonsArray[arrayPosition] = (OCCCPerson) personObject;
                                arrayPosition++;
                            } else if (personObject.getClass().equals(RegisteredPerson.class)) {
                                filePersons.add((RegisteredPerson) personObject);
                                filePersonsArray[arrayPosition] = (RegisteredPerson) personObject;
                                arrayPosition++;
                            } else if (personObject.getClass().equals(Person.class)) {
                                filePersons.add((Person) personObject);
                                filePersonsArray[arrayPosition] = (Person) personObject;
                                arrayPosition++;
                            } else {
                                fileEnd = false;
                                break;
                            }
                        } catch (EOFException eofe) {
                            fileEnd = false;
                        }
                    }
                    for (int j = 0; j < arrayPosition; j++) {
                        listModel
                                .addElement(filePersons.get(j).getFirstName() + " " + filePersons.get(j).getLastName());
                    }

                } catch (Exception fe) {
                    JOptionPane.showMessageDialog(mainPanel, "There was an issue opening the file.", "Unknown Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if (e.getSource() == fileMenu_save) {
            try {
                try {
                    FileOutputStream fout = new FileOutputStream(fileToSave);
                    ObjectOutputStream oout = new ObjectOutputStream(fout);

                    for (int i = 0; i < listModel.getSize(); i++) {
                        oout.writeObject(filePersonsArray[i]);
                    }

                    oout.flush();
                    oout.close();
                } catch (IOException ioe) {
                    System.out.println(ioe.toString());
                }
            } catch (Exception fe) {
                JOptionPane.showMessageDialog(mainPanel, "There was an issue saving the file.", "Unknown Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        }
        if (e.getSource() == fileMenu_saveAs) {
            fileChooser = new JFileChooser();
            int returnVal = fileChooser.showSaveDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    fileToSave = fileChooser.getSelectedFile();
                    try {
                        FileOutputStream fout = new FileOutputStream(fileToSave);
                        ObjectOutputStream oout = new ObjectOutputStream(fout);

                        for (int i = 0; i < listModel.getSize(); i++) {
                            oout.writeObject(filePersonsArray[i]);
                        }

                        oout.flush();
                        oout.close();
                    } catch (IOException ioe) {
                        System.out.println(ioe.toString());
                    }
                } catch (Exception fe) {
                    JOptionPane.showMessageDialog(mainPanel, "There was an issue saving the file.", "Unknown Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if (e.getSource() == fileMenu_exit) {
            exitPersonGUI();
        }

        if (e.getSource() == addPerson) {
            rightSidePanel.setVisible(true);
        }

        if (e.getSource() == delete) {
            int index = list.getSelectedIndex();
            if (index == -1) {
                JOptionPane.showMessageDialog(mainPanel, "Nothing was selected to be deleted.", "Warning",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                listModel.remove(index);
                filePersons.remove(index);
                arrayPosition--;
                filePersonsArray = new Person[30];
                for (int j = 0; j < arrayPosition; j++) {
                    filePersonsArray[j] = filePersons.get(j);
                }
            }
        }

        if (e.getSource() == savePerson) {
            if (person.isSelected() || registeredPerson.isSelected() || OCCCPerson.isSelected()) {
                if (person.isSelected()) {
                    if (firstName.getText().isEmpty() || lastName.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(mainPanel, "There were one or more fields empty.",
                                "Warning",
                                JOptionPane.WARNING_MESSAGE);
                    } else {
                        try {
                            OCCCDate date = new OCCCDate(Integer.parseInt(dayComboBox.getSelectedItem().toString()),
                                    Integer.parseInt(monthComboBox.getSelectedItem().toString()),
                                    Integer.parseInt(yearComboBox.getSelectedItem().toString()));

                            Person person = new Person(firstName.getText(), lastName.getText());
                            filePersons.add(person);
                            filePersonsArray[arrayPosition] = person;
                            arrayPosition++;
                            listModel.addElement(person.getFirstName() + " " + person.getLastName());
                        } catch (InvalidOCCCDateException ocde) {
                            System.out.println(ocde.toString());
                            JOptionPane.showMessageDialog(mainPanel, "There was an error with the date selected.",
                                    "Warning",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    }

                }
                if (registeredPerson.isSelected()) {
                    if (firstName.getText().isEmpty() || lastName.getText().isEmpty()
                            || governmentID.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(mainPanel, "There were one or more fields empty.",
                                "Warning",
                                JOptionPane.WARNING_MESSAGE);
                    } else {
                        try {
                            OCCCDate date = new OCCCDate(Integer.parseInt(dayComboBox.getSelectedItem().toString()),
                                    Integer.parseInt(monthComboBox.getSelectedItem().toString()),
                                    Integer.parseInt(yearComboBox.getSelectedItem().toString()));

                            RegisteredPerson person = new RegisteredPerson(firstName.getText(), lastName.getText(),
                                    governmentID.getText());
                            filePersons.add(person);
                            filePersonsArray[arrayPosition] = person;
                            arrayPosition++;
                            listModel.addElement(person.getFirstName() + " " + person.getLastName());
                        } catch (InvalidOCCCDateException ocde) {
                            System.out.println(ocde.toString());
                            JOptionPane.showMessageDialog(mainPanel, "There was an error with the date selected.",
                                    "Warning",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
                if (OCCCPerson.isSelected()) {
                    if (firstName.getText().isEmpty() || lastName.getText().isEmpty()
                            || governmentID.getText().isEmpty() || studentID.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(mainPanel, "There were one or more fields empty.",
                                "Warning",
                                JOptionPane.WARNING_MESSAGE);
                    } else {
                        try {
                            OCCCDate date = new OCCCDate(Integer.parseInt(dayComboBox.getSelectedItem().toString()),
                                    Integer.parseInt(monthComboBox.getSelectedItem().toString()),
                                    Integer.parseInt(yearComboBox.getSelectedItem().toString()));

                            OCCCPerson person = new OCCCPerson(firstName.getText(), lastName.getText(),
                                    governmentID.getText(), studentID.getText());
                            filePersons.add(person);
                            filePersonsArray[arrayPosition] = person;
                            arrayPosition++;
                            listModel.addElement(person.getFirstName() + " " + person.getLastName());

                        } catch (InvalidOCCCDateException ocde) {
                            System.out.println(ocde.toString());
                            JOptionPane.showMessageDialog(mainPanel, "There was an error with the date selected.",
                                    "Warning",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            }
            person.setSelected(false);
            registeredPerson.setSelected(false);
            OCCCPerson.setSelected(false);
            rightSidePanel.setVisible(false);
            firstName.setText("");
            lastName.setText("");
            governmentID.setText("");
            studentID.setText("");
            fileMenu_save.setEnabled(true);
            fileMenu_saveAs.setEnabled(true);
        }

        if (person.isSelected() || registeredPerson.isSelected() || OCCCPerson.isSelected()) {
            fileMenu_save.setEnabled(false);
            fileMenu_saveAs.setEnabled(false);
            if (person.isSelected()) {
                personFirstNamePanel.setVisible(true);
                personLastNamePanel.setVisible(true);
                personDateOfBirthPanel.setVisible(true);
                personGovernmentIDPanel.setVisible(false);
                personStudentIDPanel.setVisible(false);
            }
            if (registeredPerson.isSelected()) {
                personFirstNamePanel.setVisible(true);
                personLastNamePanel.setVisible(true);
                personDateOfBirthPanel.setVisible(true);
                personGovernmentIDPanel.setVisible(true);
                personStudentIDPanel.setVisible(false);
            }
            if (OCCCPerson.isSelected()) {
                personFirstNamePanel.setVisible(true);
                personLastNamePanel.setVisible(true);
                personDateOfBirthPanel.setVisible(true);
                personGovernmentIDPanel.setVisible(true);
                personStudentIDPanel.setVisible(true);
            }
        }
    }

    public void createArrays(String[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.toString(i + 1);
        }
    }

    public void createYearArrays(String[] array, int startPos) {
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.toString(startPos);
            startPos--;
        }
    }

    public void exitPersonGUI() {
        // exit function handle something here probably
        dispose();
        System.exit(0);
    }
}