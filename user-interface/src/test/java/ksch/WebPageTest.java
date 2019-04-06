package ksch;

import com.giffing.wicket.spring.boot.starter.configuration.extensions.external.spring.security.SecureWebSession;
import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.patient.PatientTransactions;
import ksch.testdata.TestPatient;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;

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

    @Autowired
    private PatientTransactions patientTransactions;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(wicketApplication, "applicationContext", applicationContextMock);
        tester = new WicketTester(wicketApplication);
    }

    /**
     * @see "https://stackoverflow.com/questions/8523423/reset-embedded-h2-database-periodically"
     * @see "https://thoughts-on-java.org/jpa-native-queries/"
     */
    @After
    public void resetDatabase() {
        transactionTemplate.execute((s) -> {
            entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();
            entityManager.createNativeQuery("TRUNCATE TABLE PATIENT_ENTITY").executeUpdate();
            entityManager.createNativeQuery("TRUNCATE TABLE VISIT_ENTITY").executeUpdate();
            entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
            return null;
        });
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

    protected Patient createTestPatient() {
        return patientTransactions.create(new TestPatient());
    }
}
