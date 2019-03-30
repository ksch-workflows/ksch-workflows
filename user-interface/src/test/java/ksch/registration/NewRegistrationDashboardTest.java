package ksch.registration;

import ksch.WebPageTest;
import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.patient.PatientTransactions;
import ksch.patientmanagement.visit.VisitTransactions;
import ksch.patientmanagement.visit.VisitType;
import ksch.testdata.TestPatient;
import ksch.util.CustomAssertions;
import ksch.util.HtmlAssertions;
import org.apache.wicket.util.tester.FormTester;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class NewRegistrationDashboardTest extends WebPageTest {

    @Autowired
    private PatientTransactions patientTransactions;

    @Autowired
    private VisitTransactions visitTransactions;

    @Before
    public void setup() {
        login("user", "pwd");
    }

    @Test
    public void should_render_registration_dashboard() {
        tester.startPage(NewRegistrationDashboardPage.class);
        tester.assertRenderedPage(NewRegistrationDashboardPage.class);
    }

    @Test
    public void should_render_table_with_active_opt_patients() {
        Patient patient = patientTransactions.create(new TestPatient());
        visitTransactions.startVisit(patient, VisitType.OPD);

        tester.startPage(NewRegistrationDashboardPage.class);
        tester.assertRenderedPage(NewRegistrationDashboardPage.class);

        tester.assertContains(patient.getName());
    }

    @Test
    public void should_register_new_patient() {
        tester.startPage(NewRegistrationDashboardPage.class);

        FormTester formTester = tester.newFormTester("content:addPatientForm", false);
        formTester.setValue("patientFormFields:inputName", "Ravindra Kodanda");
        formTester.setValue("patientFormFields:inputNameFather", "Javeed Sarath");
        formTester.select("patientFormFields:inputGender", 0);
        formTester.setValue("patientFormFields:inputDateOfBirth", "27-07-2000");
        formTester.setValue("patientFormFields:inputAddress", "Kirpal Sagar");
        formTester.submit();

        tester.assertRenderedPage(EditPatientDetailsPage.class);
    }

    @Test
    public void should_render_message_instead_of_opt_patient_table_if_no_active_opt_visits() {

    }

    @Test
    public void should_open_patient_details_by_entering_opt_number() {

    }

    @Test
    public void should_open_warning_dialog_if_entered_opt_number_doesnt_exist() {

    }

    @Test
    public void should_open_patient_details_by_click_in_opt_patients_table() {

    }

    @Test
    public void should_register_new_patient_via_form_in_dialog() {

    }
}
