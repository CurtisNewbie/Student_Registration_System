package com.curtisnewbie.dao;

import java.util.List;

import com.curtisnewbie.model.Lecturer;

/**
 * ------------------------------------
 * 
 * Author: Yongjie Zhuang
 * 
 * ------------------------------------
 * <p>
 * Dao for {@link com.curtisnewbie.model.Lecturer} resources
 * </p>
 */
public interface LecturerDao extends Dao<Lecturer> {

    /**
     * Update firstname of a {@link Lecturer}
     * 
     * @param id    id
     * @param fname firstname
     * @return {@code True} if successful else {@code False}
     */
    public boolean updateFirstname(int id, String fname);

    /**
     * Update lastname of a {@link Lecturer}
     * 
     * @param id    id
     * @param lname lastname
     * @return {@code True} if successful else {@code False}
     */
    public boolean updateLastname(int id, String lname);

    /**
     * Update position of a {@link Lecturer}
     * 
     * @param id  id
     * @param pos position
     * @return {@code True} if successful else {@code False}
     */
    public boolean updatePosition(int id, String pos);

    /**
     * Find a list of {@link Lecturer} by firstname
     * 
     * @param fname firstname
     * @return a list of {@link Lecturer}
     */
    public List<Lecturer> findByFirstname(String fname);

    /**
     * Find a list of {@link Lecturer} by lastname
     * 
     * @param lname lastname
     * @return a list of {@link Lecturer}
     */
    public List<Lecturer> findByLastname(String lname);

    /**
     * Find a list of {@link Lecturer} by position
     * 
     * @param pos position
     * @return a list of {@link Lecturer}
     */
    public List<Lecturer> findByPosition(String pos);

}