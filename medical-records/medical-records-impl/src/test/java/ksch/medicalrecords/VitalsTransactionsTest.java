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
