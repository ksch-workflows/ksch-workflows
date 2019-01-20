package ksch;

import ksch.patientmanagement.patient.Patient;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

public class PatientInfoBar extends Panel {

    public PatientInfoBar(Patient patient) {
        super("patientInfoBar");

        add(new Label("patientNumber", patient.getPatientNumber()));
        add(new Label("name", patient.getFullName()));
        add(new Label("age", patient.getAgeInYears()));
    }
}
