package ksch.laboratory;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor
public class LabOrderEntity implements LabOrder {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(unique = true)
    private UUID id;

    @Column(nullable = false)
    private UUID visitId;

    @Column(nullable = false)
    private LabTest labTest;

    @Enumerated(EnumType.STRING)
    @Setter
    private Status status;

    public LabOrderEntity(LabOrderCode labOrderCode) {
        labTest = new LabTest(labOrderCode);
        status = Status.NEW;
    }

    @Override
    public LabOrderCode getRequestedTest() {
        return labTest.getRequest();
    }
}
