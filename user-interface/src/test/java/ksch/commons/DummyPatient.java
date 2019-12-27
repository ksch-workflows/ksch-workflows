package ksch.commons;

import ksch.patientmanagement.patient.Gender;
import ksch.patientmanagement.patient.Patient;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
public class DummyPatient implements Patient {

    private UUID id;

    @Override
    public String getName() {
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
