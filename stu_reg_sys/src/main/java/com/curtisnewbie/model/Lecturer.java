package com.curtisnewbie.model;

/**
 * ------------------------------------
 * 
 * Author: Yongjie Zhuang
 * 
 * ------------------------------------
 * <p>
 * Lecturer representation
 * </p>
 */
public class Lecturer {

    private int id;
    private String firstname;
    private String lastname;
    private String position;

    /**
     * 
     * @param id       id, set to {@link Dao#GENERATED_ID} if it needs to be
     *                 auto-generated.
     * @param fname    firstname
     * @param lname    lastname
     * @param position position
     */
    public Lecturer(int id, String fname, String lname, String position) {
        this.id = id;
        this.firstname = fname;
        this.lastname = lname;
        this.position = position;
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
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return String.format("Id: %d, Name: %s %s, Position: %s", getId(), getFirstname(), getLastname(),
                getPosition());
    }
}