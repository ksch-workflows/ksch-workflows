package ksch.patientmanagement.patient;

public interface PatientTransactions {

    /**
     * Adds a new patient to the system.
     */
    Patient create(Patient patient);

    /**
     * Persists changes in the patient details.
     */
    void update(Patient patient);
}
