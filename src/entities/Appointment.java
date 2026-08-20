package entities;

import utils.HelperUtils;
import java.time.LocalDate;
import java.time.LocalTime;
public class Appointment {
    private String appointmentId;
    private String patientId;
    private String doctorId;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String status;
    private String reason;
    private boolean isFollowUp;
    public Appointment(String appointmentId, String patientId,
                       String doctorId, LocalDate appointmentDate,
                       LocalTime appointmentTime, String status,
                       String reason, boolean isFollowUp) {

        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
        this.reason = reason;
        this.isFollowUp = isFollowUp;
    }
    //Getter

    public String getAppointmentId() {
        return appointmentId;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public String getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }

    public boolean isFollowUp() {
        return isFollowUp;
    }
    //Setter

    public void setAppointmentId(String appointmentId) {
        if (HelperUtils.isEmpty(appointmentId)) {
            IO.println("Appointment ID cannot be empty");
            return;
        }

        this.appointmentId = appointmentId;
    }

    public void setPatientId(String patientId) {
        if (HelperUtils.isEmpty(patientId)) {
            IO.println("Patient ID cannot be empty");
            return;
        }

        this.patientId = patientId;
    }

    public void setDoctorId(String doctorId) {
        if (HelperUtils.isEmpty(doctorId)) {
            IO.println("Doctor ID cannot be empty");
            return;
        }

        this.doctorId = doctorId;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        if (appointmentDate == null) {
            IO.println("Appointment date cannot be empty");
            return;
        }

        this.appointmentDate = appointmentDate;
    }

    public void setStatus(String status) {

        if (!HelperUtils.isOneOf(status,
                new String[]{"Scheduled", "Cancelled", "Completed"}))
                 {
            IO.println("Invalid appointment status");
            return;
        }

        this.status = status;
    }

    public void setReason(String reason) {
        if (HelperUtils.isEmpty(reason)) {
            IO.println("Reason cannot be empty");
            return;
        }
        this.reason = reason;
    }

    public void setFollowUp(boolean followUp) {
        isFollowUp = followUp;
    }
    public void displayInfo() {
        IO.println("Appointment ID: " + appointmentId);
        IO.println("Patient ID: " + patientId);
        IO.println("Doctor ID: " + doctorId);
        IO.println("Appointment Date: " + appointmentDate);
        IO.println("Appointment Time: " + appointmentTime);
        IO.println("Status: " + status);
        IO.println("Reason: " + reason);
        IO.println("Follow Up: " + isFollowUp);
    }
    public void cancel() {
        status = "Cancelled";
    }

    public void complete() {
        status = "Completed";
    }
    public void reschedule(LocalDate newDate, LocalTime newTime) {
        appointmentDate = newDate;
        appointmentTime = newTime;
        status = "Rescheduled";
    }
    public boolean isPast(LocalDate date) {
        return appointmentDate.isBefore(date);
    }
    public void addNotes(String notes) {
        reason = reason + " " + notes;
    }

    public void addNotes(String notes, String author) {
        reason = reason + " " + notes + " - " + author;
    }
}

