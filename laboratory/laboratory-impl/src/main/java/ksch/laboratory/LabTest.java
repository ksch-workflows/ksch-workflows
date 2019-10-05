package ksch.laboratory;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Optional;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class LabTest {

    @Column
    private String labOrderCode;

    @Column
    private String result;

    public LabTest(LabOrderCode labOrderCode) {
        this.labOrderCode = labOrderCode.toString();
    }

    public LabOrderCode getRequest() {
        return new LabOrderCode(labOrderCode);
    }

    public Optional<String> getResult() {
        return Optional.ofNullable(result);
    }
}
