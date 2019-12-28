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

package ksch.commons;

import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class Time {

    public static final DateTimeFormatter INDIAN_DATE_FORMAT = DateTimeFormatter.ofPattern("d-M-y");

    /**
     * @param date date in format "dd-mm-yyyy" (dd - one or two digits for day in month, mm - one or two digits
     *             for month in year, yyyy - four digits for year).
     */
    public static LocalDate parseDate(String date) {
        if (date == null) {
            throw new IllegalArgumentException("Date must not be null.");
        }

        if (!isCorrectDatePattern(date)) {
            throw new IllegalArgumentException("Date " + date + " is not in expected format (dd-mm-yyyy).");
        }

        return LocalDate.parse(date, INDIAN_DATE_FORMAT);
    }

    /**
     * @return {@code true} if date matches the pattern "dd-mm-yyyy", otherwise {@code false}
     * (dd - one or two digits for day in month, mm - one or two digits for month in year,
     * yyyy - four digits for year).
     */
    public static boolean isCorrectDatePattern(String date) {
        return date.matches("[0-3]?[0-9]-[0-1]?[0-9]-[1-2][0-9]{3}");
    }
}
