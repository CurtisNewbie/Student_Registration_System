package com.curtisnewbie.util;

import java.util.logging.Logger;

/**
 * ------------------------------------
 * 
 * Author: Yongjie Zhuang
 * 
 * ------------------------------------
 */
public class LoggerProducer {

    public static Logger getLogger(Class<?> c) {
        return getLogger(c.getName());
    }

    public static Logger getLogger(String name) {
        return Logger.getLogger(name);
    }

    public static Logger getLogger(Object obj) {
        return getLogger(obj.getClass());
    }
}