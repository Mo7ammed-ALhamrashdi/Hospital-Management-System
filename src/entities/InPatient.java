package entities;

import java.time.LocalDate;

public class InPatient extends Patient {
    private LocalDate admissionDate;
    private String roomNumber;
    private double dailyCharges;
    private int daysAdmitted;
    public InPatient(String id, String firstName, String lastName,
                     LocalDate dateOfBirth, String gender,
                     String phoneNumber, String email, String address,
                     String nationalId, int age, boolean activeStatus,
                     String bloodGroup, String emergencyContact,
                     LocalDate registrationDate,
                     double outstandingBalance, boolean isInsured,
                     LocalDate admissionDate, String roomNumber,
                     double dailyCharges, int daysAdmitted) {

        // Person → Patient → InPatient
        super(id, firstName, lastName, dateOfBirth, gender,
                phoneNumber, email, address, nationalId,
                age, activeStatus,
                bloodGroup, emergencyContact, registrationDate,
                outstandingBalance, isInsured);

        this.admissionDate = admissionDate;
        this.roomNumber = roomNumber;
        this.dailyCharges = dailyCharges;
        this.daysAdmitted = daysAdmitted;
    }
    @Override
    public void displayInfo() {

        super.displayInfo();

        IO.println("Admission Date: " + admissionDate);
        IO.println("Room Number: " + roomNumber);
        IO.println("Daily Charges: " + dailyCharges);
        IO.println("Days Admitted: " + daysAdmitted);
    }

    public void admit(LocalDate date) {
        admissionDate = date;
    }

    public void discharge() {
        admissionDate = null;
        daysAdmitted = 0;
    }

    public double totalRoomCost() {
        return dailyCharges * daysAdmitted;
    }
}
