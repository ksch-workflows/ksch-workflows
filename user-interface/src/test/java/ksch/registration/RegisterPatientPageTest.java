package ksch.registration;

import ksch.WebPageTest;
import model.PatientResource;
import org.apache.wicket.util.tester.FormTester;
import org.junit.Test;
import ksch.patientmanagement.patient.Gender;
import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RegisterPatientPageTest extends WebPageTest {

    @Autowired
    private PatientService patientService;

    @Test
    public void should_render_patient_search_result_list() {
        createDummyPatients();
        login("user", "pwd");
        tester.startPage(RegisterPatientPage.class);

        FormTester formTester = tester.newFormTester("content:patientSearchForm", false);
        formTester.setValue("patientSearchTerm", "doe");
        formTester.submit();

        assertTrue("Table with patients wasn't rendered.",
                tester.getLastResponseAsString().contains("<th scope=\"col\">No.</th>"));
    }

    @Test
    public void should_open_patient_details() {
        createDummyPatients();
        login("user", "pwd");
        tester.startPage(RegisterPatientPage.class);

        FormTester formTester = tester.newFormTester("content:patientSearchForm", false);
        formTester.setValue("patientSearchTerm", "doe");
        formTester.submit();

        assertTrue("Could not find patient via search form",
                tester.getLastResponseAsString().contains("/registration/edit-patient/"));
    }

    @Test
    public void should_resubmit_patient_search() {
        createDummyPatients();
        login("user", "pwd");
        tester.startPage(RegisterPatientPage.class);
        FormTester formTester = tester.newFormTester("content:patientSearchForm", false);
        formTester.setValue("patientSearchTerm", "doe");
        formTester.submit();

        formTester = tester.newFormTester("content:patientSearchForm", false);
        formTester.setValue("patientSearchTerm", "doe");
        formTester.submit();

        assertTrue("Table with patients wasn't rendered.",
                tester.getLastResponseAsString().contains("<th scope=\"col\">No.</th>"));
    }

    @Test
    public void should_render_message_about_no_search_results() {
        login("user", "pwd");
        tester.startPage(RegisterPatientPage.class);

        FormTester formTester = tester.newFormTester("content:patientSearchForm", false);
        formTester.submit();

        assertTrue("Notification about missing search results wasn't rendered.",
                tester.getLastResponseAsString().contains("Cannot find a patient"));
        assertFalse("Table with patients was rendered even though there are no patients in the search results",
                tester.getLastResponseAsString().contains("<th scope=\"col\">No.</th>"));
    }

    @Test
    public void should_add_new_patient() {
        login("user", "pwd");
        tester.startPage(RegisterPatientPage.class);

        FormTester formTester = tester.newFormTester("content:addPatientForm", false);
        formTester.setValue("patientFormFields:inputName", "Fritz");
        formTester.setValue("patientFormFields:inputNameFather", "Carl");
        formTester.select("patientFormFields:inputGender", 0);
        formTester.setValue("patientFormFields:inputDateOfBirth", "27-07-2000");
        formTester.setValue("patientFormFields:inputAddress", "Kirpal Sagar");
        formTester.submit();

        tester.assertRenderedPage(EditPatientDetailsPage.class);

        List<Patient> searchResults = patientService.findByNameOrNumber("Fritz");

        assertEquals("Could not create new patient",
                1, searchResults.size());
        Patient retrievedPatient = searchResults.get(0);
        assertEquals("Failed to set patient's father name",
                "Carl", retrievedPatient.getNameFather());
        assertEquals("Failed to set patient gender",
                "MALE", retrievedPatient.getGender().toString());
        assertEquals("Failed to set patient address",
                "Kirpal Sagar", retrievedPatient.getAddress());
        assertEquals("Failed to set patient date of birth",
                LocalDate.of(2000, 7, 27), retrievedPatient.getDateOfBirth());
    }

    private void createDummyPatients() {

        Patient patient1 = PatientResource.builder()
                .id(UUID.randomUUID())
                .patientNumber("KSA-18-1001" + UUID.randomUUID())
                .name("John Doe")
                .gender(Gender.MALE)
                .dateOfBirth(LocalDate.now())
                .address("Kirpal Sagar")
                .build();

        Patient patient2 = PatientResource.builder()
                .id(UUID.randomUUID())
                .patientNumber("KSA-18-1002" + UUID.randomUUID())
                .name("Jane Doe")
                .gender(Gender.FEMALE)
                .dateOfBirth(LocalDate.now())
                .address("Kirpal Sagar")
                .build();

        patientService.create(patient1);
        patientService.create(patient2);
    }
}
