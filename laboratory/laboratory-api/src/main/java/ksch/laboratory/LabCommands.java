package ksch.laboratory;

import java.util.UUID;

public interface LabCommands {

    /**
     * TODO Write javadocs
     * @param visitId
     * @param labOrderCode
     */
    void requestExamination(UUID visitId, LabOrderCode labOrderCode);
}
