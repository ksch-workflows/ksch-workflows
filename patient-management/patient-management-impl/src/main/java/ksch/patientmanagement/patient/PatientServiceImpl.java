package ksch.patientmanagement.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.time.LocalDate.now;
import static java.time.temporal.ChronoUnit.YEARS;
import static ksch.patientmanagement.patient.PatientEntity.toPatientEntity;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

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
    public List<Patient> findByNameOrNumber(String nameOrNumber) {
        return patientRepository.findByPatientNumberOrName(nameOrNumber).stream()
                .map(p -> (Patient) p)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Patient> findByPatientNumber(String patientNumber) {
        return patientRepository.findByPatientNumber(patientNumber).map(p -> (Patient) p);
    }

    @Override
    public Patient getById(UUID patientId) {
        return patientRepository.getById(patientId);
    }

    @Override
    public Integer getAgeInYears(Patient patient) {
        if (patient.getDateOfBirth() == null) {
            return null;
        }

        return (int) patient.getDateOfBirth().until(now(), YEARS);
    }

    @Override
    public void update(Patient patient) {
        patientRepository.save(toPatientEntity(patient));
    }
}
