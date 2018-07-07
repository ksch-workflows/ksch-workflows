package io.github.kschworkflows.registration;

import io.github.kschworkflows.WebPageTest;
import org.junit.Test;

public class RegistrationHomeTest extends WebPageTest
{
    @Test
    public void should_render_registration_landing_page()
    {
        // ARRANGE
        login("user", "pwd");

        // ACT
        tester.startPage(RegistrationHome.class);

        // ASSERT
        tester.assertRenderedPage(RegistrationHome.class);
    }
}
