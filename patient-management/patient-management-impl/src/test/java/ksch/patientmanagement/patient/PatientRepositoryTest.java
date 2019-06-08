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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PatientRepositoryTest {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void should_create_patient() {
        PatientEntity patient = PatientEntity.builder()
                .dateOfBirth(LocalDate.now())
                .gender(Gender.MALE)
                .name("John Doe")
                .build();

        Patient createdPatient = patientRepository.save(patient);

        assertNotNull(createdPatient.getId());
    }

    @Test
    public void should_find_patient_by_name() {
        createTestPatient("KSA-18-1001", "Jane Doe");

        List<PatientEntity> retrievedPatients = patientRepository.findByPatientNumberOrName("jane");

        assertEquals("Could not find patient in database by searching her name",
                1, retrievedPatients.size());
    }

    @Test
    public void should_find_patient_by_id() {
        createTestPatient("KSA-19-1002", "Jane Doe");

        List<PatientEntity> retrievedPatients = patientRepository.findByPatientNumberOrName("-19-");

        assertEquals("Could not find patient in database by searching her medical record number",
                1, retrievedPatients.size());
    }

    @Test
    public void should_find_patient_by_patient_number() {
        createTestPatient("KSA-19-1004", "Jane Doe");

        Optional<PatientEntity> patient = patientRepository.findByPatientNumber("KSA-19-1004");

        assertTrue("Could not find patient by patient number.", patient.isPresent());
    }

    private void createTestPatient(String patientNumber, String name) {
        patientRepository.save(PatientEntity.builder()
                .dateOfBirth(LocalDate.now())
                .gender(Gender.FEMALE)
                .name(name)
                .patientNumber(patientNumber)
                .build());
    }
}
