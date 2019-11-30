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

import ksch.WebPageTest;
import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.patient.PatientQueries;
import ksch.patientmanagement.patient.PatientTransactions;
import ksch.patientmanagement.visit.Visit;
import ksch.patientmanagement.visit.VisitTransactions;
import ksch.patientmanagement.visit.VisitType;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class VisitDetailsTest extends WebPageTest {

    private static final String CSS_SELECTOR_DISCHARGE_BUTTON = "a[name='dischargeButton']";

    private static final String CSS_SELECTOR_START_VISIT_BUTTON =
            "button[name='content:generalPatientInformation:startVisitButton']";

    private static final String AJAX_RESPONSE_WITH_START_VISIT_BUTTON = "ajax-response.*startVisitButton";

    @Autowired
    private PatientTransactions patientTransactions;

    @Autowired
    private PatientQueries patientQueries;

    @Autowired
    private VisitTransactions visitTransactions;

    @Before
    public void setup() {
        login("user", "pwd");
    }

    @Test
    public void should_render_patient_details_page() {
        Patient patient = createTestPatient();
        Visit visit = visitTransactions.startVisit(patient, VisitType.OPD);
        PageParameters parameters = buildPageParameters(visit);

        tester.startPage(VisitDetails.class, parameters);

        tester.assertRenderedPage(VisitDetails.class);

        // General tab
        tester.assertContains(patient.getName());

        // Orders tab
        tester.assertContains("Surgery");
    }

    @Test
    public void should_update_patient_details() {
        Patient patient = createTestPatient();
        Visit visit = visitTransactions.startVisit(patient, VisitType.OPD);
        openPatientDetails(visit);

        tester.newFormTester("content:generalPatientInformation:updatePatientForm", false)
                .setValue("patientFormFields:inputAddress", "St. Gilgen")
                .submit();

        Patient updatedPatient = patientQueries.getById(patient.getId());
        assertEquals("St. Gilgen", updatedPatient.getAddress());
    }

    private PageParameters buildPageParameters(Visit visit) {
        PageParameters parameters = new PageParameters();
        parameters.add("visitId", visit.getId());
        return parameters;
    }

    private void openPatientDetails(Visit visit) {
        PageParameters parameters = buildPageParameters(visit);
        tester.startPage(VisitDetails.class, parameters);
    }
}
