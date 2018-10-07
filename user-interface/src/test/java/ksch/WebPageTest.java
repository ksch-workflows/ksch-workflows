package ksch;

import com.giffing.wicket.spring.boot.starter.configuration.extensions.external.spring.security.SecureWebSession;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KschWorkflowsApplication.class)
@ActiveProfiles("test")
@EnableWebSecurity
public abstract class WebPageTest {

    protected WicketTester tester;

    @Autowired
    private WebApplication wicketApplication;

    @Autowired
    private ApplicationContext applicationContextMock;

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(wicketApplication, "applicationContext", applicationContextMock);
        tester = new WicketTester(wicketApplication);
    }

    protected void login(String username, String password) {
        SecureWebSession session = (SecureWebSession) tester.getSession();
        session.signOut();
        tester.startPage(Login.class);
        FormTester formTester = tester.newFormTester("loginForm");
        formTester.setValue("username", username);
        formTester.setValue("password", password);
        formTester.submit();
    }
}
