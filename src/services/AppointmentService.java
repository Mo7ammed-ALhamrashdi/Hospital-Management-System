package services;

import entities.Appointment;
import entities.Doctor;
import entities.Patient;
import interfaces.Manageable;
import interfaces.Searchable;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentService implements Manageable, Searchable {

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

    @Override
    public void add(Object entity) {

    }

    @Override
    public void removeById(String id) {

    }

    @Override
    public Object[] getAll() {
        return new Object[0];
    }

    @Override
    public Object[] search(String keyword) {
        return new Object[0];
    }

    @Override
    public Object searchById(String id) {
        return null;
    }
}