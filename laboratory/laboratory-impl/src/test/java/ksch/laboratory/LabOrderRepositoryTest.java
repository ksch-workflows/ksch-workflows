/*
 * Copyright 2019 KS-plus e.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
        labOrder.setVisitId(visitId);

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
