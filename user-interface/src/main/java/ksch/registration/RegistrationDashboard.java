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
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;
import ksch.patientmanagement.patient.PatientQueries;
import ksch.patientmanagement.visit.Visit;
import ksch.patientmanagement.visit.VisitQueries;
import org.springframework.beans.factory.annotation.Autowired;

@Route("registration")
@Theme(Material.class)
public class RegistrationDashboard extends VerticalLayout {

    private final transient PatientQueries patientQueries;

    private final transient VisitQueries visitQueries;

    private Grid<Visit> activeOpdVisitsTable;

    @Autowired
    public RegistrationDashboard(PatientQueries patientQueries, VisitQueries visitQueries) {
        this.patientQueries = patientQueries;
        this.visitQueries = visitQueries;

        createHeading();
        createActiveOptVisitsTable();
    }

    private void createHeading() {
        var heading = new H2("Registration dashboard");
//        heading.setMaxHeight("5px");
//        heading.getStyle().set("margin-top", "1em");
//        heading.getStyle().set("line-height", "0.1");
        add(heading);
    }

    private void createActiveOptVisitsTable() {
        activeOpdVisitsTable = new Grid<>();
        var activeOpdVisits = visitQueries.getAllActiveOpdVisits();

        if (!activeOpdVisits.isEmpty()) {
            activeOpdVisitsTable.setItems(activeOpdVisits);
            add(activeOpdVisitsTable);
        }
    }
}
