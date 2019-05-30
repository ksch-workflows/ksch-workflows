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
package ksch.registration;

import ksch.WebPageTest;
import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.patient.PatientTransactions;
import ksch.patientmanagement.visit.Visit;
import ksch.patientmanagement.visit.VisitTransactions;
import ksch.patientmanagement.visit.VisitType;
import ksch.testdata.TestPatient;
import org.apache.wicket.util.tester.FormTester;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RegistrationDashboardTest extends WebPageTest {

    @Autowired
    private PatientTransactions patientTransactions;

    @Autowired
    private VisitTransactions visitTransactions;

    @Before
    public void setup() {
        login("user", "pwd");
    }

    @Test
    public void should_render_registration_dashboard() {
        tester.startPage(RegistrationDashboardPage.class);
        tester.assertRenderedPage(RegistrationDashboardPage.class);
    }

    @Test
    public void should_render_table_with_active_opt_patients() {
        Patient patient = patientTransactions.create(new TestPatient());
        visitTransactions.startVisit(patient, VisitType.OPD);

        tester.startPage(RegistrationDashboardPage.class);
        tester.assertRenderedPage(RegistrationDashboardPage.class);

        tester.assertContains(patient.getName());
        tester.assertContainsNot("There are no registered OPD patients.");
    }

    @Test
    public void should_register_new_patient() {
        tester.startPage(RegistrationDashboardPage.class);

        FormTester formTester = tester.newFormTester("content:addPatientForm", false);
        formTester.setValue("patientFormFields:inputName", "Ravindra Kodanda");
        formTester.setValue("patientFormFields:inputNameFather", "Javeed Sarath");
        formTester.select("patientFormFields:inputGender", 0);
        formTester.setValue("patientFormFields:dateOfBirth", "27-07-2000");
        formTester.setValue("patientFormFields:inputAddress", "Kirpal Sagar");
        formTester.submit();

        tester.assertRenderedPage(EditPatientDetails.class);
    }

    @Test
    public void should_render_message_instead_of_opt_patient_table_if_no_active_opt_visits() {
        tester.startPage(RegistrationDashboardPage.class);
        tester.assertRenderedPage(RegistrationDashboardPage.class);

        tester.assertContains("There are no registered OPD patients.");
    }

    @Test
    public void should_open_patient_details_by_entering_opt_number() {
        Patient patient = patientTransactions.create(new TestPatient());
        Visit visit = visitTransactions.startVisit(patient, VisitType.OPD);
        tester.startPage(RegistrationDashboardPage.class);

        FormTester formTester = tester.newFormTester("content:opdPatientForm", false);
        formTester.setValue("opdNumber", visit.getOpdNumber());
        formTester.submit();

        tester.assertRenderedPage(EditPatientDetails.class);
        tester.assertContains(patient.getName());
    }
}
