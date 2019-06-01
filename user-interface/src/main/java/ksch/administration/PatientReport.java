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

package ksch.administration;

import ksch.Activity;
import ksch.PatientReportQueries;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import static java.time.LocalDateTime.now;

@MountPath("/administration/patient-report")
@AuthorizeInstantiation({"ADMINISTRATOR"})
public class PatientReport extends AdministrationPage {

    public PatientReport(PageParameters pageParameters) {
        super(pageParameters);
    }

    @Override
    protected Panel getContent() {
        return new PatientReportActivity();
    }
}

class PatientReportActivity extends Activity {

    @SpringBean
    private PatientReportQueries patientReport;

    public PatientReportActivity() {
        add(new Label("numberOfNewPatient", patientReport.getNumberOfNewPatients(now().minusDays(7), now())));
    }

    @Override
    public String getActivityTitle() {
        return "Patient report";
    }

    @Override
    public String getPreviousPagePath() {
        return "/administration";
    }
}

