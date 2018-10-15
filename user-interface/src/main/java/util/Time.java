package util;

import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class Time {

    public static final DateTimeFormatter INDIAN_DATE_FORMAT = DateTimeFormatter.ofPattern("d-M-y");

    /**
     * @param date date in format "dd-mm-yyyy"
     */
    public static LocalDate parseDate(String date) {
        if (date == null) {
            throw new IllegalArgumentException("Date must not be null.");
        }
        
        String datePattern = "\\d{2}-\\d{2}-\\d{4}";
        if (!date.matches(datePattern)) {
            throw new IllegalArgumentException("Date " + date + " is not in expected format (dd-mm-yyyy).");
        }

        return LocalDate.parse(date, INDIAN_DATE_FORMAT);
    }
}
