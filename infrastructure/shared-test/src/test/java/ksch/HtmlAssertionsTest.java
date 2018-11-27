package ksch;

import ksch.util.HtmlAssertions;
import org.junit.Test;

public class HtmlAssertionsTest {

    @Test
    public void should_pass_if_expected_element_found() {
        String html = getHtmlPageWithLink();

        HtmlAssertions.assertContains(html, "a");
    }

    @Test(expected = AssertionError.class)
    public void should_fail_if_expected_element_not_found() {
        String html = getHtmlPageWithLink();

        HtmlAssertions.assertContains(html, "button");
    }

    @Test
    public void should_pass_if_unexpected_element_not_found() {
        String html = getHtmlPageWithLink();

        HtmlAssertions.assertNotContains(html, "button");
    }

    @Test(expected = AssertionError.class)
    public void should_fail_if_unexpected_element_found() {
        String html = getHtmlPageWithLink();

        HtmlAssertions.assertNotContains(html, "a");
    }

    private String getHtmlPageWithLink() {
        return "<html><body><a href='https://www.kirpal-sagar.org'>Click me</a></body></html>";
    }
}
