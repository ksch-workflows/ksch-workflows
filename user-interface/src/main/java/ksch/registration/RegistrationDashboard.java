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


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;
import ksch.patientmanagement.patient.PatientQueries;
import ksch.patientmanagement.patient.PatientTransactions;
import ksch.patientmanagement.visit.Visit;
import ksch.patientmanagement.visit.VisitQueries;
import ksch.patientmanagement.visit.VisitTransactions;
import ksch.patientmanagement.visit.VisitType;
import org.springframework.beans.factory.annotation.Autowired;

@Route("registration")
@Theme(Material.class)
public class RegistrationDashboard extends VerticalLayout {

    private final transient PatientQueries patientQueries;

    private final transient PatientTransactions patientTransactions;

    private final transient VisitQueries visitQueries;

    private final transient VisitTransactions visitTransactions;

    private Grid<Visit> activeOpdVisitsTable;

    private Dialog addPatientDialog = new AddPatientDialog();

    @Autowired
    public RegistrationDashboard(
            PatientQueries patientQueries,
            PatientTransactions patientTransactions,
            VisitQueries visitQueries,
            VisitTransactions visitTransactions
    ) {
        this.patientQueries = patientQueries;
        this.patientTransactions = patientTransactions;
        this.visitQueries = visitQueries;
        this.visitTransactions = visitTransactions;

        createHeading();
        createActionBar();
        createActiveOptVisitsTable();
    }

    private void createHeading() {
        var heading = new H2("Registration dashboard");
//        heading.setMaxHeight("5px");
//        heading.getStyle().set("margin-top", "1em");
//        heading.getStyle().set("line-height", "0.1");
        add(heading);
    }

    private void createActionBar() {
        var result = new HorizontalLayout();

        var addPatientButton = new Button("Add patient", event -> {
            addPatientDialog.open();
        });
        result.add(addPatientButton);

        add(result);
    }

    private void createActiveOptVisitsTable() {
        activeOpdVisitsTable = new Grid<>();
        var activeOpdVisits = visitQueries.getAllActiveOpdVisits();

        if (!activeOpdVisits.isEmpty()) {
            activeOpdVisitsTable.setItems(activeOpdVisits);
            add(activeOpdVisitsTable);
        } else {
            add(new Label("No active OPD patients."));
        }
    }

    class AddPatientDialog extends Dialog {

        private final TextField nameInputField = new TextField();

        private final Binder<NewPatient> binder = new Binder(NewPatient.class);

        AddPatientDialog() {
            createForm();
            createButtons();
        }

        private void createForm() {
            var formLayout = new FormLayout();

            nameInputField.setLabel("Name");
            nameInputField.setRequired(true);
            nameInputField.setId("nameInputField");

            binder.forField(nameInputField)
                    .withValidator(s -> s.length() > 0, "Please provide a name for the patient.")
                    .bind(NewPatient::getName, NewPatient::setName);

            formLayout.add(nameInputField);
            add(formLayout);
        }

        private void createButtons() {
            Button confirmButton = new Button("Okay", event -> {

                try {
                    NewPatient newPatient = new NewPatient();
                    binder.writeBean(newPatient);

                    var savedPatient = patientTransactions.create(newPatient);
                    var visit = visitTransactions.startVisit(savedPatient, VisitType.OPD);

                    UI.getCurrent().navigate(PatientDetailsPage.class, visit.getId().toString());

                    close();

                } catch (ValidationException e) {
                    Notification.show(e.getMessage());
                }
            });
            Button cancelButton = new Button("Cancel", event -> {
                close();
            });
            add(confirmButton, cancelButton);
        }
    }
}
