package ksch.laboratory;

import org.junit.Test;

import java.util.List;

import static ksch.laboratory.LabOrderCode.labOrderCodes;
import static org.junit.Assert.assertEquals;

public class LabOrderEntityTest {

    @Test
    public void should_build_new_lab_order_entity() {
        List<LabOrderCode> labOrderCodes = labOrderCodes("44907-4", "3299-5");

        LabOrderEntity labOrderEntity = new LabOrderEntity(labOrderCodes);

        assertEquals(LabOrder.Status.NEW, labOrderEntity.getStatus());
        assertEquals(2, labOrderEntity.getRequestedTests().size());
        assertEquals(2, labOrderEntity.getLabTests().size());
    }
}
