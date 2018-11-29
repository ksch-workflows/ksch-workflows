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
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import util.Debug;

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
        Patient patient = createTestPatient();
        Visit visit = visitService.startVisit(patient, VisitType.IPD);
        Vitals vitals = vitalsService.createMedicalRecordEntry(visit);
        PageParameters parameters = new PageParameters();
        parameters.add("vitalsRecordId", vitals.getId());
        tester.startPage(CaptureVitalsPage.class, parameters);

        tester.assertRenderedPage(CaptureVitalsPage.class);
        Debug.printLastResponse(tester);
    }
}
