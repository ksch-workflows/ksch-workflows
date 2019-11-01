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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.UUID;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class LabQueriesTest {

    @InjectMocks
    private LabQueriesImpl labQueries;

    @Mock
    private LabOrderRepository labOrderRepository;

    private final UUID visitId = UUID.randomUUID();

    @Test
    public void should_provide_list_with_all_available_lab_orders_for_visit() {
        given(labOrderRepository.findByVisitId(visitId)).willReturn(labOrders());

        List<LabOrder> retrievedLabOrder = labQueries.getLabOrders(visitId);

        assertEquals(2, retrievedLabOrder.size());
    }

    private List<LabOrderEntity> labOrders() {
        return newArrayList(
                LabOrderEntity.builder()
                        .visitId(visitId)
                        .labTest(new LabTest(new LabOrderCode("1690-7")))
                        .id(UUID.randomUUID())
                        .status(LabOrder.Status.NEW)
                        .build(),
                LabOrderEntity.builder()
                        .visitId(visitId)
                        .labTest(new LabTest(new LabOrderCode("3299-5")))
                        .id(UUID.randomUUID())
                        .status(LabOrder.Status.NEW)
                        .build()
        );
    }
}
