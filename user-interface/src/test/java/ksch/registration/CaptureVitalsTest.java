package ksch.registration;

import ksch.WebPageTest;
import ksch.medicalrecords.Vitals;
import ksch.medicalrecords.VitalsService;
import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.patient.PatientService;
import ksch.patientmanagement.visit.Visit;
import ksch.patientmanagement.visit.VisitService;
import ksch.patientmanagement.visit.VisitType;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.tester.FormTester;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import util.Debug;

import static org.junit.Assert.assertEquals;

public class CaptureVitalsTest extends WebPageTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private VisitService visitService;

    @Autowired
    private VitalsService vitalsService;

    @Before
    public void setup() {
        login("user", "pwd");
    }

    @Test
    public void should_render_capture_vitals_page() {
        Visit visit = createPatientAndStartVisit();
        Vitals vitals = vitalsService.createMedicalRecordEntry(visit);

        openCaptureVitalsPage(vitals);

        tester.assertRenderedPage(CaptureVitalsPage.class);
    }

    @Test
    public void should_save_entered_vitals() {
        Visit visit = createPatientAndStartVisit();
        Vitals vitals = vitalsService.createMedicalRecordEntry(visit);
        openCaptureVitalsPage(vitals);

        FormTester formTester = tester.newFormTester("content:vitalsForm", false);
        formTester.setValue("systolicInMmHg", "150");
        formTester.setValue("diastolicInMmHg", "70");
        formTester.setValue("temperatureInF", "98.3");
        formTester.setValue("pulseInBPM", "89");
        formTester.setValue("weightInKG", "75");
        formTester.submit();

        tester.assertRenderedPage(CaptureVitalsPage.class);
        Vitals savedVitals = vitalsService.get(vitals.getId());
        assertEquals(new Integer(150), savedVitals.getSystolicInMmHg());
        assertEquals(new Integer(70), savedVitals.getDiastolicInMmHg());
        assertEquals(new Float(98.3), savedVitals.getTemperatureInF());
        assertEquals(new Integer(89), savedVitals.getPulseInBPM());
        assertEquals(new Integer(75), savedVitals.getWeightInKG());
    }

    private Visit createPatientAndStartVisit() {
        Patient patient = createTestPatient();
        return visitService.startVisit(patient, VisitType.IPD);
    }

    private void openCaptureVitalsPage(Vitals vitals) {
        PageParameters parameters = new PageParameters();
        parameters.add("vitalsRecordId", vitals.getId());
        tester.startPage(CaptureVitalsPage.class, parameters);
    }
}
