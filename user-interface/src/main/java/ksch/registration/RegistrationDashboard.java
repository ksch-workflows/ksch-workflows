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


import com.vaadin.flow.component.ClickEvent;
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
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;
import ksch.commons.Time;
import ksch.patientmanagement.patient.Gender;
import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.patient.PatientQueries;
import ksch.patientmanagement.patient.PatientTransactions;
import ksch.patientmanagement.visit.Visit;
import ksch.patientmanagement.visit.VisitQueries;
import ksch.patientmanagement.visit.VisitTransactions;
import ksch.patientmanagement.visit.VisitType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

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
        add(new H2("Registration dashboard"));
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
        private final TextField nameFatherInputField = new TextField();
        private final TextField dateOfBirthInputField = new TextField();
        private final TextField addressInputField = new TextField();
        private final Select<Gender> genderSelectBox = new Select<>();
        private final Binder<NewPatient> binder = createBinder();

        AddPatientDialog() {
            createForm();
            createButtons();
        }

        @SuppressWarnings({"unchecked", "rawtypes"})
        private Binder<NewPatient> createBinder() {
            return new Binder(NewPatient.class);
        }

        private void createForm() {
            var formLayout = new FormLayout();

            // Name input field
            nameInputField.setLabel("Name");
            nameInputField.setRequired(true);
            nameInputField.setId("name");
            binder.forField(nameInputField)
                    .withValidator(s -> s.length() > 0, "Please provide a name for the patient.")
                    .bind(NewPatient::getName, NewPatient::setName);
            formLayout.add(nameInputField);

            // Father's name input field
            nameFatherInputField.setLabel("Father's name");
            nameFatherInputField.setId("nameFather");
            binder.forField(nameFatherInputField)
                    .bind(NewPatient::getNameFather, NewPatient::setNameFather);
            formLayout.add(nameFatherInputField);

            // Gender select box
            genderSelectBox.setItems(Gender.MALE, Gender.FEMALE, Gender.OTHER);
            genderSelectBox.setLabel("Gender");
            genderSelectBox.setEmptySelectionAllowed(false);
            genderSelectBox.setPlaceholder("Please select");
            genderSelectBox.setId("gender");
            binder.forField(genderSelectBox)
                    .withValidator(Objects::nonNull, "Please select a gender.")
                    .bind(NewPatient::getGender, NewPatient::setGender);
            formLayout.add(genderSelectBox);

            // Date of birth input field
            dateOfBirthInputField.setLabel("Date of birth");
            dateOfBirthInputField.setId("dateOfBirth");
            dateOfBirthInputField.setRequired(true);
            binder.forField(dateOfBirthInputField)
                    .withValidator(value -> value == null || Time.isCorrectDatePattern(value),
                            "Please provide the date of birth in the correct format, e.g. 24-12-2020")
                    .bind(NewPatient::getDateOfBirthAsString, NewPatient::setDateOfBirth);
            formLayout.add(dateOfBirthInputField);

            // Address input field
            addressInputField.setLabel("Address");
            addressInputField.setId("address");
            binder.forField(addressInputField)
                    .bind(NewPatient::getAddress, NewPatient::setAddress);
            formLayout.add(addressInputField);

            add(formLayout);
        }

        private void createButtons() {
            Button confirmButton = new Button("Okay", this::handlePatientCreationConfirmed);
            Button cancelButton = new Button("Cancel", event -> closeDialog());
            add(confirmButton, cancelButton);
        }

        private void handlePatientCreationConfirmed(ClickEvent<Button> event) {
            var newPatient = getNewPatient();
            newPatient.ifPresent(p -> {
                Visit visit = createPatientAndVisit(p);
                navigateToPatientDetails(visit);
                resetFormFields();
                closeDialog();
            });
        }

        private Optional<NewPatient> getNewPatient() {
            try {
                NewPatient newPatient = new NewPatient();
                binder.writeBean(newPatient);
                return Optional.of(newPatient);
            } catch (ValidationException e) {
                Notification.show(e.getMessage());
                return Optional.empty();
            }
        }

        private Visit createPatientAndVisit(NewPatient p) {
            var savedPatient = patientTransactions.create(p);
            return visitTransactions.startVisit(savedPatient, VisitType.OPD);
        }

        private void navigateToPatientDetails(Visit visit) {
            UI.getCurrent().navigate(PatientDetailsPage.class, visit.getId().toString());
        }

        private void resetFormFields() {
            nameInputField.setValue("");
        }

        private void closeDialog() {
            close();
        }
    }

    @Getter
    @Setter
    private static class NewPatient implements Patient {
        private final UUID id = null;
        private String name;
        private String nameFather;
        private Gender gender;
        private String dateOfBirth;
        private String address;

        public LocalDate getDateOfBirth() {
            return Time.parseDate(dateOfBirth);
        }

        public String getDateOfBirthAsString() {
            return dateOfBirth;
        }
    }
}
