package ksch.patientmanagement.patient;

import java.time.LocalDate;
import java.util.UUID;

import static java.time.LocalDate.now;
import static java.time.temporal.ChronoUnit.YEARS;

public interface Patient {

    UUID getId();

    String getPatientNumber();

    String getName();

    String getNameFather();

    LocalDate getDateOfBirth();

    Gender getGender();

    String getAddress();


    default Integer getAgeInYears() {
        if (getDateOfBirth() == null) {
            return null;
        }

        return (int) getDateOfBirth().until(now(), YEARS);
    }

    /**
     * @return The patients name, including the names of their parents. E.g. "Nakula Karamchand, s/o Chander Limbu"
     */
    default String getFullName() {
        if (getNameFather() == null) {
            return getName();
        }

        String relationToFather;
        switch (getGender()) {
            case MALE:
                relationToFather = "s/o";
                break;
            case FEMALE:
                relationToFather = "d/o";
                break;
            default:
                relationToFather = "c/o";
        }

        return String.format("%s, %s %s", getName(), relationToFather, getNameFather());
    }
}
