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

import java.util.List;
import java.util.UUID;

import static com.google.common.collect.Lists.newArrayList;
import static ksch.assertions.HtmlAssertions.assertContains;
import static ksch.assertions.HtmlAssertions.assertNotContains;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class LabOrderPanelTest extends PageComponentTest {

    private MockBean<LabCommands> labCommands = MockBean.of(LabCommands.class);

    private MockBean<LabQueries> labQueries = MockBean.of(LabQueries.class);

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
        verify(labCommands.getMock()).requestExamination(eq(visitId), argumentCaptor.capture());
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

    }

    private void createLabOrders() {
        when(labQueries.getMock().getLabOrders(visitId)).thenReturn(
                newArrayList(
                        new TestLabOrder("34530-6"),
                        new TestLabOrder("5834-7"),
                        new TestLabOrder("53962-7")
                )
        );
    }

    @Override
    protected List<MockBean> getMockBeans() {
        return newArrayList(
                labCommands,
                labQueries
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
