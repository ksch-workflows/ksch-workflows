package ksch.patientmanagement.patient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PatientService {

    /**
     * Adds a new patients to the system.
     */
    Patient create(Patient patient);

    /**
     * Searches for patients in the database by their name or patient number.
     */
    List<Patient> findByNameOrNumber(String nameOrNumber);

    Optional<Patient> findByPatientNumber(String patientNumber);

    /**
     * Prodives access on the details of a patient with the given technical identifiert.
     */
    Patient getById(UUID patientId);

    /**
     * Calculates the age of a patient.
     */
    Integer getAgeInYears(Patient patient);

    /**
     * Notifies the system about changes in the patient details.
     */
    void update(Patient patient);

    /**
     * @return The patients name, including the names of their parents. E.g. "Nakula Karamchand, s/o Chander Limbu"
     */
    static String getFullName(Patient patient) {

        if (patient.getNameFather() == null) {
            return patient.getName();
        }

        String relationToFather;
        switch (patient.getGender()) {
            case MALE:
                relationToFather = "s/o";
                break;
            case FEMALE:
                relationToFather = "d/o";
                break;
            default:
                relationToFather = "c/o";
        }

        return String.format("%s, %s %s", patient.getName(), relationToFather, patient.getNameFather());
    }
}
