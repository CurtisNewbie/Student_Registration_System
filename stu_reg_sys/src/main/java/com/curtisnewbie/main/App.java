package com.curtisnewbie.main;

import com.curtisnewbie.dao.DBService;

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
        new DBService().createTables();
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