package ksch.registration;

import ksch.WebPageTest;
import org.junit.Before;
import org.junit.Test;

public class NewRegistrationDashboardTest extends WebPageTest {

    @Before
    public void setup() {
        login("user", "pwd");
    }

    @Test
    public void should_render_registration_dashboard() {
        tester.startPage(NewRegistrationDashboardPage.class);
        tester.assertRenderedPage(NewRegistrationDashboardPage.class);
    }
}
