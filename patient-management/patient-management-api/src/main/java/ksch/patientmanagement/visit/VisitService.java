package ksch.patientmanagement.visit;

import ksch.patientmanagement.patient.Patient;

import java.util.Optional;
import java.util.UUID;

public interface VisitService {

    Visit get(UUID visitId);

    /**
     * @return {@code true} if the Patient is currently treated in the hospital, otherwise {@code false}.
     */
    boolean isActive(Patient patient);

    Optional<Visit> getActiveVisit(Patient patient);

    Visit startVisit(Patient patient, VisitType visitType);

    Visit discharge(Patient patient);

    Patient getPatient(UUID visitId);
}
