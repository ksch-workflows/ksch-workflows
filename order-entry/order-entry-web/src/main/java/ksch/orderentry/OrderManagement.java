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

import ksch.laboratory.LabQueries;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.UUID;

public class OrderManagement extends Panel {

    @SpringBean
    private LabQueries labQueries;

    public OrderManagement(UUID visitId) {
        super("orderManagement");

        add(createLinkToLabOrders(visitId));
        add(createLabOrderStatusLabel(visitId));
    }

    private ExternalLink createLinkToLabOrders(UUID visitId) {
        return new ExternalLink("editLabOrdersLink", String.format(
                "/registration/visits/%s/lab-orders", visitId));
    }

    private Label createLabOrderStatusLabel(UUID visitId) {
        return new Label("statusLabOrders", labQueries.getLabOrderStatus(visitId).getText());
    }
}
