package ksch.registration;

import ksch.Activity;
import model.PatientResource;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.flow.RedirectToUrlException;
import org.apache.wicket.spring.injection.annot.SpringBean;
import ksch.patientmanagement.Gender;
import ksch.patientmanagement.Patient;
import ksch.patientmanagement.PatientService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static util.Time.parseDate;

public class RegisterPatientActivity extends Activity {

    private WebMarkupContainer patientListContainer;

    private WebMarkupContainer noSearchResultsMessageContainer;

    @SpringBean
    private PatientService patientService;

    public RegisterPatientActivity() {
        createPatientList();
        createNoSearchResultsMessage();

        add(new AddPatientForm());
        add(new SearchPatientForm());
    }

    @Override
    public String getActivityTitle() {
        return "Register patient";
    }

    @Override
    public String getPreviousPagePath() {
        return "/registration";
    }

    private void createPatientList() {
        patientListContainer = new WebMarkupContainer("patientList");
        patientListContainer.setVisible(false);
        add(patientListContainer);
    }

    private void createNoSearchResultsMessage() {
        noSearchResultsMessageContainer = new WebMarkupContainer("noSearchResultsMessage");
        noSearchResultsMessageContainer.setVisible(false);
        add(noSearchResultsMessageContainer);
    }

    private void renderNoSearchResultsMessage() {
        patientListContainer.setVisible(false);
        noSearchResultsMessageContainer.setVisible(true);
    }

    private void renderPatientList(List<PatientResource> matchingPatients) {
        ListView lv = new ListView<PatientResource>("patients", matchingPatients) {
            @Override
            protected void populateItem(ListItem<PatientResource> item) {
                Patient patient = item.getModelObject();

                item.add(new Label("patientNumber", patient.getPatientNumber()));
                item.add(new Label("name", patient.getName()));
                item.add(new Label("gender", patient.getGender()));
                item.add(new Label("age", patientService.getAgeInYears(patient)));

                item.add(new ExternalLink("openPatientDetails", "/registration/edit-patient/" + patient.getId()));
            }
        };

        noSearchResultsMessageContainer.setVisible(false);
        patientListContainer.removeAll(); // required to be able to re-submit search
        patientListContainer.add(lv);
        patientListContainer.setVisible(true);
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

            UUID patientId = patientService.create(patient).getId();

            throw new RedirectToUrlException("/registration/edit-patient/" + patientId);
        }
    }

    class SearchPatientForm extends Form<SearchPatientForm> {

        private String patientSearchTerm;

        public SearchPatientForm() {
            super("patientSearchForm");

            setModel(new CompoundPropertyModel<>(this));

            add(new TextField<>("patientSearchTerm"));
        }

        @Override
        protected void onSubmit() {
            List<PatientResource> matchingPatients = patientService.findByNameOrNumber(patientSearchTerm)
                    .stream()
                    .map(PatientResource::toPatientResource)
                    .collect(Collectors.toList());
            if (matchingPatients.size() > 0) {
                renderPatientList(matchingPatients);
            } else {
                renderNoSearchResultsMessage();
            }
        }
    }
}
