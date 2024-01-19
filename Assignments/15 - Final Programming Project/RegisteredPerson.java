//Erick Lopez
//Advanced Java
//OCCC Fall 2023
//Person / RegisteredPerson / OCCCPerson 

public class RegisteredPerson extends Person {
    private String govID;

    public RegisteredPerson(String firstName, String lastName, String govID) {
        super(firstName, lastName);
        this.govID = govID;
    }

    public RegisteredPerson(Person p, String govID) {
        super(p);
        this.govID = govID;
    }

    public RegisteredPerson(RegisteredPerson p) {
        super(p);
        govID = p.govID;
    }

    public String getGovernmentID() {
        return govID;
    }

    public boolean equals(RegisteredPerson p) {
        return super.getFirstName().equalsIgnoreCase(p.getFirstName())
                && super.getLastName().equalsIgnoreCase(p.getLastName()) && govID.equalsIgnoreCase(p.govID);
    }

    public boolean equals(Person p) {
        return super.getFirstName().equalsIgnoreCase(p.getFirstName())
                && super.getLastName().equalsIgnoreCase(p.getLastName());
    }

    @Override
    public String toString() {
        return super.toString() + " [" + govID + "]";
    }
}