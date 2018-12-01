package ksch.registration;

import ksch.Activity;
import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.patient.PatientService;
import ksch.patientmanagement.visit.Visit;
import ksch.patientmanagement.visit.VisitService;
import lombok.extern.slf4j.Slf4j;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

import java.util.Optional;

@Slf4j
public class CaptureVitalsStartActivity extends Activity {

    @SpringBean
    private VisitService visitService;

    @SpringBean
    private PatientService patientService;

    public CaptureVitalsStartActivity() {
        add(new PatientNumberForm());
        add(new FeedbackPanel("feedbackMessage"));
    }

    @Override
    protected String getActivityTitle() {
        return "Capture vitals";
    }

    @Override
    protected String getPreviousPagePath() {
        return "/registration";
    }

    class PatientNumberForm extends Form<PatientNumberForm> {

        private String patientNumberInput;

        PatientNumberForm() {
            super("patientNumberForm");

            setModel(new CompoundPropertyModel<>(this));

            TextField<String> patientNumberInput = new TextField<>("patientNumberInput");
            patientNumberInput.add(new PatientExistsValidator());
            patientNumberInput.add(new ActiveVisitValidator());
            add(patientNumberInput);
        }

        @Override
        protected void onSubmit() {
            patientService.findByPatientNumber(patientNumberInput)
                    .map(patient -> visitService.getActiveVisit(patient))
                    .ifPresent(visit -> {
                        PageParameters parameters = new PageParameters();
                        parameters.add("visitId", visit.get().getId());
                        getRequestCycle().setResponsePage(CaptureVitalsPage.class, parameters);
                    });
        }
    }

    class PatientExistsValidator implements IValidator<String> {

        @Override
        public void validate(IValidatable<String> enteredPatientNumber) {
            Optional<Patient> patient = patientService.findByPatientNumber(enteredPatientNumber.getValue());
            if (!patient.isPresent()) {
                ValidationError error = new ValidationError(this);
                error.setMessage("Could not find patient with number " + enteredPatientNumber.getValue());
                enteredPatientNumber.error(error);
            }
        }
    }

    class ActiveVisitValidator implements IValidator<String> {

        @Override
        public void validate(IValidatable<String> enteredPatientNumber) {
            Optional<Patient> patient = patientService.findByPatientNumber(enteredPatientNumber.getValue());
            if (patient.isPresent()) {
                Optional<Visit> activeVisit = visitService.getActiveVisit(patient.get());
                if (!activeVisit.isPresent()) {
                    ValidationError error = new ValidationError(this);
                    error.setMessage("Could not find active visit for patient " + enteredPatientNumber.getValue());
                    enteredPatientNumber.error(error);
                }
            }
        }
    }
}
