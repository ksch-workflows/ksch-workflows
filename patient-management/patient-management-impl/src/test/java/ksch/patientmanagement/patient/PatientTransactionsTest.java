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
