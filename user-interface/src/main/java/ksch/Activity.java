/**
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

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.springframework.data.util.Pair;

import static ksch.ApplicationFrame.MAIN_CONTENT_ID;

public abstract class Activity extends Panel {

    protected Activity() {
        super(MAIN_CONTENT_ID);

        add(createHeadingLabel());
        add(createGoBackLink());
    }

    private Label createHeadingLabel() {
        return new Label("activityTitle", getActivityTitle());
    }

    private Link<Void> createGoBackLink() {
        return new Link<>("goToPreviousPage") {
            @Override
            public void onClick() {
                var previousPage = getPreviousPage();
                setResponsePage(previousPage.getFirst(), previousPage.getSecond());
            }
        };
    }

    protected abstract String getActivityTitle();

    protected abstract Pair<Class<? extends WebPage>, PageParameters> getPreviousPage();
}
