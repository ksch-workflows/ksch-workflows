package ksch.administration;

import ksch.ApplicationFrame;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("/administration")
@AuthorizeInstantiation({"ADMINISTRATOR"})
public class AdministrationDashboard extends ApplicationFrame {

    public AdministrationDashboard(PageParameters pageParameters) {
        super(pageParameters);
    }
}
