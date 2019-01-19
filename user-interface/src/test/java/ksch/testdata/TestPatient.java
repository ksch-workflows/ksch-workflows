package ksch.testdata;

import ksch.patientmanagement.patient.Gender;
import ksch.patientmanagement.patient.Patient;

import java.time.LocalDate;
import java.util.UUID;

public class TestPatient implements Patient {

    @Override
    public UUID getId() {
        return UUID.randomUUID();
    }

    @Override
    public String getPatientNumber() {
        return "0815-" + UUID.randomUUID();
    }

    @Override
    public String getName() {
        return "John Doe";
    }

    @Override
    public String getNameFather() {
        return null;
    }

    @Override
    public LocalDate getDateOfBirth() {
        return LocalDate.now();
    }

    @Override
    public Gender getGender() {
        return Gender.MALE;
    }

    @Override
    public String getAddress() {
        return "Kirpal Sagar";
    }
}
