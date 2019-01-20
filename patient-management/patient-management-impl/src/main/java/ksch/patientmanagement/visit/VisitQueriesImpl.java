package ksch.patientmanagement.visit;

import ksch.patientmanagement.DatabaseRecordNotFoundException;
import ksch.patientmanagement.patient.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VisitQueriesImpl implements VisitQueries {

    private final VisitRepository visitRepository;

    @Override
    public Visit get(UUID visitId) {
        return visitRepository.findById(visitId)
                .orElseThrow(() -> new RuntimeException("Could not find Visit entity with ID " + visitId));
    }

    @Override
    public boolean isActive(Patient patient) {
        return visitRepository.findAllByPatientId(patient.getId())
                .stream()
                .anyMatch(this::hasStartAndNoEnd);
    }

    private boolean hasStartAndNoEnd(VisitEntity v) {
        return v.getTimeStart() != null && v.getTimeEnd() == null;
    }

    @Override
    public Optional<Visit> getActiveVisit(Patient patient) {
        return visitRepository.findAllByPatientId(patient.getId())
                .stream()
                .filter(this::hasStartAndNoEnd)
                .map(e -> (Visit) e)
                .findFirst();
    }

    @Override
    public Patient getPatient(UUID visitId) {
        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new DatabaseRecordNotFoundException(visitId, VisitEntity.class));
        return visit.getPatient();
    }
}
