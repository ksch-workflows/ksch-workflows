package ksch.patientmanagement.visit;

import ksch.patientmanagement.patient.Patient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VisitQueries {

    Visit get(UUID visitId);

    /**
     * @return {@code true} if the Patient is currently treated in the hospital, otherwise {@code false}.
     */
    boolean isActive(Patient patient);

    Optional<Visit> getActiveVisit(Patient patient);

    List<Visit> getAllActiveOpdVisits();

    Patient getPatient(UUID visitId);

    Optional<Visit> findByOpdNumber(String opdNumber);
}
