package ksch.patientmanagement.patient;

import java.util.UUID;

public class PatientNotFoundException extends RuntimeException {

    public PatientNotFoundException(UUID patientId) {
        super("Could not find patient with ID " + patientId.toString());
    }
}
