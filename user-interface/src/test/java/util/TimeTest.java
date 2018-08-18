package util;

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

    @Test(expected = IllegalArgumentException.class)
    public void should_not_accept_invalid_date_format() {
        String date = "27/07/2000";

        Time.parseDate(date);
    }
}
