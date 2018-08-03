package ksch.registration;

import ksch.ApplicationFrame;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.leanhis.patientmanagement.Gender;
import org.leanhis.patientmanagement.Patient;
import org.leanhis.patientmanagement.PatientService;
import org.wicketstuff.annotation.mount.MountPath;
import util.wicket.FormBuilder;

import java.time.LocalDate;
import java.util.UUID;

import static com.google.common.collect.Lists.newArrayList;
import static util.Time.INDIAN_DATE_FORMAT;
import static util.Time.parseDate;

@MountPath("/registration/edit-patient/${id}")
@AuthorizeInstantiation({"NURSE", "CLERK"})
public class EditPatientDetailsActivity extends ApplicationFrame {

    @SpringBean
    private PatientService patientService;

    public EditPatientDetailsActivity(PageParameters pageParameters) {
        super(pageParameters);

        UUID patientID = UUID.fromString(pageParameters.get("id").toString());

        add(buildUpdatePatientForm(patientService.getById(patientID)));
    }

    private Form buildUpdatePatientForm(Patient patient) {
        return new FormBuilder("updatePatientForm")
                .textField("inputName", patient.getName())
                .textField("inputNameFather", patient.getNameFather())
                .textArea("inputAddress", patient.getAddress())
                .textField("inputDateOfBirth", patient.getDateOfBirth().format(INDIAN_DATE_FORMAT))
                .dropDownChoice("inputGender", newArrayList("MALE", "FEMALE", "OTHER"), patient.getGender().toString())
                .onSubmit(f -> {
                    patient.setName(f.getValue("inputName"));
                    patient.setNameFather(f.getValue("inputNameFather"));
                    patient.setAddress(f.getValue("inputAddress"));
                    patient.setGender(Gender.valueOf(f.getValue("inputGender")));
                    patient.setDateOfBirth(parseDate(f.getValue("inputDateOfBirth")));

                    patientService.update(patient);
                })
                .build();
    }
}
