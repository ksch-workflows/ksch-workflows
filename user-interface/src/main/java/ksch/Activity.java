package ksch;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;

import static ksch.ApplicationFrame.MAIN_CONTENT_ID;

public abstract class Activity extends Panel {

    protected Activity() {
        super(MAIN_CONTENT_ID);

        add(new ExternalLink("goToPreviousPage", getPreviousPagePath()));
        add(new Label("activityTitle", getActivityTitle()));
    }

    protected abstract String getActivityTitle();

    /**
     * @return URL path with which the link to the previous page for the back button can be build,
     * e.g. "/registration/dashboard"
     */
    protected abstract String getPreviousPagePath();
}
