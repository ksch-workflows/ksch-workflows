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
package ksch.patientmanagement.visit;

import ksch.patientmanagement.TestPatient;
import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.patient.PatientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static ksch.patientmanagement.patient.PatientEntity.toPatientEntity;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VisitTransactionsTest {

    @Autowired
    private VisitTransactions visitTransactions;

    @Autowired
    private VisitQueries visitQueries;

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void should_start_visit() {
        Patient patient = createTestPatient();

        Visit visit = visitTransactions.startVisit(patient, VisitType.IPD);
        boolean hasActiveVisit = visitQueries.isActive(patient);

        assertTrue("Patient has no active visit even though it has just been started.",
                hasActiveVisit);
        assertNotNull("Database did not generate a primariy key for the database record for the visit.",
                visit.getId());
        assertNotNull("OPD number for the visit wasn't created.",
                visit.getOpdNumber());
    }

    @Test
    public void should_discharge_patient() {
        Patient patient = createTestPatient();
        visitTransactions.startVisit(patient, VisitType.IPD);

        Visit visit = visitTransactions.discharge(patient);
        boolean hasActiveVisit = visitQueries.isActive(patient);

        assertFalse("Patient visit is still active even though he has just been discharged",
                hasActiveVisit);
        assertNotNull("Time of discharge not set",
                visit.getTimeEnd());
        assertTrue("End time of the visit is not after the start time of the visit",
                visit.getTimeEnd().isAfter(visit.getTimeStart()));
    }

    private Patient createTestPatient() {
        return patientRepository.save(toPatientEntity(new TestPatient()));
    }
}
