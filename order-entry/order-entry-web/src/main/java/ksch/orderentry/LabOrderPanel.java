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

package ksch.orderentry;

import ksch.laboratory.LabCommands;
import ksch.laboratory.LabOrder;
import ksch.laboratory.LabOrderCode;
import ksch.laboratory.LabQueries;
import ksch.terminologies.LoincLabOrderValues;
import lombok.Getter;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;


public class LabOrderPanel extends Panel {

    @SpringBean
    private LabCommands labCommands;

    @SpringBean
    private LabQueries labQueries;

    private final UUID visitId;

    private WebMarkupContainer labRequests;

    public LabOrderPanel(UUID visitId) {
        super("labOrder");

        this.visitId = visitId;

        add(createAddLabOrderButton());
        add(createLabRequestsTable());
        add(createNoLabRequestsPresentMessage());
        add(new AddLabOrderForm());
    }

    private Button createAddLabOrderButton() {
        Button result = new Button("addLabOrderButton");
        result.setOutputMarkupId(true);
        result.setOutputMarkupPlaceholderTag(true);

        return result;
    }

    private WebMarkupContainer createLabRequestsTable() {
        WebMarkupContainer result = new WebMarkupContainer("labRequests");
        if (hasLabRequestsForCurrentVisit()) {
            result.setVisible(true);
        } else {
            result.setVisible(false);
        }

        List<LabOrderRow> labOrders = labQueries.getLabOrders(visitId).stream()
                .map(LabOrderRow::new)
                .collect(toList());

        ListView lv = new ListView<>("labOrders", labOrders) {
            @Override
            protected void populateItem(ListItem<LabOrderRow> item) {
                LabOrderRow rowData = item.getModelObject();
                Label statusLabel = new Label("status", rowData.getStatus());
                statusLabel.setOutputMarkupId(true);

                item.add(new Label("loincNumber", rowData.getLoincNumber()));
                item.add(new Label("labTest", rowData.getLabTest()));

                item.add(statusLabel);

                AjaxLink<Void> btn = new AjaxLink<>("cancelLabOrder") {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        labCommands.cancel(rowData.getId());
                        statusLabel.setDefaultModelObject(LabOrder.Status.ABORTED.toString());

                        target.add(statusLabel);
                    }
                };

                item.add(btn);
            }
        };

        result.add(lv);

        return result;
    }

    private WebMarkupContainer createNoLabRequestsPresentMessage() {
        WebMarkupContainer result = new WebMarkupContainer("noLabRequestsMessage");
        if (hasLabRequestsForCurrentVisit()) {
            result.setVisible(false);
        } else {
            result.setVisible(true);
        }
        return result;
    }

    private boolean hasLabRequestsForCurrentVisit() {
        return labQueries.getLabOrders(visitId).size() > 0;
    }

    @Getter
    static class LabOrderRow implements Serializable {

        final UUID id;

        final String loincNumber;

        final String labTest;

        final String status;

        LabOrderRow(LabOrder labOrder) {
            id = labOrder.getId();
            loincNumber = labOrder.getRequestedTest().toString();
            labTest = LoincLabOrderValues.get(loincNumber).getLongCommonName();
            status = labOrder.getStatus().toString();
        }
    }

    class AddLabOrderForm extends Form<Void> {

        AddLabOrderForm() {
            super("addLabOrderForm");

            addTextField("loincNumber");
        }

        @Override
        protected void onSubmit() {
            String enteredLoincNumber = getValue("loincNumber");

            labCommands.requestExamination(visitId, new LabOrderCode(enteredLoincNumber));

            reloadPage();
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

        private void reloadPage() {
            boolean isInProductionContext = !getPage().getPageParameters().getNamedKeys().isEmpty(); // FIXME
            if (isInProductionContext) { // reloading the page in unit tests would raise an error
                setResponsePage(getPage().getClass(), getPage().getPageParameters());
            }
        }
    }
}
