package ksch.medicalrecords;

import ksch.patientmanagement.visit.Visit;

public interface VitalsTransactions extends MedicalRecordTransactions {

    @Override
    Vitals createMedicalRecordEntry(Visit visit);

    Vitals save(Vitals vitals);
}
