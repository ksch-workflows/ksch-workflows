/*
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
