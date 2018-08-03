package org.leanhis.patientmanagement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.time.LocalDate.now;
import static java.time.temporal.ChronoUnit.YEARS;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public Patient create(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> findBy(String nameOrMedicalRecordNumber) {
        return patientRepository.findByIdOrName(nameOrMedicalRecordNumber);
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
        patientRepository.save(patient);
    }
}
