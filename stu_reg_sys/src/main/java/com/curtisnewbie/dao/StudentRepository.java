package com.curtisnewbie.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
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
    private final String DELETE_BY_ID = "DELETE FROM student WHERE id = ?";
    private final String SELECT_BY_ID = "SELECT * FROM student WHERE id = ?";

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
            logger.severe(e.getMessage());
        }
        return list;
    }

    @Override
    public boolean deleteStudentById(int id) {
        logger.info(String.format("deleteStudentById: %d", id));
        try {
            var stmt = conn.prepareStatement(DELETE_BY_ID);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return false;
        }
    }

    @Override
    public Student findStudentById(int id) {
        logger.info(String.format("findStudentById: %d", id));
        try {
            var stmt = conn.prepareStatement(SELECT_BY_ID);
            stmt.setInt(1, id);
            ResultSet set = stmt.executeQuery();
            Student stu = null;
            if (set.next()) {
                stu = new Student(set.getInt(1), set.getString(2), set.getString(3), set.getDate(4));
            }
            return stu;
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
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