package ksch.laboratory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static ksch.laboratory.LabOrder.Status.DONE;
import static ksch.laboratory.LabOrderCode.labOrderCodes;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LabOrderRepositoryTest {

    @Autowired
    private LabOrderRepository labOrderRepository;

    @Test
    public void should_create_lab_order() {
        LabOrderEntity labOrder = new LabOrderEntity(labOrderCodes("44907-4", "3299-5"));

        LabOrderEntity savedLabOrder = labOrderRepository.save(labOrder);

        assertNotNull(savedLabOrder.getId());
        assertEquals("44907-4", savedLabOrder.getLabTests().get(0).getRequest().toString());
        assertTrue(savedLabOrder.getLabTests().get(0).getResult().isEmpty());
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
    public void should_save_lab_test_result() {
        LabOrderEntity labOrder = persistedLabOrder();

        labOrder.getLabTest("44907-4");
    }

    private LabOrderEntity persistedLabOrder() {
        LabOrderEntity labOrder = new LabOrderEntity(labOrderCodes("44907-4", "3299-5"));
        return labOrderRepository.save(labOrder);
    }
}
