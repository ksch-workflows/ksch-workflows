package ksch.registration;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.wicketstuff.annotation.mount.MountPath;

import static ksch.ApplicationFrame.MAIN_CONTENT_ID;

@MountPath("/registration-new")
@AuthorizeInstantiation({"NURSE", "CLERK"})
// TODO Remove "new" from class name before merge
public class NewRegistrationDashboardPage extends RegistrationPage {

    @Override
    protected Panel getContent() {
        return new NewRegistrationDashboard();
    }
}

class NewRegistrationDashboard extends Panel {

    public NewRegistrationDashboard() {
        super(MAIN_CONTENT_ID);

        add(new OpenPatientDetails());
    }

    class OpenPatientDetails extends Form<OpenPatientDetails> {

        private String optNumber;

        public OpenPatientDetails() {
            super("openPatientDetails");

            setModel(new CompoundPropertyModel<>(this));

            add(new TextField<>("optNumber"));
        }

        @Override
        protected void onSubmit() {
            throw new RuntimeException("Not implemented yet.");
        }
    }
}
