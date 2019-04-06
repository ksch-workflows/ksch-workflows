package ksch;

import ksch.registration.RegistrationDashboardPage;
import org.junit.Test;

public class LogoutTest extends WebPageTest {

    @Test
    public void should_log_out_user() {
        login("user", "pwd");

        tester.startPage(Logout.class);
        tester.startPage(RegistrationDashboardPage.class);

        tester.assertRenderedPage(Login.class);
    }
}
