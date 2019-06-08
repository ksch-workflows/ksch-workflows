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

package ksch.patientmanagement;

import ksch.patientmanagement.patient.PatientQueries;
import ksch.patientmanagement.patient.PatientTransactions;
import ksch.patientmanagement.visit.VisitQueries;
import ksch.patientmanagement.visit.VisitTransactions;
import ksch.wicket.MockBean;
import ksch.wicket.PageComponentTest;
import org.junit.Test;

import java.util.List;

import static ksch.patientmanagement.PatientResource.toPatientResource;

public class GeneralPatientInformationTest extends PageComponentTest {

    private PatientResource patient = toPatientResource(new TestPatient());

    @Override
    protected List<MockBean> getMockBeans() {
        return List.of(
                MockBean.of(PatientTransactions.class),
                MockBean.of(PatientQueries.class),
                MockBean.of(VisitTransactions.class),
                MockBean.of(VisitQueries.class)
        );
    }

    @Test
    public void should_render_panel_with_general_information_about_a_patient() {
        GeneralPatientInformation generalPatientInformation = new GeneralPatientInformation(patient);

        tester.startComponentInPage(generalPatientInformation);
    }
}
