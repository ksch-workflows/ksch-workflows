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


import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;
import ksch.patientmanagement.patient.PatientQueries;

@Route("registration")
@Theme(Material.class)
public class RegistrationDashboard extends AppLayout {

    private final transient PatientQueries patientQueries;

    public RegistrationDashboard(PatientQueries patientQueries) {
        this.patientQueries = patientQueries;

        createNavigationBar();
    }

    private void createNavigationBar() {
        var headline = new H1("Registration dashboard");
        headline.setMaxHeight("5px");
        headline.getStyle().set("margin-top", "1em");
        headline.getStyle().set("line-height", "0.1");
        addToNavbar(headline);
    }
}
