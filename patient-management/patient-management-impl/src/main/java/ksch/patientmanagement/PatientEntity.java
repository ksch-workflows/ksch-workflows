package ksch.patientmanagement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientEntity implements Patient {

    // TODO Add unique constaint for patient id
    @Id
    @GeneratedValue(generator = "UUID")
    @Column
    private UUID id;

    // TODO Add unique constaint for patient number
    @Column
    private String patientNumber;

    @Column
    private String name;

    @Column
    private String nameFather;

    @Column
    private LocalDate dateOfBirth;

    @Column
    private Gender gender;

    @Column
    private String address;

    public static PatientEntity toPatientEntity(Patient patient) {
        return PatientEntity.builder()
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
