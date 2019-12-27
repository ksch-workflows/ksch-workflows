package ksch.registration;

import ksch.patientmanagement.patient.Gender;
import ksch.patientmanagement.patient.Patient;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
class NewPatient implements Patient {

    private String name;

    @Override
    public UUID getId() {
        return null;
    }

    @Override
    public String getNameFather() {
        return null;
    }

    @Override
    public LocalDate getDateOfBirth() {
        return null;
    }

    @Override
    public Gender getGender() {
        return null;
    }

    @Override
    public String getAddress() {
        return null;
    }
}
