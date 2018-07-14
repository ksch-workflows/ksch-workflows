package io.github.kschworkflows;

import ksch.Index;
import org.junit.Test;

public class LoginTest extends WebPageTest {

    @Test
    public void should_login_user() {
        login("user", "pwd");

        tester.assertRenderedPage(Index.class);
    }
}
