package com.curtisnewbie.dao;

import java.util.List;

import com.curtisnewbie.model.Module;

/**
 * ------------------------------------
 * 
 * Author: Yongjie Zhuang
 * 
 * ------------------------------------
 * <p>
 * Dao for {@link com.curtisnewbie.model.Module} resources
 * </p>
 */
public interface ModuleDao extends UnitDao<Module> {

    /**
     * Find {@link Module} by name
     * 
     * @return {@link Module}
     */
    public Module findByName(String name);

    /**
     * update name of the {@link Module} with the given id
     * 
     * @return {@code True} if successful else {@code False}
     */
    public boolean updateName(int id, String name);

    /**
     * Find a list of {@link Module} by name
     * 
     * @return a list of {@link Module}
     */
    public List<Module> findByCredit(int credit);

    /**
     * Update the credit of the {@link Module} with the given id
     * 
     * @return {@code True} if successful else {@code False}
     */
    public boolean updateCredit(int id, int credit);

    /**
     * Update {@code Module}
     * 
     * @param name
     * @param credit
     * @param id
     * @return {@code True} if successful else {@code False}
     */
    public boolean update(String name, int credit, int id);
}