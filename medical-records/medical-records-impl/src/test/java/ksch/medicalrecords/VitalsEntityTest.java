/**
 * Copyright 2019 KS-plus e.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
            public Integer getPulseInBpm() {
                return 100;
            }

            @Override
            public Integer getWeightInKg() {
                return 80;
            }
        };
    }
}
