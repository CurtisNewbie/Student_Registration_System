package com.curtisnewbie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
    private final String UPDATE_FIRSTNAME = "UPDATE student SET firstname = ? WHERE id = ?";
    private final String UPDATE_LASTNAME = "UPDATE student SET lastname = ? WHERE id = ?";
    private final String UPDATE_REG_DATE = "UPDATE student SET reg_date = ? WHERE id = ?";

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
    public boolean updateStudent(Student stu) {
        logger.info(String.format("updateStudent: %d", stu.getId()));
        try {
            conn.setAutoCommit(false);
            Student prev;
            if ((prev = findStudentById(stu.getId())) != null) {
                boolean succeeded = true;
                if (!prev.getDateOfRegi().equals(stu.getDateOfRegi()))
                    if (!updateDateOfReg(stu.getId(), stu.getDateOfRegi()))
                        succeeded = false;

                if (succeeded && !prev.getFirstname().equals(stu.getFirstname()))
                    if (!updateFirstname(stu.getId(), stu.getFirstname()))
                        succeeded = false;

                if (succeeded && !prev.getLastname().equals(stu.getLastname()))
                    if (!updateLastname(stu.getId(), stu.getLastname()))
                        succeeded = false;

                if (succeeded) {
                    conn.commit();
                    return true;
                } else {
                    conn.rollback();
                }
            }
        } catch (Exception e) {
            logger.severe(e.getMessage());
            try {
                conn.rollback();
            } catch (SQLException e1) {
                logger.severe(e1.getMessage());
            }
        }
        return false;
    }

    @Override
    public void createStudent(Student student) {

    }

    @Override
    public boolean updateFirstname(int id, String fname) {
        logger.info(String.format("updateFirstname: %d", id));
        try {
            var stmt = conn.prepareStatement(UPDATE_FIRSTNAME);
            stmt.setString(1, fname);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateLastname(int id, String lname) {
        logger.info(String.format("updateLastname: %d", id));
        try {
            var stmt = conn.prepareStatement(UPDATE_LASTNAME);
            stmt.setString(1, lname);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateDateOfReg(int id, Date date) {
        logger.info(String.format("updateLastname: %d", id));
        try {
            PreparedStatement stmt = conn.prepareStatement(UPDATE_REG_DATE);
            stmt.setDate(1, new java.sql.Date(date.getTime()));
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

}