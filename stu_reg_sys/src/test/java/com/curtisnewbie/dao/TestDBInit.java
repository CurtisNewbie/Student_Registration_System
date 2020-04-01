package com.curtisnewbie.dao;

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
            DBManager dbManager = new DBManager();
            dbManager.dropTables();
            dbManager.createTables();
            dbManager.insertTestData();
            started = true;
        }
    }
}