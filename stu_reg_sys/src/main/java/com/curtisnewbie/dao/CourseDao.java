package com.curtisnewbie.dao;

import java.util.List;

import com.curtisnewbie.model.Course;

/**
 * ------------------------------------
 * 
 * Author: Yongjie Zhuang
 * 
 * ------------------------------------
 * <p>
 * Dao for {@link com.curtisnewbie.model.Course} resources
 * </p>
 */
public interface CourseDao extends Dao<Course> {

    /**
     * Find {@link Course} by name
     * 
     * @return {@link Course}
     */
    public Course findByName(String name);

    /**
     * update name of the {@link Course} with the given id
     * 
     * @return {@code True} if successful else {@code False}
     */
    public boolean updateName(int id, String name);

    /**
     * Find a list of {@link Course} by name
     * 
     * @return a list of {@link Course}
     */
    public List<Course> findByCredit(String name);

    /**
     * Update the credit of the {@link Course} with the given id
     * 
     * @return {@code True} if successful else {@code False}
     */
    public boolean updateCredit(int id, int credit);
}