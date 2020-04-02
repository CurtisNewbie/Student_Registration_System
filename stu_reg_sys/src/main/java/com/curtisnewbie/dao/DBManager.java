package com.curtisnewbie.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.ibatis.jdbc.ScriptRunner;
import com.curtisnewbie.util.LoggerProducer;
import com.curtisnewbie.util.LoggerWrapper;

/**
 * ------------------------------------
 * 
 * Author: Yongjie Zhuang
 * 
 * ------------------------------------
 * <p>
 * Class that stores properties required for DB connections and provides
 * convenient factory method to 'produce' Connection object.
 * </p>
 * <p>
 * The credentials are for demo only, DB and user should be created beforehand
 * using the queries below:<br>
 * <br>
 * CREATE DATABASE IF NOT EXISTS reg_sys;<br>
 * <br>
 * CREATE USER 'admin'@'localhost' IDENTIFIED BY 'adminpw';<br>
 * <br>
 * GRANT ALL ON reg_sys.* TO 'admin'@'localhost';<br>
 * </p>
 */
public class DBManager {

    private final String USERNAME = "admin";
    private final String PASSWORD = "adminpw";
    private final String DB_URL = "jdbc:mysql://localhost:3306/reg_sys";
    private final LoggerWrapper logger = LoggerProducer.getLogger(this);

    /**
     * internally stores a db manager without creating it again and again, it's not
     * necessary a singleton
     */
    private static final DBManager dbManager = new DBManager();

    /**
     * Get a {@code DBManager}
     */
    public static DBManager getDBManager() {
        return dbManager;
    }

    /**
     * Get database connection
     * 
     * @return created Connection or {@code NULL} if database access error found
     */
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return null;
    }

    /**
     * Create tables needed
     */
    public void createTables() {
        loadScript("create_tables.sql", true);
    }

    /**
     * Insert data for demo
     */
    public void insertDemoData() {
        loadScript("demo_data.sql", true);
    }

    /**
     * Drop all tables
     */
    public void dropTables() {
        loadScript("drop_tables.sql", true);
    }

    /**
     * Insert test data
     */
    public void insertTestData() {
        loadScript("test_data.sql", true);
    }

    private void loadScript(String scriptName, boolean exitOnFailure) {
        var in = getClass().getClassLoader().getResourceAsStream(scriptName);
        if (in == null) {
            logger.severe(String.format("Cannot find script file: '%s'", scriptName));
            if (exitOnFailure)
                System.exit(1);
        }

        try (Reader reader = new BufferedReader(new InputStreamReader(in));) {
            ScriptRunner scriptRunner = new ScriptRunner(getConnection());
            scriptRunner.runScript(reader);
            logger.info(String.format("Successfully ran '%s'", scriptName));
        } catch (Exception e) {
            logger.severe(e.getMessage());
            if (exitOnFailure)
                System.exit(1);
        }
    }
}