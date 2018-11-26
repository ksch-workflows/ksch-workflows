package ksch.registration;

import ksch.Activity;
import ksch.patientmanagement.patient.Gender;
import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.patient.PatientService;
import ksch.patientmanagement.visit.VisitService;
import ksch.patientmanagement.visit.VisitType;
import lombok.Getter;
import model.PatientResource;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static model.PatientResource.toPatientResource;
import static util.Time.parseDate;

public class EditPatientDetailsActivity extends Activity {

    @SpringBean
    private PatientService patientService;

    @SpringBean
    private VisitService visitService;

    private final Patient patient;

    private final Button startVisitButton;

    private final AjaxLink<Void> dischargeButton;

    public EditPatientDetailsActivity(UUID patientId) {
        PatientResource patientResource = toPatientResource(patientService.getById(patientId));
        this.patient = patientResource;

        this.startVisitButton = new Button("startVisitButton");
        startVisitButton.setOutputMarkupId(true);
        startVisitButton.setOutputMarkupPlaceholderTag(true);

        this.dischargeButton = new AjaxLink<Void>("dischargeButton") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                visitService.discharge(patient);

                startVisitButton.setVisible(true);
                dischargeButton.setVisible(false);

                target.add(startVisitButton);
                target.add(dischargeButton);
            }
        };

        dischargeButton.setOutputMarkupId(true);
        dischargeButton.setOutputMarkupPlaceholderTag(true);

        add(new UpdatePatientForm(patientResource));
        add(new StartVisitForm());
        add(startVisitButton);
        add(dischargeButton);

        if (visitService.isActive(patient)) {
            startVisitButton.setVisible(false);
            dischargeButton.setVisible(true);
        } else {
            startVisitButton.setVisible(true);
            dischargeButton.setVisible(false);
        }
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

    @Getter
    class StartVisitForm extends Form<Void> {

        private final List<String> visitTypes = Arrays.stream(VisitType.values())
                .map(Enum::toString)
                .collect(Collectors.toList());

        private String visitType;

        public StartVisitForm() {
            super("startVisitForm");

            PropertyModel<String> visitType = new PropertyModel<>(this, "visitType");
            add(new RadioChoice<>("visitTypeSelection", visitType, visitTypes));
        }

        @Override
        protected void onSubmit() {
            visitService.startVisit(patient, VisitType.valueOf(visitType));

            startVisitButton.setVisible(false);
            dischargeButton.setVisible(true);
        }
    }
}


