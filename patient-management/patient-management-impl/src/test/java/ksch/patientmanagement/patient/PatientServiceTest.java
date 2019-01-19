package ksch.patientmanagement.patient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static java.time.LocalDate.now;
import static java.time.temporal.ChronoUnit.YEARS;
import static ksch.patientmanagement.patient.Gender.FEMALE;
import static ksch.patientmanagement.patient.Gender.MALE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PatientServiceTest {

    @InjectMocks
    private PatientServiceImpl patientService;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @Mock
    private PatientNumberGenerator patientNumberGenerator;

    @Test
    public void should_create_patient() {
        when(patientNumberGenerator.generatePatientNumber()).thenReturn("18-5322");
        when(patientRepository.save(any(PatientEntity.class))).then(returnsFirstArg());
        PatientEntity p = PatientEntity.builder().id(UUID.randomUUID()).build();

        Patient createdPatient = patientService.create(p);

        assertNotNull(createdPatient);
        assertEquals("18-5322", createdPatient.getPatientNumber());
        verify(patientRepository).save(any(PatientEntity.class));
        verify(eventPublisher).publishEvent(any(PatientCreatedEvent.class));
    }

    @Test
    public void should_use_existing_patient_number_during_patient_creation() {
        when(patientRepository.save(any(PatientEntity.class))).then(returnsFirstArg());
        PatientEntity p = PatientEntity.builder().id(UUID.randomUUID()).patientNumber("KSA-15-97433").build();

        Patient createdPatient = patientService.create(p);

        assertEquals("KSA-15-97433", createdPatient.getPatientNumber());
    }

    @Test
    public void should_retrieve_patient_by_patient_number() {
        String patientNumber = "1234";
        when(patientRepository.findByPatientNumberOrName(patientNumber))
                .thenReturn(listOf(buildTestPatient(patientNumber)));

        List<Patient> patients = patientService.findByNameOrNumber(patientNumber);

        assertTrue("Could not find patient by patient number.", patients.size() > 0);
        verify(patientRepository).findByPatientNumberOrName(patientNumber);
    }

    @Test
    public void should_get_patient_by_id() {
        Patient p = PatientEntity.builder().id(UUID.randomUUID()).build();

        patientService.getById(p.getId());

        verify(patientRepository).getById(p.getId());
    }

    @Test
    public void should_calculate_patient_age() {
        Patient patient = buildTestPatient(now().minus(15, YEARS));

        int patientAgeInYears = patientService.getAgeInYears(patient);

        assertEquals("Couldn't calculate patient age correctly.", 15, patientAgeInYears);
    }

    @Test
    public void should_skip_patient_age_calculation_if_no_age_specified() {
        Patient patient = buildTestPatientWithoutDateOfBirth();

        Integer patientAgeInYears = patientService.getAgeInYears(patient);

        assertNull("Patient age is expected to be null if not date of birth is specified", patientAgeInYears);
    }

    @Test
    public void test_full_name_is_standard_name_if_father_unknown() {
        PatientEntity patient = buildTestPatient();
        patient.setName("Jaswant Ashtikar");
        patient.setNameFather(null);

        String fullName = patientService.getFullName(patient);

        assertEquals("Jaswant Ashtikar", fullName);
    }

    @Test
    public void test_full_name_includes_name_of_father_for_man() {
        PatientEntity patient = buildTestPatient();
        patient.setName("Jaswant Ashtikar");
        patient.setGender(MALE);
        patient.setNameFather("Waazir Sirasikar");

        String fullName = patientService.getFullName(patient);

        assertEquals("Jaswant Ashtikar, s/o Waazir Sirasikar", fullName);
    }

    @Test
    public void test_full_name_includes_name_of_father_for_women() {
        PatientEntity patient = buildTestPatient();
        patient.setName("Surya Limbu");
        patient.setGender(FEMALE);
        patient.setNameFather("Waazir Sirasikar");

        String fullName = patientService.getFullName(patient);

        assertEquals("Surya Limbu, d/o Waazir Sirasikar", fullName);
    }

    private PatientEntity buildTestPatient() {
        return buildTestPatient("0815");
    }

    private PatientEntity buildTestPatient(String patientNumber) {
        return PatientEntity.builder()
                .id(UUID.randomUUID())
                .patientNumber(patientNumber)
                .name("John Doe")
                .dateOfBirth(now())
                .gender(MALE)
                .address("Kirpal Sagar")
                .build();
    }

    private PatientEntity buildTestPatient(LocalDate dateOfBirth) {
        return PatientEntity.builder()
                .id(UUID.randomUUID())
                .patientNumber("KSA-18-1005")
                .name("John Doe")
                .dateOfBirth(dateOfBirth)
                .gender(MALE)
                .address("Kirpal Sagar")
                .build();
    }

    private PatientEntity buildTestPatientWithoutDateOfBirth() {
        return PatientEntity.builder()
                .id(UUID.randomUUID())
                .patientNumber("KSA-18-1005")
                .name("John Doe")
                .dateOfBirth(null)
                .gender(MALE)
                .address("Kirpal Sagar")
                .build();
    }

    private List<PatientEntity> listOf(PatientEntity... patient) {
        return Arrays.asList(patient);
    }
}
