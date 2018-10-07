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

    private final String title;
    private final ActivityLink[] activityLinks;

    public Dashboard(String title, ActivityLink... activityLinks) {
        super(MAIN_CONTENT_ID);

        this.title = title;
        this.activityLinks = activityLinks;
    }

    @Override
    public void onInitialize() {
        super.onInitialize();

        add(new Label("dashboardTitle", title));

        ListView lv = new ListView<ActivityLink>("activities", Arrays.asList(activityLinks)) {
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

        add(lv);
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
