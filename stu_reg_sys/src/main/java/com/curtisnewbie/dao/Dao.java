package com.curtisnewbie.dao;

import java.util.List;

/**
 * ------------------------------------
 * 
 * Author: Yongjie Zhuang
 * 
 * ------------------------------------
 * <p>
 * Abstract DAO that contains common methods shared by all kinds of DAO.
 * </p>
 */
public interface Dao<T> {

    /** id can be generated if id is set to GENERATED_ID */
    public static final int GENERATED_ID = -1;

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
     */
    public boolean create(T obj);

}