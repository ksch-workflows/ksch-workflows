package ksch;

import ksch.registration.RegistrationDashboard;
import org.junit.Test;

public class LogoutTest extends WebPageTest {

    @Test
    public void should_log_out_user() {
        login("user", "pwd");

        tester.startPage(Logout.class);
        tester.startPage(RegistrationDashboard.class);

        tester.assertRenderedPage(Login.class);
    }
}
