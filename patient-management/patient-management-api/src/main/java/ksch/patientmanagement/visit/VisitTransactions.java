package ksch.patientmanagement.visit;

import ksch.patientmanagement.patient.Patient;

public interface VisitTransactions {

    Visit startVisit(Patient patient, VisitType visitType);

    Visit discharge(Patient patient);
}
