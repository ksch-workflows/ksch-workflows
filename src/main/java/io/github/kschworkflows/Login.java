package io.github.kschworkflows;

import org.apache.wicket.authroles.authentication.panel.SignInPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
 
public final class Login extends WebPage
{
    public Login(final PageParameters parameters)
    {
        add(new SignInPanel("signInPanel", false));
    }
}
