package ksch.registration;

import ksch.WebPageTest;
import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.visit.VisitService;
import ksch.patientmanagement.visit.VisitType;
import org.apache.wicket.util.tester.FormTester;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CaptureVitalsStartTest extends WebPageTest {

    @Autowired
    private VisitService visitService;

    @Before
    public void setup() {
        login("user", "pwd");
    }

    @Test
    public void should_render_capture_vitals_start_page() {
        tester.startPage(CaptureVitalsStartPage.class);

        tester.assertRenderedPage(CaptureVitalsStartPage.class);
    }

    @Test
    public void should_open_capture_vitals_page_after_patient_number_input() {
        Patient patient = createTestPatient();
        visitService.startVisit(patient, VisitType.IPD);
        tester.startPage(CaptureVitalsStartPage.class);

        FormTester formTester = tester.newFormTester("content:patientNumberForm", false);
        formTester.setValue("patientNumberInput", patient.getPatientNumber());
        formTester.submit();

        tester.assertRenderedPage(CaptureVitalsPage.class);
    }

    @Test
    public void should_display_error_message_if_entered_patient_not_found() {
        // TODO Implement test + feature
    }
}
