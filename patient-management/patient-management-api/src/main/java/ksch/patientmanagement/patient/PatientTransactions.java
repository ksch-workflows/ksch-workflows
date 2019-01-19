package ksch.patientmanagement.patient;

public interface PatientTransactions {

    /**
     * Adds a new patient to the system.
     */
    Patient create(Patient patient);

    /**
     * Notifies the system about changes in the patient details.
     */
    void update(Patient patient);
}
