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

package ksch.orderentry;

import ksch.assertions.ElementContainingText;
import ksch.laboratory.LabQueries;
import ksch.laboratory.OrderStatus;
import ksch.wicket.MockBean;
import ksch.wicket.PageComponentTest;
import org.junit.Test;

import java.util.UUID;

import static ksch.assertions.HtmlAssertions.assertContains;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class OrderManagementTest extends PageComponentTest {

    @MockBean
    private LabQueries labQueries;

    private UUID visitId = UUID.randomUUID();

    @Test
    public void should_display_lab_order_status() {
        givenCanceledLabOrder();

        tester.startComponentInPage(new OrderManagement(visitId));

        assertContains(tester.getLastResponseAsString(), new ElementContainingText("Cancelled"));
    }

    private void givenCanceledLabOrder() {
        given(labQueries.getLabOrderStatus(any(UUID.class))).willReturn(OrderStatus.CANCELLED);
    }
}
