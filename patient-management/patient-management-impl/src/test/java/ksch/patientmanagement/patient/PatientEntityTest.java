package ksch.patientmanagement.patient;

import org.junit.Test;

import java.time.LocalDate;
import java.util.UUID;

import static ksch.util.CustomAssertions.assertAllPropertiesEqual;

public class PatientEntityTest {

    private Patient patient;

    @Test
    public void should_build_patient_entity() {
        givenPatient();

        PatientEntity e = PatientEntity.toPatientEntity(patient);

        assertAllPropertiesEqual(Patient.class, patient, e);
    }

    private void givenPatient() {
        patient = new Patient() {
            @Override
            public UUID getId() {
                return UUID.fromString("a577731b-7cf2-4cb1-8ceb-13e1bac034fe");
            }

            @Override
            public String getPatientNumber() {
                return "1234-123";
            }

            @Override
            public String getName() {
                return "John C. Doe";
            }

            @Override
            public String getNameFather() {
                return "John's father";
            }

            @Override
            public LocalDate getDateOfBirth() {
                return LocalDate.now();
            }

            @Override
            public Gender getGender() {
                return Gender.OTHER;
            }

            @Override
            public String getAddress() {
                return "North Pole";
            }
        };
    }
}
