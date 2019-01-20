package ksch.medicalrecords;

import ksch.patientmanagement.visit.Visit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VitalsTransactionsTest {

    @InjectMocks
    private VitalsTransactionsImpl vitalsService;

    @Mock
    private VitalsRepository vitalsRepository;

    @Test
    public void should_create_vitals_record() {
        Visit visit = new ExampleVisit();
        when(vitalsRepository.save(any(VitalsEntity.class))).then(returnsFirstArg());

        Vitals createdVitals = vitalsService.createMedicalRecordEntry(visit);

        assertNotNull(createdVitals);
    }

    @Test
    public void should_update_vitals_record() {
        Vitals vitals = new ExampleVitals();
        when(vitalsRepository.save(any(VitalsEntity.class))).then(returnsFirstArg());

        Vitals updatedVitals = vitalsService.save(vitals);

        assertNotNull(updatedVitals);
    }
}
