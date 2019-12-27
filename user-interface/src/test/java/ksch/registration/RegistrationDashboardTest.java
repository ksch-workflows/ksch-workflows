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

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import ksch.commons.DummyPatient;
import ksch.commons.DummyPatientQueries;
import ksch.commons.DummyVisit;
import ksch.commons.PageComponentTest;
import ksch.commons.SpringBean;
import ksch.patientmanagement.visit.VisitQueries;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static com.github.mvysny.kaributesting.v10.GridKt.expectRows;
import static com.github.mvysny.kaributesting.v10.LocatorJ._find;
import static com.github.mvysny.kaributesting.v10.LocatorJ._get;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationDashboardTest extends PageComponentTest {

    @SpringBean
    private DummyPatientQueries patientQueries = new DummyPatientQueries();

    @Mock
    @SpringBean
    private VisitQueries visitQueries;

    @Test
    public void should_open_registration_dashboard() {
        openPage(RegistrationDashboard.class);

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

        // TODO Assert that we have a message stating that there are no active ODT visits
    }

    private void givenOnRegistrationDashboardPage() {
        openPage(RegistrationDashboard.class);
    }

    private void givenPatientWithActiveVisit() {
        var patient = new DummyPatient();
        var visit = new DummyVisit(patient);

        when(visitQueries.getAllActiveOpdVisits()).thenReturn(List.of(visit));
    }
}
