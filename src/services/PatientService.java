package services;

import entities.Patient;

public class PatientService {

    public void addPatient(String id, String name) {
        System.out.println("Patient added: " + id + " " + name);
    }

    public void addPatient(String id, String name, String bloodGroup) {
        System.out.println("Patient added: " + id + " " + name);
        System.out.println("Blood Group: " + bloodGroup);
    }

    public void addPatient(Patient patient) {
        System.out.println("Patient added: " + patient.getFullName());
    }
}
