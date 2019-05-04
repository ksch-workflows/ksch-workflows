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
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static java.time.LocalDateTime.now;
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
                .time(now())
                .systolicInMmHg(100)
                .diastolicInMmHg(70)
                .build();

        VitalsEntity createdVitals = vitalsRepository.save(vitals);

        assertNotNull(createdVitals.getId());
    }
}
