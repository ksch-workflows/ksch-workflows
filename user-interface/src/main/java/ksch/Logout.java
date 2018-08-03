package ksch;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("/logout")
public class Logout extends WebPage {

    public Logout(final PageParameters parameters) {
        getSession().invalidate();
        getRequestCycle().setResponsePage(Index.class);
    }
}
