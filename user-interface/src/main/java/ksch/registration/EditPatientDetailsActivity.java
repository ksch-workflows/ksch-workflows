package ksch.registration;

import ksch.Activity;
import model.PatientResource;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.spring.injection.annot.SpringBean;
import ksch.patientmanagement.Gender;
import ksch.patientmanagement.PatientService;

import java.util.UUID;

import static model.PatientResource.toPatientResource;
import static util.Time.parseDate;

public class EditPatientDetailsActivity extends Activity {

    @SpringBean
    private PatientService patientService;

    public EditPatientDetailsActivity(UUID patientId) {
        PatientResource patient = toPatientResource(patientService.getById(patientId));
        add(new UpdatePatientForm(patient));
        add(new StartVisitForm());
    }

    @Override
    public String getActivityTitle() {
        return "Patient details";
    }

    @Override
    public String getPreviousPagePath() {
        return "/registration/register-patient";
    }

    class UpdatePatientForm extends Form<Void> {

        private final PatientResource patient;

        private final PatientFormFields patientFormFields;

        public UpdatePatientForm(PatientResource patient) {
            super("updatePatientForm");

            this.patient = patient;
            this.patientFormFields = new PatientFormFields(patient);

            add(patientFormFields);
        }

        @Override
        protected void onSubmit() {
            patient.setName(patientFormFields.getValue("inputName"));
            patient.setNameFather(patientFormFields.getValue("inputNameFather"));
            patient.setAddress(patientFormFields.getValue("inputAddress"));
            patient.setGender(Gender.valueOf(patientFormFields.getValue("inputGender")));
            patient.setDateOfBirth(parseDate(patientFormFields.getValue("inputDateOfBirth")));

            patientService.update(patient);
        }
    }

    class StartVisitForm extends Form<Void> {

        public StartVisitForm() {
            super("startVisitForm");
        }
    }
}


