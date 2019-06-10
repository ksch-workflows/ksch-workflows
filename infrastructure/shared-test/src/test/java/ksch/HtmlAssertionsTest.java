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

import ksch.util.HtmlAssertions;
import org.junit.Test;

import java.util.regex.Pattern;

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

    @Test
    public void should_pass_if_regex_is_found() {
        String html = "<p>Hello, World</p>";

        HtmlAssertions.assertContains(html, Pattern.compile("Hello.*World"));
    }

    @Test
    public void should_check_that_regex_is_not_found() {
        String html = "<p>Hello, World</p>";

        HtmlAssertions.assertNotContains(html, Pattern.compile("Hello.*World"));
    }

    private String getHtmlPageWithLink() {
        return "<html><body><a href='https://www.kirpal-sagar.org'>Click me</a></body></html>";
    }
}
