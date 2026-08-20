package services;

import entities.InPatient;
import entities.Patient;
import interfaces.Manageable;
import interfaces.Searchable;
import java.time.LocalDate;

public class PatientService implements Manageable, Searchable {
    private Patient[] patients = new Patient[100];
    private int count = 0;
    //addPatient
    public void addPatient(String id, String firstName, String lastName) {

        Patient patient = new Patient(
                id,
                firstName,
                lastName,
                LocalDate.now(),
                "Unknown",
                "Unknown",
                "Unknown",
                "Unknown",
                id,
                0,
                true,
                "Unknown",
                "Unknown",
                LocalDate.now(),
                0,
                false
        );

        patients[count] = patient;
        count++;
    }
    //addPatient-details+blood group
    public void addPatient(String id, String firstName, String lastName,
                           String bloodGroup) {

        Patient patient = new Patient(
                id,
                firstName,
                lastName,
                LocalDate.now(),
                "Unknown",
                "Unknown",
                "Unknown",
                "Unknown",
                id,
                0,
                true,
                bloodGroup,
                "Unknown",
                LocalDate.now(),
                0,
                false
        );

        patients[count] = patient;
        count++;
    }
//addPatient - existing Patient
public void addPatient(Patient patient) {

    patients[count] = patient;
    count++;
}

    @Override
    public void add(Object entity) {
        if (entity instanceof Patient) {
            addPatient((Patient) entity);
        }
    }

    @Override
    public void removeById(String id) {

        for (int i = 0; i < count; i++) {

            if (patients[i].getId().equals(id)) {

                for (int j = i; j < count - 1; j++) {
                    patients[j] = patients[j + 1];
                }

                patients[count - 1] = null;
                count--;

                return;
            }
        }
    }

    @Override
    public Object[] getAll() {

        Object[] result = new Object[count];

        for (int i = 0; i < count; i++) {
            result[i] = patients[i];
        }

        return result;
    }

    @Override
    public Object[] search(String keyword) {

        Patient[] result = new Patient[count];
        int resultCount = 0;

        for (int i = 0; i < count; i++) {

            if (patients[i].getFullName().contains(keyword)
                    || patients[i].getId().contains(keyword)) {

                result[resultCount] = patients[i];
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

            if (patients[i].getId().equals(id)) {
                return patients[i];
            }
        }

        return null;
    }

    public void updateContact(String id, String phone) {

        Patient patient = (Patient) searchById(id);

        if (patient != null) {
            patient.updateContact(phone);
        }
    }

    public void updateContact(String id, String phone, String email) {

        Patient patient = (Patient) searchById(id);

        if (patient != null) {
            patient.updateContact(phone, email);
        }
    }

    public Object[] listInPatients() {

        Patient[] result = new Patient[count];
        int resultCount = 0;

        for (int i = 0; i < count; i++) {

            if (patients[i] instanceof InPatient) {
                result[resultCount] = patients[i];
                resultCount++;
            }
        }

        Object[] finalResult = new Object[resultCount];

        for (int i = 0; i < resultCount; i++) {
            finalResult[i] = result[i];
        }

        return finalResult;
    }

    public double totalOutstanding() {

        double total = 0;

        for (int i = 0; i < count; i++) {
            total += patients[i].getOutstandingBalance();
        }

        return total;
    }
}

