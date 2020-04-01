package com.curtisnewbie.util;

import java.util.logging.Logger;

/**
 * ------------------------------------
 * 
 * Author: Yongjie Zhuang
 * 
 * ------------------------------------
 * <p>
 * Producer of Logger. Before claiming any logger using the get methods, the
 * logger can be disabled beforehands using
 * {@link LoggerProducer#disableLogging()} Any logger claimed after this method
 * call will not log any message, e.g., on terminal and so on.
 * </p>
 */
public class LoggerProducer {

    /** Control whether the logging functionalities should be disabled */
    private static boolean disabled = false;

    /**
     * Get logger with the name of the class. See {@link LoggerWrapper}
     * 
     * @return {@link LoggerWrapper}
     */
    public static LoggerWrapper getLogger(Class<?> c) {
        return getLogger(c.getName());
    }

    /**
     * Get logger with the given name. See {@link LoggerWrapper}
     * 
     * @return {@link LoggerWrapper}
     */
    public static LoggerWrapper getLogger(String name) {
        return new LoggerWrapper(name, disabled);
    }

    /**
     * Get logger with the name of the class of the object. See
     * {@link LoggerWrapper}
     * 
     * @return {@link LoggerWrapper}
     */
    public static LoggerWrapper getLogger(Object obj) {
        return getLogger(obj.getClass());
    }

    /**
     * Disable logging functionalty. Any logger claimed after this method call will
     * not log any message, e.g., on terminal and so on.
     */
    public static void disableLogging() {
        if (!disabled)
            disabled = true;
    }
}