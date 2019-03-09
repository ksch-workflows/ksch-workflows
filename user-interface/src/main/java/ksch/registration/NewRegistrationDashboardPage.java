package ksch.registration;

import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.patient.PatientQueries;
import ksch.patientmanagement.visit.Visit;
import ksch.patientmanagement.visit.VisitQueries;
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
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static ksch.ApplicationFrame.MAIN_CONTENT_ID;

@MountPath("/registration-new")
@AuthorizeInstantiation({"NURSE", "CLERK"})
// TODO Remove "new" from class name before merge
public class NewRegistrationDashboardPage extends RegistrationPage {

    @Override
    protected Panel getContent() {
        return new NewRegistrationDashboard();
    }
}

class NewRegistrationDashboard extends Panel {

    @SpringBean
    private PatientQueries patientQueries;

    @SpringBean
    private VisitQueries visitQueries;

    private WebMarkupContainer patientListContainer;

    public NewRegistrationDashboard() {
        super(MAIN_CONTENT_ID);

        add(createOptPatientList());
        add(new OpenPatientDetails());
    }

    private WebMarkupContainer createOptPatientList() {
        patientListContainer = new WebMarkupContainer("activeOpdPatientVisits");

        List<PatientResource> activeOptVisits = visitQueries.getAllActiveOptVisits().stream()
                .map(Visit::getPatient)
                .map(PatientResource::toPatientResource)
                .collect(toList());

        ListView lv = new ListView<PatientResource>("opdPatients", activeOptVisits) {
            @Override
            protected void populateItem(ListItem<PatientResource> item) {
                Patient patient = item.getModelObject();

                item.add(new Label("opdNumber", patient.getPatientNumber()));
                item.add(new Label("name", patient.getName()));
                item.add(new Label("location", patient.getGender()));
                item.add(new Label("age", patient.getGender()));

                item.add(new ExternalLink("openPatientDetails", "/registration/edit-patient/" + patient.getId()));
            }
        };

        patientListContainer.add(lv);


        return patientListContainer;
    }

    class OpenPatientDetails extends Form<OpenPatientDetails> {

        private String optNumber;

        public OpenPatientDetails() {
            super("openPatientDetails");

            setModel(new CompoundPropertyModel<>(this));

            add(new TextField<>("optNumber"));
        }

        @Override
        protected void onSubmit() {
            throw new RuntimeException("Not implemented yet.");
        }
    }
}
