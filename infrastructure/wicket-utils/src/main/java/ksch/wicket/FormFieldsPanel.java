/*
 * Copyright 2019 KS-plus e.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ksch.wicket;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormFieldsPanel extends Panel {

    private final Map<String, FormComponent<String>> formComponents = new HashMap<>();

    protected FormFieldsPanel(String wicketId) {
        super(wicketId);
    }

    protected void addTextArea(String wicketId) {
        addTextArea(wicketId, null);
    }

    protected void addTextArea(String wicketId, String initialValue) {
        TextArea<String> textArea = new TextArea<>(wicketId, new Model<>(initialValue));
        formComponents.put(wicketId, textArea);
        add(textArea);
    }

    protected void addTextField(String wicketId) {
        addTextField(wicketId, null);
    }

    protected void addTextField(String wicketId, String initialValue) {
        TextField<String> textField = new TextField<>(wicketId, new Model<>(initialValue));
        formComponents.put(wicketId, textField);
        add(textField);
    }

    protected void addDropDownChoice(String wicketId, List<String> choices) {
        addDropDownChoice(wicketId, choices, null);
    }

    protected void addDropDownChoice(String wicketId, List<String> choices, String initialValue) {
        DropDownChoice<String> dropDownChoice = new DropDownChoice<>(wicketId, new Model<>(initialValue), choices);
        formComponents.put(wicketId, dropDownChoice);
        add(dropDownChoice);
    }

    public String getAndResetValue(String wicketId) {
        IModel<String> model = formComponents.get(wicketId).getModel();
        Object object = model.getObject();
        model.setObject(null);
        return object != null ? object.toString() : null;
    }

    public String getValue(String wicketId) {
        IModel<String> model = formComponents.get(wicketId).getModel();
        Object object = model.getObject();
        return object != null ? object.toString() : null;
    }
}
