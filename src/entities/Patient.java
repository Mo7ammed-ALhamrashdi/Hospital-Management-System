package entities;

import java.time.LocalDate;
import java.util.ArrayList;

public class Patient {
    private String bloodGroup;
    private String emergencyContact;
    private LocalDate registrationDate;
    private ArrayList allergies;
    private ArrayList recordIds;
    private double outstandingBalance;
    private boolean isInsured;

    public Patient(String bloodGroup, String emergencyContact,
                   LocalDate registrationDate, ArrayList allergies,
                   ArrayList recordIds, double outstandingBalance,
                   boolean isInsured) {
        this.bloodGroup = bloodGroup;
        this.emergencyContact = emergencyContact;
        this.registrationDate = registrationDate;
        this.allergies = allergies;
        this.recordIds = recordIds;
        this.outstandingBalance = outstandingBalance;
        this.isInsured = isInsured;

    }
}

