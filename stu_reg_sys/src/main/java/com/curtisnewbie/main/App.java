package com.curtisnewbie.main;

import java.util.Date;

import com.curtisnewbie.dao.DBManager;
import com.curtisnewbie.dao.StudentDao;
import com.curtisnewbie.dao.StudentRepository;
import com.curtisnewbie.model.Student;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * ------------------------------------
 * 
 * Author: Yongjie Zhuang
 * 
 * ------------------------------------
 * <p>
 * Javafx Application
 * </p>
 */
public class App extends Application {

    /** Primary Stage of Javafx App, may be used to create alerts. */
    static Stage primaryStage;

    /** Parent in FXML that will be loaded */
    private Parent root;

    private final String TITLE = "Student Registration System";

    @Override
    public void init() throws Exception {
        /*
         * ------------------------------------
         * 
         * Should be updated once implemented
         * 
         * ------------------------------------
         */
        var manager = new DBManager();
        manager.dropTables();
        manager.createTables();
        manager.insertDemoData();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        App.primaryStage = primaryStage;

        Scene s = new Scene(new Pane());
        primaryStage.setScene(s);
        primaryStage.setTitle(TITLE);
        primaryStage.setOnCloseRequest(e -> {
            System.exit(0);
        });
        primaryStage.show();
    }

    public static void main(String... args) {
        launch(args);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

}
