package com.curtisnewbie.model;

/**
 * ------------------------------------
 * 
 * Author: Yongjie Zhuang
 * 
 * ------------------------------------
 * <p>
 * School representation
 * </p>
 */
public class School {

    private int id;
    private String name;
    /** Foreign key that references to a {@code Faculty} */
    private int facultyFk;

    /**
     * 
     * @param id        set to {@link Dao#GENERATED_ID} if it needs to be
     *                  auto-generated
     * @param name
     * @param facultyId id of {@code Faculty}
     */
    public School(int id, String name, int facultyId) {
        this.id = id;
        this.name = name;
        this.facultyFk = facultyId;
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
     * @return the facultyFk
     */
    public int getFacultyFk() {
        return facultyFk;
    }

    /**
     * @param facultyFk the facultyFk to set
     */
    public void setFacultyFk(int facultyFk) {
        this.facultyFk = facultyFk;
    }

    @Override
    public String toString() {
        return String.format("School - {id: %d, name: %s, faculty_fk: %d}", getId(), getName(), getFacultyFk());
    }
}