package ksch;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public abstract class ApplicationFrame extends WebPage {

    public static final String MAIN_CONTENT_ID = "content";

    public ApplicationFrame(PageParameters pageParameters) {
        super(pageParameters);
    }

    @Override
    public void onInitialize() {
        super.onInitialize();

        add(new ExternalLink("goToHomePage", getHomePageUrl()));
        Panel content = getContent();
        content.setMarkupId(MAIN_CONTENT_ID);
        add(content);
    }

    protected abstract Panel getContent();

    protected abstract String getHomePageUrl();
}
