package ksch.medicalrecords;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * An item in the medical records of a patient.
 */
public interface MedicalRecordEntry {

    /**
     * @return The technical identifier for this medical record entry.
     */
    UUID getId();

    /**
     * @return The reference to the technical indentifier of the Visit during which this medical
     * record entry was taken.
     *
     * @see ksch.patientmanagement.visit.Visit
     */
    UUID getVisitId();

    /**
     * @return The point in time when the medical record entry was taken.
     */
    LocalDateTime getTime();
}
