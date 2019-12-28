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

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import ksch.commons.Time;
import ksch.patientmanagement.patient.Gender;
import ksch.patientmanagement.patient.PatientTransactions;
import ksch.patientmanagement.visit.Visit;
import ksch.patientmanagement.visit.VisitTransactions;
import ksch.patientmanagement.visit.VisitType;
import ksch.registration.PatientDetailsPage;

import java.util.Objects;
import java.util.Optional;

@SuppressWarnings({"unchecked", "rawtypes"})
class AddPatientDialog extends Dialog {

    private final PatientTransactions patientTransactions;

    private final VisitTransactions visitTransactions;

    private Binder<NewPatient> binder = new Binder(NewPatient.class);

    AddPatientDialog(PatientTransactions patientTransactions, VisitTransactions visitTransactions) {
        this.patientTransactions = patientTransactions;
        this.visitTransactions = visitTransactions;

        createForm();
        createButtons();
    }

    private void createForm() {
        var formLayout = new FormLayout();

        formLayout.add(createNameInputField());
        formLayout.add(createNameFatherInputField());
        formLayout.add(createGenderSelectBox());
        formLayout.add(createDateOfBirthInputField());
        formLayout.add(createAddressInputField());

        add(formLayout);
    }

    private TextField createNameInputField() {
        var result = new TextField();
        result.setLabel("Name");
        result.setRequired(true);
        result.setId("name");
        binder.forField(result)
                .withValidator(s -> s.length() > 0, "Please provide a name for the patient.")
                .bind(NewPatient::getName, NewPatient::setName);
        return result;
    }

    private TextField createDateOfBirthInputField() {
        var result = new TextField();
        result.setLabel("Date of birth");
        result.setId("dateOfBirth");
        result.setRequired(true);
        binder.forField(result)
                .withValidator(value -> value == null || Time.isCorrectDatePattern(value),
                        "Please provide the date of birth in the correct format, e.g. 24-12-2020")
                .bind(NewPatient::getDateOfBirthAsString, NewPatient::setDateOfBirth);
        return result;
    }

    private Select createGenderSelectBox() {
        var result = new Select<Gender>();
        result.setItems(Gender.MALE, Gender.FEMALE, Gender.OTHER);
        result.setLabel("Gender");
        result.setEmptySelectionAllowed(false);
        result.setPlaceholder("Please select");
        result.setId("gender");
        binder.forField(result)
                .withValidator(Objects::nonNull, "Please select a gender.")
                .bind(NewPatient::getGender, NewPatient::setGender);
        return result;
    }

    private TextField createNameFatherInputField() {
        var result = new TextField();
        result.setLabel("Father's name");
        result.setId("nameFather");
        binder.forField(result)
                .bind(NewPatient::getNameFather, NewPatient::setNameFather);
        return result;
    }

    private TextField createAddressInputField() {
        var addressInputField = new TextField();
        addressInputField.setLabel("Address");
        addressInputField.setId("address");
        binder.forField(addressInputField)
                .bind(NewPatient::getAddress, NewPatient::setAddress);
        return addressInputField;
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

    private void closeDialog() {
        close();
    }
}
