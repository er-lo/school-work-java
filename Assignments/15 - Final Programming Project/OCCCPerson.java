//Erick Lopez
//Advanced Java
//OCCC Fall 2023
//Person / RegisteredPerson / OCCCPerson 

public class OCCCPerson extends RegisteredPerson {
    private String studentID;

    public OCCCPerson(String firstName, String lastName, String govID, String studentID) {
        super(firstName, lastName, govID);
        this.studentID = studentID;
    }

    public OCCCPerson(RegisteredPerson p, String studentID) {
        super(p);
        this.studentID = studentID;
    }

    public OCCCPerson(OCCCPerson p) {
        super(p);
        studentID = p.studentID;
    }

    public String getStudentID() {
        return studentID;
    }

    public boolean equals(OCCCPerson p) {
        return super.getFirstName().equalsIgnoreCase(p.getFirstName())
                && super.getLastName().equalsIgnoreCase(p.getLastName())
                && super.getGovernmentID().equals(p.getGovernmentID()) && studentID.equalsIgnoreCase(p.studentID);
    }

    public boolean equals(RegisteredPerson p) {
        return super.getFirstName().equalsIgnoreCase(p.getFirstName())
                && super.getLastName().equalsIgnoreCase(p.getLastName())
                && super.getGovernmentID().equals(p.getGovernmentID());
    }

    public boolean equals(Person p) {
        return super.getFirstName().equalsIgnoreCase(p.getFirstName())
                && super.getLastName().equalsIgnoreCase(p.getLastName());
    }

    @Override
    public String toString() {
        return super.toString() + " [" + studentID + "]";
    }
}
