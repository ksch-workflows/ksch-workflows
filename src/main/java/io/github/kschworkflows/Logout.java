package io.github.kschworkflows;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class Logout extends WebPage
{
    public Logout(final PageParameters parameters)
    {
        getSession().invalidate();
        getRequestCycle().setResponsePage(HomePage.class);
    }
}
