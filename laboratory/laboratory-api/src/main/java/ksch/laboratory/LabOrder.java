package ksch.laboratory;

import java.util.UUID;

public interface LabOrder {

    UUID getId();

    Status getStatus();

    LabOrderCode getRequestedTest();

    enum Status {
        NEW,
        IN_PROGRESS,
        DONE,
        ABORTED
    }
}
