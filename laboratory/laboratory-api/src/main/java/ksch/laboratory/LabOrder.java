package ksch.laboratory;

import java.util.List;
import java.util.UUID;

public interface LabOrder {

    UUID getId();

    Status getStatus();

    List<LabOrderCode> getRequestedTests();

    enum Status {
        NEW,
        IN_PROGRESS,
        DONE,
        ABORTED
    }
}
