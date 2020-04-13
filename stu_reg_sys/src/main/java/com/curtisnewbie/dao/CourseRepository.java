package com.curtisnewbie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.curtisnewbie.model.Course;
import com.curtisnewbie.util.LoggerProducer;
import com.curtisnewbie.util.LoggerWrapper;

/**
 * ------------------------------------
 * 
 * Author: Yongjie Zhuang
 * 
 * ------------------------------------
 * <p>
 * Implementation of {@link com.curtisnewbie.dao.CourseDao} for
 * {@link com.curtisnewbie.model.Course} resources
 * </p>
 */
public class CourseRepository implements CourseDao {

    private final String SELECT_ALL = "SELECT * FROM course";
    private final String SELECT_BY_ID = "SELECT * FROM course WHERE id = ?";
    private final String SELECT_BY_NAME = "SELECT * FROM course WHERE name = ?";
    private final String SELECT_BY_CREDIT = "SELECT * FROM course WHERE credit = ?";
    private final String DELETE_BY_ID = "DELETE FROM course WHERE id = ?";
    private final String UPDATE_NAME = "UPDATE course SET name = ? WHERE id = ?";
    private final String UPDATE_CREDIT = "UPDATE course SET credit = ? WHERE id = ?";
    private final String CREATE_COURSE_WITH_ID = "INSERT INTO course VALUES (?,?,?,?,?)";
    private final String CREATE_COURSE_WITHOUT_ID = "INSERT INTO course (name, credit, sch_fk, lec_fk) VALUES (?,?,?,?)";
    private final String UPDATE_COURSE = "UPDATE course SET name = ?, credit = ?, sch_fk = ?, lec_fk = ? WHERE id = ?";

    private final Connection conn = DBManager.getDBManager().getConnection();
    private final LoggerWrapper logger = LoggerProducer.getLogger(this);

    @Override
    public List<Course> getAll() {
        logger.info("Get all courses");
        List<Course> list = new ArrayList<>();
        try {
            var stmt = conn.createStatement();
            var set = stmt.executeQuery(SELECT_ALL);
            while (set.next())
                list.add(new Course(set.getInt(1), set.getString(2), set.getInt(3), set.getInt(4), set.getInt(5)));
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
        }
        return false;
    }

    @Override
    public Course findById(int id) {
        logger.info(String.format("Find id: '%d'", id));
        Course cour = null;
        try {
            var stmt = conn.prepareStatement(SELECT_BY_ID);
            stmt.setInt(1, id);
            var set = stmt.executeQuery();
            if (set.next())
                cour = new Course(set.getInt(1), set.getString(2), set.getInt(3), set.getInt(4), set.getInt(5));
            return cour;
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return cour;
    }

    @Override
    public boolean update(Course cour) {
        if (cour == null) {
            logger.severe("The course to be updated is null!");
            return false;
        }
        return update(cour.getId(), cour.getName(), cour.getCredit(), cour.getSchoolFk(), cour.getLecturerFk());
    }

    @Override
    public boolean update(int id, String name, int credit, int schoolId, int lecturerId) {
        logger.info(String.format("Update course to: '{id: %d, name: %s, credit: %d, school_fk: %d, lecturer_fk: %d}'",
                id, name, credit, schoolId, lecturerId));
        try {
            PreparedStatement stmt = conn.prepareStatement(UPDATE_COURSE);
            stmt.setString(1, name);
            stmt.setInt(2, credit);
            if (schoolId != NULL_INT)
                stmt.setInt(3, schoolId);
            else
                stmt.setNull(3, Types.INTEGER);
            if (lecturerId != NULL_INT)
                stmt.setInt(4, lecturerId);
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
    public boolean create(Course cour) {
        logger.info("Create course: '" + cour.toString() + "'");
        boolean withId = true;
        if (cour.getId() == UnitDao.GENERATED_ID)
            withId = false;

        try {
            PreparedStatement stmt;
            int i = 1;
            if (withId) {
                stmt = conn.prepareStatement(CREATE_COURSE_WITH_ID);
                stmt.setInt(i++, cour.getId());
            } else {
                stmt = conn.prepareStatement(CREATE_COURSE_WITHOUT_ID);
            }
            stmt.setString(i++, cour.getName());
            stmt.setInt(i++, cour.getCredit());
            stmt.setInt(i++, cour.getSchoolFk());
            stmt.setInt(i++, cour.getLecturerFk());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public Course findByName(String name) {
        logger.info(String.format("Find name: '%s'", name));
        Course cour = null;
        try {
            var stmt = conn.prepareStatement(SELECT_BY_NAME);
            stmt.setString(1, name);
            var set = stmt.executeQuery();
            if (set.next()) {
                cour = new Course(set.getInt(1), set.getString(2), set.getInt(3), set.getInt(4), set.getInt(5));
            }
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return cour;
    }

    @Override
    public boolean updateName(int id, String name) {
        logger.info(String.format("Update id: '%d', name updated to '%s'", id, name));
        try {
            var stmt = conn.prepareStatement(UPDATE_NAME);
            stmt.setString(1, name);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public List<Course> findByCredit(int credit) {
        logger.info(String.format("Find credit: %d", credit));
        List<Course> list = new ArrayList<>();
        try {
            var stmt = conn.prepareStatement(SELECT_BY_CREDIT);
            stmt.setInt(1, credit);
            var set = stmt.executeQuery();
            while (set.next())
                list.add(new Course(set.getInt(1), set.getString(2), set.getInt(3), set.getInt(4), set.getInt(5)));
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return list;
    }

    @Override
    public boolean updateCredit(int id, int credit) {
        logger.info(String.format("Update id: '%d', credit updated to '%s'", id, credit));
        try {
            var stmt = conn.prepareStatement(UPDATE_CREDIT);
            stmt.setInt(1, credit);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return false;
    }
}