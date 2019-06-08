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
import ksch.patientmanagement.patient.PatientEntity;
import ksch.patientmanagement.patient.PatientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VisitRepositoryTest {

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void should_store_visit() {
        PatientEntity patient = createTestPatient("KSA-123", "John Doe");

        VisitEntity visit = createNewVisit(patient);

        assertNotNull("Could not generate primary key in database", visit.getId());
        assertTrue("Could not find any visit in database.", visitRepository.findAll().iterator().hasNext());
    }

    @Test
    public void should_find_visits_by_patient_id() {
        PatientEntity patient = createTestPatient("KSA-124", "John Doe");
        VisitEntity visit = createNewVisit(patient);

        List<VisitEntity> retrievedVisits = visitRepository.findAllByPatientId(patient.getId());

        assertEquals(1, retrievedVisits.size());
    }

    @Test
    public void should_find_all_active_opt_visits() {
        createNewVisit(createTestPatient("KSA-124", "John Doe"), VisitType.OPD);
        createNewVisit(createTestPatient("KSA-125", "Jane Doe"), VisitType.IPD);

        List<VisitEntity> allActiveOptVisits = visitRepository.findAllActiveOptVisits();

        assertEquals(1, allActiveOptVisits.size());
    }

    private VisitEntity createNewVisit(PatientEntity patient) {
        return createNewVisit(patient, VisitType.OPD);
    }

    private VisitEntity createNewVisit(PatientEntity patient, VisitType visitType) {
        VisitEntity visit = VisitEntity.builder()
                .patient(patient)
                .type(visitType)
                .timeStart(LocalDateTime.now())
                .build();

        return visitRepository.save(visit);
    }

    private PatientEntity createTestPatient(String patientNumber, String name) {
        return patientRepository.save(PatientEntity.builder()
                .dateOfBirth(LocalDate.now())
                .gender(Gender.FEMALE)
                .name(name)
                .patientNumber(patientNumber)
                .build());
    }
}
