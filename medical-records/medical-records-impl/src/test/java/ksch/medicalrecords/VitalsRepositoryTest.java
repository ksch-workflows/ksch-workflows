package ksch.medicalrecords;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VitalsRepositoryTest {

    @Autowired
    private VitalsRepository vitalsRepository;

    @Test
    public void should_create_vitals_entity() {
        VitalsEntity vitals = VitalsEntity.builder()
                .visitId(UUID.randomUUID())
                .systolicInMmHg(100)
                .diastolicInMmHg(70)
                .build();

        VitalsEntity createdVitals = vitalsRepository.save(vitals);

        assertNotNull(createdVitals.getId());
    }
}
