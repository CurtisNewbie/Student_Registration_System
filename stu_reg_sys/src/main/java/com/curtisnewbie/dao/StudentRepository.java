package com.curtisnewbie.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.curtisnewbie.model.Student;
import com.curtisnewbie.util.LoggerProducer;

/**
 * ------------------------------------
 * 
 * Author: Yongjie Zhuang
 * 
 * ------------------------------------
 * <p>
 * Implementation of StudentDAO for student table.
 * </p>
 */
public class StudentRepository implements StudentDao {

    private final String SELECT_ALL = "SELECT * FROM student";

    private final Connection conn = new DBManager().getConnection();
    private final Logger logger = LoggerProducer.getLogger(this);

    @Override
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        try {
            var stmt = conn.createStatement();
            ResultSet set = stmt.executeQuery(SELECT_ALL);
            while (set.next()) {
                var stu = new Student(set.getInt(1), set.getString(2), set.getString(3), set.getDate(4));
                list.add(stu);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return list;
    }

    @Override
    public boolean deleteStudentById(int id) {
        return false;
    }

    @Override
    public Student findStudentById(int id) {
        return null;
    }

    @Override
    public boolean updateStudent(Student student) {
        return false;
    }

    @Override
    public void createStudent(Student student) {

    }
}