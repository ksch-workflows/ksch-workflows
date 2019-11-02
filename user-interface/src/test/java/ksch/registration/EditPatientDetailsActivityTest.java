package ksch.registration;

import ksch.patientmanagement.patient.PatientQueries;
import ksch.patientmanagement.patient.PatientTransactions;
import ksch.patientmanagement.visit.VisitQueries;
import ksch.patientmanagement.visit.VisitTransactions;
import ksch.testdata.TestPatient;
import ksch.wicket.MockBean;
import ksch.wicket.PageComponentTest;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class EditPatientDetailsActivityTest extends PageComponentTest {

    @MockBean
    private PatientQueries patientQueries;

    @MockBean
    private PatientTransactions patientTransactions;

    @MockBean
    private VisitQueries visitQueries;

    @MockBean
    private VisitTransactions visitTransactions;

    @Test
    public void should_validate_that_there_is_an_active_visit_for_the_patient() {
        var patient = new TestPatient();
        when(patientQueries.getById(eq(patient.getId()))).thenReturn(patient);

        var editPatientDetailsActivity = new EditPatientDetailsActivity(patient.getId());

        tester.startComponentInPage(editPatientDetailsActivity);
    }
}
