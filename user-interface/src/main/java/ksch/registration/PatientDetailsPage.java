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

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import ksch.patientmanagement.visit.VisitQueries;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Route("registration/visits")
public class PatientDetailsPage extends VerticalLayout implements HasUrlParameter<String>  {

    private final transient VisitQueries visitQueries;

    private UUID visitId;

    private TextField patientName;

    @Autowired
    public PatientDetailsPage(VisitQueries visitQueries) {
        this.visitQueries = visitQueries;

        createHeadline();
        createPatientNameTextField(visitQueries);
    }

    private void createHeadline() {
        add(new H2("Patient details"));
    }

    private void createPatientNameTextField(VisitQueries visitQueries) {
        patientName = new TextField();
        patientName.setId("patientName");
        patientName.setReadOnly(true);
        add(patientName);
    }

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        visitId = UUID.fromString(parameter);
        // TODO Error handling, in case the URL parameter is not a valid UUID
        // TODO Error handling, in case the URL parameter does not belong to an existing visit

        patientName.setValue(visitQueries.get(visitId).getPatient().getName());
    }
}
