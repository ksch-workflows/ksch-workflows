package ksch.registration;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.UUID;

@MountPath("/registration/edit-patient/${id}")
@AuthorizeInstantiation({"NURSE", "CLERK"})
public class EditPatientDetailsPage extends RegistrationPage {

    private final UUID patientId;

    public EditPatientDetailsPage(PageParameters pageParameters) {
        super(pageParameters);

        patientId = UUID.fromString(pageParameters.get("id").toString());
    }

    @Override
    protected Panel getContent() {
        return new EditPatientDetailsActivity(patientId);
    }
}
