package io.github.kschworkflows.registration;

import io.github.kschworkflows.WebPageTest;
import ksch.registration.RegistrationHome;
import org.junit.Test;

public class RegistrationHomeTest extends WebPageTest {

    @Test
    public void should_render_registration_landing_page() {
        login("user", "pwd");

        tester.startPage(RegistrationHome.class);

        tester.assertRenderedPage(RegistrationHome.class);
    }
}
