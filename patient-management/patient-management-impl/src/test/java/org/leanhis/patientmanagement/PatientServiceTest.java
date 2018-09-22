package org.leanhis.patientmanagement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.YEARS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PatientServiceTest {

    @InjectMocks
    private PatientServiceImpl patientService;

    @Mock
    private PatientRepository patientRepository;

    @Test
    public void should_create_patient() {
        PatientEntity p = PatientEntity.builder().id(UUID.randomUUID()).build();

        patientService.create(p);

        verify(patientRepository).save(any(PatientEntity.class));
    }

    @Test
    public void should_retrieve_patient_by_patient_number() {
        String patientNumber = "1234";

        patientService.findByNameOrNumber(patientNumber);

        verify(patientRepository).findByIdOrName(patientNumber);
    }

    @Test
    public void should_get_patient_by_id() {
        Patient p = PatientEntity.builder().id(UUID.randomUUID()).build();

        patientService.getById(p.getId());

        verify(patientRepository).getById(p.getId());
    }

    @Test
    public void should_calculate_patient_age() {
        Patient patient = buildTestPatient(LocalDate.now().minus(15, YEARS));

        int patientAgeInYears = patientService.getAgeInYears(patient);

        assertEquals("Couldn't calculate patient age correctly.", 15, patientAgeInYears);
    }

    @Test
    public void should_skip_patient_age_calculation_if_no_age_specified() {
        Patient patient = buildTestPatient(null);

        Integer patientAgeInYears = patientService.getAgeInYears(patient);

        assertNull("Patient age is expected to be null if not date of birth is specified", patientAgeInYears);
    }

    private Patient buildTestPatient(LocalDate dateOfBirth) {
        return PatientEntity.builder()
                .id(UUID.randomUUID())
                .patientNumber("KSA-18-1005")
                .name("John Doe")
                .dateOfBirth(dateOfBirth)
                .gender(Gender.MALE)
                .address("Kirpal Sagar")
                .build();
    }
}
