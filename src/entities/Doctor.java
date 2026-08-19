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

    public Doctor(String id, String firstName, String lastName,
                  LocalDate dateOfBirth, String gender,
                  String phoneNumber, String email, String address,
                  String nationalId, int age, boolean activeStatus,
                  String specialization, int experienceYears,
                  double consultationFee, boolean isOnCall) {

        super(id, firstName, lastName,
                dateOfBirth, gender,
                phoneNumber, email, address,
                nationalId, age, activeStatus);

        this.specialization = specialization;
        this.experienceYears = experienceYears;
        this.consultationFee = consultationFee;
        this.isOnCall = isOnCall;

        availableSlots = new ArrayList();
        assignedPatientIds = new ArrayList();
    }
    @Override
    public void displayInfo() {
        super.displayInfo();

        IO.println("Specialization: " + specialization);
        IO.println("Experience Years: " + experienceYears);
        IO.println("Consultation Fee: " + consultationFee);
        IO.println("Available Slots: " + availableSlots);
        IO.println("Assigned Patients: " + assignedPatientIds);
        IO.println("On Call: " + isOnCall);
    }
    public void setExperienceYears(int experienceYears) {
        if (experienceYears < 0) {
            IO.println("Experience years cannot be negative");
            return;
        }
        this.experienceYears = experienceYears;
    }
    public void updateFee(double fee) {
        setConsultationFee(fee);
    }

    public void updateFee(double fee, String reason) {
        setConsultationFee(fee);

        System.out.println("Fee updated because: " + reason);
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
