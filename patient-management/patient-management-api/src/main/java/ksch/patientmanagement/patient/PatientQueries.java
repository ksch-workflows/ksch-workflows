package ksch.patientmanagement.patient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PatientQueries {

    /**
     * Searches for patients in the database by their name or patient number.
     */
    List<Patient> findByNameOrNumber(String nameOrNumber);

    Optional<Patient> findByPatientNumber(String patientNumber);

    /**
     * Prodives access on the details of a patient with the given technical identifiert.
     */
    Patient getById(UUID patientId);
}
