package ksch.patientmanagement.visit;

import ksch.patientmanagement.patient.Gender;
import ksch.patientmanagement.patient.Patient;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class TestPatient implements Patient {

    private UUID id = UUID.randomUUID();

    private String patientNumber = "0815-" + UUID.randomUUID();

    private String name = "Jane Doe";

    private String nameFather = "John Doe";

    private LocalDate dateOfBirth = LocalDate.now().minusYears(15);

    private Gender gender = Gender.FEMALE;

    private String address = "Kirpal Sagar";
}
