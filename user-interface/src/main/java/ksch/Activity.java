package ksch;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public abstract class Activity extends ApplicationFrame {

    public Activity(PageParameters pageParameters) {
        super(pageParameters);

        add(new ExternalLink("goToPreviousPage", getPreviousPagePath()));
        add(new Label("activityTitle", getActivityTitle()));
    }

    public abstract String getActivityTitle();

    /**
     * @return URL path with which the link to the previous page for the back button can be build, e.g. "/dashboard"
     */
    public abstract String getPreviousPagePath();
}
