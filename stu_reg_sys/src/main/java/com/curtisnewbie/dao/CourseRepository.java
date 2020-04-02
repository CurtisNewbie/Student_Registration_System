package com.curtisnewbie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    // should be changed once all setup
    private final String CREATE_COURSE_WITH_ID = "INSERT INTO course VALUES (?,?,?, NULL, NULL)";
    private final String CREATE_COURSE_WITHOUT_ID = "INSERT INTO course (name, credit, sch_fk, lec_fk) VALUES (?,?,NULL, NULL)";

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
                list.add(new Course(set.getInt(1), set.getString(2), set.getInt(3)));
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
                cour = new Course(set.getInt(1), set.getString(2), set.getInt(3));
            return cour;
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return cour;
    }

    @Override
    public boolean update(Course cour) {
        logger.info(String.format("Update course to: '%s'", cour.toString()));
        try {
            conn.setAutoCommit(false);
            if (findById(cour.getId()) != null) {
                boolean succeeded = true;
                if (!updateName(cour.getId(), cour.getName()))
                    succeeded = false;

                if (succeeded && !updateCredit(cour.getId(), cour.getCredit()))
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
    public boolean create(Course cour) {
        logger.info("Create course: '" + cour.toString() + "'");
        boolean withId = true;
        if (cour.getId() == Dao.GENERATED_ID)
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
                cour = new Course(set.getInt(1), set.getString(2), set.getInt(3));
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
                list.add(new Course(set.getInt(1), set.getString(2), set.getInt(3)));
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