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

package ksch.registration.dashboard;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;
import ksch.patientmanagement.patient.PatientTransactions;
import ksch.patientmanagement.visit.VisitQueries;
import ksch.patientmanagement.visit.VisitTransactions;
import org.springframework.beans.factory.annotation.Autowired;

import static java.util.stream.Collectors.toList;

@Route("registration")
@Theme(Material.class)
public class RegistrationDashboardPage extends VerticalLayout {

    private final transient PatientTransactions patientTransactions;

    private final transient VisitQueries visitQueries;

    private final transient VisitTransactions visitTransactions;

    private Grid<OpdPatientVisitRow> activeOpdVisitsTable;

    @Autowired
    public RegistrationDashboardPage(
            PatientTransactions patientTransactions,
            VisitQueries visitQueries,
            VisitTransactions visitTransactions
    ) {
        this.patientTransactions = patientTransactions;
        this.visitQueries = visitQueries;
        this.visitTransactions = visitTransactions;

        createHeading();
        createActionBar();
        createActiveOptVisitsTable();
    }

    private void createHeading() {
        add(new H2("Registration dashboard"));
    }

    private void createActionBar() {
        var result = new HorizontalLayout();

        var addPatientButton = new Button("Add patient", event -> {
            new AddPatientDialog(patientTransactions, visitTransactions).open();
        });
        result.add(addPatientButton);

        add(result);
    }

    private void createActiveOptVisitsTable() {
        activeOpdVisitsTable = new Grid<>(OpdPatientVisitRow.class);

        activeOpdVisitsTable.setColumns("opdNumber", "name", "location", "age");
        activeOpdVisitsTable.getColumnByKey("opdNumber").setHeader("OPD No.");

        var activeOpdVisits = visitQueries.getAllActiveOpdVisits()
                .stream()
                .map(OpdPatientVisitRow::new)
                .collect(toList());

        if (!activeOpdVisits.isEmpty()) {
            activeOpdVisitsTable.setItems(activeOpdVisits);
            add(activeOpdVisitsTable);
        } else {
            add(new Label("No active OPD patients."));
        }
    }
}
