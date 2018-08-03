package ksch.registration;

import ksch.WebPageTest;
import org.junit.Test;

public class RegistrationHomeTest extends WebPageTest {

    @Test
    public void should_render_registration_landing_page() {
        login("user", "pwd");

        tester.startPage(RegistrationDashboard.class);

        tester.assertRenderedPage(RegistrationDashboard.class);
    }
}
