package io.github.kschworkflows;

import org.junit.Test;

public class LoginTest extends WebPageTest {

    @Test
    public void should_login_user() {
        login("user", "pwd");

        tester.assertRenderedPage(Index.class);
    }
}
