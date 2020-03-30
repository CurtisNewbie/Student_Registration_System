package com.curtisnewbie.dao;

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
     * Create a {@code Student}
     * 
     * @param student
     * @return {@code True} if successful else {@code False}
     */
    public void createStudent(Student student);

}