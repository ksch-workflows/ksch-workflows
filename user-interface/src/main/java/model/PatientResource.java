package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.leanhis.patientmanagement.Gender;
import org.leanhis.patientmanagement.Patient;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientResource implements Patient, Serializable {

    private UUID id;

    private String patientNumber;

    private String name;

    private String nameFather;

    private LocalDate dateOfBirth;

    private Gender gender;

    private String address;

    public static PatientResource toPatientResource(Patient patient) {
        return PatientResource.builder()
                .id(patient.getId())
                .patientNumber(patient.getPatientNumber())
                .name(patient.getName())
                .nameFather(patient.getNameFather())
                .dateOfBirth(patient.getDateOfBirth())
                .gender(patient.getGender())
                .address(patient.getAddress())
                .build();
    }
}
