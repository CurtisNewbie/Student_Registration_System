package com.curtisnewbie.dao;

import java.util.Date;
import java.util.List;

import com.curtisnewbie.model.Student;

/**
 * ------------------------------------
 * 
 * Author: Yongjie Zhuang
 * 
 * ------------------------------------
 * <p>
 * Dao for Student resources
 * </p>
 */
public interface StudentDao {

    /**
     * get a list of {@code Student}
     * 
     * @return a list of {@code Student}
     */
    public List<Student> getAllStudents();

    /**
     * delete a {@code Student} by id
     * 
     * @param id id
     * @return {@code True} if successful else {@code False}
     */
    public boolean deleteStudentById(int id);

    /**
     * find a {@code Student} by id
     * 
     * @param id
     * @return the {@code Student} found or {@code NULL} if not found
     */
    public Student findStudentById(int id);

    /**
     * Update a {@code Student}
     * 
     * @param student student
     * @return {@code True} if successful else {@code False}
     */
    public boolean updateStudent(Student student);

    /**
     * Update firstname of a {@code Student}
     * 
     * @param id    id
     * @param fname firstname
     * @return {@code True} if successful else {@code False}
     */
    public boolean updateFirstname(int id, String fname);

    /**
     * Update lastname of a {@code Student}
     * 
     * @param id    id
     * @param lname lastname
     * @return {@code True} if successful else {@code False}
     */
    public boolean updateLastname(int id, String lname);

    /**
     * Update date of registration of a {@code Student}
     * 
     * @param id   id
     * @param date date of registration
     * @return {@code True} if successful else {@code False}
     */
    public boolean updateDateOfReg(int id, Date date);

    /**
     * Create a {@code Student}
     * 
     * @param student
     * @return {@code True} if successful else {@code False}
     */
    public boolean createStudent(Student student);

}