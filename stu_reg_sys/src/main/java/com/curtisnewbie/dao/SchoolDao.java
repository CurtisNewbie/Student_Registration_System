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
public interface SchoolDao extends UnitDao<School> {

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

    /**
     * Update the faculty foreign key of the {@code School}
     * 
     * @param id
     * @param facultyId set to {@code UnitDao.NULL_INT} if the facultyId is to be
     *                  set to NULL in DBMS
     * @return {@code True} if successful else {@code False}
     */
    public boolean updateFacultyFk(int id, int facultyId);

    /**
     * Update {@code School}
     * 
     * @param id
     * @param name
     * @param facultyId
     * @return {@code True} if successful else {@code False}
     */
    public boolean update(int id, String name, int facultyId);
}