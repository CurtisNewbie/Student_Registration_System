package com.curtisnewbie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.curtisnewbie.model.Student;
import com.curtisnewbie.util.LoggerProducer;
import com.curtisnewbie.util.LoggerWrapper;

/**
 * ------------------------------------
 * 
 * Author: Yongjie Zhuang
 * 
 * ------------------------------------
 * <p>
 * Implementation of {@link com.curtisnewbie.dao.StudentDao} for
 * {@link com.curtisnewbie.model.Student} table.
 * </p>
 */
public class StudentRepository implements StudentDao {

    private final String SELECT_ALL = "SELECT * FROM student";
    private final String SELECT_BY_FNAME = "SELECT * FROM student WHERE firstname LIKE ?";
    private final String SELECT_BY_LNAME = "SELECT * FROM student WHERE lastname LIKE ?";
    private final String SELECT_BY_REG_DATE = "SELECT * FROM student WHERE reg_date = ?";
    private final String DELETE_BY_ID = "DELETE FROM student WHERE id = ?";
    private final String SELECT_BY_ID = "SELECT * FROM student WHERE id = ?";
    private final String UPDATE_FIRSTNAME = "UPDATE student SET firstname = ? WHERE id = ?";
    private final String UPDATE_LASTNAME = "UPDATE student SET lastname = ? WHERE id = ?";
    private final String UPDATE_REG_DATE = "UPDATE student SET reg_date = ? WHERE id = ?";
    private final String CREATE_STUDENT_WITH_ID = "INSERT INTO student VALUES(?, ?, ?, ?, ?)";
    private final String CREATE_STUDENT_WITHOUT_ID = "INSERT INTO student (firstname, lastname, reg_date, cou_fk) VALUES(?, ?, ?, ?)";
    private final String UPDATE_STUDENT = "UPDATE student SET firstname = ?, lastname = ?, reg_date = ?, cou_fk = ? WHERE id = ?";

    private final Connection conn = DBManager.getDBManager().getConnection();
    private final LoggerWrapper logger = LoggerProducer.getLogger(this);

    @Override
    public List<Student> getAll() {
        logger.info("Get all students");
        List<Student> list = new ArrayList<>();
        try {
            var stmt = conn.createStatement();
            ResultSet set = stmt.executeQuery(SELECT_ALL);
            while (set.next()) {
                var stu = new Student(set.getInt(1), set.getString(2), set.getString(3),
                        LocalDate.parse(set.getString(4)), set.getInt(5));
                list.add(stu);
            }
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return list;
    }

    @Override
    public boolean deleteById(int id) {
        logger.info(String.format("Delete id: '%d'", id));
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
    public Student findById(int id) {
        logger.info(String.format("Find id: '%d'", id));
        try {
            var stmt = conn.prepareStatement(SELECT_BY_ID);
            stmt.setInt(1, id);
            ResultSet set = stmt.executeQuery();
            Student stu = null;
            if (set.next()) {
                stu = new Student(set.getInt(1), set.getString(2), set.getString(3), LocalDate.parse(set.getString(4)),
                        set.getInt(5));
            }
            return stu;
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean update(Student stu) {
        if (stu == null) {
            logger.severe("The student to be updated is null!");
            return false;
        }
        return update(stu.getFirstname(), stu.getLastname(), stu.getDateOfRegi(), stu.getId(), stu.getCourseFk());
    }

    @Override
    public boolean update(String firstname, String lastname, LocalDate date, int id, int courseId) {
        logger.info(String.format("Update student to: 'id: %d, firstname: %s, lastname: %s, date: %s, courseFk: %d'",
                id, firstname, lastname, date.toString(), courseId));
        try {
            PreparedStatement stmt = conn.prepareStatement(UPDATE_STUDENT);
            stmt.setString(1, firstname);
            stmt.setString(2, lastname);
            stmt.setString(3, date.toString());
            if (courseId != UnitDao.NULL_INT)
                stmt.setInt(4, courseId);
            else
                stmt.setNull(4, Types.INTEGER);
            stmt.setInt(5, id);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean create(Student stu) {
        logger.info("Create student: '" + stu.toString() + "'");
        boolean withId = true;
        if (stu.getId() == UnitDao.GENERATED_ID)
            withId = false;

        try {
            PreparedStatement stmt;
            int i = 1;
            if (withId) {
                stmt = conn.prepareStatement(CREATE_STUDENT_WITH_ID);
                stmt.setInt(i++, stu.getId());
            } else {
                stmt = conn.prepareStatement(CREATE_STUDENT_WITHOUT_ID);
            }
            stmt.setString(i++, stu.getFirstname());
            stmt.setString(i++, stu.getLastname());
            stmt.setString(i++, stu.getDateOfRegi().toString());
            if (stu.getCourseFk() != UnitDao.NULL_INT)
                stmt.setInt(i++, stu.getCourseFk());
            else
                stmt.setNull(i++, Types.INTEGER);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateFirstname(int id, String fname) {
        logger.info(String.format("Update id: '%d', firstname updated to '%s'", id, fname));
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
        logger.info(String.format("Update id: '%d', lastname updated to '%s'", id, lname));
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
    public boolean updateDateOfReg(int id, LocalDate date) {
        logger.info(String.format("Update id: '%d', date updated to: '%s'", id, date.toString()));
        try {
            PreparedStatement stmt = conn.prepareStatement(UPDATE_REG_DATE);
            stmt.setDate(1, java.sql.Date.valueOf(date));
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public List<Student> findStusByFirstname(String fname) {
        logger.info(String.format("Find firstname: '%s'", fname));
        List<Student> list = new ArrayList<>();
        try {
            var stmt = conn.prepareStatement(SELECT_BY_FNAME);
            stmt.setString(1, "%" + fname + "%");
            ResultSet set = stmt.executeQuery();
            while (set.next()) {
                var stu = new Student(set.getInt(1), set.getString(2), set.getString(3),
                        LocalDate.parse(set.getString(4)), set.getInt(5));
                list.add(stu);
            }
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return list;
    }

    @Override
    public List<Student> findStusByLastname(String lname) {
        logger.info(String.format("Find lastname: '%s'", lname));
        List<Student> list = new ArrayList<>();
        try {
            var stmt = conn.prepareStatement(SELECT_BY_LNAME);
            stmt.setString(1, "%" + lname + "%");
            ResultSet set = stmt.executeQuery();
            while (set.next()) {
                var stu = new Student(set.getInt(1), set.getString(2), set.getString(3),
                        LocalDate.parse(set.getString(4)), set.getInt(5));
                list.add(stu);
            }
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return list;
    }

    @Override
    public List<Student> findStusByDateOfReg(LocalDate date) {
        logger.info(String.format("Find date of registration: '%s'", date.toString()));
        List<Student> list = new ArrayList<>();
        try {
            var stmt = conn.prepareStatement(SELECT_BY_REG_DATE);
            stmt.setString(1, date.toString());
            ResultSet set = stmt.executeQuery();
            while (set.next()) {
                var stu = new Student(set.getInt(1), set.getString(2), set.getString(3),
                        LocalDate.parse(set.getString(4)), set.getInt(5));
                list.add(stu);
            }
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return list;
    }

}