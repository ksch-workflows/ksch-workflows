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

import com.vaadin.flow.component.html.H1;
import ksch.commons.PageComponentTest;
import ksch.commons.TestBean;
import ksch.patientmanagement.patient.PatientQueries;
import org.junit.Test;

import static com.github.mvysny.kaributesting.v10.LocatorJ._get;

public class RegistrationDashboardTest extends PageComponentTest {

    @TestBean
    private PatientQueries patientQueries;

    @Test
    public void should_open_registration_dashboard() {
        openPage(RegistrationDashboard.class);

        _get(H1.class, spec -> spec.withText("Registration dashboard"));

        // Test passes if elements specified above are present and visible
    }
}
