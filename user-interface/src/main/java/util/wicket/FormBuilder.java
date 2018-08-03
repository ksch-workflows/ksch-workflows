package util.wicket;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.danekja.java.util.function.serializable.SerializableConsumer;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormBuilder implements Serializable {

    private String formId;

    private Map<String, FormComponent> formComponents = new HashMap<>();

    private SerializableConsumer<FormBuilder> onSubmitAction;

    public FormBuilder(String wicketId) {
        this.formId = wicketId;
    }

    public FormBuilder textArea(String wicketId) {
        return textArea(wicketId, null);
    }

    public FormBuilder textArea(String wicketId, String initialValue) {
        formComponents.put(wicketId, new TextArea<>(wicketId, new Model<>(initialValue)));
        return this;
    }

    public FormBuilder textField(String wicketId) {
        return textField(wicketId, null);
    }

    public FormBuilder textField(String wicketId, String initialValue) {
        formComponents.put(wicketId, new TextField<>(wicketId, new Model<>(initialValue)));
        return this;
    }

    public FormBuilder dropDownChoice(String wicketId, List<String> choices) {
        return dropDownChoice(wicketId, choices, null);
    }

    public FormBuilder dropDownChoice(String wicketId, List<String> choices, String initialValue) {
        formComponents.put(wicketId, new DropDownChoice<>(wicketId, new Model<>(initialValue), choices));
        return this;
    }

    public String getAndResetValue(String wicketId) {
        IModel model = formComponents.get(wicketId).getModel();
        Object object = model.getObject();
        model.setObject(null);
        return object != null ? object.toString() : null;
    }

    public String getValue(String wicketId) {
        IModel model = formComponents.get(wicketId).getModel();
        Object object = model.getObject();
        return object != null ? object.toString() : null;
    }

    public FormBuilder onSubmit(SerializableConsumer<FormBuilder> onSubmitAction) {
        this.onSubmitAction = onSubmitAction;
        return this;
    }

    public Form build() {

        if (onSubmitAction == null) {
            throw new IllegalStateException("Cannot build form without onSubmitAction");
        }

        FormBuilder fb = this;

        Form<Void> form = new Form<Void>(formId) {
            @Override
            protected void onSubmit() {
                onSubmitAction.accept(fb);
            }
        };

        formComponents.forEach((wicketId, formComponent) -> form.add(formComponent));

        return form;
    }
}
