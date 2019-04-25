package ksch.wicket;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This Wicket application can be used to fastly execute Wicket component tests which don't need to use Spring features.
 *
 * @see "https://ci.apache.org/projects/wicket/guide/8.x/single.html#_test_driven_development_with_wicket"
 * @see "https://ci.apache.org/projects/wicket/guide/8.x/single.html#_test_driven_development_with_wicket_and_spring"
 */
public class WicketTestApplication extends WebApplication {

    private List<MockBean> mockBeans = new ArrayList<>();

    public WicketTestApplication(MockBean... mockBeans) {
        this.mockBeans.addAll(Arrays.asList(mockBeans));
    }

    @Override
    public void init() {
        super.init();

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        mockBeans.forEach(b -> ctx.registerBean(b.getBeanClass(), b.getSupplier()));
        ctx.refresh();

        getComponentInstantiationListeners().add(new SpringComponentInjector(this, ctx));
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return TestHomePage.class;
    }
}
