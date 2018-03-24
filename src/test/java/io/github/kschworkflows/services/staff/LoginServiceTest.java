package io.github.kschworkflows.services.staff;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginServiceTest
{
    private LoginService loginService = new LoginService();

    @Test
    public void should_allow_login_for_registered_users()
    {
        // GIVEN
        loginService.addUser(new User("John Doe", "jdoe", "111222333", "NURSE"));

        // WHEN
        boolean loginAllowed = loginService.isLoginAllowed("jdoe", "111222333");

        // THEN
        assertTrue("User is not allowed to login",
                loginAllowed);
    }

    @Test
    public void should_not_allow_login_for_unknown_user()
    {
        assertFalse("Didn't reject login for unknown user",
                loginService.isLoginAllowed("fred", "xxxxx"));
    }

    @Test
    public void should_provide_access_on_user_roles()
    {
        // GIVEN
        loginService.addUser(new User("John Doe", "jdoe", "111222333", "NURSE"));

        // WHEN
        String roles = loginService.getUser("jdoe").getRoles();

        // THEN
        assertEquals("Could not retrieve roles for user.",
                "NURSE", roles);
    }
}