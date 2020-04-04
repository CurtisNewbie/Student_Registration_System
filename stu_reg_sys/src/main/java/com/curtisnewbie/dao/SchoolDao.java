package com.curtisnewbie.dao;

import com.curtisnewbie.model.School;

/**
 * ------------------------------------
 * 
 * Author: Yongjie Zhuang
 * 
 * ------------------------------------
 * <p>
 * Dao for {@link com.curtisnewbie.model.School} resources
 * </p>
 */
public interface SchoolDao extends Dao<School> {

    /**
     * Find {@code School} by name
     * 
     * @return a School
     */
    public School findByName(String name);

    /**
     * Update the name of the {@code School} with the given id
     * 
     * @return {@code True} if successful else {@code False}
     */
    public boolean updateName(int id, String name);
}