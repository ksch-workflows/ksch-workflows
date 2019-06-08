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
import ksch.patientmanagement.visit.VisitTransactions;
import ksch.patientmanagement.visit.VisitType;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static ksch.util.HtmlAssertions.assertContains;
import static ksch.util.HtmlAssertions.assertNotContains;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ContextConfiguration(classes = MockVisitTransactions.class)
public class EditPatientDetailsActivityTest extends WebPageTest {

    public static final String CSS_SELECTOR_DISCHARGE_BUTTON = "a[name='dischargeButton']";

    public static final String CSS_SELECTOR_START_VISIT_BUTTON =
            "button[name='content:generalPatientInformation:startVisitButton']";

    public static final String AJAX_RESPONSE_WITH_START_VISIT_BUTTON = "ajax-response.*startVisitButton";

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
        PageParameters parameters = buildPageParameters(patient);

        tester.startPage(EditPatientDetails.class, parameters);

        tester.assertRenderedPage(EditPatientDetails.class);
        tester.assertContains(patient.getPatientNumber());
        tester.assertContains(patient.getName());
    }

    @Test
    public void should_update_patient_details() {
        Patient patient = createTestPatient();
        openPatientDetails(patient);

        tester.newFormTester("content:generalPatientInformation:updatePatientForm", false)
                .setValue("patientFormFields:inputAddress", "St. Gilgen")
                .submit();

        Patient updatedPatient = patientQueries.getById(patient.getId());
        assertEquals("St. Gilgen", updatedPatient.getAddress());
    }

    @Test
    public void should_start_visit() {
        Patient patient = createTestPatient();
        openPatientDetails(patient);
        assertNotContains(currentPage(), CSS_SELECTOR_DISCHARGE_BUTTON);

        tester.newFormTester("content:generalPatientInformation:startVisitForm")
                .select("visitTypeSelection", 1)
                .submit();

        verify(visitTransactions).startVisit(any(Patient.class), any(VisitType.class));
        assertContains(currentPage(), CSS_SELECTOR_DISCHARGE_BUTTON);
        assertNotContains(currentPage(), CSS_SELECTOR_START_VISIT_BUTTON);
    }

    @Test
    public void should_discharge_patient() {
        Patient patient = createTestPatient();
        openPatientDetails(patient);
        startVisit();

        tester.clickLink("content:generalPatientInformation:dischargeButton");

        tester.assertContains(AJAX_RESPONSE_WITH_START_VISIT_BUTTON);
        // Without re-opening of the page only the Ajax response is available for verifications
        openPatientDetails(patient);

        assertContains(currentPage(), CSS_SELECTOR_START_VISIT_BUTTON);
        assertNotContains(currentPage(), CSS_SELECTOR_DISCHARGE_BUTTON);
    }

    private PageParameters buildPageParameters(Patient patient) {
        PageParameters parameters = new PageParameters();
        parameters.add("id", patient.getId());
        return parameters;
    }

    private void openPatientDetails(Patient patient) {
        PageParameters parameters = buildPageParameters(patient);
        tester.startPage(EditPatientDetails.class, parameters);
    }

    private void startVisit() {
        tester.newFormTester("content:generalPatientInformation:startVisitForm")
                .select("visitTypeSelection", 1)
                .submit();
        tester.assertComponent("content:generalPatientInformation:dischargeButton", AjaxLink.class);
        assertNotContains(currentPage(), CSS_SELECTOR_START_VISIT_BUTTON);
    }

    private String currentPage() {
        return tester.getLastResponseAsString();
    }
}
