package com.curtisnewbie.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.curtisnewbie.model.Course;
import com.curtisnewbie.model.Lecturer;
import com.curtisnewbie.model.Module;
import com.curtisnewbie.model.Student;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CommonDaoTest {

    private final CommonDao dao = new CommonDaoImpl();

    // for testing the add and getAll functionalities
    private final int ADDONLY_MODULE = 1;
    private final int ADDONLY_LECTURER = 1;
    private final int ADDONLY_COURSE = 1;
    private final int ADDONLY_STUDENT = 1;
    private final int LECTURE_TO_BE_ADDED = 4;
    private final int MODULE_TO_BE_ADDED = 4;
    private final int STUDENT_TO_BE_ADDED = 4;

    // for testing the remove functionalties
    private final int REMOVEONLY_MODULE = 2;
    private final int REMOVEONLY_COURSE = 2;
    private final int LECTURE_TO_BE_REMOVED = 1;
    private final int MODULE_TO_BE_REMOVED = 1;
    private final int STUDENT_TO_BE_REMOVED = 1;

    @BeforeAll
    static void insertTestData() {
        TestDBInit.init();
    }

    @Test
    void shouldGetAllLecturersInModule() {
        var list = dao.getAllLectInModu(ADDONLY_MODULE);
        assertTrue(list.size() > 0);
        assertTrue(list.get(0) instanceof Lecturer);
    }

    @Test
    void shouldGetAllModulesOfLecturer() {
        var list = dao.getAllModuOfLect(ADDONLY_LECTURER);
        assertTrue(list.size() > 0);
        assertTrue(list.get(0) instanceof Module);
    }

    @Test
    void shouldGetAllModulesInCourse() {
        var list = dao.getAllModuInCour(ADDONLY_COURSE);
        assertTrue(list.size() > 0);
        assertTrue(list.get(0) instanceof Module);

    }

    @Test
    void shouldGetAllCoursesOfModule() {
        var list = dao.getAllCourOfModu(ADDONLY_MODULE);
        assertTrue(list.size() > 0);
        assertTrue(list.get(0) instanceof Course);
    }

    @Test
    void shouldGetAllStudentsInModule() {
        var list = dao.getAllStudInModu(ADDONLY_MODULE);
        assertTrue(list.size() > 0);
        assertTrue(list.get(0) instanceof Student);
    }

    @Test
    void shouldGetAllModulesOfStudent() {
        var list = dao.getAllModuOfStud(ADDONLY_STUDENT);
        assertTrue(list.size() > 0);
        assertTrue(list.get(0) instanceof Module);
    }

    @Test
    void shouldAddLecturerToModule() {
        assertTrue(dao.addLectToModu(ADDONLY_MODULE, LECTURE_TO_BE_ADDED));
    }

    @Test
    void shouldRemoveLecturerFromModule() {
        assertTrue(dao.removeLectFromModu(REMOVEONLY_MODULE, LECTURE_TO_BE_REMOVED));
    }

    @Test
    void shouldAddModuleToCourse() {
        assertTrue(dao.addModuToCour(ADDONLY_COURSE, MODULE_TO_BE_ADDED));
    }

    @Test
    void shouldRemoveModuleFromCourse() {
        assertTrue(dao.removeModuFromCour(REMOVEONLY_COURSE, MODULE_TO_BE_REMOVED));
    }

    @Test
    void shouldAddStudentToModule() {
        assertTrue(dao.addStudToModu(ADDONLY_MODULE, STUDENT_TO_BE_ADDED));
    }

    @Test
    void shouldRemoveStudentFromModule() {
        assertTrue(dao.removeStudFromModu(REMOVEONLY_MODULE, STUDENT_TO_BE_REMOVED));
    }
}