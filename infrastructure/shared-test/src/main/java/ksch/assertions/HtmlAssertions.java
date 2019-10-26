/*
 * Copyright 2019 KS-plus e.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ksch.assertions;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.function.Function;

import static org.junit.Assert.*;

public class HtmlAssertions {

    public static void assertContains(String html, String cssSelector) {
        Document doc = Jsoup.parse(html);
        Elements searchResults = doc.select(cssSelector);
        assertTrue("Cannot find CSS selector '" + cssSelector + "' in HTML " + html, searchResults.size() > 0);
    }

    public static void assertContains(String html, CssQuery cssQuery) {
        assertContains(html, document -> document.select(cssQuery.toString()));
    }

    public static void assertNotContains(String html, CssQuery cssQuery) {
        assertNotContains(html, document -> document.select(cssQuery.toString()));
    }

    public static void assertContains(String html, WicketId wicketId) {
        assertContains(html, document -> document.getElementsByAttributeValue("wicket:id", wicketId.toString()));
    }

    public static void assertNotContains(String html, WicketId wicketId) {
        assertNotContains(html, document -> document.getElementsByAttributeValue("wicket:id", wicketId.toString()));
    }

    public static void assertNotContains(String html, String cssSelector) {
        Document doc = Jsoup.parse(html);
        Elements searchResults = doc.select(cssSelector);
        assertEquals("CSS selector '" + cssSelector + "' should not have been found in HTML " + html, searchResults.size(), 0);
    }

    // TODO I guess this method should be private instead as it's hard to read in the tests
    public static void assertContains(String html, Function<Document, Elements> elementLocator) {
        Document doc = Jsoup.parse(html);
        Elements retrievedElements = elementLocator.apply(doc);
        assertFalse("Cannot find matching element in HTML " + html, retrievedElements.isEmpty());
    }

    private static void assertNotContains(String html, Function<Document, Elements> elementLocator) {
        Document doc = Jsoup.parse(html);
        Elements retrievedElements = elementLocator.apply(doc);
        assertTrue("Specified element should not have been found in HTML " + html, retrievedElements.isEmpty());
    }
}
