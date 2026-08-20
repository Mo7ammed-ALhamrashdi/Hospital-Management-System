package services;

import entities.MedicalRecord;
import interfaces.Manageable;
import interfaces.Searchable;

public class RecordService implements Manageable, Searchable {
        private MedicalRecord[] records = new MedicalRecord[100];
        private int count = 0;
        // add
        @Override
        public void add(Object entity) {
            if (entity instanceof MedicalRecord) {
                records[count] = (MedicalRecord) entity;
                count++;
            }
        }
        // removeById
        @Override
        public void removeById(String id) {

            for (int i = 0; i < count; i++) {

                if (records[i].getRecordId().equals(id)) {

                    for (int j = i; j < count - 1; j++) {
                        records[j] = records[j + 1];
                    }

                    records[count - 1] = null;
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
                result[i] = records[i];
            }

            return result;
        }

        // search
        @Override
        public Object[] search(String keyword) {

            MedicalRecord[] result = new MedicalRecord[count];
            int resultCount = 0;

            for (int i = 0; i < count; i++) {

                if (records[i].getRecordId().contains(keyword)
                        || records[i].getPatientId().contains(keyword)
                        || records[i].getDoctorId().contains(keyword)
                        || records[i].getDiagnosis().contains(keyword)) {

                    result[resultCount] = records[i];
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

                if (records[i].getRecordId().equals(id)) {
                    return records[i];
                }
            }

            return null;
        }

        // listByPatient
        public Object[] listByPatient(String patientId) {

            MedicalRecord[] result = new MedicalRecord[count];
            int resultCount = 0;

            for (int i = 0; i < count; i++) {

                if (records[i].getPatientId().equals(patientId)) {

                    result[resultCount] = records[i];
                    resultCount++;
                }
            }

            Object[] finalResult = new Object[resultCount];

            for (int i = 0; i < resultCount; i++) {
                finalResult[i] = result[i];
            }

            return finalResult;
        }

        // countConfidential
        public int countConfidential() {

            int total = 0;

            for (int i = 0; i < count; i++) {

                if (records[i].isConfidential()) {
                    total++;
                }
            }

            return total;
        }
    }