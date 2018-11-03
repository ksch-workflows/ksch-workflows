package ksch.registration;

import ksch.WebPageTest;
import model.PatientResource;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.tester.FormTester;
import org.junit.Before;
import org.junit.Test;
import ksch.patientmanagement.Gender;
import ksch.patientmanagement.Patient;
import ksch.patientmanagement.PatientService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class EditPatientDetailsActivityTest extends WebPageTest {

    @Autowired
    private PatientService patientService;

    @Before
    public void setup() {
        login("user", "pwd");
    }

    @Test
    public void should_open_patient_details() {
        Patient patient = createTestPatient();
        PageParameters parameters = buildPageParameters(patient);

        tester.startPage(EditPatientDetailsPage.class, parameters);

        tester.assertRenderedPage(EditPatientDetailsPage.class);
        tester.assertContains(patient.getName());
    }

    @Test
    public void should_update_patient_details() {
        Patient patient = createTestPatient();
        openPatientDetails(patient);

        FormTester formTester = tester.newFormTester("content:updatePatientForm", false);
        formTester.setValue("patientFormFields:inputAddress", "St. Gilgen");
        formTester.submit();

        Patient updatedPatient = patientService.getById(patient.getId());
        assertEquals("St. Gilgen", updatedPatient.getAddress());
    }

    @Test
    public void should_start_visit() {
        Patient patient = createTestPatient();
        openPatientDetails(patient);

        tester.newFormTester("content:startVisitForm").submit();

        



        // TODO assert that discharge button is rendered

    }

    @Test
    public void should_discharge_patient() {
        // TODO Implement should_discharge_patient
    }

    private Patient createTestPatient() {
        PatientResource patient = PatientResource.builder()
                .id(UUID.randomUUID())
                .patientNumber(UUID.randomUUID().toString())
                .name("John Doe")
                .gender(Gender.MALE)
                .dateOfBirth(LocalDate.now())
                .address("Kirpal Sagar")
                .build();

        return patientService.create(patient);
    }

    private PageParameters buildPageParameters(Patient patient) {
        PageParameters parameters = new PageParameters();
        parameters.add("id", patient.getId());
        return parameters;
    }

    private void openPatientDetails(Patient patient) {
        PageParameters parameters = buildPageParameters(patient);
        tester.startPage(EditPatientDetailsPage.class, parameters);
    }
}
