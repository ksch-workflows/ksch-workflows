package ksch.registration;

import ksch.ApplicationFrame;
import ksch.Dashboard;
import ksch.Dashboard.ActivityLink;
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
        return new Dashboard("Registration dashboard",
                new ActivityLink("Register patient", "/registration/register-patient", "fa fa-book"),
                new ActivityLink("Capture vitals", "/registration/capture-vitals/start", "fa fa-heartbeat")
        );
    }

    @Override
    protected String getHomePageUrl() {
        return "/registration";
    }
}
