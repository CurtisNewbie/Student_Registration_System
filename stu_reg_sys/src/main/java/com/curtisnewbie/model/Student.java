package com.curtisnewbie.model;

import java.util.Date;

public class Student {

    private int reg_id;
    private String firstname;
    private String lastname;
    private Date dateOfRegi;

    public Student(int id, String fname, String lname, Date dateOfRegi) {
        this.reg_id = id;
        this.firstname = fname;
        this.lastname = lname;
        this.dateOfRegi = dateOfRegi;
    }

    /**
     * @return the reg_id
     */
    public int getReg_id() {
        return reg_id;
    }

    /**
     * @param reg_id the reg_id to set
     */
    public void setReg_id(int reg_id) {
        this.reg_id = reg_id;
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
        return String.format("Registration ID: %d, firstname: %s, lastname: %s, registration_date: %s", getReg_id(),
                getFirstname(), getLastname(), getDateOfRegi().toString());
    }
}