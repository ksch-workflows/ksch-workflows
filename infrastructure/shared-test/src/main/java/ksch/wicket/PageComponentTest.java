package ksch.wicket;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

public class PageComponentTest {

    protected WicketTester tester;

    protected List<MockBean> getMockBeans() {
        return new ArrayList<>();
    }

    @Before
    public void setUp() {
        tester = new WicketTester(new PageComponentTestApplication(getMockBeans()));
    }
}
