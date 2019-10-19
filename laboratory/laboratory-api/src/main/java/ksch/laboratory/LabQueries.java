package ksch.laboratory;

import java.util.List;
import java.util.UUID;

public interface LabQueries {

    List<LabOrder> getLabOrders(UUID visitId);
}
