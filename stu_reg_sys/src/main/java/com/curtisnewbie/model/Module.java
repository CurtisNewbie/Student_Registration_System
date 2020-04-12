package com.curtisnewbie.model;

/**
 * ------------------------------------
 * 
 * Author: Yongjie Zhuang
 * 
 * ------------------------------------
 * <p>
 * Module representation
 * </p>
 */
public class Module {

    private int id;
    private String name;
    private int credit;

    /**
     * 
     * @param id     set to {@link Dao#GENERATED_ID} if it needs to be
     *               auto-generated
     * @param name
     * @param credit
     */
    public Module(int id, String name, int credit) {
        this.id = id;
        this.name = name;
        this.credit = credit;
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

    /**
     * @return the credit
     */
    public int getCredit() {
        return credit;
    }

    /**
     * @param credit the credit to set
     */
    public void setCredit(int credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return String.format("Module - Id: %d, Name: %s, Credit:%d", getId(), getName(), getCredit());
    }

}