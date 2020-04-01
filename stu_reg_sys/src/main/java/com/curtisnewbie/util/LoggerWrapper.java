package com.curtisnewbie.util;

import java.util.logging.Logger;

/**
 * ------------------------------------
 * 
 * Author: Yongjie Zhuang
 * 
 * ------------------------------------
 * <p>
 * A wapper of {@link java.util.logging.Logger} that can be disabled.
 * </p>
 */
public class LoggerWrapper {

    private boolean disabled;
    private Logger logger;

    LoggerWrapper(String name) {
        this.disabled = false;
        this.logger = Logger.getLogger(name);
    }

    LoggerWrapper(String name, boolean disabled) {
        this.disabled = disabled;
        this.logger = Logger.getLogger(name);
    }

    public void info(String msg) {
        if (!disabled)
            logger.info(msg);
    }

    public void severe(String msg) {
        if (!disabled)
            logger.info(msg);
    }
}