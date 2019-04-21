package ksch.patientmanagement;

import ksch.patientmanagement.patient.Patient;
import ksch.wicket.FormFieldsPanel;
import ksch.wicket.Time;

import static com.google.common.collect.Lists.newArrayList;

public class PatientFormFields extends FormFieldsPanel {

    public PatientFormFields() {
        super("patientFormFields");

        addTextField("inputName");
        addTextField("inputNameFather");
        addTextField("inputDateOfBirth");
        addTextArea("inputAddress");
        addDropDownChoice("inputGender", newArrayList("MALE", "FEMALE", "OTHER"));
    }

    public PatientFormFields(Patient patient) {
        super("patientFormFields");

        addTextField("inputName", patient.getName());
        addTextField("inputNameFather", patient.getNameFather());
        addTextField("inputDateOfBirth", patient.getDateOfBirth().format(Time.INDIAN_DATE_FORMAT));
        addTextArea("inputAddress", patient.getAddress());
        addDropDownChoice("inputGender", newArrayList("MALE", "FEMALE", "OTHER"), patient.getGender().toString());
    }
}
