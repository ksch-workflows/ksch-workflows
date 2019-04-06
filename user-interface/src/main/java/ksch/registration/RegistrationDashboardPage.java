package ksch.registration;

import ksch.patientmanagement.patient.Gender;
import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.patient.PatientQueries;
import ksch.patientmanagement.patient.PatientTransactions;
import ksch.patientmanagement.visit.Visit;
import ksch.patientmanagement.visit.VisitQueries;
import lombok.Getter;
import model.PatientResource;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.flow.RedirectToUrlException;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.toList;
import static ksch.ApplicationFrame.MAIN_CONTENT_ID;
import static util.Time.parseDate;

@MountPath("/registration")
@AuthorizeInstantiation({"NURSE", "CLERK"})
public class RegistrationDashboardPage extends RegistrationPage {

    @Override
    protected Panel getContent() {
        return new RegistrationDashboard();
    }
}

class RegistrationDashboard extends Panel {

    @SpringBean
    private PatientQueries patientQueries;

    @SpringBean
    private PatientTransactions patientTransactions;

    @SpringBean
    private VisitQueries visitQueries;

    private WebMarkupContainer patientListContainer;

    public RegistrationDashboard() {
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

        ListView lv = new ListView<OptPatientVisitRow>("opdPatients", activeOptVisits) {
            @Override
            protected void populateItem(ListItem<OptPatientVisitRow> item) {
                OptPatientVisitRow rowData = item.getModelObject();

                item.add(new Label("opdNumber", rowData.getOpdNumber()));
                item.add(new Label("name", rowData.getName()));
                item.add(new Label("location", rowData.getLocation()));
                item.add(new Label("age", rowData.getAge()));

                item.add(new ExternalLink(
                        "openPatientDetails",
                        "/registration/edit-patient/" + rowData.getPatientId())
                );
            }
        };

        patientListContainer.add(lv);

        if (activeOptVisits.isEmpty()) {
            patientListContainer.setVisible(false);
        }

        return patientListContainer;
    }

    @Getter
    class OptPatientVisitRow implements Serializable {

        final UUID patientId;

        final String opdNumber;

        final String name;

        final String location;

        final Integer age;

        public OptPatientVisitRow(Visit visit) {
            Patient patient = visit.getPatient();

            patientId = patient.getId();
            opdNumber = visit.getOpdNumber();
            name = patient.getName();
            location = patient.getAddress();
            age = patient.getAgeInYears();
        }
    }

    class AddPatientForm extends Form<Void> {

        private final PatientFormFields patientFormFields;

        public AddPatientForm() {
            super("addPatientForm");

            this.patientFormFields = new PatientFormFields();

            add(patientFormFields);
        }

        @Override
        protected void onSubmit() {
            PatientResource patient = PatientResource.builder()
                    .name(patientFormFields.getAndResetValue("inputName"))
                    .nameFather(patientFormFields.getAndResetValue("inputNameFather"))
                    .address(patientFormFields.getAndResetValue("inputAddress"))
                    .gender(Gender.valueOf(patientFormFields.getAndResetValue("inputGender").toUpperCase()))
                    .dateOfBirth(parseDate(patientFormFields.getAndResetValue("inputDateOfBirth")))
                    .build();

            UUID patientId = patientTransactions.create(patient).getId();

            throw new RedirectToUrlException("/registration/edit-patient/" + patientId);
        }
    }

    class OpenOpdPatientForm extends Form<OpenOpdPatientForm> {

        private String opdNumber;

        public OpenOpdPatientForm() {
            super("opdPatientForm");

            setModel(new CompoundPropertyModel<>(this));

            add(new TextField<>("opdNumber"));
        }

        @Override
        protected void onSubmit() {
            Optional<Visit> visit = visitQueries.findByOpdNumber(opdNumber);
            if (visit.isPresent()) {
                UUID patientId = visit.get().getPatient().getId();
                throw new RedirectToUrlException("/registration/edit-patient/" + patientId);
            } else {
                opdNumber = "";
            }
        }
    }
}
