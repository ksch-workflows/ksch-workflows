package ksch.registration.dashboard;

import ksch.commons.Time;
import ksch.patientmanagement.patient.Gender;
import ksch.patientmanagement.patient.Patient;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
class NewPatient implements Patient {
    private final UUID id = null;
    private String name;
    private String nameFather;
    private Gender gender;
    private String dateOfBirth;
    private String address;

    public LocalDate getDateOfBirth() {
        return Time.parseDate(dateOfBirth);
    }

    public String getDateOfBirthAsString() {
        return dateOfBirth;
    }
}
