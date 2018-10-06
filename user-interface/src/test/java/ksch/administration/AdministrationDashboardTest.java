package ksch.administration;

import ksch.WebPageTest;
import org.junit.Test;

public class AdministrationDashboardTest extends WebPageTest {

    @Test
    public void should_render_administration_dashboard() {
        login("user", "pwd");

        tester.startPage(AdministrationDashboard.class);

        tester.assertRenderedPage(AdministrationDashboard.class);
    }
}
