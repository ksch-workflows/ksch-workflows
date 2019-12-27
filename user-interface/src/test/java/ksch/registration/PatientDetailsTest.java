package ksch.registration;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.textfield.TextField;
import ksch.api.DummyPatient;
import ksch.commons.PageComponentTest;
import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.patient.PatientTransactions;
import ksch.patientmanagement.visit.Visit;
import ksch.patientmanagement.visit.VisitTransactions;
import ksch.patientmanagement.visit.VisitType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.mvysny.kaributesting.v10.LocatorJ._get;
import static org.junit.Assert.assertEquals;

public class PatientDetailsTest extends PageComponentTest {

    @Autowired
    private PatientTransactions patientTransactions;

    @Autowired
    private VisitTransactions visitTransactions;

    private Patient patient;

    private Visit visit;

    @Test
    public void should_render_patient_details() {
        givenPatientWithActiveVisit();

        UI.getCurrent().navigate(PatientDetailsPage.class, visit.getId().toString());

        assertEquals("Patient details", _get(H2.class).getText());
        assertEquals("John Doe", _get(TextField.class, spec -> spec.withId("patientName")).getValue());
    }

    private void givenPatientWithActiveVisit() {
        patient = patientTransactions.create(new DummyPatient());
        visit = visitTransactions.startVisit(patient, VisitType.OPD);
    }
}
