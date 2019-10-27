package ksch.laboratory;

import java.util.UUID;

public interface LabCommands {

    /**
     * TODO Write javadocs
     * @param visitId
     * @param labOrderCode
     */
    void requestExamination(UUID visitId, LabOrderCode labOrderCode);

    /**
     * Abort the request for the given lab order with the given ID.
     *
     * @param id The ID of the lab order to be aborted.
     */
    void cancel(UUID id);
}
