package ksch.registration;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

@Deprecated
@MountPath("/registration/register-patient")
@AuthorizeInstantiation({"NURSE", "CLERK"})
public class RegisterPatientPage extends RegistrationPage {

    public RegisterPatientPage(PageParameters pageParameters) {
        super(pageParameters);
    }

    @Override
    public Panel getContent() {
        return new RegisterPatientActivity();
    }
}
