package entities;

import java.time.LocalDate;
import java.util.ArrayList;

public class Patient extends Person {

    private String bloodGroup;
    private String emergencyContact;
    private LocalDate registrationDate;
    private ArrayList allergies;
    private ArrayList recordIds;
    private double outstandingBalance;
    private boolean isInsured;

    public Patient(String id, String firstName, String lastName,
                   LocalDate dateOfBirth, String gender,
                   String phoneNumber, String email, String address,
                   String nationalId, int age, boolean activeStatus,
                   String bloodGroup, String emergencyContact,
                   LocalDate registrationDate,
                   double outstandingBalance, boolean isInsured) {

        super(id, firstName, lastName, dateOfBirth, gender,
                phoneNumber, email, address, nationalId,
                age, activeStatus);

        this.bloodGroup = bloodGroup;
        this.emergencyContact = emergencyContact;
        this.registrationDate = registrationDate;
        this.allergies = new ArrayList();
        this.recordIds = new ArrayList();
        this.outstandingBalance = outstandingBalance;
        this.isInsured = isInsured;
    }

    @Override
    public void displayInfo() {

        super.displayInfo();

        IO.println("Blood Group: " + bloodGroup);
        IO.println("Emergency Contact: " + emergencyContact);
        IO.println("Registration Date: " + registrationDate);
        IO.println("Allergies: " + allergies);
        IO.println("Medical Record IDs: " + recordIds);
        IO.println("Outstanding Balance: " + outstandingBalance);
        IO.println("Insured: " + isInsured);
    }

    public void addAllergy(String allergy) {
        allergies.add(allergy);
    }

    public boolean hasAllergy(String allergy) {
        return allergies.contains(allergy);
    }

    public void listAllergies() {
        for (Object allergy : allergies) {
            IO.println(allergy);
        }
    }

    public void addRecordId(String recordId) {
        recordIds.add(recordId);
    }

    public int getRecordCount() {
        return recordIds.size();
    }

    public void addToBalance(double amount) {
        outstandingBalance += amount;
    }

    public void clearBalance() {
        outstandingBalance = 0;
    }
}
