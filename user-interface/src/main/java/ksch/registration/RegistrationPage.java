package ksch.registration;

import ksch.ApplicationFrame;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public abstract class RegistrationPage extends ApplicationFrame {

    public RegistrationPage(PageParameters pageParameters) {
        super(pageParameters);
    }

    @Override
    protected String getHomePageUrl() {
        return "/registration";
    }
}
