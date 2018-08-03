package org.leanhis.patientmanagement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient implements Serializable {

    private UUID id;

    private String patientNumber;

    private String name;

    private String nameFather;

    private LocalDate dateOfBirth;

    private Gender gender;

    private String address;
}
