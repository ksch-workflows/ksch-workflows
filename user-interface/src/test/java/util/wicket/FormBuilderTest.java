package util.wicket;

import ksch.WebPageTest;
import org.apache.wicket.markup.html.form.Form;
import org.junit.Test;
import org.leanhis.patientmanagement.Gender;
import org.leanhis.patientmanagement.Patient;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.leanhis.patientmanagement.Gender.MALE;

public class FormBuilderTest extends WebPageTest {

    @Test
    public void should_build_form() {
        Patient p = new Patient();

        Form form = new FormBuilder("myFormId")
                .textField("inputName")
                .textArea("inputAddress")
                .dropDownChoice("inputGender", newArrayList("MALE", "FEMALE", "OTHER"))
                .onSubmit(f -> {
                    p.setName(f.getAndResetValue("inputName"));
                    p.setAddress(f.getAndResetValue("inputAddress"));
                    p.setGender(Gender.valueOf(f.getAndResetValue("inputGender")));
                })
                .build();

        assertNotNull(form.get("inputName"));
        assertNotNull(form.get("inputAddress"));
        assertNotNull(form.get("inputGender"));
    }

    @Test
    public void should_build_form_with_initial_values() {
        Patient p = Patient.builder()
                .name("John Doe")
                .address("Kirpal Sagar")
                .gender(MALE)
                .build();

        Form form = new FormBuilder("myFormId")
                .textField("inputName", p.getName())
                .textArea("inputAddress", p.getAddress())
                .dropDownChoice("inputGender", newArrayList("MALE", "FEMALE", "OTHER"), p.getGender().toString())
                .onSubmit(f -> {
                    p.setName(f.getAndResetValue("inputName"));
                    p.setAddress(f.getAndResetValue("inputAddress"));
                    p.setGender(Gender.valueOf(f.getAndResetValue("inputGender")));
                })
                .build();

        assertEquals("Could not add default value for text field to created form.",
                p.getName(), form.get("inputName").getDefaultModelObjectAsString());
        assertEquals("Could not add default value for text area to created form.",
                p.getAddress(), form.get("inputAddress").getDefaultModelObjectAsString());
        assertEquals("Could not add default value for drop down choice to created form.",
                p.getGender().toString(), form.get("inputGender").getDefaultModelObjectAsString());
    }
}
