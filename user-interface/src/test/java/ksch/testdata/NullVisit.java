package ksch.testdata;

import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.visit.Visit;
import ksch.patientmanagement.visit.VisitType;

import java.time.LocalDateTime;
import java.util.UUID;

public class NullVisit implements Visit {

    @Override
    public UUID getId() {
        return null;
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
