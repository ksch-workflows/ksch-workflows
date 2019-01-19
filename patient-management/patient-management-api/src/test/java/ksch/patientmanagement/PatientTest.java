package ksch.patientmanagement;

import org.junit.Test;

import static java.time.LocalDate.now;
import static java.time.temporal.ChronoUnit.YEARS;
import static ksch.patientmanagement.patient.Gender.FEMALE;
import static ksch.patientmanagement.patient.Gender.MALE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PatientTest {

    private final TestPatientEntity patient = new TestPatientEntity();

    @Test
    public void should_calculate_patient_age() {
        patient.setDateOfBirth(now().minus(15, YEARS));

        int patientAgeInYears = patient.getAgeInYears();

        assertEquals("Couldn't calculate patient age correctly.", 15, patientAgeInYears);
    }

    @Test
    public void should_skip_patient_age_calculation_if_no_age_specified() {
        patient.setDateOfBirth(null);

        Integer patientAgeInYears = patient.getAgeInYears();

        assertNull("Patient age is expected to be null if not date of birth is specified", patientAgeInYears);
    }

    @Test
    public void test_full_name_is_standard_name_if_father_unknown() {
        patient.setName("Jaswant Ashtikar");
        patient.setNameFather(null);

        String fullName = patient.getFullName();

        assertEquals("Jaswant Ashtikar", fullName);
    }

    @Test
    public void test_full_name_includes_name_of_father_for_man() {
        patient.setName("Jaswant Ashtikar");
        patient.setGender(MALE);
        patient.setNameFather("Waazir Sirasikar");

        String fullName = patient.getFullName();

        assertEquals("Jaswant Ashtikar, s/o Waazir Sirasikar", fullName);
    }

    @Test
    public void test_full_name_includes_name_of_father_for_women() {
        patient.setName("Surya Limbu");
        patient.setGender(FEMALE);
        patient.setNameFather("Waazir Sirasikar");

        String fullName = patient.getFullName();

        assertEquals("Surya Limbu, d/o Waazir Sirasikar", fullName);
    }
}
