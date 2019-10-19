package ksch.orderentry;

import ksch.laboratory.LabCommands;
import ksch.laboratory.LabOrderCode;
import ksch.wicket.MockBean;
import ksch.wicket.PageComponentTest;
import org.apache.wicket.util.tester.FormTester;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static ksch.assertions.HtmlAssertions.assertContains;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SuppressWarnings("unchecked")
public class LabOrderTest extends PageComponentTest {

    private MockBean<LabCommands> labCommands = MockBean.of(LabCommands.class);

    @Test
    public void should_render_panel_lab_order_table() {
        LabOrder labOrder = new LabOrder();

        tester.startComponentInPage(labOrder);
    }

    @Test
    public void should_provide_button_to_add_lab_order() {
        renderLabOrderPage();

        assertContains(lastRenderedPage(), doc -> doc.getElementsByTag("button"));
    }

    @Test
    public void should_add_lab_order_by_filling_in_dialog() {
        renderLabOrderPage();

        FormTester formTester = tester.newFormTester("labOrder:addLabOrderForm", false);
        formTester.setValue("loincNumber", "34530-6");
        formTester.submit();

        verify(labCommands.getMock()).requestExamination(any(), any(LabOrderCode.class));
    }

    @Override
    protected List<MockBean> getMockBeans() {
        return newArrayList(labCommands);
    }

    private String lastRenderedPage() {
        return tester.getLastResponseAsString();
    }

    private void renderLabOrderPage() {
        LabOrder labOrder = new LabOrder();
        tester.startComponentInPage(labOrder);
    }
}
