package com.curtisnewbie.dao;

import java.util.List;

/**
 * ------------------------------------
 * 
 * Author: Yongjie Zhuang
 * 
 * ------------------------------------
 * <p>
 * Abstract DAO that contains common methods shared by all kinds of DAOs that
 * serve a specific model.
 * </p>
 * <p>
 * These include:
 * <ul>
 * <li>{@link com.curtisnewbie.dao.CourseDao}</li>
 * <li>{@link com.curtisnewbie.dao.FacultyDao}</li>
 * <li>{@link com.curtisnewbie.dao.LecturerDao}</li>
 * <li>{@link com.curtisnewbie.dao.ModuleDao}</li>
 * <li>{@link com.curtisnewbie.dao.SchoolDao}</li>
 * <li>{@link com.curtisnewbie.dao.StudentDao}</li>
 * </ul>
 * These DAOs are for handling a specific model. For the one that serves the
 * whole system:
 * 
 * @see {@link com.curtisnewbie.dao.CommonDao}
 *      </p>
 */
public interface UnitDao<T> {

    /** id can be generated if id is set to GENERATED_ID */
    public static final int GENERATED_ID = -1;

    /** Indicating that the associated int value should be null */
    public static final int NULL_INT = -2;

    /**
     * get a list of instance
     * 
     * @return a list of instances found
     */
    public List<T> getAll();

    /**
     * delete an instance by id
     * 
     * @param id id
     * @return {@code True} if successful else {@code False}
     */
    public boolean deleteById(int id);

    /**
     * find an instance by id
     * 
     * @param id
     * @return the instance found or {@code NULL} if not found
     */
    public T findById(int id);

    /**
     * Update an instance
     * 
     * @param obj obj
     * @return {@code True} if successful else {@code False}
     */
    public boolean update(T obj);

    /**
     * Create an instance
     * 
     * @param obj
     * @return {@code True} if successful else {@code False}
     * @see {@linkplain UnitDao#GENERATED_ID} for id to be auto-generated
     * @see {@linkplain UnitDao#NULL_INT} for foreign key to be set to null
     */
    public boolean create(T obj);

}