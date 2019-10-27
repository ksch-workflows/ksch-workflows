package ksch.registration;

import ksch.Activity;
import ksch.orderentry.LabOrderPanel;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.UUID;

@MountPath("/registration/visits/${visitId}/lab-orders")
@AuthorizeInstantiation({"NURSE", "CLERK"})
public class LabOrderDetails extends RegistrationPage {

    private final UUID visitId;

    public LabOrderDetails(PageParameters pageParameters) {
        super(pageParameters);

        visitId = UUID.fromString(pageParameters.get("visitId").toString());
    }

    @Override
    protected Panel getContent() {
        return new EditLabOrdersActivity(visitId);
    }
}

class EditLabOrdersActivity extends Activity {

    EditLabOrdersActivity(UUID visitId) {
        add(new LabOrderPanel(visitId));
    }

    @Override
    protected String getActivityTitle() {
        return "Lab orders";
    }

    @Override
    protected String getPreviousPagePath() {
        return "/";
    }
}
