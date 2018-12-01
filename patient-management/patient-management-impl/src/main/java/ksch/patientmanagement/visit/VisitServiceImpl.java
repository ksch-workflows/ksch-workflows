package ksch.patientmanagement.visit;

import ksch.patientmanagement.DatabaseRecordNotFoundException;
import ksch.patientmanagement.patient.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static java.time.LocalDateTime.now;
import static ksch.patientmanagement.patient.PatientEntity.toPatientEntity;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {

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
    public Visit startVisit(Patient patient, VisitType visitType) {
        VisitEntity visit = VisitEntity.builder()
                .timeStart(now())
                .type(visitType)
                .patient(toPatientEntity(patient))
                .build();
        return visitRepository.save(visit);
    }

    @Override
    public Visit discharge(Patient patient) {
        VisitEntity visit = visitRepository.findAllByPatientId(patient.getId())
                .stream()
                .filter(this::hasStartAndNoEnd)
                .findFirst()
                .orElseThrow(() -> new NoActiveVisitException(patient.getId()));

        visit.setTimeEnd(now());
        return visitRepository.save(visit);
    }

    @Override
    public Patient getPatient(UUID visitId) {
        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new DatabaseRecordNotFoundException(visitId, VisitEntity.class));
        return visit.getPatient();
    }

    class NoActiveVisitException extends RuntimeException {
        public NoActiveVisitException(UUID patientId) {
            super("There is no active visit for patient " + patientId);
        }
    }
}
