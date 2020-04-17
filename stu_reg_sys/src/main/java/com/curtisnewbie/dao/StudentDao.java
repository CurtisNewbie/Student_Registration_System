package com.curtisnewbie.dao;

import java.time.LocalDate;
import java.util.List;

import com.curtisnewbie.model.Student;

/**
 * ------------------------------------
 * 
 * Author: Yongjie Zhuang
 * 
 * ------------------------------------
 * <p>
 * Dao for {@link com.curtisnewbie.model.Student} resources
 * </p>
 */
public interface StudentDao extends UnitDao<Student> {

    /**
     * Update firstname of a {@link Student}
     * 
     * @param id    id
     * @param fname firstname
     * @return {@code True} if successful else {@code False}
     */
    public boolean updateFirstname(int id, String fname);

    /**
     * Update lastname of a {@link Student}
     * 
     * @param id    id
     * @param lname lastname
     * @return {@code True} if successful else {@code False}
     */
    public boolean updateLastname(int id, String lname);

    /**
     * Update date of registration of a {@link Student}
     * 
     * @param id   id
     * @param date date of registration
     * @return {@code True} if successful else {@code False}
     */
    public boolean updateDateOfReg(int id, LocalDate date);

    /**
     * Find a list of {@link Student} by firstname
     * 
     * @param fname firstname
     * @return a list of {@link Student}
     */
    public List<Student> findStusByFirstname(String fname);

    /**
     * Find a list of {@link Student} by lastname
     * 
     * @param lname lastname
     * @return a list of {@link Student}
     */
    public List<Student> findStusByLastname(String lname);

    /**
     * Find a list of {@link Student} by date of registration
     * 
     * @param date date of registration
     * @return a list of {@link Student}
     */
    public List<Student> findStusByDateOfReg(LocalDate date);

    /**
     * Update {@code Student}
     * 
     * @param firstname
     * @param lastname
     * @param date
     * @param id
     * @return {@code True} if successful else {@code False}
     */
    public boolean update(String firstname, String lastname, LocalDate date, int id, int courseId);

}