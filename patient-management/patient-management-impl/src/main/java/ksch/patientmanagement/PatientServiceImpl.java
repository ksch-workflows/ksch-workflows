package ksch.patientmanagement;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.time.LocalDate.now;
import static java.time.temporal.ChronoUnit.YEARS;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Patient create(Patient patient) {
        PatientEntity p = patientRepository.save(PatientEntity.toPatientEntity(patient));
        eventPublisher.publishEvent(new PatientCreatedEvent(p));
        return p;
    }

    @Override
    public List<Patient> findByNameOrNumber(String nameOrNumber) {
        return patientRepository.findByIdOrName(nameOrNumber).stream()
                .map(p -> (Patient) p)
                .collect(Collectors.toList());
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
        patientRepository.save(PatientEntity.toPatientEntity(patient));
    }
}
