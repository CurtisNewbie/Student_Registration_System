package com.curtisnewbie.dao;

import java.util.List;

import com.curtisnewbie.model.Faculty;

/**
 * ------------------------------------
 * 
 * Author: Yongjie Zhuang
 * 
 * ------------------------------------
 * <p>
 * Dao for {@link com.curtisnewbie.model.Faculty} resources
 * </p>
 */
public interface FacultyDao extends UnitDao<Faculty> {

    /**
     * Find a {@code List} of {@code Faculty} by name
     * 
     * @return a List of {@code Faculty}
     */
    public List<Faculty> findByName(String name);

    /**
     * Update the name of the {@code Faculty} with the given id
     * 
     * @return {@code True} if successful else {@code False}
     */
    public boolean updateName(int id, String name);

}