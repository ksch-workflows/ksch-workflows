package ksch.registration;

import ksch.WebPageTest;
import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.visit.Visit;
import ksch.patientmanagement.visit.VisitService;
import ksch.patientmanagement.visit.VisitType;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.tester.FormTester;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class CaptureVitalsTest extends WebPageTest {

    @Autowired
    private VisitService visitService;

    @Before
    public void setup() {
        login("user", "pwd");
    }

    @Test
    public void should_render_capture_vitals_page() {
        Visit visit = createPatientAndStartVisit();

        openCaptureVitalsPage(visit.getId());

        tester.assertRenderedPage(CaptureVitalsPage.class);
    }

    @Test
    public void should_save_entered_vitals() {
        Visit visit = createPatientAndStartVisit();
        openCaptureVitalsPage(visit.getId());

        FormTester formTester = tester.newFormTester("content:vitalsForm", false);
        formTester.setValue("systolicInMmHg", "150");
        formTester.setValue("diastolicInMmHg", "70");
        formTester.setValue("temperatureInF", "98.3");
        formTester.setValue("pulseInBPM", "89");
        formTester.setValue("weightInKG", "75");
        formTester.submit();

        tester.assertRenderedPage(CaptureVitalsPage.class);
    }

    private Visit createPatientAndStartVisit() {
        Patient patient = createTestPatient();
        return visitService.startVisit(patient, VisitType.IPD);
    }

    private void openCaptureVitalsPage(UUID visitId) {
        PageParameters parameters = new PageParameters();
        parameters.add("visitId", visitId);
        tester.startPage(CaptureVitalsPage.class, parameters);
    }
}
