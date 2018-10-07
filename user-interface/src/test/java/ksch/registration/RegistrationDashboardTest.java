package ksch.registration;

import ksch.WebPageTest;
import org.junit.Test;

public class RegistrationDashboardTest extends WebPageTest {

    @Test
    public void should_render_registration_dashboard() {
        login("user", "pwd");

        tester.startPage(RegistrationDashboard.class);

        tester.assertRenderedPage(RegistrationDashboard.class);
    }
}

