package com.curtisnewbie.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
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
    private final int MONTH = 1;
    private final int DAY = 21;
    private final String FNAME = "Curtis";
    private final String LNAME = "Newbie";
    private final int COURSE_ID = 1;

    private final StudentDao dao = new StudentRepository();

    @BeforeAll
    static void insertTestData() {
        TestDBInit.init();
    }

    @Test
    void shouldGetAllStudents() {
        List<Student> stus = dao.getAll();
        assertTrue(stus.size() > 0);
    }

    @Test
    void shouldFindStuById() {
        Student stu = dao.findById(READ_ONLY_ID);
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
        List<Student> stus = dao.findStusByDateOfReg(LocalDate.of(YEAR, MONTH, DAY));
        assertTrue(stus.size() > 0);
        var stu = stus.get(0);
        assertNotNull(stu.getFirstname());
        assertNotNull(stu.getLastname());
        assertNotNull(stu.getDateOfRegi());
    }

    @Test
    void shouldUpdateFirstname() {
        var prevFirstname = dao.findById(MODIFIED_ID).getFirstname();
        var updatedFirstname = prevFirstname + "_Updated";
        dao.updateFirstname(MODIFIED_ID, updatedFirstname);
        var currFirstname = dao.findById(MODIFIED_ID).getFirstname();
        assertNotEquals(prevFirstname, currFirstname);
        assertEquals(updatedFirstname, currFirstname);
    }

    @Test
    void shouldUpdateLastname() {
        var prevLastname = dao.findById(MODIFIED_ID).getLastname();
        var updatedLastname = prevLastname + "_Updated";
        dao.updateLastname(MODIFIED_ID, updatedLastname);
        var currLastname = dao.findById(MODIFIED_ID).getLastname();
        assertNotEquals(prevLastname, currLastname);
        assertEquals(updatedLastname, currLastname);
    }

    @Test
    void shouldUpdateDateOfReg() {
        var prev = dao.findById(MODIFIED_ID);
        dao.updateDateOfReg(MODIFIED_ID, prev.getDateOfRegi().minusDays(1));
        assertNotEquals(prev.getDateOfRegi(), dao.findById(MODIFIED_ID).getDateOfRegi());
    }

    @Test
    void shouldUpdateStudent() {
        var prev = dao.findById(MODIFIED_ID);
        Student stu = new Student(MODIFIED_ID, prev.getFirstname() + "PenPineapple", prev.getLastname() + "ApplePen",
                prev.getDateOfRegi().minusDays(1), COURSE_ID);
        dao.update(stu);
        var updated = dao.findById(MODIFIED_ID);
        assertNotEquals(prev.getDateOfRegi(), updated.getDateOfRegi());
        assertNotEquals(prev.getLastname(), updated.getLastname());
        assertNotEquals(prev.getFirstname(), updated.getFirstname());
    }

    @Test
    void shouldDeleteStudent() {
        assertTrue(dao.deleteById(DELETED_ID));
        assertNull(dao.findById(DELETED_ID));
    }

    @Test
    void shouldCreateStudent() {
        var stu = new Student(CREATED_ID, "PenPineapple", "ApplePen", LocalDate.now(), COURSE_ID);
        assertTrue(dao.create(stu));
        assertNotNull(dao.findById(CREATED_ID));
    }

}