package ksch.administration;

import ksch.Dashboard;
import ksch.Dashboard.ActivityLink;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("/administration")
@AuthorizeInstantiation({"ADMINISTRATOR"})
public class AdministrationDashboard extends AdministrationPage {

    public AdministrationDashboard(PageParameters pageParameters) {
        super(pageParameters);
    }

    @Override
    protected Panel getContent() {
        return new Dashboard("Administration dashboard",
                new ActivityLink("Patient report", "/administration/patient-report", "fa fa-book")
        );
    }
}
