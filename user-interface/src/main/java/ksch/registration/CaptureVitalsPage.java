package ksch.registration;

import ksch.medicalrecords.Vitals;
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

    private final Vitals vitals;

    @SpringBean
    private VitalsService vitalsService;

    public CaptureVitalsPage(PageParameters pageParameters) {
        super(pageParameters);

        UUID vitalsRecordId = UUID.fromString(pageParameters.get("vitalsRecordId").toString());
        this.vitals = vitalsService.get(vitalsRecordId);
    }

    @Override
    protected Panel getContent() {
        return new CaptureVitalsActivity(vitals);
    }
}
