package main;

import entities.*;
import services.*;
import java.time.LocalDate;
import java.time.LocalTime;
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

        app.start();
    }

    // =========================
    // MAIN MENU
    // =========================

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

    // =========================
    // MENU
    // =========================

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

    // =========================
    // PATIENT MENU
    // =========================

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
                                + patientService.totalOutstanding());
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

        for (Object obj : patients) {

            Patient patient = (Patient) obj;

            patient.displayInfo();

            IO.println("-------------------");
        }
    }

    private void searchPatients() {

        String keyword = readText("Search: ");

        Object[] result = patientService.search(keyword);

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

        for (Object obj : result) {

            Patient patient = (Patient) obj;

            patient.displayInfo();

            IO.println("-------------------");
        }
    }

    // =========================
    // DOCTOR MENU
    // =========================

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

        for (Object obj : doctors) {

            Doctor doctor = (Doctor) obj;

            doctor.displayInfo();

            IO.println("-------------------");
        }
    }

    private void searchDoctors() {

        String keyword = readText("Search: ");

        Object[] result = doctorService.search(keyword);

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

        IO.println("Slot added.");
    }

    private void assignPatientToDoctor() {

        String doctorId = readText("Doctor ID: ");
        String patientId = readText("Patient ID: ");

        doctorService.assignPatient(doctorId, patientId);

        IO.println("Patient assigned.");
    }

    private void listDoctorsBySpecialization() {

        String specialization =
                readText("Specialization: ");

        Object[] result =
                doctorService.listBySpecialization(specialization);

        for (Object obj : result) {

            Doctor doctor = (Doctor) obj;

            doctor.displayInfo();

            IO.println("-------------------");
        }
    }

    private void availableDoctors() {

        Object[] result =
                doctorService.availableDoctors();

        for (Object obj : result) {

            Doctor doctor = (Doctor) obj;

            doctor.displayInfo();

            IO.println("-------------------");
        }
    }

    // =========================
    // NURSE MENU
    // =========================

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

        for (Object obj : nurses) {

            Nurse nurse = (Nurse) obj;

            nurse.displayInfo();

            IO.println("-------------------");
        }
    }

    private void searchNurses() {

        String keyword = readText("Search: ");

        Object[] result =
                nurseService.search(keyword);

        for (Object obj : result) {

            Nurse nurse = (Nurse) obj;

            nurse.displayInfo();

            IO.println("-------------------");
        }
        result = nurseService.search(keyword);

        if (result.length == 0) {
            IO.println("No nurse found.");
        } else {
            for (Object obj : result) {
                Nurse nurse = (Nurse) obj;
                nurse.displayInfo();
                IO.println("-------------------");
            }
        }
    }

    private void removeNurse() {

        String id = readText("Nurse ID: ");

        nurseService.removeById(id);

        IO.println("Nurse removed.");
    }

    private void listNursesByShift() {

        String shift = readText("Shift: ");

        Object[] result =
                nurseService.listByShift(shift);

        for (Object obj : result) {

            Nurse nurse = (Nurse) obj;

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

        IO.println("Patient reassigned.");
    }

    // =========================
    // APPOINTMENT MENU
    // =========================

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

        int choice = readInt("Choose: ");

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
                LocalDate.parse(
                        readText("Date YYYY-MM-DD: ")
                );

        LocalTime time =
                LocalTime.parse(
                        readText("Time HH:MM: ")
                );

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

        appointmentService.schedule(appointment);

        IO.println("Appointment scheduled.");
    }

    private void viewAppointments() {

        Object[] result =
                appointmentService.getAll();

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
                LocalDate.parse(
                        readText("New Date YYYY-MM-DD: ")
                );

        LocalTime time =
                LocalTime.parse(
                        readText("New Time HH:MM: ")
                );

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

        for (Object obj : result) {

            Appointment appointment =
                    (Appointment) obj;

            appointment.displayInfo();
        }
    }

    private void listAppointmentsByPatient() {

        String patientId =
                readText("Patient ID: ");

        Object[] result =
                appointmentService.listByPatient(patientId);

        for (Object obj : result) {

            Appointment appointment =
                    (Appointment) obj;

            appointment.displayInfo();
        }
    }

    // =========================
    // MEDICAL RECORD MENU
    // =========================

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

        for (Object obj : result) {

            MedicalRecord record =
                    (MedicalRecord) obj;

            record.displayInfo();

            IO.println("-------------------");
        }
        String patientId = readText("Patient ID: ");

        result = recordService.listByPatient(patientId);

        if (result.length == 0) {
            IO.println("No records found for this patient.");
        } else {

            for (Object obj : result) {

                MedicalRecord record = (MedicalRecord) obj;

                record.displayInfo();

                IO.println("-------------------");
            }
        }
    }

    private void searchRecords() {

        String keyword =
                readText("Search: ");

        Object[] result =
                recordService.search(keyword);

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

        IO.println("Record removed.");
    }

    private void listRecordsByPatient() {

        String patientId =
                readText("Patient ID: ");

        Object[] result =
                recordService.listByPatient(patientId);

        for (Object obj : result) {

            MedicalRecord record =
                    (MedicalRecord) obj;

            record.displayInfo();
        }
    }

    // =========================
    // REPORTS
    // =========================

    private void reportsMenu() {

        System.out.println();
        System.out.println("..... REPORTS .....");

        System.out.println("Total Patients: "
                + patientService.getAll().length);

        System.out.println("Total Doctors: "
                + doctorService.getAll().length);

        System.out.println("Total Nurses: "
                + nurseService.getAll().length);

        System.out.println("Total Appointments: "
                + appointmentService.getAll().length);

        System.out.println("Total Medical Records: "
                + recordService.getAll().length);

        System.out.println("Total Outstanding: "
                + patientService.totalOutstanding());

        System.out.println("Confidential Records: "
                + recordService.countConfidential());
    }

    // =========================
    // INPUT METHODS
    // =========================

    private String readText(String message) {

        IO.print(message);

        return scanner.nextLine();
    }

    private int readInt(String message) {

        IO.print(message);

        return Integer.parseInt(scanner.nextLine());
    }

    private double readDouble(String message) {

        IO.print(message);

        return Double.parseDouble(scanner.nextLine());
    }

}