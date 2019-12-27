package ksch.commons;

import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.visit.Visit;
import ksch.patientmanagement.visit.VisitType;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class DummyVisit implements Visit {

    private final UUID id = UUID.randomUUID();

    private final Patient patient;

    public DummyVisit(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String getOpdNumber() {
        return "08-15";
    }

    @Override
    public VisitType getType() {
        return VisitType.OPD;
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
