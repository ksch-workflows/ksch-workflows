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

import ksch.wicket.PageComponentTest;
import org.junit.Ignore;
import org.junit.Test;

import static ksch.util.HtmlAssertions.link;

public class OrderManagementTest extends PageComponentTest {

    @Test
    public void should_render_panel_with_order_management_table() {
        OrderManagement orderManagement = new OrderManagement();

        tester.startComponentInPage(orderManagement);
    }

    @Test
    public void should_request_lab_order() {
        OrderManagement orderManagement = new OrderManagement();
        tester.startComponentInPage(orderManagement);
        tester.assertContains(link("Request"));
        tester.assertContains("labOrderStatus.*Not required");

        tester.clickLink("orderManagement:labOrderAction");

        tester.startPage(tester.getLastRenderedPage());
        tester.assertContains("labOrderStatus.*Pending");
        tester.assertContains(link("Edit"));
    }

    @Ignore
    @Test
    public void test_all_order_types_are_initially_not_required() {
        //        tester.assertContains("ecgOrderStatus.*Not required");
//        tester.assertContains("usgOrderStatus.*Not required");
//        tester.assertContains("xRayOrderStatus.*Not required");
//        tester.assertContains("surgeryOrderStatus.*Not required");
    }
}
