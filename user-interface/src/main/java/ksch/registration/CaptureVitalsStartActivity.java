package ksch.registration;

import ksch.Activity;
import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.patient.PatientService;
import ksch.patientmanagement.visit.Visit;
import ksch.patientmanagement.visit.VisitService;
import lombok.extern.slf4j.Slf4j;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.Optional;

@Slf4j
public class CaptureVitalsStartActivity extends Activity {

    @SpringBean
    private VisitService visitService;

    @SpringBean
    private PatientService patientService;

    public CaptureVitalsStartActivity() {
        add(new PatientNumberForm());
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

            add(new TextField<>("patientNumberInput"));
        }

        @Override
        protected void onSubmit() {
            Optional<Patient> patient = patientService.findByPatientNumber(patientNumberInput);

            if (patient.isPresent()) {
                Optional<Visit> activeVisit = visitService.getActiveVisit(patient.get());
                if (activeVisit.isPresent()) {
                    //Vitals vitals = vitalsService.createMedicalRecordEntry(activeVisit.get());

                    // TODO Move this block into a private method
                    PageParameters parameters = new PageParameters();
                    parameters.add("visitId", activeVisit.get().getId());
                    getRequestCycle().setResponsePage(CaptureVitalsPage.class, parameters);
                } else {
                    log.error("Could not find active visit for patient " + patient.get().getId());
                    // TODO Display error that there is no active visit for the patient
                }
            } else {
                log.error("Could not find patient with number " + patientNumberInput);
                // TODO Display error message that Patient could not be found.
            }
        }
    }
}
