package com.curtisnewbie.dao;

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
     * Find {@code Faculty} by name
     * 
     * @return a Faculty
     */
    public Faculty findByName(String name);

    /**
     * Update the name of the {@code Faculty} with the given id
     * 
     * @return {@code True} if successful else {@code False}
     */
    public boolean updateName(int id, String name);

}