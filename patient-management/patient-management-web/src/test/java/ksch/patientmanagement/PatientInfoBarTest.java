package ksch.patientmanagement;

import ksch.wicket.PageComponentTest;
import org.junit.Test;

public class PatientInfoBarTest extends PageComponentTest {

    @Test
    public void should_render_index_page() {
        PatientInfoBar patientInfoBar = new PatientInfoBar(new TestPatient());

        tester.startComponentInPage(patientInfoBar);
    }
}