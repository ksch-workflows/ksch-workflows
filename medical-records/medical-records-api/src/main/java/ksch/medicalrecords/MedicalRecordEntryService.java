package ksch.medicalrecords;

import ksch.patientmanagement.visit.Visit;

interface MedicalRecordEntryService {

    MedicalRecordEntry createMedicalRecordEntry(Visit visit);
}
