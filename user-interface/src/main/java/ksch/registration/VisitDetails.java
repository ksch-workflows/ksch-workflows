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
import ksch.patientmanagement.visit.VisitQueries;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.data.util.Pair;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.UUID;

import static ksch.patientmanagement.PatientResource.toPatientResource;

@MountPath("/registration/visits/${visitId}")
@AuthorizeInstantiation({"NURSE", "CLERK"})
public class VisitDetails extends RegistrationPage {

    @SpringBean
    private VisitQueries visitQueries;

    private final UUID visitId;

    private final UUID patientId;

    public VisitDetails(PageParameters pageParameters) {
        super(pageParameters);

        visitId = UUID.fromString(pageParameters.get("visitId").toString());
        patientId = visitQueries.get(visitId).getPatient().getId();
    }

    @Override
    protected Panel getContent() {
        return new VisitDetailsActivity(patientId);
    }
}

class VisitDetailsActivity extends Activity {

    @SpringBean
    private PatientQueries patientQueries;

    @SpringBean
    private VisitQueries visitQueries;

    VisitDetailsActivity(UUID patientId) {
        Patient patientEntity = patientQueries.getById(patientId);
        add(new GeneralPatientInformation(toPatientResource(patientEntity)));
        add(new OrderManagement(activeVisitId(patientEntity)));
    }

    private UUID activeVisitId(Patient patient) {
        return visitQueries.getActiveVisit(patient).orElseThrow().getId();
    }

    @Override
    public String getActivityTitle() {
        return "Patient details";
    }

    @Override
    protected Pair<Class<? extends WebPage>, PageParameters> getPreviousPage() {
        return Pair.of(RegistrationDashboard.class, new PageParameters());
    }
}
