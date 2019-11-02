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

import ksch.Activity;
import ksch.orderentry.OrderManagement;
import ksch.patientmanagement.GeneralPatientInformation;
import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.patient.PatientQueries;
import ksch.patientmanagement.visit.Visit;
import ksch.patientmanagement.visit.VisitQueries;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.Optional;
import java.util.UUID;

import static ksch.patientmanagement.PatientResource.toPatientResource;

@MountPath("/registration/edit-patient/${id}")
@AuthorizeInstantiation({"NURSE", "CLERK"})
public class EditPatientDetails extends RegistrationPage {

    private final UUID patientId;

    public EditPatientDetails(PageParameters pageParameters) {
        super(pageParameters);

        patientId = UUID.fromString(pageParameters.get("id").toString());
    }

    @Override
    protected Panel getContent() {
        return new EditPatientDetailsActivity(patientId);
    }
}

class EditPatientDetailsActivity extends Activity {

    @SpringBean
    private PatientQueries patientQueries;

    @SpringBean
    private VisitQueries visitQueries;

    EditPatientDetailsActivity(UUID patientId) {
        Patient patientEntity = patientQueries.getById(patientId);
        add(new GeneralPatientInformation(toPatientResource(patientEntity)));
        add(new OrderManagement(activeVisitId(patientEntity)));
    }

    private UUID activeVisitId(Patient patient) {
        Optional<Visit> activeVisit = visitQueries.getActiveVisit(patient);
        // TODO Is there a better option for the case that there is no active visit for the current patient?
        return activeVisit.map(Visit::getId).orElse(null);
    }

    @Override
    public String getActivityTitle() {
        return "Patient details";
    }

    @Override
    public String getPreviousPagePath() {
        return "/registration";
    }
}
