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

package ksch.wicket;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class TimeTest {

    @Test
    public void should_parse_date() {
        String date = "27-07-2000";

        LocalDate parsedDate = Time.parseDate(date);

        assertEquals("Failed to parse year from date", 2000, parsedDate.getYear());
        assertEquals("Failed to parse month from date", 7, parsedDate.getMonth().getValue());
        assertEquals("Failed to parse day from date", 27, parsedDate.getDayOfMonth());
    }

    @Test
    public void should_parse_date_with_single_digit_month_and_day() {
        String date = "1-1-2000";

        LocalDate parsedDate = Time.parseDate(date);

        assertEquals("Failed to parse year from date", 2000, parsedDate.getYear());
        assertEquals("Failed to parse month from date", 1, parsedDate.getMonth().getValue());
        assertEquals("Failed to parse day from date", 1, parsedDate.getDayOfMonth());
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_accept_invalid_date_format() {
        String date = "27/07/2000";

        Time.parseDate(date);
    }
}
