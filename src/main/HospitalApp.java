package main;

import entities.*;
import services.*;
import utils.HelperUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class HospitalApp {

    private Scanner scanner = new Scanner(System.in);

    private PatientService patientService = new PatientService();
    private DoctorService doctorService = new DoctorService();
    private NurseService nurseService = new NurseService();
    private AppointmentService appointmentService = new AppointmentService();
    private RecordService recordService = new RecordService();

    public static void main(String[] args) {

        HospitalApp app = new HospitalApp();

        // Task 2.9
        app.seedSampleData();

        // Start application
        app.start();
    }

    // =====================================================
    // MAIN MENU
    // =====================================================

    public void start() {

        int choice;

        do {

            showMenu();

            choice = readInt("Choose: ");

            switch (choice) {

                case 1:
                    patientMenu();
                    break;

                case 2:
                    doctorMenu();
                    break;

                case 3:
                    nurseMenu();
                    break;

                case 4:
                    appointmentMenu();
                    break;

                case 5:
                    recordMenu();
                    break;

                case 6:
                    reportsMenu();
                    break;

                case 7:
                    IO.println("Goodbye!");
                    break;

                default:
                    IO.println("Invalid choice.");
            }

        } while (choice != 7);
    }

    // =====================================================
    // MAIN MENU DISPLAY
    // =====================================================

    private void showMenu() {

        IO.println();
        IO.println("..... HOSPITAL SYSTEM .....");
        IO.println("1. Patients");
        IO.println("2. Doctors");
        IO.println("3. Nurses");
        IO.println("4. Appointments");
        IO.println("5. Medical Records");
        IO.println("6. Reports");
        IO.println("7. Exit");
    }

    // =====================================================
    // PATIENT MENU
    // =====================================================

    private void patientMenu() {

        IO.println();
        IO.println("===== PATIENTS =====");
        IO.println("1. Add Patient");
        IO.println("2. View All");
        IO.println("3. Search");
        IO.println("4. Update Contact");
        IO.println("5. Remove");
        IO.println("6. List InPatients");
        IO.println("7. Total Outstanding");
        IO.println("8. Back");

        int choice = readInt("Choose: ");

        switch (choice) {

            case 1:
                addPatient();
                break;

            case 2:
                viewPatients();
                break;

            case 3:
                searchPatients();
                break;

            case 4:
                updatePatientContact();
                break;

            case 5:
                removePatient();
                break;

            case 6:
                listInPatients();
                break;

            case 7:
                IO.println(
                        "Total Outstanding: "
                                + patientService.totalOutstanding()
                );
                break;

            case 8:
                break;

            default:
                IO.println("Invalid choice.");
        }
    }

    private void addPatient() {

        String id = readText("ID: ");
        String firstName = readText("First Name: ");
        String lastName = readText("Last Name: ");
        String bloodGroup = readText("Blood Group: ");

        patientService.addPatient(
                id,
                firstName,
                lastName,
                bloodGroup
        );

        IO.println("Patient added.");
    }

    private void viewPatients() {

        Object[] patients = patientService.getAll();

        if (patients.length == 0) {
            IO.println("No patients found.");
            return;
        }

        for (Object obj : patients) {

            Patient patient = (Patient) obj;

            patient.displayInfo();

            IO.println("-------------------");
        }
    }

    private void searchPatients() {

        String keyword = readText("Search: ");

        Object[] result = patientService.search(keyword);

        if (result.length == 0) {
            IO.println("No patient found.");
            return;
        }

        for (Object obj : result) {

            Patient patient = (Patient) obj;

            patient.displayInfo();

            IO.println("-------------------");
        }
    }

    private void updatePatientContact() {

        String id = readText("Patient ID: ");
        String phone = readText("New Phone: ");
        String email = readText("New Email: ");

        patientService.updateContact(id, phone, email);

        IO.println("Contact updated.");
    }

    private void removePatient() {

        String id = readText("Patient ID: ");

        patientService.removeById(id);

        IO.println("Patient removed.");
    }

    private void listInPatients() {

        Object[] result = patientService.listInPatients();

        if (result.length == 0) {
            IO.println("No InPatients found.");
            return;
        }

        for (Object obj : result) {

            Patient patient = (Patient) obj;

            patient.displayInfo();

            IO.println("-------------------");
        }
    }

    // =====================================================
    // DOCTOR MENU
    // =====================================================

    private void doctorMenu() {

        IO.println();
        IO.println("===== DOCTORS =====");
        IO.println("1. Add Doctor");
        IO.println("2. View All");
        IO.println("3. Search");
        IO.println("4. Remove");
        IO.println("5. Add Slot");
        IO.println("6. Assign Patient");
        IO.println("7. List By Specialization");
        IO.println("8. Available Doctors");
        IO.println("9. Back");

        int choice = readInt("Choose: ");

        switch (choice) {

            case 1:
                addDoctor();
                break;

            case 2:
                viewDoctors();
                break;

            case 3:
                searchDoctors();
                break;

            case 4:
                removeDoctor();
                break;

            case 5:
                addDoctorSlot();
                break;

            case 6:
                assignPatientToDoctor();
                break;

            case 7:
                listDoctorsBySpecialization();
                break;

            case 8:
                availableDoctors();
                break;

            case 9:
                break;

            default:
                IO.println("Invalid choice.");
        }
    }

    private void addDoctor() {

        String id = readText("ID: ");
        String firstName = readText("First Name: ");
        String lastName = readText("Last Name: ");
        String specialization = readText("Specialization: ");

        int experience = readInt("Experience Years: ");
        double fee = readDouble("Consultation Fee: ");

        Doctor doctor = new Doctor(
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
                specialization,
                experience,
                fee,
                false
        );

        doctorService.add(doctor);

        IO.println("Doctor added.");
    }

    private void viewDoctors() {

        Object[] doctors = doctorService.getAll();

        if (doctors.length == 0) {
            IO.println("No doctors found.");
            return;
        }

        for (Object obj : doctors) {

            Doctor doctor = (Doctor) obj;

            doctor.displayInfo();

            IO.println("-------------------");
        }
    }

    private void searchDoctors() {

        String keyword = readText("Search: ");

        Object[] result = doctorService.search(keyword);

        if (result.length == 0) {
            IO.println("No doctor found.");
            return;
        }

        for (Object obj : result) {

            Doctor doctor = (Doctor) obj;

            doctor.displayInfo();

            IO.println("-------------------");
        }
    }

    private void removeDoctor() {

        String id = readText("Doctor ID: ");

        doctorService.removeById(id);

        IO.println("Doctor removed.");
    }

    private void addDoctorSlot() {

        String id = readText("Doctor ID: ");
        String slot = readText("Slot: ");

        doctorService.addSlot(id, slot);

        IO.println("Slot operation completed.");
    }

    private void assignPatientToDoctor() {

        String doctorId = readText("Doctor ID: ");
        String patientId = readText("Patient ID: ");

        doctorService.assignPatient(
                doctorId,
                patientId
        );

        IO.println("Patient assignment completed.");
    }

    private void listDoctorsBySpecialization() {

        String specialization =
                readText("Specialization: ");

        Object[] result =
                doctorService.listBySpecialization(
                        specialization
                );

        if (result.length == 0) {
            IO.println("No doctors found.");
            return;
        }

        for (Object obj : result) {

            Doctor doctor = (Doctor) obj;

            doctor.displayInfo();

            IO.println("-------------------");
        }
    }

    private void availableDoctors() {

        Object[] result =
                doctorService.availableDoctors();

        if (result.length == 0) {
            IO.println("No available doctors.");
            return;
        }

        for (Object obj : result) {

            Doctor doctor = (Doctor) obj;

            doctor.displayInfo();

            IO.println("-------------------");
        }
    }

    // =====================================================
    // NURSE MENU
    // =====================================================

    private void nurseMenu() {

        IO.println();
        IO.println("===== NURSES =====");
        IO.println("1. Add Nurse");
        IO.println("2. View All");
        IO.println("3. Search");
        IO.println("4. Remove");
        IO.println("5. List By Shift");
        IO.println("6. Reassign Patient");
        IO.println("7. Back");

        int choice = readInt("Choose: ");

        switch (choice) {

            case 1:
                addNurse();
                break;

            case 2:
                viewNurses();
                break;

            case 3:
                searchNurses();
                break;

            case 4:
                removeNurse();
                break;

            case 5:
                listNursesByShift();
                break;

            case 6:
                reassignNursePatient();
                break;

            case 7:
                break;

            default:
                IO.println("Invalid choice.");
        }
    }

    private void addNurse() {

        String id = readText("ID: ");
        String firstName = readText("First Name: ");
        String lastName = readText("Last Name: ");
        String departmentId = readText("Department ID: ");
        String shift = readText("Shift: ");

        int years =
                readInt("Years Of Service: ");

        Nurse nurse = new Nurse(
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
                departmentId,
                shift,
                years
        );

        nurseService.add(nurse);

        IO.println("Nurse added.");
    }

    private void viewNurses() {

        Object[] nurses = nurseService.getAll();

        if (nurses.length == 0) {
            IO.println("No nurses found.");
            return;
        }

        for (Object obj : nurses) {

            Nurse nurse = (Nurse) obj;

            nurse.displayInfo();

            IO.println("-------------------");
        }
    }

    private void searchNurses() {

        String keyword =
                readText("Search: ");

        Object[] result =
                nurseService.search(keyword);

        if (result.length == 0) {
            IO.println("No nurse found.");
            return;
        }

        for (Object obj : result) {

            Nurse nurse =
                    (Nurse) obj;

            nurse.displayInfo();

            IO.println("-------------------");
        }
    }

    private void removeNurse() {

        String id =
                readText("Nurse ID: ");

        nurseService.removeById(id);

        IO.println("Nurse removed.");
    }

    private void listNursesByShift() {

        String shift =
                readText("Shift: ");

        Object[] result =
                nurseService.listByShift(shift);

        if (result.length == 0) {
            IO.println("No nurses found for this shift.");
            return;
        }

        for (Object obj : result) {

            Nurse nurse =
                    (Nurse) obj;

            nurse.displayInfo();

            IO.println("-------------------");
        }
    }

    private void reassignNursePatient() {

        String nurseId =
                readText("Nurse ID: ");

        String oldPatientId =
                readText("Old Patient ID: ");

        String newPatientId =
                readText("New Patient ID: ");

        nurseService.reassign(
                nurseId,
                oldPatientId,
                newPatientId
        );

        IO.println("Patient reassignment completed.");
    }

    // =====================================================
    // APPOINTMENT MENU
    // =====================================================

    private void appointmentMenu() {

        IO.println();
        IO.println("===== APPOINTMENTS =====");
        IO.println("1. Schedule");
        IO.println("2. View All");
        IO.println("3. Search");
        IO.println("4. Cancel");
        IO.println("5. Complete");
        IO.println("6. Reschedule");
        IO.println("7. List By Status");
        IO.println("8. List By Patient");
        IO.println("9. Back");

        int choice =
                readInt("Choose: ");

        switch (choice) {

            case 1:
                scheduleAppointment();
                break;

            case 2:
                viewAppointments();
                break;

            case 3:
                searchAppointments();
                break;

            case 4:
                cancelAppointment();
                break;

            case 5:
                completeAppointment();
                break;

            case 6:
                rescheduleAppointment();
                break;

            case 7:
                listAppointmentsByStatus();
                break;

            case 8:
                listAppointmentsByPatient();
                break;

            case 9:
                break;

            default:
                IO.println("Invalid choice.");
        }
    }

    private void scheduleAppointment() {

        String patientId =
                readText("Patient ID: ");

        String doctorId =
                readText("Doctor ID: ");

        LocalDate date =
                readDate("Date YYYY-MM-DD: ");

        LocalTime time =
                readTime("Time HH:MM: ");

        Appointment appointment =
                new Appointment(
                        "A" + System.currentTimeMillis(),
                        patientId,
                        doctorId,
                        date,
                        time,
                        "Scheduled",
                        "General",
                        false
                );

        appointmentService.schedule(
                appointment
        );

        IO.println("Appointment scheduled.");
    }

    private void viewAppointments() {

        Object[] result =
                appointmentService.getAll();

        if (result.length == 0) {
            IO.println("No appointments found.");
            return;
        }

        for (Object obj : result) {

            Appointment appointment =
                    (Appointment) obj;

            appointment.displayInfo();

            IO.println("-------------------");
        }
    }

    private void searchAppointments() {

        String keyword =
                readText("Search: ");

        Object[] result =
                appointmentService.search(keyword);

        if (result.length == 0) {
            IO.println("No appointment found.");
            return;
        }

        for (Object obj : result) {

            Appointment appointment =
                    (Appointment) obj;

            appointment.displayInfo();

            IO.println("-------------------");
        }
    }

    private void cancelAppointment() {

        String id =
                readText("Appointment ID: ");

        appointmentService.cancel(id);

        IO.println("Appointment cancelled.");
    }

    private void completeAppointment() {

        String id =
                readText("Appointment ID: ");

        appointmentService.complete(id);

        IO.println("Appointment completed.");
    }

    private void rescheduleAppointment() {

        String id =
                readText("Appointment ID: ");

        LocalDate date =
                readDate("New Date YYYY-MM-DD: ");

        LocalTime time =
                readTime("New Time HH:MM: ");

        appointmentService.reschedule(
                id,
                date,
                time
        );

        IO.println("Appointment rescheduled.");
    }

    private void listAppointmentsByStatus() {

        String status =
                readText("Status: ");

        Object[] result =
                appointmentService.listByStatus(status);

        if (result.length == 0) {
            IO.println("No appointments found.");
            return;
        }

        for (Object obj : result) {

            Appointment appointment =
                    (Appointment) obj;

            appointment.displayInfo();

            IO.println("-------------------");
        }
    }

    private void listAppointmentsByPatient() {

        String patientId =
                readText("Patient ID: ");

        Object[] result =
                appointmentService.listByPatient(
                        patientId
                );

        if (result.length == 0) {
            IO.println("No appointments found for this patient.");
            return;
        }

        for (Object obj : result) {

            Appointment appointment =
                    (Appointment) obj;

            appointment.displayInfo();

            IO.println("-------------------");
        }
    }

    // =====================================================
    // MEDICAL RECORD MENU
    // =====================================================

    private void recordMenu() {

        IO.println();
        IO.println("===== MEDICAL RECORDS =====");
        IO.println("1. Add Record");
        IO.println("2. View All");
        IO.println("3. Search");
        IO.println("4. Remove");
        IO.println("5. List By Patient");
        IO.println("6. Count Confidential");
        IO.println("7. Back");

        int choice =
                readInt("Choose: ");

        switch (choice) {

            case 1:
                addRecord();
                break;

            case 2:
                viewRecords();
                break;

            case 3:
                searchRecords();
                break;

            case 4:
                removeRecord();
                break;

            case 5:
                listRecordsByPatient();
                break;

            case 6:
                IO.println(
                        "Confidential Records: "
                                + recordService.countConfidential()
                );
                break;

            case 7:
                break;

            default:
                IO.println("Invalid choice.");
        }
    }

    private void addRecord() {

        String recordId =
                readText("Record ID: ");

        String patientId =
                readText("Patient ID: ");

        String doctorId =
                readText("Doctor ID: ");

        String diagnosis =
                readText("Diagnosis: ");

        String prescription =
                readText("Prescription: ");

        String notes =
                readText("Notes: ");

        MedicalRecord record =
                new MedicalRecord(
                        recordId,
                        patientId,
                        doctorId,
                        LocalDate.now(),
                        diagnosis,
                        prescription,
                        notes,
                        false
                );

        recordService.add(record);

        IO.println("Medical record added.");
    }

    private void viewRecords() {

        Object[] result =
                recordService.getAll();

        if (result.length == 0) {
            IO.println("No medical records found.");
            return;
        }

        for (Object obj : result) {

            MedicalRecord record =
                    (MedicalRecord) obj;

            record.displayInfo();

            IO.println("-------------------");
        }
    }

    private void searchRecords() {

        String keyword =
                readText("Search: ");

        Object[] result =
                recordService.search(keyword);

        if (result.length == 0) {
            IO.println("No medical record found.");
            return;
        }

        for (Object obj : result) {

            MedicalRecord record =
                    (MedicalRecord) obj;

            record.displayInfo();

            IO.println("-------------------");
        }
    }

    private void removeRecord() {

        String id =
                readText("Record ID: ");

        recordService.removeById(id);

        IO.println("Medical record removed.");
    }

    private void listRecordsByPatient() {

        String patientId =
                readText("Patient ID: ");

        Object[] result =
                recordService.listByPatient(
                        patientId
                );

        if (result.length == 0) {
            IO.println("No records found for this patient.");
            return;
        }

        for (Object obj : result) {

            MedicalRecord record =
                    (MedicalRecord) obj;

            record.displayInfo();

            IO.println("-------------------");
        }
    }

    // =====================================================
    // REPORTS
    // =====================================================

    private void reportsMenu() {

        IO.println();
        IO.println("..... REPORTS .....");

        IO.println(
                "Total Patients: "
                        + patientService.getAll().length
        );

        IO.println(
                "Total Doctors: "
                        + doctorService.getAll().length
        );

        IO.println(
                "Total Nurses: "
                        + nurseService.getAll().length
        );

        IO.println(
                "Total Appointments: "
                        + appointmentService.getAll().length
        );

        IO.println(
                "Total Medical Records: "
                        + recordService.getAll().length
        );

        IO.println(
                "Total Outstanding: "
                        + patientService.totalOutstanding()
        );

        IO.println(
                "Confidential Records: "
                        + recordService.countConfidential()
        );
    }

    // =====================================================
    // INPUT METHODS
    // =====================================================

    private String readText(String message) {

        IO.print(message);

        return scanner.nextLine();
    }

    private int readInt(String message) {

        while (true) {

            try {

                IO.print(message);

                return Integer.parseInt(
                        scanner.nextLine()
                );

            } catch (NumberFormatException e) {

                IO.println("Please enter a valid number.");
            }
        }
    }

    private double readDouble(String message) {

        while (true) {

            try {

                IO.print(message);

                return Double.parseDouble(
                        scanner.nextLine()
                );

            } catch (NumberFormatException e) {

                IO.println("Please enter a valid number.");
            }
        }
    }

    private LocalDate readDate(String message) {

        while (true) {

            String text =
                    readText(message);

            try {

                return LocalDate.parse(
                        text,
                        DateTimeFormatter.ofPattern("yyyy-M-d")
                );

            } catch (DateTimeParseException e) {

                IO.println(
                        "Invalid date. Example: 2026-09-01"
                );
            }
        }
    }

    private LocalTime readTime(String message) {

        while (true) {

            String text =
                    readText(message);

            try {

                return LocalTime.parse(
                        text,
                        DateTimeFormatter.ofPattern("H:mm")
                );

            } catch (DateTimeParseException e) {

                IO.println(
                        "Invalid time. Example: 09:30"
                );
            }
        }
    }

    // =====================================================
    // TASK 2.9 - SAMPLE DATA
    // =====================================================

    private void seedSampleData() {

        IO.println("===== SEEDING SAMPLE DATA =====");

        // =================================================
        // 6 PATIENTS
        // =================================================

        patientService.addPatient(
                "P001",
                "Ahmed",
                "Ali"
        );

        patientService.addPatient(
                "P002",
                "Mohammed",
                "Said",
                "A+"
        );

        Patient patient3 = new Patient(
                "P003",
                "Salim",
                "Khalid",
                LocalDate.of(1995, 5, 10),
                "Male",
                "99112233",
                "salim@email.com",
                "Muscat",
                "N003",
                31,
                true,
                "B+",
                "99887766",
                LocalDate.now(),
                50,
                true
        );

        patientService.addPatient(patient3);

        Patient patient4 = new Patient(
                "P004",
                "Fatma",
                "Hassan",
                LocalDate.of(1998, 2, 15),
                "Female",
                "99223344",
                "fatma@email.com",
                "Muscat",
                "N004",
                28,
                true,
                "O+",
                "99776655",
                LocalDate.now(),
                100,
                true
        );

        patientService.addPatient(patient4);

        InPatient inPatient1 = new InPatient(
                "P005",
                "Khalid",
                "Omar",
                LocalDate.of(1985, 6, 20),
                "Male",
                "99334455",
                "khalid@email.com",
                "Muscat",
                "N005",
                41,
                true,
                "AB+",
                "99665544",
                LocalDate.now(),
                200,
                true,
                LocalDate.now(),
                "R101",
                50.0,
                3
        );

        patientService.addPatient(inPatient1);

        InPatient inPatient2 = new InPatient(
                "P006",
                "Aisha",
                "Salim",
                LocalDate.of(1990, 9, 12),
                "Female",
                "99445566",
                "aisha@email.com",
                "Muscat",
                "N006",
                36,
                true,
                "O-",
                "99554433",
                LocalDate.now(),
                150,
                false,
                LocalDate.now(),
                "R102",
                75.0,
                5
        );

        patientService.addPatient(inPatient2);

        // =================================================
        // 4 DOCTORS
        // =================================================

        Doctor doctor1 = new Doctor(
                "D001",
                "Mohammed",
                "Said",
                LocalDate.of(1980, 3, 20),
                "Male",
                "99887766",
                "doctor1@email.com",
                "Muscat",
                "N101",
                46,
                true,
                "Cardiology",
                15,
                100.0,
                true
        );

        Doctor doctor2 = new Doctor(
                "D002",
                "Ahmed",
                "Hassan",
                LocalDate.of(1985, 7, 10),
                "Male",
                "99776655",
                "doctor2@email.com",
                "Muscat",
                "N102",
                41,
                true,
                "Neurology",
                10,
                120.0,
                false
        );

        Doctor doctor3 = new Doctor(
                "D003",
                "Sara",
                "Ali",
                LocalDate.of(1988, 11, 5),
                "Female",
                "99665544",
                "doctor3@email.com",
                "Muscat",
                "N103",
                38,
                true,
                "Pediatrics",
                8,
                80.0,
                false
        );

        doctorService.add(doctor1);
        doctorService.add(doctor2);
        doctorService.add(doctor3);

        Surgeon surgeon1 = new Surgeon(
                "D004",
                "Khalid",
                "Salim",
                LocalDate.of(1978, 4, 25),
                "Male",
                "99554433",
                "surgeon@email.com",
                "Muscat",
                "N104",
                48,
                true,
                "General Surgery",
                20,
                200.0,
                true,
                5,
                true
        );

        doctorService.addSurgeon(surgeon1);

        // =================================================
        // 3 NURSES
        // =================================================

        Nurse nurse1 = new Nurse(
                "N001",
                "Mona",
                "Ali",
                LocalDate.of(1990, 1, 10),
                "Female",
                "99443322",
                "nurse1@email.com",
                "Muscat",
                "NN001",
                36,
                true,
                "DEP01",
                "Morning",
                8
        );

        Nurse nurse2 = new Nurse(
                "N002",
                "Sara",
                "Ahmed",
                LocalDate.of(1992, 3, 15),
                "Female",
                "99332211",
                "nurse2@email.com",
                "Muscat",
                "NN002",
                34,
                true,
                "DEP02",
                "Evening",
                6
        );

        Nurse nurse3 = new Nurse(
                "N003",
                "Fatma",
                "Khalid",
                LocalDate.of(1985, 8, 20),
                "Female",
                "99221100",
                "nurse3@email.com",
                "Muscat",
                "NN003",
                41,
                true,
                "DEP03",
                "Night",
                12
        );

        nurseService.add(nurse1);
        nurseService.add(nurse2);
        nurseService.add(nurse3);

        // =================================================
        // 6 APPOINTMENTS
        // =================================================

        // Overload 1
        appointmentService.schedule(
                "P001",
                "D001",
                LocalDate.of(2026, 9, 1)
        );

        // Overload 2
        appointmentService.schedule(
                "P002",
                "D002",
                LocalDate.of(2026, 9, 2),
                LocalTime.of(10, 30)
        );

        // Overload 3
        appointmentService.schedule(
                patient3,
                doctor3,
                LocalDate.of(2026, 9, 3),
                LocalTime.of(11, 0),
                "Regular checkup"
        );

        appointmentService.schedule(
                "P004",
                "D001",
                LocalDate.of(2026, 9, 4),
                LocalTime.of(9, 30)
        );

        Patient patient5 =
                (Patient) patientService.searchById("P005");

        Doctor doctor4 =
                (Doctor) doctorService.searchById("D004");

        appointmentService.schedule(
                patient5,
                doctor4,
                LocalDate.of(2026, 9, 5),
                LocalTime.of(13, 0),
                "Surgery consultation"
        );

        appointmentService.schedule(
                "P006",
                "D002",
                LocalDate.of(2026, 9, 6),
                LocalTime.of(14, 0)
        );

        // =================================================
        // APPOINTMENT OVERLOADED addNotes()
        // =================================================

        Appointment appointment =
                (Appointment)
                        appointmentService.searchById("A1");

        if (appointment != null) {

            appointment.addNotes(
                    "Patient should arrive early"
            );

            appointment.addNotes(
                    "Bring previous medical reports",
                    "Dr. Mohammed"
            );
        }

        // =================================================
        // 5 MEDICAL RECORDS
        // =================================================

        MedicalRecord record1 =
                new MedicalRecord(
                        "R001",
                        "P001",
                        "D001",
                        LocalDate.of(2026, 8, 1),
                        "High blood pressure",
                        "Medication A",
                        "Regular monitoring required",
                        false
                );

        MedicalRecord record2 =
                new MedicalRecord(
                        "R002",
                        "P002",
                        "D002",
                        LocalDate.of(2026, 8, 2),
                        "Headache",
                        "Medication B",
                        "Follow up after one week",
                        false
                );

        MedicalRecord record3 =
                new MedicalRecord(
                        "R003",
                        "P003",
                        "D003",
                        LocalDate.of(2026, 8, 3),
                        "Fever",
                        "Medication C",
                        "Rest and drink water",
                        false
                );

        MedicalRecord record4 =
                new MedicalRecord(
                        "R004",
                        "P005",
                        "D004",
                        LocalDate.of(2026, 8, 4),
                        "Appendicitis",
                        "Surgery required",
                        "Confidential surgery record",
                        true
                );

        MedicalRecord record5 =
                new MedicalRecord(
                        "R005",
                        "P006",
                        "D001",
                        LocalDate.of(2026, 8, 5),
                        "Diabetes",
                        "Medication D",
                        "Regular blood sugar check",
                        true
                );

        recordService.add(record1);
        recordService.add(record2);
        recordService.add(record3);
        recordService.add(record4);
        recordService.add(record5);

        // =================================================
        // OTHER METHODS
        // =================================================

        doctorService.assignPatient(
                "D001",
                "P001"
        );

        doctorService.assignPatient(
                "D002",
                "P002"
        );

        doctor1.addSlot("09:00");
        doctor1.addSlot("10:00");

        nurse1.assignPatient("P001");
        nurse2.assignPatient("P002");

        surgeon1.performSurgery();

        surgeon1.scheduleSurgery(
                LocalDate.of(2026, 9, 10)
        );

        // Patient overloaded updateContact
        patient3.updateContact(
                "99111111"
        );

        patient3.updateContact(
                "99222222",
                "newemail@email.com"
        );

        // Doctor overloaded updateFee
        doctor1.updateFee(
                110.0
        );

        doctor2.updateFee(
                130.0,
                "Annual fee adjustment"
        );

        // HelperUtils overloaded methods
        String id1 =
                HelperUtils.generateId();

        String id2 =
                HelperUtils.generateId("PAT");

        IO.println(
                "Generated ID: " + id1
        );

        IO.println(
                "Generated ID: " + id2
        );

        IO.println("===== SAMPLE DATA READY =====");
    }
}