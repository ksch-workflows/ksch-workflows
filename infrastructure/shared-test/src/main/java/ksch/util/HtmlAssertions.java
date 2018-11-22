package ksch.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Assert;

public class HtmlAssertions {

    public static void assertContains(String html, String cssSelector) {
        Document doc = Jsoup.parse(html);
        Elements searchResults = doc.select(cssSelector);
        Assert.assertTrue("Cannot find CSS selector '" + cssSelector + "' in HTML " + html, searchResults.size() > 0);
    }

    public static void assertNotContains(String html, String cssSelector) {
        Document doc = Jsoup.parse(html);
        Elements searchResults = doc.select(cssSelector);
        Assert.assertTrue("Could find CSS selector '" + cssSelector + "' in HTML " + html, searchResults.size() == 0);
    }
}
