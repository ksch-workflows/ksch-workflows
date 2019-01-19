package ksch.medicalrecords;

import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.visit.Visit;
import ksch.patientmanagement.visit.VisitType;

import java.time.LocalDateTime;
import java.util.UUID;

public class ExampleVisit implements Visit {

    @Override
    public UUID getId() {
        return UUID.randomUUID();
    }

    @Override
    public Patient getPatient() {
        return null;
    }

    @Override
    public VisitType getType() {
        return null;
    }

    @Override
    public LocalDateTime getTimeStart() {
        return null;
    }

    @Override
    public LocalDateTime getTimeEnd() {
        return null;
    }
}
