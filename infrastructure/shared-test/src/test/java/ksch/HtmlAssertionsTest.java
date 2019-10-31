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

package ksch;

import ksch.assertions.CssQuery;
import ksch.assertions.ElementContainingText;
import ksch.assertions.HtmlAssertions;
import ksch.assertions.WicketId;
import org.junit.Test;

public class HtmlAssertionsTest {

    @Test
    public void should_pass_find_css_query() {
        String html = "<html><span class=\"hello\"></span></html>";

        HtmlAssertions.assertContains(html, new CssQuery(".hello"));
    }

    @Test(expected = AssertionError.class)
    public void should_fail_find_css_query() {
        String html = "<html><span></span></html>";

        HtmlAssertions.assertContains(html, new CssQuery(".hello"));
    }

    // --------------------------------------------------------------------------------------------------------------

    @Test
    public void should_pass_preclude_css_query() {
        String html = "<html><span></span></html>";

        HtmlAssertions.assertNotContains(html, new CssQuery(".hello"));
    }

    @Test(expected = AssertionError.class)
    public void should_fail_preclude_css_query() {
        String html = "<html><span class=\"hello\"></span></html>";

        HtmlAssertions.assertNotContains(html, new CssQuery(".hello"));
    }

    // --------------------------------------------------------------------------------------------------------------

    @Test
    public void should_pass_find_wicket_id() {
        String html = "<html><span wicket:id=\"content\"></span></html>";

        HtmlAssertions.assertContains(html, new WicketId("content"));
    }

    @Test(expected = AssertionError.class)
    public void should_fail_find_wicket_id() {
        String html = "<html></html>";

        HtmlAssertions.assertContains(html, new WicketId("content"));
    }

    // --------------------------------------------------------------------------------------------------------------

    @Test
    public void should_pass_preclude_wicket_id() {
        String html = "<html></html>";

        HtmlAssertions.assertNotContains(html, new WicketId("content"));
    }

    @Test(expected = AssertionError.class)
    public void should_fail_preclude_wicket_id() {
        String html = "<html><span wicket:id=\"content\"></span></html>";

        HtmlAssertions.assertNotContains(html, new WicketId("content"));
    }

    // --------------------------------------------------------------------------------------------------------------

    @Test
    public void should_pass_find_element_with_containing_text() {
        String html = "<html><span>Hello</span></html>";

        HtmlAssertions.assertContains(html, new ElementContainingText("Hello"));
        HtmlAssertions.assertContains(html, new ElementContainingText("hello"));
    }

    @Test(expected = AssertionError.class)
    public void should_fail_find_element_with_containing_text() {
        String html = "<html></html>";

        HtmlAssertions.assertContains(html, new ElementContainingText("hello"));
    }
}
