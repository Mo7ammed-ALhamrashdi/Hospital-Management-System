package entities;
import java.time.LocalDate;
import java.util.ArrayList;

public class Doctor extends Person {
    private String specialization;
    private int experienceYears;
    private double consultationFee;
    private ArrayList availableSlots;
    private ArrayList assignedPatientIds;
    private boolean isOnCall;

    public Doctor(String id, String firstName,
                  String lastName, LocalDate dateOfBirth,
                  String gender, String phoneNumber,
                  String email, String address,
                  String nationalId, int age, boolean activeStatus,
                  String specialization, int experienceYears,
                  double consultationFee, ArrayList availableSlots,
                  ArrayList assignedPatientIds, boolean isOnCall) {
        super(id, firstName, lastName, dateOfBirth,
                gender, phoneNumber, email, address,
                nationalId, age, activeStatus);
        this.specialization = specialization;
        this.experienceYears = experienceYears;
        this.consultationFee = consultationFee;
        this.availableSlots = availableSlots;
        this.assignedPatientIds = assignedPatientIds;
        this.isOnCall = isOnCall;

    }
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Specialization: " + specialization);
        System.out.println("Experience Years: " + experienceYears);
        System.out.println("Consultation Fee: " + consultationFee);
        System.out.println("Available Slots: " + availableSlots);
        System.out.println("Assigned Patient IDs: " + assignedPatientIds);
        System.out.println("On Call: " + isOnCall);
    }
    public void setExperienceYears(int experienceYears) {
        if (experienceYears < 0) {
            IO.println("Experience years cannot be negative");
            return;
        }
        this.experienceYears = experienceYears;
    }
    public void setConsultationFee(double consultationFee) {
        if (consultationFee < 0) {
            IO.println("Fee cannot be negative");
            return;
        }
        this.consultationFee = consultationFee;
    }
    public void addSlot(String slot) {
        availableSlots.add(slot);
    }

    public void removeSlot(String slot) {
        availableSlots.remove(slot);
    }

    public boolean hasSlot(String slot) {
        return availableSlots.contains(slot);
    }
    public void assignPatient(String patientId) {
        assignedPatientIds.add(patientId);
    }

    public int getPatientLoad() {
        return assignedPatientIds.size();
    }
    public void raiseFee(double amount) {

        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        consultationFee += amount;
    }
}
