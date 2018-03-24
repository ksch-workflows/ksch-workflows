package io.github.kschworkflows;

import io.github.kschworkflows.services.staff.LoginService;
import io.github.kschworkflows.services.staff.User;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

public class AppWebSession extends AuthenticatedWebSession
{
    private LoginService loginService = new LoginService();

    private User user;

    public AppWebSession(Request request)
    {
        super(request);
    }

    @Override
    public boolean authenticate(String login, String password)
    {
        user = loginService.getUser(login);

        return loginService.isLoginAllowed(login, password);
    }
 
    @Override
    public Roles getRoles()
    {
        return isSignedIn() ? new Roles(user.getRoles()) : null;
    }
}
