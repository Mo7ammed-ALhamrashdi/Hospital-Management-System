package entities;
import java.time.LocalDate;
import java.util.ArrayList;
    public class Surgeon extends Doctor {

        private int surgeriesPerformed;
        private boolean operationTheatreAccess;
        private ArrayList upcomingSurgeryDates;

        public Surgeon(String id, String firstName, String lastName,
                       LocalDate dateOfBirth, String gender,
                       String phoneNumber, String email, String address,
                       String nationalId, int age, boolean activeStatus,
                       String specialization, int experienceYears,
                       double consultationFee, boolean isOnCall,
                       int surgeriesPerformed,
                       boolean operationTheatreAccess) {

            // Person → Doctor → Surgeon
            super(id, firstName, lastName,
                    dateOfBirth, gender,
                    phoneNumber, email, address,
                    nationalId, age, activeStatus,
                    specialization, experienceYears,
                    consultationFee, isOnCall);

            this.surgeriesPerformed = surgeriesPerformed;
            this.operationTheatreAccess = operationTheatreAccess;
            this.upcomingSurgeryDates = new ArrayList();
        }
        @Override
        public void displayInfo() {

            super.displayInfo();

            System.out.println("Surgeries Performed: " + surgeriesPerformed);
            System.out.println("Operation Theatre Access: " + operationTheatreAccess);
            System.out.println("Upcoming Surgery Dates: " + upcomingSurgeryDates);
        }

        public void performSurgery() {
            surgeriesPerformed++;
        }

        public void scheduleSurgery(LocalDate date) {
            upcomingSurgeryDates.add(date);
        }

        public int getUpcomingCount() {
            return upcomingSurgeryDates.size();
        }
    }

