package ksch.patientmanagement.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import static ksch.patientmanagement.patient.PatientEntity.toPatientEntity;

@Service
@RequiredArgsConstructor
public class PatientTransactionsImpl implements PatientTransactions {

    private final PatientRepository patientRepository;

    private final PatientNumberGenerator patientNumberGenerator;

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Patient create(Patient patient) {
        PatientEntity patientEntity = toPatientEntity(patient);
        if (patient.getPatientNumber() == null) {
            patientEntity.setPatientNumber(patientNumberGenerator.generatePatientNumber());
        }
        patientEntity = patientRepository.save(patientEntity);
        eventPublisher.publishEvent(new PatientCreatedEvent(patientEntity));
        return patientEntity;
    }

    @Override
    public void update(Patient patient) {
        patientRepository.save(toPatientEntity(patient));
    }
}
