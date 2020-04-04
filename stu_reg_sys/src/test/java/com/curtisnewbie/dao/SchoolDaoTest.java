package com.curtisnewbie.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.curtisnewbie.model.School;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SchoolDaoTest {

    /** the id of a School instance that should be read-only */
    private final int READ_ONLY_ID = 1;
    /** the id of a School instance that is expected to be modified */
    private final int MODIFIED_ID = 2;
    /** the id of a School instance that is expected to be deleted */
    private final int DELETED_ID = 3;
    /** the id of a School instance that will be created */
    private final int CREATED_ID = 10;

    private final String NAME = "Information School";

    private final SchoolDao dao = new SchoolRepository();

    @BeforeAll
    static void insertTestData() {
        TestDBInit.init();
    }

    @Test
    void shouldGetAllSchools() {
        List<School> list = dao.getAll();
        assertTrue(list.size() > 0);
    }

    @Test
    void shouldDeleteSchool() {
        assertTrue(dao.deleteById(DELETED_ID));
        assertNull(dao.findById(DELETED_ID));
    }

    @Test
    void shouldFindSchoolById() {
        School scho = dao.findById(READ_ONLY_ID);
        assertNotNull(scho.getName());
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
        School scho = dao.findByName(NAME);
        assertNotNull(scho);
    }

    @Test
    void shouldUpdateSchool() {
        var prev = dao.findById(MODIFIED_ID);
        var updatedName = prev.getName() + "_updated";
        School scho = new School(MODIFIED_ID, updatedName);
        dao.update(scho);
        var updated = dao.findById(MODIFIED_ID);
        assertNotEquals(prev.getName(), updated.getName());
        assertEquals(updatedName, updated.getName());
    }

    @Test
    void shouldCreateSchool() {
        assertTrue(dao.create(new School(CREATED_ID, "created School")));
        assertNotNull(dao.findById(CREATED_ID));
    }
}