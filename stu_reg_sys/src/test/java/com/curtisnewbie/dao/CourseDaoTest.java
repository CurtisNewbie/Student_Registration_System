package com.curtisnewbie.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.curtisnewbie.model.Course;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CourseDaoTest {

    /** the id of a Course instance that should be read-only */
    private final int READ_ONLY_ID = 1;
    /** the id of a Course instance that is expected to be modified */
    private final int MODIFIED_ID = 2;
    /** the id of a Course instance that is expected to be deleted */
    private final int DELETED_ID = 3;
    /** the id of a Course instance that will be created */
    private final int CREATED_ID = 10;

    private final String NAME = "Computer Science";
    private final int CREDIT = 180;

    private final CourseDao dao = new CourseRepository();

    @BeforeAll
    static void insertTestData() {
        TestDBInit.init();
    }

    @Test
    void shouldGetAllCourses() {
        List<Course> list = dao.getAll();
        assertTrue(list.size() > 0);
    }

    @Test
    void shouldFindCourseById() {
        Course lect = dao.findById(READ_ONLY_ID);
        assertNotNull(lect.getName());
        assertNotNull(lect.getCredit());
    }

    @Test
    void shouldDeleteCourse() {
        assertTrue(dao.deleteById(DELETED_ID));
        assertNull(dao.findById(DELETED_ID));
    }

    @Test
    void shouldFindByName() {
        Course lect = dao.findByName(NAME);
        assertNotNull(lect.getName());
        assertNotNull(lect.getCredit());
    }

    @Test
    void shouldFindByCredit() {
        List<Course> list = dao.findByCredit(CREDIT);
        assertTrue(list.size() > 0);
    }

    @Test
    void shouldUpdateName() {
        var prevName = dao.findById(MODIFIED_ID).getName();
        var updatedName = prevName + "_Updated";
        dao.updateName(MODIFIED_ID, updatedName);
        var currName = dao.findById(MODIFIED_ID).getName();
        assertNotEquals(prevName, currName);
        assertEquals(updatedName, currName);
    }

    @Test
    void shouldUpdateCredit() {
        var prevCredit = dao.findById(MODIFIED_ID).getCredit();
        var updatedCredit = prevCredit + 1;
        dao.updateCredit(MODIFIED_ID, updatedCredit);
        var currCredit = dao.findById(MODIFIED_ID).getCredit();
        assertNotEquals(prevCredit, currCredit);
        assertEquals(updatedCredit, currCredit);
    }

}
