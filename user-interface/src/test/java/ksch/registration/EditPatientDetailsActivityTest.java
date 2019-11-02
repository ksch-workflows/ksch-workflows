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

package ksch.registration;

import ksch.patientmanagement.patient.PatientQueries;
import ksch.patientmanagement.patient.PatientTransactions;
import ksch.patientmanagement.visit.VisitQueries;
import ksch.patientmanagement.visit.VisitTransactions;
import ksch.testdata.TestPatient;
import ksch.wicket.MockBean;
import ksch.wicket.PageComponentTest;
import org.junit.Test;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class EditPatientDetailsActivityTest extends PageComponentTest {

    @MockBean
    private PatientQueries patientQueries;

    @MockBean
    private PatientTransactions patientTransactions;

    @MockBean
    private VisitQueries visitQueries;

    @MockBean
    private VisitTransactions visitTransactions;

    @Test
    public void should_validate_that_there_is_an_active_visit_for_the_patient() {
        var patient = new TestPatient();
        when(patientQueries.getById(eq(patient.getId()))).thenReturn(patient);

        var editPatientDetailsActivity = new EditPatientDetailsActivity(patient.getId());

        tester.startComponentInPage(editPatientDetailsActivity);
        
        fail();
    }
}
