package ksch.patientmanagement.visit;

import ksch.patientmanagement.patient.PatientEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisitEntity implements Visit {

    // TODO Add unique constaint for patient id
    @Id
    @GeneratedValue(generator = "UUID")
    @Column
    private UUID id;

    @OneToOne
    private PatientEntity patient;

    @Column
    private VisitType type;

    @Column
    private LocalDateTime timeStart;

    @Column
    private LocalDateTime timeEnd;
}
