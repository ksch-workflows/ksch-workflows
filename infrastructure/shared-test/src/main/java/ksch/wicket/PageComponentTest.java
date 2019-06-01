package ksch.wicket;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PageComponentTest {

    protected WicketTester tester;

    private List<MockBean> mockBeans = new ArrayList<>();

    protected void setMockBeans(MockBean... mockBeans) {
        this.mockBeans = new ArrayList<>();
        this.mockBeans.addAll(Arrays.asList(mockBeans));
    }

    @Before
    public void setUp() {
        setMockBeans();
        tester = new WicketTester(new PageComponentTestApplication(mockBeans));
    }
}
