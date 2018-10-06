package ksch.registration;

import ksch.Activity;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public abstract class RegistrationActivity extends Activity implements RegistrationHeaderMixing {

    public RegistrationActivity(PageParameters pageParameters) {
        super(pageParameters);
    }
}
