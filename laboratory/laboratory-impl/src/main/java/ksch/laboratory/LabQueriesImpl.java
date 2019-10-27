package ksch.laboratory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LabQueriesImpl implements LabQueries {

    private final LabOrderRepository labOrderRepository;

    @Override
    public List<LabOrder> getLabOrders(UUID visitId) {
        return new ArrayList<>(labOrderRepository.findByVisitId(visitId));
    }
}
