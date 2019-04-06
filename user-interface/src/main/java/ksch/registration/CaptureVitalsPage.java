package ksch.registration;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.UUID;

@Deprecated
@MountPath("/registration/capture-vitals")
@AuthorizeInstantiation({"NURSE", "CLERK"})
public class CaptureVitalsPage extends RegistrationPage {

    private final UUID visitId;

    public CaptureVitalsPage(PageParameters pageParameters) {
        super(pageParameters);

        this.visitId = UUID.fromString(pageParameters.get("visitId").toString());
    }

    @Override
    protected Panel getContent() {
        return new CaptureVitalsActivity(visitId);
    }
}
