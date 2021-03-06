package com.curtisnewbie.dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.curtisnewbie.model.Module;

public class ModuleDaoTest {

    /** the id of a Module instance that should be read-only */
    private final int READ_ONLY_ID = 1;
    /** the id of a Module instance that is expected to be modified */
    private final int MODIFIED_ID = 2;
    /** the id of a Module instance that is expected to be deleted */
    private final int DELETED_ID = 3;
    /** the id of a Module instance that will be created */
    private final int CREATED_ID = 10;

    private final String NAME = "Data Structure";
    private final int CREDIT = 20;

    private final ModuleDao dao = new ModuleRepository();

    @BeforeAll
    static void insertTestData() {
        TestDBInit.init();
    }

    @Test
    void shouldGetAllModules() {
        List<Module> list = dao.getAll();
        assertTrue(list.size() > 0);
    }

    @Test
    void shouldFindModuleById() {
        Module modu = dao.findById(READ_ONLY_ID);
        assertNotNull(modu.getName());
        assertNotNull(modu.getCredit());
    }

    @Test
    void shouldDeleteModule() {
        assertTrue(dao.deleteById(DELETED_ID));
        assertNull(dao.findById(DELETED_ID));
    }

    @Test
    void shouldFindByName() {
        var list = dao.findByName(NAME);
        assertTrue(list.size() > 0);
    }

    @Test
    void shouldFindByCredit() {
        List<Module> list = dao.findByCredit(CREDIT);
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
    void shouldUpdateModule() {
        var prev = dao.findById(MODIFIED_ID);
        var updatedName = prev.getName() + "_updated";
        var updatedCredit = prev.getCredit() + 1;
        Module stu = new Module(MODIFIED_ID, updatedName, updatedCredit);
        dao.update(stu);
        var updated = dao.findById(MODIFIED_ID);
        assertNotEquals(prev.getName(), updated.getName());
        assertNotEquals(prev.getCredit(), updated.getCredit());
        assertEquals(updatedName, updated.getName());
        assertEquals(updatedCredit, updated.getCredit());
    }

    @Test
    void shouldCreateModule() {
        assertTrue(dao.create(new Module(CREATED_ID, "CreatedModule", 200)));
        assertNotNull(dao.findById(CREATED_ID));
    }
}