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

package ksch.patientmanagement.visit;

import ksch.patientmanagement.patient.Gender;
import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.patient.PatientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static ksch.patientmanagement.patient.PatientEntity.toPatientEntity;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VisitQueriesTest {

    @Autowired
    private VisitQueries visitQueries;

    @Autowired
    private VisitTransactions visitTransactions;

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void should_retrieve_visit_entity_by_id() {
        Patient patient = createTestPatient();
        Visit visit = visitTransactions.startVisit(patient, VisitType.IPD);

        Visit retrievedVisit = visitQueries.get(visit.getId());

        assertNotNull("Could not retrieve existing Visit entity by its technical identifier.", retrievedVisit);
    }

    @Test
    public void should_retrieve_visit_entity_by_opd_number() {
        Patient patient = createTestPatient();
        Visit visit = visitTransactions.startVisit(patient, VisitType.IPD);

        Optional<Visit> retrievedVisit = visitQueries.findByOpdNumber(visit.getOpdNumber());

        assertTrue("Could not retrieve existing Visit entity by OPD number.", retrievedVisit.isPresent());
    }

    @Test
    public void should_not_be_active_for_new_patient() {
        Patient patient = createTestPatient();

        boolean hasActiveVisit = visitQueries.isActive(patient);

        assertFalse("Patient has an active visit while it was actually not yet started", hasActiveVisit);
    }

    @Test
    public void should_retrieve_active_visit_entity() {
        Patient patient = createTestPatient();
        visitTransactions.startVisit(patient, VisitType.IPD);

        Optional<Visit> activeVisit = visitQueries.getActiveVisit(patient);

        assertTrue("Could not find an active visit for the test patient.", activeVisit.isPresent());
    }

    @Test
    public void should_retrieve_emtpy_visit_entity_if_no_active_visit() {
        Patient patient = createTestPatient();

        Optional<Visit> activeVisit = visitQueries.getActiveVisit(patient);

        assertFalse("There should not be an active visit for the test patient.", activeVisit.isPresent());
    }

    @Test
    public void test_access_on_patient() {
        Patient patient = createTestPatient();
        Visit visit = visitTransactions.startVisit(patient, VisitType.IPD);

        Patient retrievedPatient = visitQueries.getPatient(visit.getId());

        assertEquals(patient.getId(), retrievedPatient.getId());
    }

    private Patient createTestPatient() {
        return patientRepository.save(toPatientEntity(buildTestPatient()));
    }

    private Patient buildTestPatient() {
        return new Patient() {
            @Override
            public UUID getId() {
                return UUID.randomUUID();
            }

            @Override
            public String getName() {
                return "John";
            }

            @Override
            public String getNameFather() {
                return null;
            }

            @Override
            public LocalDate getDateOfBirth() {
                return LocalDate.now();
            }

            @Override
            public Gender getGender() {
                return Gender.MALE;
            }

            @Override
            public String getAddress() {
                return "Jena, Germany";
            }
        };
    }
}
