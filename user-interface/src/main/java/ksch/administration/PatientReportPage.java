package ksch.administration;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("/administration/patient-report")
@AuthorizeInstantiation({"ADMINISTRATOR"})
public class PatientReportPage extends AdministrationPage {

    public PatientReportPage(PageParameters pageParameters) {
        super(pageParameters);
    }

    @Override
    protected Panel getContent() {
        return new PatientReportActivity();
    }
}
