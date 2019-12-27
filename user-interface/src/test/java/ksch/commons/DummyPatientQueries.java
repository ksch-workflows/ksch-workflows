package ksch.commons;

import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.patient.PatientQueries;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Setter
public class DummyPatientQueries implements PatientQueries {

    private Patient patient;

    @Override
    public Patient getById(UUID patientId) {
        return patient;
    }
}
