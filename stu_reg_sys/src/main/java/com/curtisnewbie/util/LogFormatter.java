package com.curtisnewbie.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * ------------------------------------
 * 
 * Author: Yongjie Zhuang
 * 
 * ------------------------------------
 * <p>
 * LogFormatter are used to format the msg logged in terminal
 * </p>
 * 
 * @see https://wiki.bash-hackers.org/scripting/terminalcodes
 * 
 */
public class LogFormatter extends Formatter {

    public static final String ANSI_RESET_ALL = "\u001B[0m";

    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RESET_COLOUR = "\u001B[39m";

    public static final String ANSI_BOLD = "\u001B[1m";

    @Override
    public String format(LogRecord record) {
        var levelColour = ANSI_GREEN;
        if (record.getLevel().equals(Level.SEVERE))
            levelColour = ANSI_RED;
        StringBuilder sb = new StringBuilder();
        // append level msg
        sb.append(String.format("[%s%s%s%s]", ANSI_BOLD, levelColour, record.getLevel(), ANSI_RESET_ALL));
        // append date, time
        sb.append(String.format(" %s ", datestr(record.getInstant())));
        // append logger name
        sb.append(String.format("%s%s%s%s ", ANSI_BOLD, ANSI_BLUE, record.getLoggerName(), ANSI_RESET_COLOUR));
        // append msg
        sb.append(String.format("%s'%s'%s\n", record.getLevel().equals(Level.SEVERE) ? ANSI_RED : ANSI_WHITE,
                record.getMessage(), ANSI_RESET_ALL));
        return sb.toString();
    }

    private String datestr(Instant instant) {
        var ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return ldt.getDayOfMonth() + "-" + ldt.getMonthValue() + "-" + ldt.getYear() + " " + ldt.getHour() + ":"
                + ldt.getMinute() + ":" + ldt.getSecond();
    }
}