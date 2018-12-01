package ksch.medicalrecords;

import ksch.patientmanagement.visit.Visit;

/**
 * Basis for all services which deal with medical record entries.
 *
 * It is required to make sure that all medical record entries are linked to a {@link Visit}.
 */
interface MedicalRecordEntryService {

    MedicalRecordEntry createMedicalRecordEntry(Visit visit);
}
