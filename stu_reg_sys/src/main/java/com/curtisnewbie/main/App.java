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
        var manager = new DBManager();
        manager.createTables();
        manager.insertDemoData();

        StudentDao dao = new StudentRepository();
        var stu = dao.findStudentById(1);
        System.out.println("\n\n" + stu.toString());

        // update fname
        dao.updateFirstname(1, "Yongjie");
        System.out.println("Updated Firstname: " + dao.findStudentById(1).toString());

        // update lname
        dao.updateLastname(1, "Zhuang");
        System.out.println("Updated Lastname: " + dao.findStudentById(1).toString());

        // update date of reg
        dao.updateDateOfReg(1, new Date(System.currentTimeMillis()));
        System.out.println(String.format("Updated Date:%s ", dao.findStudentById(1).toString()));

        Student toBeUpdated = new Student(1, "PenPineapple", "ApplePen", stu.getDateOfRegi());
        dao.updateStudent(toBeUpdated);
        System.out.println(String.format("Updated Student:%s ", dao.findStudentById(1).toString()));

        Student createdWithoutId = new Student(Student.GENERATED_ID, "New", "StudentWithoutId",
                new Date(System.currentTimeMillis()));
        dao.createStudent(createdWithoutId);
        Student createdWithId = new Student(5, "New", "StudentWithId", new Date(System.currentTimeMillis()));
        dao.createStudent(createdWithId);

        System.out.println();
        var list = dao.getAllStudents();
        for (var s : list) {
            System.out.println(s.toString());
        }
        System.out.println();

        System.out.println("Find by firstname " + dao.findStusByFirstname("PenPineapple"));
        System.out.println("Find by lastname " + dao.findStusByLastname("Lalala"));
        System.out.println("Find by dateOfReg " + dao.findStusByDateOfReg(new Date(System.currentTimeMillis())));

        // System.out.printf("\nDeleted student with id 1, %b\n",
        // dao.deleteStudentById(1));
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
