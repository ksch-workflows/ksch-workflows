package ksch.laboratory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

import static ksch.laboratory.LabOrder.Status.DONE;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LabOrderRepositoryTest {

    @Autowired
    private LabOrderRepository labOrderRepository;

    private final UUID visitId = UUID.randomUUID();

    @Test
    public void should_create_lab_order() {
        LabOrderEntity labOrder = new LabOrderEntity(new LabOrderCode("44907-4"));

        LabOrderEntity savedLabOrder = labOrderRepository.save(labOrder);

        assertNotNull(savedLabOrder.getId());
        assertEquals("44907-4", savedLabOrder.getLabTest().getRequest().toString());
        assertTrue(savedLabOrder.getLabTest().getResult().isEmpty());
    }

    @Test
    public void should_update_lab_order_status() {
        LabOrderEntity labOrder = persistedLabOrder();

        labOrder.setStatus(DONE);
        labOrderRepository.save(labOrder);

        LabOrderEntity retrievedLabOrder = labOrderRepository.findById(labOrder.getId()).get();
        assertEquals(DONE, retrievedLabOrder.getStatus());
    }

    @Test
    public void should_find_all_lab_orders_for_a_visit() {
        persistedLabOrder("6765-2");
        persistedLabOrder("49054-0");

        List<LabOrderEntity> result = labOrderRepository.findByVisitId(visitId);

        assertEquals(2, result.size());
    }

    private LabOrderEntity persistedLabOrder() {
        return persistedLabOrder("44907-4");
    }

    private LabOrderEntity persistedLabOrder(String loincNumber) {
        LabOrderEntity labOrder = LabOrderEntity.builder()
                .visitId(visitId)
                .status(LabOrder.Status.NEW)
                .labTest(new LabTest(new LabOrderCode(loincNumber)))
                .build();
        return labOrderRepository.save(labOrder);
    }
}
