package ksch.patientmanagement.patient;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Calendar;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PatientNumberGeneratorTest {

    private static final int PATIENT_INDEX_NUMBER = 1011;

    @InjectMocks
    private PatientNumberGeneratorImpl patientNumberGenerator;

    @Mock
    private PatientNumberIndexRepository patientNumberIndexRepository;

    private int currentYearWithTwoDigits = Calendar.getInstance().get(Calendar.YEAR) % 100;

    @Before
    public void setup() {
        when(patientNumberIndexRepository.save(any(PatientNumberIndex.class)))
                .thenReturn(new PatientNumberIndex(PATIENT_INDEX_NUMBER));
    }

    @Test
    public void should_prepend_year() {
        String generatedPatientNumber = patientNumberGenerator.generatePatientNumber();

        assertTrue("Generated patient number '" + generatedPatientNumber + "' doesn't start with current year",
                generatedPatientNumber.startsWith(currentYearWithTwoDigits + "-"));
    }

    @Test
    public void should_contain_patient_index_number() {
        String generatedPatientNumber = patientNumberGenerator.generatePatientNumber();

        assertTrue("Generated patient number '" + generatedPatientNumber +
                        "' doesn't contain the patient index number '" + PATIENT_INDEX_NUMBER + "'.",
                generatedPatientNumber.contains("-" + PATIENT_INDEX_NUMBER));
    }
}
