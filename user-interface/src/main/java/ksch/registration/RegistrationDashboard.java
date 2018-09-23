package ksch.registration;

import ksch.ApplicationFrame;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.leanhis.RegistrationStatistics;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("/registration")
@AuthorizeInstantiation({"NURSE", "CLERK"})
public class RegistrationDashboard extends ApplicationFrame {

    @SpringBean
    private RegistrationStatistics registrationStatistics;

    public RegistrationDashboard(PageParameters pageParameters) {
        super(pageParameters);

        registrationStatistics.doJustAnything();
    }
}
