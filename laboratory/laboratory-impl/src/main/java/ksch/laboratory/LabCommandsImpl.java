package ksch.laboratory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class LabCommandsImpl implements LabCommands {

    private final LabOrderRepository labOrderRepository;

    @Override
    public void requestExamination(UUID visitId, LabOrderCode labOrderCode) {
        var labOrder = LabOrderEntity.builder()
                .visitId(visitId)
                .labTest(new LabTest(labOrderCode))
                .status(LabOrder.Status.NEW)
                .build();
        labOrderRepository.save(labOrder);
    }
}
