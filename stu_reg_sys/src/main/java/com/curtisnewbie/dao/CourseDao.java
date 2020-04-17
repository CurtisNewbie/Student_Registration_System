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
     * Find a {@code List} of {@code Course} by name
     * 
     * @return {@code Course}
     */
    public List<Course> findByName(String name);

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
     * @param id
     * @param name
     * @param credit
     * @param schoolId   set to {@code UnitDao.NULL_INT} if the {@code schoolId} is
     *                   to be set to NULL in DBMS
     * @param lecturerId set to {@code UnitDao.NULL_INT} if the {@code lecturerId}
     *                   is to be set to NULL in DBMS
     * @return {@code True} if successful else {@code False}
     */
    public boolean update(int id, String name, int credit, int schoolId, int lecturerId);

    /**
     * Update foreign key to a {@code School}
     * 
     * @param id
     * @param schoolId set to {@code UnitDao.NULL_INT} if the {@code schoolId} is to
     *                 be set to NULL in DBMS
     * @return {@code True} if successful else {@code False}
     */
    public boolean updateSchoolFk(int id, int schoolId);

    /**
     * Update foreign key to a {@code Lecturer}
     * 
     * @param id
     * @param lecturerId set to {@code UnitDao.NULL_INT} if the {@code lecturerId}
     *                   is to be set to NULL in DBMS
     * @return {@code True} if successful else {@code False}
     */
    public boolean updateLecturerFk(int id, int lecturerId);

}