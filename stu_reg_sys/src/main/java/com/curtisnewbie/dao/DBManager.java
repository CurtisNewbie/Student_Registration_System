package com.curtisnewbie.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.ibatis.jdbc.ScriptRunner;
import com.curtisnewbie.util.LoggerProducer;

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
    private final Logger logger = LoggerProducer.getLogger(this);

    /**
     * Get database connection
     * 
     * @return created Connection or {@code NULL} if database access error found
     */
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return null;
    }

    /**
     * Create tables needed
     */
    public void createTables() {
        var in = getClass().getClassLoader().getResourceAsStream("create_tables.sql");
        if (in == null) {
            logger.log(Level.SEVERE, "create_tables.sql script not found");
            System.exit(1);
        }

        try (Reader reader = new BufferedReader(new InputStreamReader(in));) {
            ScriptRunner scriptRunner = new ScriptRunner(getConnection());
            scriptRunner.runScript(reader);
            logger.info("Script successfully ran for creating tables");
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            System.exit(1);
        }
    }
}