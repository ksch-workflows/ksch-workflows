package ksch.patientmanagement.patient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationEventPublisher;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PatientTransactionsTest {

    @InjectMocks
    private PatientTransactionsImpl patientService;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @Mock
    private PatientNumberGenerator patientNumberGenerator;

    @Test
    public void should_create_patient() {
        when(patientNumberGenerator.generateOpdNumber()).thenReturn("18-5322");
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
}
