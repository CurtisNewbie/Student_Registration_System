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
 * 
 * @see {@link com.curtisnewbie.model.Course}
 * @see {@link com.curtisnewbie.model.Module}
 * @see {@link com.curtisnewbie.dao.UnitDao}
 * 
 *      </p>
 */
public interface CourseDao extends UnitDao<Course> {

    /**
     * Find {@code Course} by name
     * 
     * @return {@code Course}
     */
    public Course findByName(String name);

    /**
     * update name of the {@code Course} with the given id
     * 
     * @return {@code True} if successful else {@code False}
     */
    public boolean updateName(int id, String name);

    /**
     * Find a list of {@code Course} by name
     * 
     * @return a list of {@code Course}
     */
    public List<Course> findByCredit(int credit);

    /**
     * Update the credit of the {@code Course} with the given id
     * 
     * @return {@code True} if successful else {@code False}
     */
    public boolean updateCredit(int id, int credit);

    /**
     * Update {@code Course}
     * 
     * @param name
     * @param credit
     * @param id
     * @return {@code True} if successful else {@code False}
     */
    public boolean update(String name, int credit, int id);

}