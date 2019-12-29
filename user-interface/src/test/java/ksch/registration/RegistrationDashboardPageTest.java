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

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import ksch.api.DummyPatient;
import ksch.commons.PageComponentTest;
import ksch.patientmanagement.patient.Gender;
import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.patient.PatientQueries;
import ksch.patientmanagement.patient.PatientTransactions;
import ksch.patientmanagement.visit.VisitQueries;
import ksch.patientmanagement.visit.VisitTransactions;
import ksch.patientmanagement.visit.VisitType;
import ksch.registration.dashboard.RegistrationDashboardPage;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.time.LocalDate;

import static com.github.mvysny.kaributesting.v10.GridKt.expectRows;
import static com.github.mvysny.kaributesting.v10.LocatorJ._click;
import static com.github.mvysny.kaributesting.v10.LocatorJ._find;
import static com.github.mvysny.kaributesting.v10.LocatorJ._get;
import static com.github.mvysny.kaributesting.v10.LocatorJ._setValue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@SuppressWarnings("unchecked")
public class RegistrationDashboardPageTest extends PageComponentTest {

    @Autowired
    private PatientQueries patientQueries;

    @SpyBean
    private PatientTransactions patientTransactions;

    @Autowired
    private VisitQueries visitQueries;

    @Autowired
    private VisitTransactions visitTransactions;

    @Captor
    private ArgumentCaptor<Patient> patientArgumentCaptor;

    @Test
    public void should_open_registration_dashboard() {
        openPage(RegistrationDashboardPage.class);

        _get(H2.class, spec -> spec.withText("Registration dashboard"));

        // Test passes if elements specified above are present and visible
    }

    @Test
    public void should_render_table_with_active_opt_patients() {
        givenPatientWithActiveVisit();
        givenOnRegistrationDashboardPage();

        assertEquals(1, _find(Grid.class).size());
        expectRows(_get(Grid.class), 1);
    }

    @Test
    public void should_not_render_table_if_no_active_opt_patient() {
        givenOnRegistrationDashboardPage();

        assertEquals(0, _find(Grid.class).size());
        _get(Label.class, spec -> spec.withText("No active OPD patients."));
    }

    @Test
    public void should_register_new_patient() {
        // GIVEN
        givenOnRegistrationDashboardPage();

        // WHEN
        _click(_get(Button.class, spec -> spec.withText("Add patient")));
        _setValue(_get(TextField.class, spec -> spec.withId("name")), "Ravindra Kodanda");
        _setValue(_get(TextField.class, spec -> spec.withId("nameFather")), "Javeed Sarath");
        _setValue(_get(TextField.class, spec -> spec.withId("dateOfBirth")), "27-07-2000");
        _setValue(_get(TextField.class, spec -> spec.withId("address")), "Kirpal Sagar");
        _setValue(_get(Select.class, spec -> spec.withId("gender")), Gender.MALE);

        _click(_get(Button.class, spec -> spec.withText("Okay")));

        // THEN
        verify(patientTransactions).create(patientArgumentCaptor.capture());
        var createdPatient = patientArgumentCaptor.getValue();
        assertEquals("Ravindra Kodanda", createdPatient.getName());
        assertEquals("Javeed Sarath", createdPatient.getNameFather());
        assertEquals("Kirpal Sagar", createdPatient.getAddress());
        assertEquals(LocalDate.of(2000, 7, 27), createdPatient.getDateOfBirth());
        assertEquals(Gender.MALE, createdPatient.getGender());
        verifyNavigatedToPatientDetails();
    }

    private void givenOnRegistrationDashboardPage() {
        openPage(RegistrationDashboardPage.class);
    }

    private void givenPatientWithActiveVisit() {
        var patient = patientTransactions.create(new DummyPatient());
        visitTransactions.startVisit(patient, VisitType.OPD);
    }

    private void verifyNavigatedToPatientDetails() {
        assertEquals("Patient details", _get(H2.class).getText());
    }
}
