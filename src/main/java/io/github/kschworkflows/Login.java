/**
 * Copyright notice:
 *
 * This class is derived from `LoginPage.java` in the `spring-security` example from
 * https://github.com/MarcGiffing/wicket-spring-boot-examples
 */

package io.github.kschworkflows;

import com.giffing.wicket.spring.boot.context.scan.WicketSignInPage;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

@WicketSignInPage
@MountPath("/login")
public class Login extends WebPage
{
    public Login(PageParameters parameters)
    {
        super(parameters);

        add(new LoginForm("loginForm"));
    }
}

class LoginForm extends Form<LoginForm>
{
    private String username;

    private String password;

    public LoginForm(String id)
    {
        super(id);
        setModel(new CompoundPropertyModel<>(this));
        add(new FeedbackPanel("feedback"));
        add(new RequiredTextField<String>("username"));
        add(new PasswordTextField("password"));
    }

    @Override
    protected void onSubmit()
    {
        AuthenticatedWebSession session = AuthenticatedWebSession.get();
        if (session.signIn(username, password)) {
            continueToOriginalDestination();
            setResponsePage(Index.class);
        } else {
            error("Login failed");
        }
    }
}
