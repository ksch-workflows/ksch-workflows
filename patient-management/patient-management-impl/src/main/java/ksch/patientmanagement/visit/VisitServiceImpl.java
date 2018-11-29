package ksch.patientmanagement.visit;

import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.patient.PatientRepository;
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

    private final PatientRepository patientRepository;

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

    class NoActiveVisitException extends RuntimeException {
        public NoActiveVisitException(UUID patientId) {
            super("There is no active visit for patient " + patientId);
        }
    }
}
