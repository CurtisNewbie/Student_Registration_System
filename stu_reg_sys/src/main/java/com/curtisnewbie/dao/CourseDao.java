package com.curtisnewbie.dao;

import java.util.List;

import com.curtisnewbie.model.Course;
import com.curtisnewbie.model.Module;

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
     * Add a {@code Module} to the {@code Course}
     * 
     * @param courseId id of {@code Course}
     * @param moduleId id of {@code Module}
     * @return {@code True} if successful else {@code False}
     */
    public boolean addModule(int courseId, int moduleId);

    /**
     * remove a {@code Module} from a {@code Course}
     * 
     * @param courseId id of {@code Course}
     * @param moduleId id of {@code Module}
     * @return {@code True} if successful else {@code False}
     */
    public boolean removeModule(int courseId, int moduleId);

    /**
     * get all {@code Module}(s) from a {@code Course}
     * 
     * @param courseId id of {@code Course}
     * @param moduleId id of {@code Module}
     * @return A list of {@code Module}(s) belong to this {@code Course}
     */
    public List<Module> getAllModules(int courseId);
}