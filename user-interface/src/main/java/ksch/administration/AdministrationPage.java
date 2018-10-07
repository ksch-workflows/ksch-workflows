package ksch.administration;

import ksch.ApplicationFrame;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public abstract class AdministrationPage extends ApplicationFrame {

    public AdministrationPage(PageParameters pageParameters) {
        super(pageParameters);
    }

    @Override
    public String getHomePageUrl() {
        return "/administration";
    }
}
