package io.github.kschworkflows;

import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;

public class WicketApplication extends AuthenticatedWebApplication
{
    @Override
    public Class<HomePage> getHomePage()
    {
        return HomePage.class;
    }

    @Override
    public void init()
    {
        super.init();
    }

    @Override
    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass()
    {
        return AppWebSession.class;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass()
    {
        return Login.class;
    }
}
