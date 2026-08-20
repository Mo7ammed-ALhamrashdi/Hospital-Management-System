package services;

import entities.Appointment;
import entities.Doctor;
import entities.Patient;
import interfaces.Manageable;
import interfaces.Searchable;
import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentService implements Manageable, Searchable {
    private Appointment[] appointments = new Appointment[100];
    private int count = 0;

    // add
    @Override
    public void add(Object entity) {

        if (entity instanceof Appointment) {
            appointments[count] = (Appointment) entity;
            count++;
        }
    }

    // schedule - ids + date
    public void schedule(String patientId,
                         String doctorId,
                         LocalDate date) {

        Appointment appointment = new Appointment(
                "A" + (count + 1),
                patientId,
                doctorId,
                date,
                LocalTime.of(9, 0),
                "Scheduled",
                "General appointment",
                false
        );

        appointments[count] = appointment;
        count++;
    }

    // schedule - ids + date + time
    public void schedule(String patientId,
                         String doctorId,
                         LocalDate date,
                         LocalTime time) {
        Appointment appointment = new Appointment(
                "A" + (count + 1),
                patientId,
                doctorId,
                date,
                time,
                "Scheduled",
                "General appointment",
                false
        );
        appointments[count] = appointment;
        count++;
    }
    // schedule - full objects + reason
    public void schedule(Patient patient,
                         Doctor doctor,
                         LocalDate date,
                         LocalTime time,
                         String reason) {
        Appointment appointment = new Appointment(
                "A" + (count + 1),
                patient.getId(),
                doctor.getId(),
                date,
                time,
                "Scheduled",
                reason,
                false
        );
        appointments[count] = appointment;
        count++;
    }
    // removeById
    @Override
    public void removeById(String id) {

        for (int i = 0; i < count; i++) {

            if (appointments[i].getAppointmentId().equals(id)) {

                for (int j = i; j < count - 1; j++) {
                    appointments[j] = appointments[j + 1];
                }
                appointments[count - 1] = null;
                count--;

                return;
            }
        }
    }
    // getAll
    @Override
    public Object[] getAll() {

        Object[] result = new Object[count];

        for (int i = 0; i < count; i++) {
            result[i] = appointments[i];
        }
        return result;
    }
    // search
    @Override
    public Object[] search(String keyword) {

        Appointment[] result = new Appointment[count];
        int resultCount = 0;

        for (int i = 0; i < count; i++) {

            if (appointments[i].getAppointmentId().contains(keyword)
                    || appointments[i].getPatientId().contains(keyword)
                    || appointments[i].getDoctorId().contains(keyword)
                    || appointments[i].getStatus().contains(keyword)) {

                result[resultCount] = appointments[i];
                resultCount++;
            }
        }
        Object[] finalResult = new Object[resultCount];

        for (int i = 0; i < resultCount; i++) {
            finalResult[i] = result[i];
        }

        return finalResult;
    }
    // searchById
    @Override
    public Object searchById(String id) {

        for (int i = 0; i < count; i++) {

            if (appointments[i].getAppointmentId().equals(id)) {
                return appointments[i];
            }
        }
        return null;
    }
    // cancel
    public void cancel(String id) {
        Appointment appointment = (Appointment) searchById(id);

        if (appointment != null) {
            appointment.cancel();
        }
    }
    // complete
    public void complete(String id) {

        Appointment appointment = (Appointment) searchById(id);

        if (appointment != null) {
            appointment.complete();
        }
    }
    // reschedule
    public void reschedule(String id,
                           LocalDate newDate,
                           LocalTime newTime) {
        Appointment appointment = (Appointment) searchById(id);

        if (appointment != null) {
            appointment.reschedule(newDate, newTime);
        }
    }
    // listByStatus
    public Object[] listByStatus(String status) {

        Appointment[] result = new Appointment[count];
        int resultCount = 0;
        for (int i = 0; i < count; i++) {

            if (appointments[i].getStatus().equals(status)) {

                result[resultCount] = appointments[i];
                resultCount++;
            }
        }
        Object[] finalResult = new Object[resultCount];

        for (int i = 0; i < resultCount; i++) {
            finalResult[i] = result[i];
        }
        return finalResult;
    }
    // listByPatient
    public Object[] listByPatient(String patientId) {
        Appointment[] result = new Appointment[count];
        int resultCount = 0;
        for (int i = 0; i < count; i++) {

            if (appointments[i].getPatientId().equals(patientId)) {

                result[resultCount] = appointments[i];
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