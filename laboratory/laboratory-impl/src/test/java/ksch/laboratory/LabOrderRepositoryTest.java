package ksch.laboratory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static ksch.laboratory.LabOrderCode.labOrderCodes;
import static org.junit.Assert.assertNotNull;

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
    }
}
