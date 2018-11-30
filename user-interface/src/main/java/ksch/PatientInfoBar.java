package ksch;

import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.patient.PatientService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class PatientInfoBar extends Panel {

    @SpringBean
    private PatientService patientService;

    public PatientInfoBar(Patient patient) {
        super("patientInfoBar");

        add(new Label("patientNumber", patient.getPatientNumber()));
        add(new Label("name", PatientService.getFullName(patient))); // TODO Use normal service method here as well?
        add(new Label("location", "")); // TODO Include patient location
        add(new Label("age", patientService.getAgeInYears(patient)));
    }
}
