package com.curtisnewbie.main;

import com.curtisnewbie.dao.DBManager;
import com.curtisnewbie.util.LoggerProducer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    private final int DEF_WIDTH = 1300;
    private final int MIN_WIDTH = 780;
    private final int MIN_HEIGHT = 600;

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

        // initialise formatter for logging
        LoggerProducer.initFormatter();

        // load fxml
        var fxmlIn = this.getClass().getClassLoader().getResourceAsStream("gui.fxml");
        root = new FXMLLoader().load(fxmlIn);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        App.primaryStage = primaryStage;

        Scene s = new Scene(root);
        primaryStage.setScene(s);
        primaryStage.setTitle(TITLE);
        primaryStage.setWidth(DEF_WIDTH);
        primaryStage.setMinWidth(MIN_WIDTH);
        primaryStage.setMinHeight(MIN_HEIGHT);
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
