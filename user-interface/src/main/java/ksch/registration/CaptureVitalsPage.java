package ksch.registration;

import ksch.medicalrecords.VitalsService;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.UUID;

@MountPath("/registration/capture-vitals/${vitalsRecordId}")
@AuthorizeInstantiation({"NURSE", "CLERK"})
public class CaptureVitalsPage extends RegistrationPage {

    private final UUID vitalsId;

    @SpringBean
    private VitalsService vitalsService;

    public CaptureVitalsPage(PageParameters pageParameters) {
        super(pageParameters);

        this.vitalsId = UUID.fromString(pageParameters.get("vitalsRecordId").toString());
    }

    @Override
    protected Panel getContent() {
        return new CaptureVitalsActivity(vitalsId);
    }
}
