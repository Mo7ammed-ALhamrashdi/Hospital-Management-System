package entities;

import utils.HelperUtils;
import interfaces.Displayable;
import java.time.LocalDate;
import java.util.ArrayList;
    public class Surgeon extends Doctor implements Displayable {

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
            IO.println("Surgeries Performed: " + surgeriesPerformed);
            IO.println("Operation Theatre Access: " + operationTheatreAccess);
            IO.println("Upcoming Surgery Dates: " + upcomingSurgeryDates);
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
        public void setSurgeriesPerformed(int surgeriesPerformed) {
            if (surgeriesPerformed < 0) {
                IO.println("Surgeries performed cannot be negative");
                return;
            }

            this.surgeriesPerformed = surgeriesPerformed;
        }
    }

