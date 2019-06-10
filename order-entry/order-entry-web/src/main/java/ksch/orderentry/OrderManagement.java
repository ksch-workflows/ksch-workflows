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

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

public class OrderManagement extends Panel {

    public OrderManagement() {
        super("orderManagement");


        final Label label = new Label("labOrderStatus", "Not required");
        label.setOutputMarkupId(true);

        AjaxLink<Void> labOrderAction = new AjaxLink<>("labOrderAction") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                label.setDefaultModelObject("Pending");
                target.add(label);
            }
        };

        add(label);
        add(labOrderAction);
    }
}
