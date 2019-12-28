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

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.textfield.TextField;
import ksch.api.DummyPatient;
import ksch.commons.PageComponentTest;
import ksch.commons.RouteNotFoundError;
import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.patient.PatientTransactions;
import ksch.patientmanagement.visit.Visit;
import ksch.patientmanagement.visit.VisitTransactions;
import ksch.patientmanagement.visit.VisitType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static com.github.mvysny.kaributesting.v10.LocatorJ._get;
import static org.junit.Assert.assertEquals;

public class PatientDetailsPageTest extends PageComponentTest {

    @Autowired
    private PatientTransactions patientTransactions;

    @Autowired
    private VisitTransactions visitTransactions;

    private Patient patient;

    private Visit visit;

    @Test
    public void should_render_patient_details() {
        givenPatientWithActiveVisit();

        UI.getCurrent().navigate(PatientDetailsPage.class, visit.getId().toString());

        assertEquals("Patient details", _get(H2.class).getText());
        assertEquals("John Doe", _get(TextField.class, spec -> spec.withId("patientName")).getValue());
    }

    @Test
    public void should_show_error_for_invalid_visit_id() {
        UI.getCurrent().navigate(PatientDetailsPage.class, UUID.randomUUID().toString());

        _get(RouteNotFoundError.class);
    }

    private void givenPatientWithActiveVisit() {
        patient = patientTransactions.create(new DummyPatient());
        visit = visitTransactions.startVisit(patient, VisitType.OPD);
    }
}
