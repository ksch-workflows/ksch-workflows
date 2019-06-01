package ksch.patientmanagement;


import ksch.patientmanagement.patient.Patient;
import org.apache.wicket.markup.html.panel.Panel;

public class GeneralPatientInformation extends Panel {

    public GeneralPatientInformation(Patient patient) {
        super("generalPatientInformation");
    }
}
