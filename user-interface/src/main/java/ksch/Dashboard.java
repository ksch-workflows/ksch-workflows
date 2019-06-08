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

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import java.util.Arrays;

import static ksch.ApplicationFrame.MAIN_CONTENT_ID;

public class Dashboard extends Panel {

    public Dashboard(String title, ActivityLink... activityLinks) {
        super(MAIN_CONTENT_ID);

        add(new Label("dashboardTitle", title));
        add(createActivityLinkList(activityLinks));
    }

    private ListView createActivityLinkList(ActivityLink[] activityLinks) {
        return new ListView<ActivityLink>("activities", Arrays.asList(activityLinks)) {
            @Override
            protected void populateItem(ListItem<ActivityLink> activityLink) {
                ActivityLink link = activityLink.getModelObject();
                ExternalLink a = new ExternalLink("activityLink", link.href);
                activityLink.add(a);
                a.add(new Label("activityLinkText", link.text));
                Label i = new Label("activityLinkIcon", "");
                i.add(AttributeModifier.replace("class", link.icon));
                a.add(i);
            }
        };
    }

    @AllArgsConstructor
    public static class ActivityLink {

        /**
         * The activity title, e.g. "Register patient"
         */
        @NonNull
        private final String text;

        /**
         * Link to the activity, e.g. "/registration/register-patient"
         */
        @NonNull
        private final String href;

        /**
         * CSS code for a Font Awesome icon, e.g. "fa fa-book"
         */
        @NonNull
        private final String icon;
    }
}
