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

import ksch.assertions.CssQuery;
import ksch.assertions.ElementContainingText;
import ksch.assertions.WicketId;
import ksch.laboratory.LabCommands;
import ksch.laboratory.LabOrderCode;
import ksch.laboratory.LabQueries;
import ksch.wicket.MockBean;
import ksch.wicket.PageComponentTest;
import org.apache.wicket.util.tester.FormTester;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.UUID;

import static com.google.common.collect.Lists.newArrayList;
import static ksch.assertions.HtmlAssertions.assertContains;
import static ksch.assertions.HtmlAssertions.assertNotContains;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class LabOrderPanelTest extends PageComponentTest {

    @MockBean
    private LabCommands labCommands;

    @MockBean
    private LabQueries labQueries;

    private final UUID visitId = UUID.randomUUID();

    @Test
    public void should_render_panel_lab_order_table() {
        LabOrderPanel labOrderPanel = new LabOrderPanel(visitId);

        tester.startComponentInPage(labOrderPanel);

        assertContains(lastRenderedPage(), new CssQuery("button"));
    }

    @Test
    public void should_add_lab_order_by_filling_in_dialog() {
        renderLabOrderPage();

        FormTester formTester = tester.newFormTester("labOrder:addLabOrderForm", false);
        formTester.setValue("loincNumber", "34530-6");
        formTester.submit();

        ArgumentCaptor<LabOrderCode> argumentCaptor = ArgumentCaptor.forClass(LabOrderCode.class);
        verify(labCommands).requestLaboratoryTest(eq(visitId), argumentCaptor.capture());
        assertEquals("34530-6", argumentCaptor.getValue().toString());
    }

    @Test
    public void should_display_placeholder_if_there_are_no_lab_orders() {
        renderLabOrderPage();

        assertNotContains(lastRenderedPage(), new CssQuery("table"));
        assertContains(lastRenderedPage(), new WicketId("noLabRequestsMessage"));
    }

    @Test
    public void should_render_table_with_all_lab_orders_for_visit() {
        createLabOrders();

        renderLabOrderPage();

        assertContains(lastRenderedPage(), new CssQuery("table"));
        assertNotContains(lastRenderedPage(), new WicketId("noLabRequestsMessage"));
        assertContains(lastRenderedPage(), new ElementContainingText("53962-7"));
    }

    @Test
    public void should_cancel_lab_order() {
        createLabOrders();
        renderLabOrderPage();

        tester.clickLink("labOrder:labRequests:labOrders:1:cancelLabOrder");

        verify(labCommands).cancel(any(UUID.class));
    }

    private void createLabOrders() {
        when(labQueries.getLabOrders(visitId)).thenReturn(
                newArrayList(
                        new TestLabOrder("34530-6"),
                        new TestLabOrder("5834-7"),
                        new TestLabOrder("53962-7")
                )
        );
    }

    private String lastRenderedPage() {
        return tester.getLastResponseAsString();
    }

    private void renderLabOrderPage() {
        LabOrderPanel labOrderPanel = new LabOrderPanel(visitId);

        tester.startComponentInPage(labOrderPanel);
    }
}
