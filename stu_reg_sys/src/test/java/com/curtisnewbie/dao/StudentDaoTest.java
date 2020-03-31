package com.curtisnewbie.dao;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.curtisnewbie.model.Student;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * ------------------------------------
 * 
 * Author: Yongjie Zhuang
 * 
 * ------------------------------------
 * <p>
 * Unit tests for {@link StudentDao}
 * </p>
 */
public class StudentDaoTest {

    /** the id of a Student instance that should be read-only */
    private final int READ_ONLY_ID = 1;
    /** the id of a Student instance that is expected to be modified */
    private final int MODIFIED_ID = 2;
    /** the id of a Student instance that is expected to be deleted */
    private final int DELETED_ID = 3;
    /** the id of a Student instance that will be created */
    private final int CREATED_ID = 10;

    private final int YEAR = 2020;
    private final int MONTH = 0;
    private final int DAY = 21;
    private final String FNAME = "Curtis";
    private final String LNAME = "Newbie";
    private final StudentDao dao = new StudentRepository();

    @BeforeAll
    static void insertTestData() {
        DBManager dbManager = new DBManager();
        dbManager.dropTables();
        dbManager.createTables();
        dbManager.insertTestData();
    }

    @Test
    void shouldGetAllStudents() {
        List<Student> stus = dao.getAllStudents();
        assertTrue(stus.size() > 0);
    }

    @Test
    void shouldFindStuById() {
        Student stu = dao.findStudentById(READ_ONLY_ID);
        assertNotNull(stu.getFirstname());
        assertNotNull(stu.getLastname());
        assertNotNull(stu.getDateOfRegi());
    }

    @Test
    void shouldFindStusByFirstname() {
        List<Student> stus = dao.findStusByFirstname(FNAME);
        assertTrue(stus.size() > 0);
    }

    @Test
    void shouldFindStusByLastname() {
        List<Student> stus = dao.findStusByLastname(LNAME);
        assertTrue(stus.size() > 0);
    }

    @Test
    void shouldFindStusByDateOfReg() {
        Date d = new GregorianCalendar(YEAR, MONTH, DAY).getTime();
        List<Student> stus = dao.findStusByDateOfReg(d);
        assertTrue(stus.size() > 0);
        var stu = stus.get(0);
        assertNotNull(stu.getFirstname());
        assertNotNull(stu.getLastname());
        assertNotNull(stu.getDateOfRegi());
    }

    @Test
    void shouldUpdateFirstname() {
        var prev = dao.findStudentById(MODIFIED_ID);
        dao.updateFirstname(MODIFIED_ID, prev.getFirstname() + "_Updated");
        assertNotEquals(prev.getFirstname(), dao.findStudentById(MODIFIED_ID).getFirstname());
    }

    @Test
    void shouldUpdateLastname() {
        var prev = dao.findStudentById(MODIFIED_ID);
        dao.updateLastname(MODIFIED_ID, prev.getLastname() + "_Updated");
        assertNotEquals(prev.getLastname(), dao.findStudentById(MODIFIED_ID).getLastname());
    }

    @Test
    void shouldUpdateDateOfReg() {
        var prev = dao.findStudentById(MODIFIED_ID);
        dao.updateDateOfReg(MODIFIED_ID, new Date(prev.getDateOfRegi().getTime() - 24 * 60 * 60 * 1000));
        assertNotEquals(prev.getDateOfRegi(), dao.findStudentById(MODIFIED_ID).getDateOfRegi());
    }

    @Test
    void shouldUpdateStudent() {
        var prev = dao.findStudentById(MODIFIED_ID);
        Student stu = new Student(MODIFIED_ID, prev.getFirstname() + "PenPineapple", prev.getLastname() + "ApplePen",
                new Date(prev.getDateOfRegi().getTime() - 24 * 60 * 60 * 1000));
        dao.updateStudent(stu);
        var updated = dao.findStudentById(MODIFIED_ID);
        assertNotEquals(prev.getDateOfRegi(), updated.getDateOfRegi());
        assertNotEquals(prev.getLastname(), updated.getLastname());
        assertNotEquals(prev.getFirstname(), updated.getFirstname());
    }

    @Test
    void shouldDeleteStudent() {
        assertTrue(dao.deleteStudentById(DELETED_ID));
        assertNull(dao.findStudentById(DELETED_ID));
    }

    @Test
    void shouldCreateStudent() {
        var stu = new Student(CREATED_ID, "PenPineapple", "ApplePen", new Date(System.currentTimeMillis()));
        assertTrue(dao.createStudent(stu));
        assertNotNull(dao.findStudentById(CREATED_ID));
    }

}