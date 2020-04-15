package com.curtisnewbie.model;

/**
 * ------------------------------------
 * 
 * Author: Yongjie Zhuang
 * 
 * ------------------------------------
 * <p>
 * Course representation
 * </p>
 */
public class Course {

    private int id;
    private String name;
    private int credit;
    /** Foreign key that references to a {@code School} */
    private int schoolFk;
    /** Foreign key that references to a {@code Lecturer} */
    private int lecturerFk;

    /**
     * 
     * @param id         set to {@link Dao#GENERATED_ID} if it needs to be
     *                   auto-generated
     * @param name
     * @param credit
     * @param schoolId   id of {@link com.curtisnewbie.model.School}
     * @param lecturerId id of {@link com.curtisnewbie.model.Lecturer}
     */
    public Course(int id, String name, int credit, int schoolId, int lecturerId) {
        this.id = id;
        this.name = name;
        this.credit = credit;
        this.schoolFk = schoolId;
        this.lecturerFk = lecturerId;
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

    /**
     * @return the schoolFk
     */
    public int getSchoolFk() {
        return schoolFk;
    }

    /**
     * @param schoolFk the schoolFk to set
     */
    public void setSchoolFk(int schoolFk) {
        this.schoolFk = schoolFk;
    }

    /**
     * @return the lecturerFk
     */
    public int getLecturerFk() {
        return lecturerFk;
    }

    /**
     * @param lecturerFk the lecturerFk to set
     */
    public void setLecturerFk(int lecturerFk) {
        this.lecturerFk = lecturerFk;
    }

    @Override
    public String toString() {
        return String.format("Id: %d, Name: %s, Credit: %d", getId(), getName(), getCredit());
    }

}