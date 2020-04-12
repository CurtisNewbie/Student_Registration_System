package com.curtisnewbie.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Date string formatter that contains a number of static methods to produce the
 * formatted date string
 */
public class DateFormatter {

    /**
     * Parse and return a string that is in the correct Date format (YYYY-MM-DD)
     * 
     * @param dateStr
     * @return string of date in format YYYY-MM-DD or {@code NULL} if failed
     */
    public static String parseDateStr(String dateStr) {
        dateStr = dateStr.trim();
        if (dateStr == null || dateStr.isEmpty())
            return null;

        int y;
        int m;
        int d;
        Pattern pat = Pattern.compile(".*([1-9]\\d\\d\\d)-(\\d\\d?)-(\\d\\d?).*"); // yyyy-mm-dd
        Matcher matcher = pat.matcher(dateStr);
        if (matcher.find()) {
            try {
                y = Integer.parseInt(matcher.group(1));
                m = Integer.parseInt(matcher.group(2)) - 1;
                d = Integer.parseInt(matcher.group(3));
                return y + "-" + m + "-" + d;
            } catch (NumberFormatException e1) {
            }
        }
        return null;
    }

    /**
     * Convert a {@code LocalDate} to a date string in format: YYYY-MM-DD
     * 
     * @param date
     * @return a date string in format: YYYY-MM-DD
     */
    public static String toDateStr(LocalDate date) {
        return date.getYear() + "-" + date.getMonth() + "-" + date.getDayOfMonth();
    }

    /**
     * Convert an {@code Instant} to a date time string in format: DD-MM-YYYY
     * HH:MM:SS
     * 
     * @param instant
     * @return a date time string in format: DD-MM-YYYY HH:MM:SS
     */
    public static String toDateStr(Instant instant) {
        var ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return ldt.getDayOfMonth() + "-" + ldt.getMonthValue() + "-" + ldt.getYear() + " " + ldt.getHour() + ":"
                + ldt.getMinute() + ":" + ldt.getSecond();
    }
}