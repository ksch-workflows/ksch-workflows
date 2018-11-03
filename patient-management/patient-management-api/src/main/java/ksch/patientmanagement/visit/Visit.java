package ksch.patientmanagement.visit;

import ksch.patientmanagement.patient.Patient;

import java.time.LocalDateTime;
import java.util.UUID;

public interface Visit {

    UUID getId();

    Patient getPatient();

    VisitType getType();

    LocalDateTime getTimeStart();

    LocalDateTime getTimeEnd();
}
