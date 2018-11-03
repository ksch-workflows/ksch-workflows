package ksch.patientmanagement.visit;

import ksch.patientmanagement.patient.Patient;

public interface VisitService {

    /**
     * @return {@code true} if the Patient is currently treated in the hospital, otherwise {@code false}.
     */
    boolean isActive(Patient patient);

    Visit startVisit(Patient patient, VisitType visitType);

    Visit discharge(Patient patient);
}
