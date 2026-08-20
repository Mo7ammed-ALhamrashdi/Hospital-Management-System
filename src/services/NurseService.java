package services;

import entities.Nurse;
import interfaces.Manageable;
import interfaces.Searchable;

public class NurseService implements Manageable, Searchable {
    private Nurse[] nurses = new Nurse[100];
    private int count = 0;

    // add
    public void add(Nurse nurse) {
        nurses[count] = nurse;
        count++;
    }

    @Override
    public void add(Object entity) {

        if (entity instanceof Nurse) {
            add((Nurse) entity);
        }
    }

    // removeById
    @Override
    public void removeById(String id) {

        for (int i = 0; i < count; i++) {

            if (nurses[i].getId().equals(id)) {

                for (int j = i; j < count - 1; j++) {
                    nurses[j] = nurses[j + 1];
                }

                nurses[count - 1] = null;
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
            result[i] = nurses[i];
        }

        return result;
    }

    // search
    @Override
    public Object[] search(String keyword) {

        Nurse[] result = new Nurse[count];
        int resultCount = 0;

        for (int i = 0; i < count; i++) {

            if (nurses[i].getFullName().contains(keyword)
                    || nurses[i].getId().contains(keyword)) {

                result[resultCount] = nurses[i];
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

            if (nurses[i].getId().equals(id)) {
                return nurses[i];
            }
        }

        return null;
    }

    // listByShift
    public Object[] listByShift(String shift) {

        Nurse[] result = new Nurse[count];
        int resultCount = 0;

        for (int i = 0; i < count; i++) {

            if (nurses[i].getShift().equals(shift)) {

                result[resultCount] = nurses[i];
                resultCount++;
            }
        }

        Object[] finalResult = new Object[resultCount];

        for (int i = 0; i < resultCount; i++) {
            finalResult[i] = result[i];
        }

        return finalResult;
    }

    // reassign
    public void reassign(String nurseId,
                         String oldPatientId,
                         String newPatientId) {

        Nurse nurse = (Nurse) searchById(nurseId);

        if (nurse != null) {
            nurse.unassignPatient(oldPatientId);
            nurse.assignPatient(newPatientId);
        }
    }
}
