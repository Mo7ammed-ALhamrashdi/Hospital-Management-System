package services;

import entities.Appointment;
import entities.Doctor;
import entities.Patient;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentService {

    public void schedule(String patientId,
                         String doctorId,
                         LocalDate date) {

        System.out.println("Appointment scheduled");
    }

    public void schedule(String patientId,
                         String doctorId,
                         LocalDate date,
                         LocalTime time) {

        System.out.println("Appointment scheduled");
    }

    public void schedule(Patient patient,
                         Doctor doctor,
                         LocalDate date,
                         String reason) {

        System.out.println("Appointment scheduled");
    }
}