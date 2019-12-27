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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.google.common.collect.Lists.newArrayList;
import static ksch.laboratory.LabOrder.Status.CANCELED;
import static ksch.laboratory.LabOrder.Status.DONE;
import static ksch.laboratory.LabOrder.Status.IN_PROGRESS;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
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

    @Test
    public void test_getLabOrderStatus_with_no_lab_orders() {
        givenVisitWithoutLabOrders();

        var result = labQueries.getLabOrderStatus(visitId);

        assertEquals("A wrong lab order status was calculated", OrderStatus.NOT_REQUIRED, result);
    }

    @Test
    public void test_getLabOrderStatus_with_single_pending_lab_order() {
        givenVisitWithLabOrder(IN_PROGRESS);

        var result = labQueries.getLabOrderStatus(visitId);

        assertEquals("A wrong lab order status was calculated", OrderStatus.PENDING, result);
    }

    @Test
    public void test_getLabOrderStatus_with_single_canceled_lab_order() {
        givenVisitWithLabOrder(CANCELED);

        var result = labQueries.getLabOrderStatus(visitId);

        assertEquals("A wrong lab order status was calculated", OrderStatus.CANCELLED, result);
    }

    @Test
    public void test_getLabOrderStatus_with_one_order_pending_and_one_done() {
        givenVisitWithLabOrders(IN_PROGRESS, DONE);

        var result = labQueries.getLabOrderStatus(visitId);

        assertEquals("A wrong lab order status was calculated", OrderStatus.PENDING, result);
    }

    @Test
    public void test_getLabOrderStatus_with_one_order_done_and_one_aborted() {
        givenVisitWithLabOrders(DONE, CANCELED);

        var result = labQueries.getLabOrderStatus(visitId);

        assertEquals("A wrong lab order status was calculated", OrderStatus.DONE, result);
    }

    private void givenVisitWithoutLabOrders() {
        given(labOrderRepository.findByVisitId(eq(visitId))).willReturn(newArrayList());
    }

    private void givenVisitWithLabOrder(LabOrder.Status status) {
        givenVisitWithLabOrders(status);
    }

    private void givenVisitWithLabOrders(LabOrder.Status... requestedStatuses) {
        var labOrders = new ArrayList<LabOrderEntity>();
        for (LabOrder.Status status : requestedStatuses) {
            labOrders.add(LabOrderEntity.builder()
                    .status(status)
                    .build());
        }
        given(labOrderRepository.findByVisitId(eq(visitId))).willReturn(labOrders);
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
