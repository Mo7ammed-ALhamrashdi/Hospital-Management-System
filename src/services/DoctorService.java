package services;

import entities.Doctor;
import entities.Surgeon;
import interfaces.Manageable;
import interfaces.Searchable;

public class DoctorService implements Manageable, Searchable {
    private Doctor[] doctors = new Doctor[100];
    private int count = 0;
    //add
    public void add(Doctor doctor) {
        doctors[count] = doctor;
        count++;
    }
    @Override
    public void add(Object entity) {

        if (entity instanceof Doctor) {
            add((Doctor) entity);
        }
    }

    @Override
    public void removeById(String id) {

        for (int i = 0; i < count; i++) {

            if (doctors[i].getId().equals(id)) {

                for (int j = i; j < count - 1; j++) {
                    doctors[j] = doctors[j + 1];
                }

                doctors[count - 1] = null;
                count--;

                return;
            }
        }
    }

    @Override
    public Object[] getAll() {

        Object[] result = new Object[count];

        for (int i = 0; i < count; i++) {
            result[i] = doctors[i];
        }

        return result;
    }

    @Override
    public Object[] search(String keyword) {

        Doctor[] result = new Doctor[count];
        int resultCount = 0;

        for (int i = 0; i < count; i++) {

            if (doctors[i].getFullName().contains(keyword)
                    || doctors[i].getId().contains(keyword)
                    || doctors[i].getSpecialization().contains(keyword)) {

                result[resultCount] = doctors[i];
                resultCount++;
            }
        }

        Object[] finalResult = new Object[resultCount];

        for (int i = 0; i < resultCount; i++) {
            finalResult[i] = result[i];
        }

        return finalResult;
    }

    @Override
    public Object searchById(String id) {

        for (int i = 0; i < count; i++) {

            if (doctors[i].getId().equals(id)) {
                return doctors[i];
            }
        }

        return null;
    }

    // addSurgeon
    public void addSurgeon(Surgeon surgeon) {

        doctors[count] = surgeon;
        count++;
    }

    // assignPatient
    public void assignPatient(String doctorId, String patientId) {

        Doctor doctor = (Doctor) searchById(doctorId);

        if (doctor != null) {
            doctor.assignPatient(patientId);
        }
    }

    // listBySpecialization
    public Object[] listBySpecialization(String specialization) {

        Doctor[] result = new Doctor[count];
        int resultCount = 0;

        for (int i = 0; i < count; i++) {

            if (doctors[i].getSpecialization().equals(specialization)) {

                result[resultCount] = doctors[i];
                resultCount++;
            }
        }

        Object[] finalResult = new Object[resultCount];

        for (int i = 0; i < resultCount; i++) {
            finalResult[i] = result[i];
        }

        return finalResult;
    }

    // availableDoctors
    public Object[] availableDoctors(String slot) {

        Doctor[] result = new Doctor[count];
        int resultCount = 0;

        for (int i = 0; i < count; i++) {

            if (doctors[i].hasSlot(slot)) {

                result[resultCount] = doctors[i];
                resultCount++;
            }
        }

        Object[] finalResult = new Object[resultCount];

        for (int i = 0; i < resultCount; i++) {
            finalResult[i] = result[i];
        }

        return finalResult;
    }
}