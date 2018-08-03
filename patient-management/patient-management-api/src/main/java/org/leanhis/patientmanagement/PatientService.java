package org.leanhis.patientmanagement;

import java.util.List;
import java.util.UUID;

public interface PatientService {

    Patient create(Patient patient);

    List<Patient> findBy(String nameOrId);

    Patient getById(UUID patientId);

    Integer getAgeInYears(Patient patient);

    void update(Patient patient);
}
