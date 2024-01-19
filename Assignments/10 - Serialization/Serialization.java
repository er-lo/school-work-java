//Erick Lopez
//OCCC Fall 2023
//Advanced Java 
//Serialization program
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Serialization{
    public static void main(String[] args) {
        System.out.println("Please enter a file name to load: (or N for no file)");
        Scanner initialFileUserInput = new Scanner(System.in);
        String initialFileName = initialFileUserInput.next();
        Scanner personUserInput = new Scanner(System.in);

        if(initialFileName.equals("N")) {
            System.out.println("No file selected. Creating 5 Persons...");
            Person [] persons = new Person[5];

            for(int i=0; i<persons.length; i++){
                System.out.println("Choose what type of Person to create: (Person, RegisteredPerson, OCCCPerson)");
                String personPickerString = personUserInput.nextLine();

                while (!personPickerString.equals("Person") && !personPickerString.equals("RegisteredPerson") && !personPickerString.equals("OCCCPerson")) {
                    System.out.println("Didn't quite understand that... Please choose again: (Person, RegisteredPerson, OCCCPerson)");
                    personPickerString = personUserInput.nextLine();
                }
                if(personPickerString.equals("Person")){
                    System.out.println("Please enter a first name.");
                    String firstName = personUserInput.nextLine();
                    System.out.println("Please enter a last name.");
                    String lastName = personUserInput.nextLine();
                    persons[i] = new Person(firstName, lastName);
                    System.out.println("Person: " + persons[i].toString());

                } else if(personPickerString.equals("RegisteredPerson")){
                    System.out.println("Please enter a first name.");
                    String firstName = personUserInput.nextLine();
                    System.out.println("Please enter a last name.");
                    String lastName = personUserInput.nextLine();
                    System.out.println("Please enter a government ID.");
                    String governmentID = personUserInput.nextLine();
                    persons[i] = new RegisteredPerson(firstName, lastName, governmentID);
                    System.out.println("RegisteredPerson: " + persons[i].toString());

                } else if(personPickerString.equals("OCCCPerson")){
                    System.out.println("Please enter a first name.");
                    String firstName = personUserInput.nextLine();
                    System.out.println("Please enter a last name.");
                    String lastName = personUserInput.nextLine();
                    System.out.println("Please enter a government ID.");
                    String governmentID = personUserInput.nextLine();
                    System.out.println("Please enter a student ID.");
                    String studentID = personUserInput.nextLine();
                    persons[i] = new OCCCPerson(firstName, lastName, governmentID, studentID);
                    System.out.println("OCCCPerson: " + persons[i].toString());

                } else{
                    System.out.println("This shouldn't be possible..");
                }
            }

            System.out.println("Please enter a file name to save the persons to: ");
            Scanner saveFileUserInput = new Scanner(System.in);
            String saveFileName = saveFileUserInput.next();
        
            System.out.println("Saving objects to " + saveFileName + "...");

            try{
                FileOutputStream   fout = new FileOutputStream(saveFileName);
                ObjectOutputStream oout = new ObjectOutputStream(fout);

                for(int i = 0; i < persons.length; ++i){
                    oout.writeObject(persons[i]);
                }

                oout.close();
            }
            catch(IOException e){
                System.out.println(e.toString());
            }

            System.out.println("Now let's read our save file back in...");

            Person [] newSaveFilePersons = new Person[5];

            try{
                FileInputStream   saveFileInputStream = new FileInputStream(saveFileName);
                ObjectInputStream saveFileObjectInputStream = new ObjectInputStream(saveFileInputStream);
                Object saveFileObject;
                for(int i = 0; i < newSaveFilePersons.length; ++i){
                    saveFileObject = saveFileObjectInputStream.readObject();
                    if (saveFileObject.getClass().equals(OCCCPerson.class)){
                        newSaveFilePersons[i] = (OCCCPerson) saveFileObject;
                        System.out.println("OCCCPerson: " + newSaveFilePersons[i].toString()); 
                    }
                    else if (saveFileObject.getClass().equals(RegisteredPerson.class)){
                        newSaveFilePersons[i] = (RegisteredPerson) saveFileObject;
                        System.out.println("RegisteredPerson: " + newSaveFilePersons[i].toString()); 
                    } else{
                        newSaveFilePersons[i] = (Person) saveFileObject;
                        System.out.println("Person: " + newSaveFilePersons[i].toString()); 
                    }
                }

                saveFileInputStream.close();
            } catch(Exception e){
                System.out.println(e.toString());
            }

            System.out.println("Now let's create three more persons. One of each kind.");
            Person [] newPersons = new Person[8];

            for (int i=0; i<persons.length; i++) {
                newPersons[i] = persons[i];
            }
            
            System.out.println("Let's start off with a regular person.");
            System.out.println("Please enter a first name.");
            String personFirstName = personUserInput.nextLine();
            System.out.println("Please enter a last name.");
            String personLastName = personUserInput.nextLine();
            newPersons[5] = new Person(personFirstName, personLastName);
            System.out.println("Person: " + newPersons[5].toString());

            System.out.println("Next a registered person.");
            System.out.println("Please enter a first name.");
            String registeredPersonFirstName = personUserInput.nextLine();
            System.out.println("Please enter a last name.");
            String registeredPersonLastName = personUserInput.nextLine();
            System.out.println("Please enter a government ID.");
            String registeredPersonGovernmentID = personUserInput.nextLine();
            newPersons[6] = new RegisteredPerson(registeredPersonFirstName, registeredPersonLastName, registeredPersonGovernmentID);
            System.out.println("Person: " + newPersons[6].toString());

            System.out.println("Next an OCCC person.");
            System.out.println("Please enter a first name.");
            String OCCCPersonFirstName = personUserInput.nextLine();
            System.out.println("Please enter a last name.");
            String OCCCPersonLastName = personUserInput.nextLine();
            System.out.println("Please enter a government ID.");
            String OCCCPersonGovernmentID = personUserInput.nextLine();
            System.out.println("Please enter a student ID.");
            String OCCCPersonStudentID = personUserInput.nextLine();
            newPersons[7] = new OCCCPerson(OCCCPersonFirstName, OCCCPersonLastName, OCCCPersonGovernmentID, OCCCPersonStudentID);
            System.out.println("Person: " + newPersons[7].toString());


            System.out.println("Saving new objects to " + saveFileName + "...");

            try{
                FileOutputStream   fout = new FileOutputStream(saveFileName);
                ObjectOutputStream oout = new ObjectOutputStream(fout);

                for(int i = 0; i < newPersons.length; ++i){
                    oout.writeObject(newPersons[i]);
                }

                oout.close();
            }
            catch(IOException e){
                System.out.println(e.toString());
            }

            System.out.println("Now let's read our save file back in... again...");

            Person [] newSaveFilePersonsAgain = new Person[8];

            try{
                FileInputStream   saveFileInputStream = new FileInputStream(saveFileName);
                ObjectInputStream saveFileObjectInputStream = new ObjectInputStream(saveFileInputStream);
                Object saveFileObject;
                for(int i = 0; i < newSaveFilePersonsAgain.length; ++i){
                    saveFileObject = saveFileObjectInputStream.readObject();
                    if (saveFileObject.getClass().equals(OCCCPerson.class)){
                        newSaveFilePersonsAgain[i] = (OCCCPerson) saveFileObject;
                        System.out.println("OCCCPerson: " + newSaveFilePersonsAgain[i].toString());
                    }
                    else if (saveFileObject.getClass().equals(RegisteredPerson.class)){
                        newSaveFilePersonsAgain[i] = (RegisteredPerson) saveFileObject;
                        System.out.println("RegisteredPerson: " + newSaveFilePersonsAgain[i].toString());
                    } else{
                        newSaveFilePersonsAgain[i] = (Person) saveFileObject;
                        System.out.println("Person: " + newSaveFilePersonsAgain[i].toString());
                    }
                }

                saveFileInputStream.close();
            } catch(Exception e){
                System.out.println(e.toString());
            }

        } else {
            Person [] filePersons = new Person[8];
            try{
                FileInputStream   intialFileInput = new FileInputStream(initialFileName);
                ObjectInputStream initialObjectInput = new ObjectInputStream(intialFileInput);
                Object personObject;
                for(int i = 0; i < filePersons.length; ++i){
                    personObject = initialObjectInput.readObject();
                    if (personObject.getClass().equals(OCCCPerson.class)){
                        filePersons[i] = (OCCCPerson) personObject;
                        System.out.println("OCCCPerson: " + filePersons[i].toString());
                    } else if(personObject.getClass().equals(RegisteredPerson.class)){
                        filePersons[i] = (RegisteredPerson) personObject;
                        System.out.println("RegisteredPerson: " + filePersons[i].toString());
                    } else {
                        filePersons[i] = (Person) personObject;
                        System.out.println("Person: " + filePersons[i].toString());
                    }
                }

            } catch(Exception e){
                System.out.println("INPUT ERROR");
                System.out.println(e.toString());
            }
    

        }

        
    }
    
}
