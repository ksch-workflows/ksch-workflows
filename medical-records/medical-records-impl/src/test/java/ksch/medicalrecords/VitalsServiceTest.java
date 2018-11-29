package ksch.medicalrecords;

import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.visit.Visit;
import ksch.patientmanagement.visit.VisitType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static ksch.medicalrecords.VitalsEntity.toVitalsEntity;
import static org.junit.Assert.assertNotNull;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VitalsServiceTest {

    @InjectMocks
    private VitalsServiceImpl vitalsService;

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

    @Test
    public void should_retrieve_vitals_record() {
        VitalsEntity vitals = toVitalsEntity(new ExampleVitals());
        when(vitalsRepository.findById(any(UUID.class))).thenReturn(Optional.of(vitals));

        Vitals retrievedVitals = vitalsService.get(vitals.getId());

        assertNotNull(retrievedVitals);
    }
}

class ExampleVitals implements Vitals {

    @Override
    public UUID getId() {
        return UUID.randomUUID();
    }

    @Override
    public UUID getVisitId() {
        return UUID.randomUUID();
    }

    @Override
    public Integer getSystolicInMmHg() {
        return 130;
    }

    @Override
    public Integer getDiastolicInMmHg() {
        return 80;
    }

    @Override
    public Float getTemperatureInF() {
        return 98.4f;
    }

    @Override
    public Integer getPulseInBPM() {
        return 80;
    }

    @Override
    public Integer getWeightInKG() {
        return 73;
    }
}

class ExampleVisit implements Visit {

    @Override
    public UUID getId() {
        return UUID.randomUUID();
    }

    @Override
    public Patient getPatient() {
        return null;
    }

    @Override
    public VisitType getType() {
        return null;
    }

    @Override
    public LocalDateTime getTimeStart() {
        return null;
    }

    @Override
    public LocalDateTime getTimeEnd() {
        return null;
    }
}
