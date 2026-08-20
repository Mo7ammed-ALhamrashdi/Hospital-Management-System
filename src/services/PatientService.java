package services;

import entities.Patient;
import interfaces.Manageable;
import interfaces.Searchable;

public class PatientService implements Manageable, Searchable {

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
