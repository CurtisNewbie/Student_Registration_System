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

    /** id of a Course instance that should be read-only */
    private final int READ_ONLY_ID = 1;
    /** id of a Course instance that is expected to be modified */
    private final int MODIFIED_ID = 2;
    /** id of a Course instance that is expected to be deleted */
    private final int DELETED_ID = 3;
    /** id of a Course instance that will be created */
    private final int CREATED_ID = 10;
    /** id of a School instance that must exist */
    private final int SCHOOL_ID = 1;
    /** id of a Lecturer instance that must exist */
    private final int LECTURER_ID = 1;
    /** name of a Course instance */
    private final String NAME = "Data Science";
    /** credit of one or more Course instance(s) */
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
        Course cour = dao.findById(READ_ONLY_ID);
        assertNotNull(cour.getName());
        assertNotNull(cour.getCredit());
    }

    @Test
    void shouldDeleteCourse() {
        assertTrue(dao.deleteById(DELETED_ID));
        assertNull(dao.findById(DELETED_ID));
    }

    @Test
    void shouldFindByName() {
        List<Course> list = dao.findByName(NAME);
        assertTrue(list.size() > 0);
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

    @Test
    void shouldUpdateCourse() {
        var prev = dao.findById(MODIFIED_ID);
        var updatedName = prev.getName() + "_updated";
        var updatedCredit = prev.getCredit() + 1;
        Course stu = new Course(MODIFIED_ID, updatedName, updatedCredit, SCHOOL_ID, LECTURER_ID);
        dao.update(stu);
        var updated = dao.findById(MODIFIED_ID);
        assertNotEquals(prev.getName(), updated.getName());
        assertNotEquals(prev.getCredit(), updated.getCredit());
        assertEquals(updatedName, updated.getName());
        assertEquals(updatedCredit, updated.getCredit());
    }

    @Test
    void shouldCreateCourse() {
        assertTrue(dao.create(new Course(CREATED_ID, "CreatedCourse", 200, SCHOOL_ID, LECTURER_ID)));
        assertNotNull(dao.findById(CREATED_ID));
    }
}
