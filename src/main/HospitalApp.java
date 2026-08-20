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
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 7);
    }

    // =========================
    // MENU
    // =========================

    private void showMenu() {

        System.out.println();
        System.out.println("===== HOSPITAL SYSTEM =====");
        System.out.println("1. Patients");
        System.out.println("2. Doctors");
        System.out.println("3. Nurses");
        System.out.println("4. Appointments");
        System.out.println("5. Medical Records");
        System.out.println("6. Reports");
        System.out.println("7. Exit");
    }

    // =========================
    // PATIENT MENU
    // =========================

    private void patientMenu() {

        System.out.println();
        System.out.println("===== PATIENTS =====");
        System.out.println("1. Add Patient");
        System.out.println("2. View All");
        System.out.println("3. Search");
        System.out.println("4. Update Contact");
        System.out.println("5. Remove");
        System.out.println("6. List InPatients");
        System.out.println("7. Total Outstanding");
        System.out.println("8. Back");

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
                System.out.println(
                        "Total Outstanding: "
                                + patientService.totalOutstanding());
                break;

            case 8:
                break;

            default:
                System.out.println("Invalid choice.");
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

        System.out.println("Patient added.");
    }

    private void viewPatients() {

        Object[] patients = patientService.getAll();

        for (Object obj : patients) {

            Patient patient = (Patient) obj;

            patient.displayInfo();

            System.out.println("-------------------");
        }
    }

    private void searchPatients() {

        String keyword = readText("Search: ");

        Object[] result = patientService.search(keyword);

        for (Object obj : result) {

            Patient patient = (Patient) obj;

            patient.displayInfo();

            System.out.println("-------------------");
        }
    }

    private void updatePatientContact() {

        String id = readText("Patient ID: ");
        String phone = readText("New Phone: ");
        String email = readText("New Email: ");

        patientService.updateContact(id, phone, email);

        System.out.println("Contact updated.");
    }

    private void removePatient() {

        String id = readText("Patient ID: ");

        patientService.removeById(id);

        System.out.println("Patient removed.");
    }

    private void listInPatients() {

        Object[] result = patientService.listInPatients();

        for (Object obj : result) {

            Patient patient = (Patient) obj;

            patient.displayInfo();

            System.out.println("-------------------");
        }
    }

    // =========================
    // DOCTOR MENU
    // =========================

    private void doctorMenu() {

        System.out.println();
        System.out.println("===== DOCTORS =====");
        System.out.println("1. Add Doctor");
        System.out.println("2. View All");
        System.out.println("3. Search");
        System.out.println("4. Remove");
        System.out.println("5. Add Slot");
        System.out.println("6. Assign Patient");
        System.out.println("7. List By Specialization");
        System.out.println("8. Available Doctors");
        System.out.println("9. Back");

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
                System.out.println("Invalid choice.");
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

        System.out.println("Doctor added.");
    }

    private void viewDoctors() {

        Object[] doctors = doctorService.getAll();

        for (Object obj : doctors) {

            Doctor doctor = (Doctor) obj;

            doctor.displayInfo();

            System.out.println("-------------------");
        }
    }

    private void searchDoctors() {

        String keyword = readText("Search: ");

        Object[] result = doctorService.search(keyword);

        for (Object obj : result) {

            Doctor doctor = (Doctor) obj;

            doctor.displayInfo();

            System.out.println("-------------------");
        }
    }

    private void removeDoctor() {

        String id = readText("Doctor ID: ");

        doctorService.removeById(id);

        System.out.println("Doctor removed.");
    }

    private void addDoctorSlot() {

        String id = readText("Doctor ID: ");
        String slot = readText("Slot: ");

        doctorService.addSlot(id, slot);

        System.out.println("Slot added.");
    }

    private void assignPatientToDoctor() {

        String doctorId = readText("Doctor ID: ");
        String patientId = readText("Patient ID: ");

        doctorService.assignPatient(doctorId, patientId);

        System.out.println("Patient assigned.");
    }

    private void listDoctorsBySpecialization() {

        String specialization =
                readText("Specialization: ");

        Object[] result =
                doctorService.listBySpecialization(specialization);

        for (Object obj : result) {

            Doctor doctor = (Doctor) obj;

            doctor.displayInfo();

            System.out.println("-------------------");
        }
    }

    private void availableDoctors() {

        Object[] result =
                doctorService.availableDoctors();

        for (Object obj : result) {

            Doctor doctor = (Doctor) obj;

            doctor.displayInfo();

            System.out.println("-------------------");
        }
    }

    // =========================
    // NURSE MENU
    // =========================

    private void nurseMenu() {

        System.out.println();
        System.out.println("===== NURSES =====");
        System.out.println("1. Add Nurse");
        System.out.println("2. View All");
        System.out.println("3. Search");
        System.out.println("4. Remove");
        System.out.println("5. List By Shift");
        System.out.println("6. Reassign Patient");
        System.out.println("7. Back");

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
                System.out.println("Invalid choice.");
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

        System.out.println("Nurse added.");
    }

    private void viewNurses() {

        Object[] nurses = nurseService.getAll();

        for (Object obj : nurses) {

            Nurse nurse = (Nurse) obj;

            nurse.displayInfo();

            System.out.println("-------------------");
        }
    }

    private void searchNurses() {

        String keyword = readText("Search: ");

        Object[] result =
                nurseService.search(keyword);

        for (Object obj : result) {

            Nurse nurse = (Nurse) obj;

            nurse.displayInfo();

            System.out.println("-------------------");
        }
    }

    private void removeNurse() {

        String id = readText("Nurse ID: ");

        nurseService.removeById(id);

        System.out.println("Nurse removed.");
    }

    private void listNursesByShift() {

        String shift = readText("Shift: ");

        Object[] result =
                nurseService.listByShift(shift);

        for (Object obj : result) {

            Nurse nurse = (Nurse) obj;

            nurse.displayInfo();

            System.out.println("-------------------");
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

        System.out.println("Patient reassigned.");
    }

    // =========================
    // APPOINTMENT MENU
    // =========================

    private void appointmentMenu() {

        System.out.println();
        System.out.println("===== APPOINTMENTS =====");
        System.out.println("1. Schedule");
        System.out.println("2. View All");
        System.out.println("3. Search");
        System.out.println("4. Cancel");
        System.out.println("5. Complete");
        System.out.println("6. Reschedule");
        System.out.println("7. List By Status");
        System.out.println("8. List By Patient");
        System.out.println("9. Back");

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
                System.out.println("Invalid choice.");
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

        System.out.println("Appointment scheduled.");
    }

    private void viewAppointments() {

        Object[] result =
                appointmentService.getAll();

        for (Object obj : result) {

            Appointment appointment =
                    (Appointment) obj;

            appointment.displayInfo();

            System.out.println("-------------------");
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

            System.out.println("-------------------");
        }
    }

    private void cancelAppointment() {

        String id =
                readText("Appointment ID: ");

        appointmentService.cancel(id);

        System.out.println("Appointment cancelled.");
    }

    private void completeAppointment() {

        String id =
                readText("Appointment ID: ");

        appointmentService.complete(id);

        System.out.println("Appointment completed.");
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

        System.out.println("Appointment rescheduled.");
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

        System.out.println();
        System.out.println("===== MEDICAL RECORDS =====");
        System.out.println("1. Add Record");
        System.out.println("2. View All");
        System.out.println("3. Search");
        System.out.println("4. Remove");
        System.out.println("5. List By Patient");
        System.out.println("6. Count Confidential");
        System.out.println("7. Back");

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
                System.out.println(
                        "Confidential Records: "
                                + recordService.countConfidential()
                );
                break;

            case 7:
                break;

            default:
                System.out.println("Invalid choice.");
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

        System.out.println("Medical record added.");
    }

    private void viewRecords() {

        Object[] result =
                recordService.getAll();

        for (Object obj : result) {

            MedicalRecord record =
                    (MedicalRecord) obj;

            record.displayInfo();

            System.out.println("-------------------");
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

            System.out.println("-------------------");
        }
    }

    private void removeRecord() {

        String id =
                readText("Record ID: ");

        recordService.removeById(id);

        System.out.println("Record removed.");
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
        System.out.println("===== REPORTS =====");

        System.out.println(
                "Total Patients: "
                        + patientService.getAll().length
        );

        System.out.println(
                "Total Doctors: "
                        + doctorService.getAll().length
        );

        System.out.println(
                "Total Nurses: "
                        + nurseService.getAll().length
        );

        System.out.println(
                "Total Appointments: "
                        + appointmentService.getAll().length
        );

        System.out.println(
                "Total Medical Records: "
                        + recordService.getAll().length
        );

        System.out.println(
                "Outstanding Balance: "
                        + patientService.totalOutstanding()
        );

        System.out.println(
                "Confidential Records: "
                        + recordService.countConfidential()
        );
    }

    // =========================
    // INPUT METHODS
    // =========================

    private String readText(String message) {

        System.out.print(message);

        return scanner.nextLine();
    }

    private int readInt(String message) {

        System.out.print(message);

        return Integer.parseInt(scanner.nextLine());
    }

    private double readDouble(String message) {

        System.out.print(message);

        return Double.parseDouble(scanner.nextLine());
    }
}