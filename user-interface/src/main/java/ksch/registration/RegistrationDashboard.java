package ksch.registration;

import ksch.ApplicationFrame;
import ksch.Dashboard;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("/registration")
@AuthorizeInstantiation({"NURSE", "CLERK"})
public class RegistrationDashboard extends ApplicationFrame {

    public RegistrationDashboard(PageParameters pageParameters) {
        super(pageParameters);
    }

    @Override
    protected Panel getContent() {
        return new Dashboard();
    }

    @Override
    protected String getHomePageUrl() {
        return "/registration";
    }
}
