package com.curtisnewbie.model;

/**
 * ------------------------------------
 * 
 * Author: Yongjie Zhuang
 * 
 * ------------------------------------
 * <p>
 * Faculty Representation
 * </p>
 */
public class Faculty {

    private int id;
    private String name;

    /**
     * 
     * @param id   set to {@link Dao#GENERATED_ID} if it needs to be auto-generated
     * @param name
     */
    public Faculty(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}