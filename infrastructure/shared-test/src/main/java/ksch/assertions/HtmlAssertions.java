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

    public static void assertContains(String html, CssQuery cssQuery) {
        String message = String.format("Could not find element with CSS query '%s' in HTML:\n\n%s",
                cssQuery, html
        );
        assertContains(message, html, document -> document.select(cssQuery.toString()));
    }

    public static void assertNotContains(String html, CssQuery cssQuery) {
        String message = String.format("Element with CSS query '%s' should not have been found in HTML:\n\n%s",
                cssQuery, html
        );
        assertNotContains(message, html, document -> document.select(cssQuery.toString()));
    }

    public static void assertContains(String html, WicketId wicketId) {
        String message = String.format("Could not find element with Wicket ID '%s' in HTML:\n\n%s",
                wicketId, html
        );
        assertContains(
                message,
                html,
                document -> document.getElementsByAttributeValue("wicket:id", wicketId.toString())
        );
    }

    public static void assertNotContains(String html, WicketId wicketId) {
        String message = String.format("Element with Wicket ID '%s' should not have been found in HTML:\n\n%s",
                wicketId, html
        );
        assertNotContains(
                message,
                html,
                document -> document.getElementsByAttributeValue("wicket:id", wicketId.toString())
        );
    }

    public static void assertContains(String html, ElementContainingText elementContainingText) {
        String message = String.format("Could not find any element which contains text '%s' in HTML:\n\n%s",
                elementContainingText, html
        );
        assertContains(
                message,
                html,
                document -> document.getElementsContainingText(elementContainingText.toString())
        );
    }

    private static void assertContains(String message, String html, Function<Document, Elements> elementLocator) {
        Document doc = Jsoup.parse(html);
        Elements retrievedElements = elementLocator.apply(doc);
        assertFalse(message, retrievedElements.isEmpty());
    }

    private static void assertNotContains(String errorMessage, String html, Function<Document, Elements> elementLocator) {
        Document doc = Jsoup.parse(html);
        Elements retrievedElements = elementLocator.apply(doc);
        assertTrue(errorMessage, retrievedElements.isEmpty());
    }
}
