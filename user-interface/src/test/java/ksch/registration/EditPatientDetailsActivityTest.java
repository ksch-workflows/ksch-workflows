package ksch.registration;

import ksch.WebPageTest;
import model.PatientResource;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.tester.FormTester;
import org.junit.Test;
import org.leanhis.patientmanagement.Gender;
import org.leanhis.patientmanagement.Patient;
import org.leanhis.patientmanagement.PatientService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class EditPatientDetailsActivityTest extends WebPageTest {

    @Autowired
    private PatientService patientService;

    @Test
    public void should_open_patient_details() {
        login("user", "pwd");
        Patient patient = createDummyPatient();
        PageParameters parameters = new PageParameters();
        parameters.add("id", patient.getId());

        tester.startPage(EditPatientDetailsPage.class, parameters);

        tester.assertRenderedPage(EditPatientDetailsPage.class);
        tester.assertContains(patient.getName());
    }

    @Test
    public void should_update_patient_details() {
        login("user", "pwd");
        Patient patient = createDummyPatient();
        PageParameters parameters = new PageParameters();
        parameters.add("id", patient.getId());
        tester.startPage(EditPatientDetailsPage.class, parameters);

        FormTester formTester = tester.newFormTester("content:updatePatientForm", false);
        formTester.setValue("patientFormFields:inputAddress", "St. Gilgen");
        formTester.submit();

        Patient updatedPatient = patientService.getById(patient.getId());
        assertEquals("St. Gilgen", updatedPatient.getAddress());
    }

    private Patient createDummyPatient() {
        PatientResource patient = PatientResource.builder()
                .id(UUID.randomUUID())
                .patientNumber("KSA-18-1001")
                .name("John Doe")
                .gender(Gender.MALE)
                .dateOfBirth(LocalDate.now())
                .address("Kirpal Sagar")
                .build();

        return patientService.create(patient);
    }
}
