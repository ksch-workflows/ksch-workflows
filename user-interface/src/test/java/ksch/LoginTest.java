package ksch;

import org.junit.Test;

public class LoginTest extends WebPageTest {

    @Test
    public void should_login_user() {
        login("user", "pwd");

        tester.assertNoErrorMessage();
        tester.assertNoInfoMessage();
        tester.assertRenderedPage(Index.class);
    }

    @Test
    public void should_fail_to_login_with_invalid_credentials() {
        login("user", "xxxxxxxxx");

        tester.assertErrorMessages("Login failed");
    }
}
