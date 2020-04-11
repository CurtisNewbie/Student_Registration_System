package com.curtisnewbie.main;

import java.util.Date;

import com.curtisnewbie.dao.DBManager;
import com.curtisnewbie.dao.StudentDao;
import com.curtisnewbie.dao.StudentRepository;
import com.curtisnewbie.model.Student;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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

    private final int MAX_WID = 1095;

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
        primaryStage.setWidth(MAX_WID);
        primaryStage.setResizable(false);
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
