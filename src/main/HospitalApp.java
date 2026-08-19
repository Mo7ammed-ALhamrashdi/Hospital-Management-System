package main;

import entities.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class HospitalApp {

    public static void main(String[] args) {

        // =========================
        // 1. Person
        // =========================

        Person person = new Person(
                "P001",
                "Ahmed",
                "Ali",
                LocalDate.of(2000, 5, 10),
                "Male",
                "99112233",
                "ahmed@email.com",
                "Muscat",
                "N001",
                26,
                true
        );


        // =========================
        // 2. Patient
        // =========================

        Patient patient = new Patient(
                "P002",
                "Salim",
                "Said",
                LocalDate.of(1998, 3, 15),
                "Male",
                "99223344",
                "salim@email.com",
                "Muscat",
                "N002",
                28,
                true,
                "O+",
                "99334455",
                LocalDate.of(2026, 8, 1),
                100.0,
                true
        );

        patient.addAllergy("Penicillin");
        patient.addAllergy("Dust");
        patient.addRecordId("R001");
        patient.addRecordId("R002");


        // =========================
        // 3. Doctor
        // =========================

        Doctor doctor = new Doctor(
                "D001",
                "Mohammed",
                "Said",
                LocalDate.of(1980, 3, 20),
                "Male",
                "99887766",
                "doctor@email.com",
                "Muscat",
                "N003",
                46,
                true,
                "Cardiology",
                15,
                100.0,
                true
        );

        doctor.addSlot("09:00");
        doctor.addSlot("10:00");
        doctor.assignPatient("P002");


        // =========================
        // 4. Nurse
        // =========================

        Nurse nurse = new Nurse(
                "N001",
                "Fatima",
                "Ahmed",
                LocalDate.of(1990, 7, 10),
                "Female",
                "99776655",
                "nurse@email.com",
                "Muscat",
                "N004",
                36,
                true,
                "DEP01",
                "Night",
                8
        );

        nurse.assignPatient("P002");


        // =========================
        // 5. Medical Record
        // =========================

        MedicalRecord record = new MedicalRecord(
                "R001",
                "P002",
                "D001",
                LocalDate.of(2026, 8, 18),
                "Flu",
                "Medicine",
                "Patient needs rest",
                false
        );

        record.appendNote("Follow-up required.");
        record.markConfidential();


        // =========================
        // 6. Appointment
        // =========================

        Appointment appointment = new Appointment(
                "A001",
                "P002",
                "D001",
                LocalDate.of(2026, 8, 25),
                LocalTime.of(10, 0),
                "Scheduled",
                "Regular checkup",
                false
        );


        // =========================
        // 7. InPatient
        // =========================

        InPatient inPatient = new InPatient(
                "P003",
                "Khalid",
                "Hassan",
                LocalDate.of(1995, 2, 10),
                "Male",
                "99554433",
                "khalid@email.com",
                "Muscat",
                "N005",
                31,
                true,
                "A+",
                "99443322",
                LocalDate.of(2026, 8, 10),
                100.0,
                true,
                LocalDate.of(2026, 8, 15),
                "Room 101",
                50.0,
                5
        );


        // =========================
        // 8. Surgeon
        // =========================

        Surgeon surgeon = new Surgeon(
                "S001",
                "Ali",
                "Hamad",
                LocalDate.of(1978, 4, 20),
                "Male",
                "99332211",
                "surgeon@email.com",
                "Muscat",
                "N006",
                48,
                true,
                "General Surgery",
                20,
                200.0,
                true,
                30,
                true
        );

        surgeon.performSurgery();
        surgeon.scheduleSurgery(LocalDate.of(2026, 8, 28));
        surgeon.scheduleSurgery(LocalDate.of(2026, 9, 5));


        // =====================================================
        // POLYMORPHISM - Task 2.3
        // =====================================================

        Person[] people = new Person[6];

        people[0] = person;
        people[1] = patient;
        people[2] = doctor;
        people[3] = nurse;
        people[4] = inPatient;
        people[5] = surgeon;


        // =========================
        // Print All
        // =========================

        IO.println();
        IO.println("===== ALL PEOPLE =====");

        printAll(people);


        // =========================
        // Count By Type
        // =========================

        IO.println();
        IO.println("===== COUNT BY TYPE =====");

        countByType(people);


        // =========================
        // Find Oldest
        // =========================

        IO.println();
        IO.println("===== OLDEST PERSON =====");

        Person oldest = findOldest(people);

        IO.println("Oldest Person: " + oldest.getFullName());
        IO.println("Age: " + oldest.getAge());
    }


    // =====================================================
    // Task 2.3 - printAll
    // =====================================================

    public static void printAll(Person[] people) {

        for (Person person : people) {

            person.displayInfo();

            IO.println("--------------------");
        }
    }


    // =====================================================
    // Task 2.3 - countByType
    // =====================================================

    public static void countByType(Person[] people) {

        int patientCount = 0;
        int doctorCount = 0;
        int nurseCount = 0;

        for (Person person : people) {

            if (person instanceof InPatient) {

                patientCount++;

            } else if (person instanceof Patient) {

                patientCount++;

            } else if (person instanceof Doctor) {

                doctorCount++;

            } else if (person instanceof Nurse) {

                nurseCount++;
            }
        }

        IO.println("Patients: " + patientCount);
        IO.println("Doctors: " + doctorCount);
        IO.println("Nurses: " + nurseCount);
    }


}