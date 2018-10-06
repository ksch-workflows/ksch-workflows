package ksch;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public abstract class ApplicationFrame extends WebPage implements HeaderMixin {

    public ApplicationFrame(PageParameters pageParameters) {
        super(pageParameters);

        add(new ExternalLink("goToHomePage", getHomePagePath()));
    }
}
