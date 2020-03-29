package com.curtisnewbie.dao;

import java.sql.Connection;
import java.sql.DriverManager;

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
public class DBService {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "adminpw";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/reg_sys";

    public static Connection getConnection() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);) {
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}