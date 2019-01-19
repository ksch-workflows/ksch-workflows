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
        add(new Label("name", patientService.getFullName(patient)));
        add(new Label("age", patientService.getAgeInYears(patient)));
    }
}
