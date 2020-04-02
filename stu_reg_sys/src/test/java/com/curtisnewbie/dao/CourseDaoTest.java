package com.curtisnewbie.dao;

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
}