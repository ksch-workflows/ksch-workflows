package ksch.administration;

import ksch.WebPageTest;
import org.junit.Test;
import org.leanhis.PatientReport;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class PatientReportActivityTest extends WebPageTest {

    @MockBean
    private PatientReport patientReport;

    @Test
    public void should_render_patient_report_activity() {
        login("user", "pwd");
        given(patientReport.getNumberOfNewPatients(any(), any())).willReturn(42);

        tester.startPage(PatientReportPage.class);

        tester.assertContains("42");
        tester.assertRenderedPage(PatientReportPage.class);
    }
}
