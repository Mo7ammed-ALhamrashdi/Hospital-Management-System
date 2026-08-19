package entities;

import java.time.LocalDate;

public class MedicalRecord {

    private String recordId;
    private String patientId;
    private String doctorId;
    private LocalDate visitDate;
    private String diagnosis;
    private String prescription;
    private String notes;
    private boolean isConfidential;
    public MedicalRecord(String recordId, String patientId,
                         String doctorId, LocalDate visitDate,
                         String diagnosis, String prescription,
                         String notes, boolean isConfidential) {

        this.recordId = recordId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.visitDate = visitDate;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.notes = notes;
        this.isConfidential = isConfidential;
    }
// Getter
    public String getRecordId() {
        return recordId;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getPrescription() {
        return prescription;
    }

    public String getNotes() {
        return notes;
    }

    public boolean isConfidential() {
        return isConfidential;
    }
//Setter
public void setRecordId(String recordId) {
    if (recordId == null || recordId.isEmpty()) {
        IO.println("Record ID cannot be empty");
        return;
    }

    this.recordId = recordId;
}

    public void setPatientId(String patientId) {
        if (patientId == null || patientId.isEmpty()) {
            IO.println("Patient ID cannot be empty");
            return;
        }

        this.patientId = patientId;
    }

    public void setDoctorId(String doctorId) {
        if (doctorId == null || doctorId.isEmpty()) {
            IO.println("Doctor ID cannot be empty");
            return;
        }

        this.doctorId = doctorId;
    }

    public void setVisitDate(LocalDate visitDate) {
        if (visitDate == null) {
            IO.println("Visit date cannot be empty");
            return;
        }

        this.visitDate = visitDate;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setConfidential(boolean confidential) {
        isConfidential = confidential;
    }
    public void displayInfo() {
        IO.println("Record ID: " + recordId);
        IO.println("Patient ID: " + patientId);
        IO.println("Doctor ID: " + doctorId);
        IO.println("Visit Date: " + visitDate);
        IO.println("Diagnosis: " + diagnosis);
        IO.println("Prescription: " + prescription);
        IO.println("Notes: " + notes);
        IO.println("Confidential: " + isConfidential);
    }
    public void appendNote(String extraNote) {
        notes = notes + "       " + extraNote;
    }
    public void markConfidential() {
        isConfidential = true;
    }

}
