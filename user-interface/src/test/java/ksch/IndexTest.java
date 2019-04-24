package ksch;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

public class IndexTest {

    private WicketTester tester;

    @Before
    public void setUp() {
        tester = new WicketTester(new WicketTestApplication());
    }

    @Test
    public void should_render_index_page(){
        tester.startPage(Index.class);
        tester.assertRenderedPage(Index.class);
    }
}
