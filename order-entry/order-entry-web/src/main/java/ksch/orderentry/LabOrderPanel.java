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
import lombok.extern.java.Log;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.tester.BaseWicketTester;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;
import static ksch.terminologies.LoincLabOrderValues.isValid;

@Log
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
        add(new FeedbackPanel("validationError"));
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
        result.add(new LabOrderListView(labQueries.getLabOrders(visitId)));

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

    class AddLabOrderForm extends Form<Void> {

        AddLabOrderForm() {
            super("addLabOrderForm");

            add(createLoincNumberInputField());
        }

        @Override
        protected void onSubmit() {
            String enteredLoincNumber = getEnteredLoincNumber();
            labCommands.requestLaboratoryTest(visitId, new LabOrderCode(enteredLoincNumber));
            reloadPage();
        }

        private TextField createLoincNumberInputField() {
            TextField<String> result = new TextField<>("loincNumber", new Model<>(null));
            result.add(new LoincNumberValidator());
            return result;
        }

        private String getEnteredLoincNumber() {
            Object object = get("loincNumber").getDefaultModel().getObject();
            return object != null ? object.toString() : null;
        }

        private void reloadPage() {
            if (!getPage().getClass().equals(BaseWicketTester.StartComponentInPage.class)) {
                setResponsePage(getPage().getClass(), getPage().getPageParameters());
            } else { // This can only happen in page component tests
                log.warning("Could not reload page");
            }
        }

        private class LoincNumberValidator implements IValidator<String> {

            @Override
            public void validate(IValidatable<String> validatable) {
                var enteredLoincNumber = validatable.getValue();
                if (!isValid(enteredLoincNumber)) {
                    ValidationError error = new ValidationError(this);
                    error.setMessage("Invalid LOINC number entered: " + enteredLoincNumber);
                    validatable.error(error);
                }
            }
        }
    }

    private class LabOrderListView extends ListView<LabOrderRow> {

        LabOrderListView(List<LabOrder> labOrders) {
            super("labOrders", labOrders.stream().map(LabOrderRow::new).collect(toList()));
        }

        @Override
        protected void populateItem(ListItem<LabOrderRow> item) {
            LabOrderRow rowData = item.getModelObject();
            Label statusLabel = createLabOrderStatusLabel(rowData);

            item.add(new Label("loincNumber", rowData.getLoincNumber()));
            item.add(new Label("labTest", rowData.getLabTest()));
            item.add(statusLabel);
            item.add(createCancelLabOrderButton(rowData, statusLabel));
        }

        private Label createLabOrderStatusLabel(LabOrderRow rowData) {
            Label statusLabel = new Label("status", rowData.getStatus());
            statusLabel.setOutputMarkupId(true);
            return statusLabel;
        }

        private AjaxLink<Void> createCancelLabOrderButton(LabOrderRow rowData, Label statusLabel) {
            return new AjaxLink<>("cancelLabOrder") {
                @Override
                public void onClick(AjaxRequestTarget target) {
                    var newStatus = labCommands.cancel(rowData.getId());
                    statusLabel.setDefaultModelObject(newStatus.toString());

                    target.add(statusLabel);
                }
            };
        }
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
}
