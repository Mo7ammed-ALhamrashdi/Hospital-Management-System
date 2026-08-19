package entities;

import java.time.LocalDate;
import java.util.ArrayList;

public class Nurse extends Person {

    private String departmentId;
    private String shift;
    private ArrayList assignedPatientIds;
    private int yearsOfService;

    public Nurse(String id, String firstName, String lastName,
                 LocalDate dateOfBirth, String gender,
                 String phoneNumber, String email, String address,
                 String nationalId, int age, boolean activeStatus,
                 String departmentId, String shift,
                 int yearsOfService) {

        super(id, firstName, lastName, dateOfBirth, gender,
                phoneNumber, email, address, nationalId,
                age, activeStatus);

        this.departmentId = departmentId;
        this.shift = shift;
        this.assignedPatientIds = new ArrayList();
        this.yearsOfService = yearsOfService;
    }
    @Override
    public void displayInfo() {

        super.displayInfo();

        IO.println("Department ID: " + departmentId);
        IO.println("Shift: " + shift);
        IO.println("Assigned Patient IDs: " + assignedPatientIds);
        IO.println("Years of Service: " + yearsOfService);
    }
    public void assignPatient(String patientId) {
        assignedPatientIds.add(patientId);
    }

    public void unassignPatient(String patientId) {
        assignedPatientIds.remove(patientId);
    }

}
