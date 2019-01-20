package ksch.medicalrecords;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.UUID;

import static ksch.medicalrecords.VitalsEntity.toVitalsEntity;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VitalsQueriesTest {

    @InjectMocks
    private VitalsQueriesImpl vitalsQueries;

    @Mock
    private VitalsRepository vitalsRepository;

    @Test
    public void should_retrieve_vitals_record() {
        VitalsEntity vitals = toVitalsEntity(new ExampleVitals());
        when(vitalsRepository.findById(any(UUID.class))).thenReturn(Optional.of(vitals));

        Vitals retrievedVitals = vitalsQueries.get(vitals.getId());

        assertNotNull(retrievedVitals);
    }
}
