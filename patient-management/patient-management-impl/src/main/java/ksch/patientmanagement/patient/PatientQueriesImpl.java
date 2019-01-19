package ksch.patientmanagement.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientQueriesImpl implements PatientQueries {

    private final PatientRepository patientRepository;

    @Override
    public List<Patient> findByNameOrNumber(String nameOrNumber) {
        return patientRepository.findByPatientNumberOrName(nameOrNumber).stream()
                .map(p -> (Patient) p)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Patient> findByPatientNumber(String patientNumber) {
        return patientRepository.findByPatientNumber(patientNumber).map(p -> p);
    }

    @Override
    public Patient getById(UUID patientId) {
        return patientRepository.getById(patientId);
    }
}
