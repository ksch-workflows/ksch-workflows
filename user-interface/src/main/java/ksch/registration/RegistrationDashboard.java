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

import ksch.patientmanagement.PatientFormFields;
import ksch.patientmanagement.PatientResource;
import ksch.patientmanagement.patient.Gender;
import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.patient.PatientQueries;
import ksch.patientmanagement.patient.PatientTransactions;
import ksch.patientmanagement.visit.Visit;
import ksch.patientmanagement.visit.VisitQueries;
import ksch.patientmanagement.visit.VisitTransactions;
import ksch.patientmanagement.visit.VisitType;
import lombok.Getter;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.flow.RedirectToUrlException;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.toList;
import static ksch.ApplicationFrame.MAIN_CONTENT_ID;
import static ksch.wicket.Time.parseDate;

@MountPath("/registration")
@AuthorizeInstantiation({"NURSE", "CLERK"})
public class RegistrationDashboard extends RegistrationPage {

    @Override
    protected Panel getContent() {
        return new RegistrationDashboardPanel();
    }
}

class RegistrationDashboardPanel extends Panel {

    @SpringBean
    private PatientQueries patientQueries;

    @SpringBean
    private PatientTransactions patientTransactions;

    @SpringBean
    private VisitQueries visitQueries;

    @SpringBean
    private VisitTransactions visitTransactions;

    private WebMarkupContainer patientListContainer;

    RegistrationDashboardPanel() {
        super(MAIN_CONTENT_ID);

        add(createOpdPatientList());
        add(createEmptyOpdPatientListMessage());
        add(new AddPatientForm());
        add(new OpenOpdPatientForm());
    }

    private WebMarkupContainer createEmptyOpdPatientListMessage() {
        if (patientListContainer == null) {
            throw new IllegalStateException(
                    "This method needs to be called after the evaluation of the patient list container.");
        }

        WebMarkupContainer result = new WebMarkupContainer("noActiveOpdPatientVisits");

        if (patientListContainer.isVisible()) {
            result.setVisible(false);
        } else {
            result.setVisible(true);
        }

        return result;
    }

    private WebMarkupContainer createOpdPatientList() {
        patientListContainer = new WebMarkupContainer("activeOpdPatientVisits");

        List<OptPatientVisitRow> activeOptVisits = visitQueries.getAllActiveOpdVisits().stream()
                .map(OptPatientVisitRow::new)
                .collect(toList());

        ListView lv = new ListView<>("opdPatients", activeOptVisits) {
            @Override
            protected void populateItem(ListItem<OptPatientVisitRow> item) {
                OptPatientVisitRow rowData = item.getModelObject();

                item.add(new Label("opdNumber", rowData.getOpdNumber()));
                item.add(new Label("name", rowData.getName()));
                item.add(new Label("location", rowData.getLocation()));
                item.add(new Label("age", rowData.getAge()));

                var linkToVisitDetails = new Link<Void>("openPatientDetails") {
                    @Override
                    public void onClick() {
                        var parameters = new PageParameters();
                        parameters.add("visitId", rowData.getVisitId());
                        setResponsePage(VisitDetails.class, parameters);
                    }
                };
                item.add(linkToVisitDetails);
            }
        };

        patientListContainer.add(lv);

        if (activeOptVisits.isEmpty()) {
            patientListContainer.setVisible(false);
        }

        return patientListContainer;
    }

    @Getter
    static class OptPatientVisitRow implements Serializable {

        final UUID visitId;

        final String opdNumber;

        final String name;

        final String location;

        final Integer age;

        OptPatientVisitRow(Visit visit) {
            Patient patient = visit.getPatient();

            visitId = visit.getId();
            opdNumber = visit.getOpdNumber();
            name = patient.getName();
            location = patient.getAddress();
            age = patient.getAgeInYears();
        }
    }

    class AddPatientForm extends Form<Void> {

        private final PatientFormFields patientFormFields;

        AddPatientForm() {
            super("addPatientForm");

            this.patientFormFields = new PatientFormFields();

            add(patientFormFields);
        }

        @Override
        protected void onSubmit() {
            var enteredPatientData = PatientResource.builder()
                    .name(patientFormFields.getAndResetValue("inputName"))
                    .nameFather(patientFormFields.getAndResetValue("inputNameFather"))
                    .address(patientFormFields.getAndResetValue("inputAddress"))
                    .gender(Gender.valueOf(patientFormFields.getAndResetValue("inputGender").toUpperCase()))
                    .dateOfBirth(parseDate(patientFormFields.getAndResetValue("dateOfBirth")))
                    .build();

            var patient = patientTransactions.create(enteredPatientData);
            var visit = visitTransactions.startVisit(patient, VisitType.OPD);

            throw new RedirectToUrlException("/registration/visits/" + visit.getId());
        }
    }

    class OpenOpdPatientForm extends Form<OpenOpdPatientForm> {

        private String opdNumber;

        OpenOpdPatientForm() {
            super("opdPatientForm");

            setModel(new CompoundPropertyModel<>(this));

            add(new TextField<>("opdNumber"));
        }

        @Override
        protected void onSubmit() {
            Optional<Visit> visit = visitQueries.findByOpdNumber(opdNumber);
            if (visit.isPresent()) {
                throw new RedirectToUrlException("/registration/visits/" + visit.get().getId());
            } else {
                opdNumber = "";
            }
        }
    }
}
