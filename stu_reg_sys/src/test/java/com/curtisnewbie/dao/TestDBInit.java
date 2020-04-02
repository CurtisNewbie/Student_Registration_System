package com.curtisnewbie.dao;

import com.curtisnewbie.util.LoggerProducer;

/**
 * ------------------------------------
 * 
 * Author: Yongjie Zhuang
 * 
 * ------------------------------------
 * <p>
 * Class that handles db init and inserts test data for testing
 * </p>
 */
public class TestDBInit {

    private static boolean started = false;

    /** Drop tables and insert test data for testing, it only runs for once */
    static void init() {
        if (!started) {
            // disable logger
            // LoggerProducer.disableLogging();
            LoggerProducer.initFormatter();
            // db operations
            DBManager dbManager = DBManager.getDBManager();
            dbManager.dropTables();
            dbManager.createTables();
            dbManager.insertTestData();
            started = true;
        }
    }
}