package ksch.registration;

import ksch.Activity;
import model.PatientResource;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.leanhis.patientmanagement.Gender;
import org.leanhis.patientmanagement.Patient;
import org.leanhis.patientmanagement.PatientService;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.UUID;

import static model.PatientResource.toPatientResource;
import static util.Time.parseDate;

@MountPath("/registration/edit-patient/${id}")
@AuthorizeInstantiation({"NURSE", "CLERK"})
public class EditPatientDetailsActivity extends Activity {

    @SpringBean
    private PatientService patientService;

    public EditPatientDetailsActivity(PageParameters pageParameters) {
        super(pageParameters);

        UUID patientID = UUID.fromString(pageParameters.get("id").toString());
        PatientResource patient = toPatientResource(patientService.getById(patientID));

        add(new UpdatePatientForm(patient));
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
}


