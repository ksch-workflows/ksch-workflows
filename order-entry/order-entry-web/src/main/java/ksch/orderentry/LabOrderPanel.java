package ksch.orderentry;

import ksch.laboratory.LabCommands;
import ksch.laboratory.LabOrderCode;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.UUID;

public class LabOrderPanel extends Panel {

    @SpringBean
    private LabCommands labCommands;

    private final UUID visitId;

    private Button addLabOrderButton;

    public LabOrderPanel(UUID visitId) {
        super("labOrder");

        this.visitId = visitId;

        add(createAddLabOrderButton());
        add(new AddLabOrderForm());
    }

    private Button createAddLabOrderButton() {
        Button result = new Button("addLabOrderButton");
        result.setOutputMarkupId(true);
        result.setOutputMarkupPlaceholderTag(true);

        this.addLabOrderButton = result;
        return result;
    }

    class AddLabOrderForm extends Form<Void> {

        public AddLabOrderForm() {
            super("addLabOrderForm");

            addTextField("loincNumber");
        }

        @Override
        protected void onSubmit() {
            String enteredLoincNumber = getValue("loincNumber");

            labCommands.requestExamination(visitId, new LabOrderCode(enteredLoincNumber));
        }

        private void addTextField(String wicketId) {
            addTextField(wicketId, null);
        }

        private void addTextField(String wicketId, String initialValue) {
            TextField<String> textField = new TextField<>(wicketId, new Model<>(initialValue));
            add(textField);
        }

        private String getValue(String wicketId) {
            Object object = get(wicketId).getDefaultModel().getObject();
            return object != null ? object.toString() : null;
        }
    }
}
