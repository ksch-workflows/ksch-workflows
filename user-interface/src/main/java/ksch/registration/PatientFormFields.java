package ksch.registration;

import ksch.patientmanagement.patient.Patient;
import ksch.wicket.FormFieldsPanel;

import static com.google.common.collect.Lists.newArrayList;
import static util.Time.INDIAN_DATE_FORMAT;

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
        addTextField("inputDateOfBirth", patient.getDateOfBirth().format(INDIAN_DATE_FORMAT));
        addTextArea("inputAddress", patient.getAddress());
        addDropDownChoice("inputGender", newArrayList("MALE", "FEMALE", "OTHER"), patient.getGender().toString());
    }
}
