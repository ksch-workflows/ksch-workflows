package ksch.patientmanagement;

import java.time.LocalDate;
import java.util.UUID;

public interface Patient {

    UUID getId();

    String getPatientNumber();

    String getName();

    String getNameFather();

    LocalDate getDateOfBirth();

    Gender getGender();

    String getAddress();
}
