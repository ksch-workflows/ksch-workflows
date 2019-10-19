package ksch.laboratory;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class LabQueriesImpl implements LabQueries {

    private final LabOrderRepository labOrderRepository;

    @Override
    public List<LabOrder> getLabOrders(UUID visitId) {
        return new ArrayList<>(labOrderRepository.findByVisitId(visitId));
    }
}
