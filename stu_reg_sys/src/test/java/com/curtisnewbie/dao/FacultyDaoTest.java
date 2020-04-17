package com.curtisnewbie.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.curtisnewbie.model.Faculty;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class FacultyDaoTest {

    /** the id of a Faculty instance that should be read-only */
    private final int READ_ONLY_ID = 1;
    /** the id of a Faculty instance that is expected to be modified */
    private final int MODIFIED_ID = 2;
    /** the id of a Faculty instance that is expected to be deleted */
    private final int DELETED_ID = 3;
    /** the id of a Faculty instance that will be created */
    private final int CREATED_ID = 10;

    private final String NAME = "Faculty of Social Science";

    private final FacultyDao dao = new FacultyRepository();

    @BeforeAll
    static void insertTestData() {
        TestDBInit.init();
    }

    @Test
    void shouldGetAllFaculties() {
        List<Faculty> list = dao.getAll();
        assertTrue(list.size() > 0);
    }

    @Test
    void shouldDeleteFaculty() {
        assertTrue(dao.deleteById(DELETED_ID));
        assertNull(dao.findById(DELETED_ID));
    }

    @Test
    void shouldFindFacultyById() {
        Faculty facu = dao.findById(READ_ONLY_ID);
        assertNotNull(facu.getName());
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
    void shouldFindByName() {
        var list = dao.findByName(NAME);
        assertTrue(list.size() > 0);
    }

    @Test
    void shouldUpdateFaculty() {
        var prev = dao.findById(MODIFIED_ID);
        var updatedName = prev.getName() + "_updated";
        Faculty facu = new Faculty(MODIFIED_ID, updatedName);
        dao.update(facu);
        var updated = dao.findById(MODIFIED_ID);
        assertNotEquals(prev.getName(), updated.getName());
        assertEquals(updatedName, updated.getName());
    }

    @Test
    void shouldCreateFaculty() {
        assertTrue(dao.create(new Faculty(CREATED_ID, "created Faculty")));
        assertNotNull(dao.findById(CREATED_ID));
    }

}