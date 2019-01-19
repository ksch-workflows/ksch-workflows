package ksch.registration;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.panel.Panel;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("/registration/capture-vitals/start")
@AuthorizeInstantiation({"NURSE", "CLERK"})
public class CaptureVitalsStartPage extends RegistrationPage {

    @Override
    protected Panel getContent() {
        return new CaptureVitalsStartActivity();
    }
}
