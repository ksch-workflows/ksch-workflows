package ksch.medicalrecords;

import ksch.patientmanagement.visit.Visit;

import java.util.UUID;

public interface VitalsService extends MedicalRecordEntryService {

    @Override
    Vitals createMedicalRecordEntry(Visit visit);

    Vitals save(Vitals vitals);

    Vitals get(UUID vitalsId);
}
