package ksch.laboratory;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.stream.Collectors.toList;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LabOrderEntity implements LabOrder {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(unique = true)
    private UUID id;

    @ElementCollection
    private List<LabTest> labTests = newArrayList();

    @Enumerated(EnumType.STRING)
    private Status status;

    public LabOrderEntity(List<LabOrderCode> requestedTests) {
        labTests = requestedTests.stream()
                .map(c -> new LabTest())
                .collect(Collectors.toList());
        status = Status.NEW;
    }

    @Override
    public List<LabOrderCode> getRequestedTests() {
        return labTests.stream()
                .map(LabTest::getRequest)
                .collect(toList());
    }
}
