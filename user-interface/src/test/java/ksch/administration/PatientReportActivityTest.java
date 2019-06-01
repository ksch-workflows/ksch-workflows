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

import ksch.WebPageTest;
import org.junit.Test;
import ksch.PatientReportQueries;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class PatientReportActivityTest extends WebPageTest {

    @MockBean
    private PatientReportQueries patientReport;

    @Test
    public void should_render_patient_report_activity() {
        login("user", "pwd");
        given(patientReport.getNumberOfNewPatients(any(), any())).willReturn(42);

        tester.startPage(PatientReport.class);

        tester.assertContains("42");
        tester.assertRenderedPage(PatientReport.class);
    }
}
