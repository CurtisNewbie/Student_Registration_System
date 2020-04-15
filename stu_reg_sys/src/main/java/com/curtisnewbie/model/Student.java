package com.curtisnewbie.model;

import java.time.LocalDate;

import com.curtisnewbie.dao.UnitDao;

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
    private LocalDate dateOfRegi;
    /** Foreign key that references to a {@code Course} */
    private int courseFk;

    /**
     * 
     * @param id         id, set to {@link UnitDao#GENERATED_ID} if it needs to be
     *                   auto-generated.
     * @param fname      firstname
     * @param lname      lastname
     * @param dateOfRegi date of registration
     * @param courseId   id of a {@code Course}
     */
    public Student(int id, String fname, String lname, LocalDate dateOfRegi, int courseId) {
        this.id = id;
        this.firstname = fname;
        this.lastname = lname;
        this.dateOfRegi = dateOfRegi;
        this.courseFk = courseId;
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
    public LocalDate getDateOfRegi() {
        return dateOfRegi;
    }

    /**
     * @param dateOfRegi the dateOfRegi to set
     */
    public void setDateOfRegi(LocalDate dateOfRegi) {
        this.dateOfRegi = dateOfRegi;
    }

    /**
     * @return the courseFk
     */
    public int getCourseFk() {
        return courseFk;
    }

    /**
     * @param courseFk the courseFk to set
     */
    public void setCourseFk(int courseFk) {
        this.courseFk = courseFk;
    }

    @Override
    public String toString() {
        return String.format("Id: %d, Name: %s %s, Registration Date: %s", getId(), getFirstname(), getLastname(),
                getDateOfRegi().toString());
    }

}