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

package ksch;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H2;
import ksch.commons.PageComponentTest;
import org.junit.Test;

import static com.github.mvysny.kaributesting.v10.LocatorJ._get;
import static org.junit.Assert.assertEquals;

public class IndexPageTest extends PageComponentTest {

    /**
     * At the moment there is only one work station supported: the registration. So for now we can
     * always re-direct from the root path to the registration dashboard.
     */
    @Test
    public void should_redirect_from_root_path_to_registration_dashboard() {
        UI.getCurrent().navigate(IndexPage.class);

        assertEquals("Registration dashboard", _get(H2.class).getText());
    }
}
