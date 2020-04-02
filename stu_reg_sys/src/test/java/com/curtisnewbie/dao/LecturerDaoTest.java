package com.curtisnewbie.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.curtisnewbie.model.Lecturer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * ------------------------------------
 * 
 * Author: Yongjie Zhuang
 * 
 * ------------------------------------
 * <p>
 * Unit tests for {@link LecturerDao}
 * </p>
 */
public class LecturerDaoTest {

    /** the id of a Lecturer instance that should be read-only */
    private final int READ_ONLY_ID = 1;
    /** the id of a Lecturer instance that is expected to be modified */
    private final int MODIFIED_ID = 2;
    /** the id of a Lecturer instance that is expected to be deleted */
    private final int DELETED_ID = 3;
    /** the id of a Lecturer instance that will be created */
    private final int CREATED_ID = 10;

    private final String FNAME = "John";
    private final String LNAME = "Cena";
    private final String POSITION = "Pricipal Lecturer";

    private final LecturerDao dao = new LecturerRepository();

    @BeforeAll
    static void insertTestData() {
        TestDBInit.init();
    }

    @Test
    void shouldGetAllLecturers() {
        List<Lecturer> lects = dao.getAll();
        assertTrue(lects.size() > 0);
    }

    @Test
    void shouldFindLecturerById() {
        Lecturer lect = dao.findById(READ_ONLY_ID);
        assertNotNull(lect.getFirstname());
        assertNotNull(lect.getLastname());
        assertNotNull(lect.getPosition());
    }

    @Test
    void shouldDeleteLecturer() {
        assertTrue(dao.deleteById(DELETED_ID));
        assertNull(dao.findById(DELETED_ID));
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
    void shouldUpdatePosition() {
        var prevPosition = dao.findById(MODIFIED_ID).getPosition();
        var updatedPosition = prevPosition + "_Updated";
        dao.updatePosition(MODIFIED_ID, updatedPosition);
        var currPosition = dao.findById(MODIFIED_ID).getPosition();
        assertNotEquals(prevPosition, currPosition);
        assertEquals(updatedPosition, currPosition);
    }

    @Test
    void shouldUpdateLecturer() {
        var prev = dao.findById(MODIFIED_ID);
        var updatedFname = prev.getFirstname() + "PenPineapple";
        var updatedLname = prev.getLastname() + "ApplePen";
        var updatedPos = prev.getPosition() + "_ppap";
        Lecturer stu = new Lecturer(MODIFIED_ID, updatedFname, updatedLname, updatedPos);
        dao.update(stu);
        var updated = dao.findById(MODIFIED_ID);
        assertNotEquals(prev.getPosition(), updated.getPosition());
        assertNotEquals(prev.getLastname(), updated.getLastname());
        assertNotEquals(prev.getFirstname(), updated.getFirstname());
        assertEquals(updatedFname, updated.getFirstname());
        assertEquals(updatedLname, updated.getLastname());
        assertEquals(updatedPos, updated.getPosition());
    }

    @Test
    void shouldFindLecturerByFirstname() {
        List<Lecturer> lect = dao.findByFirstname(FNAME);
        assertTrue(lect.size() > 0);
    }

    @Test
    void shouldFindLecturerByLastname() {
        List<Lecturer> lect = dao.findByLastname(LNAME);
        assertTrue(lect.size() > 0);
    }

    @Test
    void shouldFindLecturerByPosition() {
        List<Lecturer> lect = dao.findByPosition(POSITION);
        assertTrue(lect.size() > 0);
    }

    @Test
    void shouldCreateLecturer() {
        var lect = new Lecturer(CREATED_ID, "CreatedLecturer", "WaterBottle", "Student Dean");
        assertTrue(dao.create(lect));
        assertNotNull(dao.findById(CREATED_ID));
    }
}