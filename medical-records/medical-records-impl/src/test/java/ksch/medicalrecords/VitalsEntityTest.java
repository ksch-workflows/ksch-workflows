package ksch.medicalrecords;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static ksch.util.CustomAssertions.assertAllPropertiesEqual;

public class VitalsEntityTest {

    @Test
    public void should_build_patient_entity() {
        Vitals vitals = buildVitals();

        VitalsEntity e = VitalsEntity.toVitalsEntity(vitals);

        assertAllPropertiesEqual(Vitals.class, vitals, e);
    }

    private Vitals buildVitals() {
        return new Vitals() {

            @Override
            public UUID getId() {
                return UUID.fromString("15e5145d-36f1-46a7-9dd2-30b90c936e92");
            }

            @Override
            public UUID getVisitId() {
                return UUID.fromString("95a82244-15b6-4115-951a-b99ccae86e9c");
            }

            @Override
            public LocalDateTime getTime() {
                return LocalDateTime.of(2018, 11, 30, 14, 56);
            }

            @Override
            public Integer getSystolicInMmHg() {
                return 100;
            }

            @Override
            public Integer getDiastolicInMmHg() {
                return 70;
            }

            @Override
            public Float getTemperatureInF() {
                return 89.7f;
            }

            @Override
            public Integer getPulseInBPM() {
                return 100;
            }

            @Override
            public Integer getWeightInKG() {
                return 80;
            }
        };
    }
}
