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
import ksch.patientmanagement.visit.Visit;
import ksch.patientmanagement.visit.VisitTransactions;
import ksch.patientmanagement.visit.VisitType;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class LabOrderDetailsTest extends WebPageTest {

    @Autowired
    private VisitTransactions visitTransactions;

    @Before
    public void setup() {
        login("user", "pwd");
    }

    @Test
    public void should_lab_order_details_page() {
        Patient patient = createTestPatient();
        Visit visit = visitTransactions.startVisit(patient, VisitType.OPD);

        PageParameters parameters = buildPageParameters(visit.getId());

        tester.startPage(LabOrderDetails.class, parameters);

        tester.assertRenderedPage(LabOrderDetails.class);

    }

    private PageParameters buildPageParameters(UUID visitId) {
        PageParameters parameters = new PageParameters();
        parameters.add("visitId", visitId);
        return parameters;
    }
}
