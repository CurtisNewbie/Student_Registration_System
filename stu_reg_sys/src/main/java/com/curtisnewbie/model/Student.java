package com.curtisnewbie.model;

import java.util.Date;

import com.curtisnewbie.dao.Dao;

/**
 * ------------------------------------
 * 
 * Author: Yongjie Zhuang
 * 
 * ------------------------------------
 * <p>
 * Student representation
 * </p>
 */
public class Student {

    private int id;
    private String firstname;
    private String lastname;
    private Date dateOfRegi;

    /**
     * 
     * @param id         id, set to {@link Dao#GENERATED_ID} if it needs to be
     *                   auto-generated.
     * @param fname      firstname
     * @param lname      lastname
     * @param dateOfRegi date of registration
     */
    public Student(int id, String fname, String lname, Date dateOfRegi) {
        this.id = id;
        this.firstname = fname;
        this.lastname = lname;
        this.dateOfRegi = dateOfRegi;
    }

    /**
     * @return the registration id
     */
    public int getId() {
        return id;
    }

    /**
     * @param reg_id the registration id to set
     */
    public void setId(int reg_id) {
        this.id = reg_id;
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
     * @return the dateOfRegi
     */
    public Date getDateOfRegi() {
        return dateOfRegi;
    }

    /**
     * @param dateOfRegi the dateOfRegi to set
     */
    public void setDateOfRegi(Date dateOfRegi) {
        this.dateOfRegi = dateOfRegi;
    }

    @Override
    public String toString() {
        return String.format("Registration ID: %d, firstname: %s, lastname: %s, registration_date: %s", getId(),
                getFirstname(), getLastname(), getDateOfRegi().toString());
    }
}