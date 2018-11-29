package ksch.registration;

import ksch.ApplicationFrame;
import lombok.NoArgsConstructor;
import org.apache.wicket.request.mapper.parameter.PageParameters;

@NoArgsConstructor
public abstract class RegistrationPage extends ApplicationFrame {

    public RegistrationPage(PageParameters pageParameters) {
        super(pageParameters);
    }

    @Override
    protected String getHomePageUrl() {
        return "/registration";
    }
}
